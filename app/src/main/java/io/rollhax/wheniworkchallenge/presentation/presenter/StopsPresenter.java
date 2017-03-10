package io.rollhax.wheniworkchallenge.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.rollhax.nextripdomain.GsonFactory;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.nextripservice.service.NextripRetroService;
import io.rollhax.utils.filters.FilterNull;
import io.rollhax.utils.transformers.FlattenCollection;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.util.mapper.StopViewModelMap;
import io.rollhax.wheniworkchallenge.view.IStopsListView;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public class StopsPresenter implements IStopsPresenter {

    private static final String TAG = StopsPresenter.class.getSimpleName();

    private IStopsListView mPresentation;
    private INextripService mNextripService;

    private String mRouteId;

    //region IStopPresenter
    @Override
    public void onCreate(IStopsListView presentation) {
        mPresentation = presentation;

        mNextripService = new NextripRetroService(GsonFactory.getDefault());
    }

    @Override
    public void setRoute(String route) {
        mRouteId = route;
    }
    //endregion

    //region IRefreshable
    @Override
    public void onRefresh() {
        refreshStops();
    }

    @Override
    public void onSwipeToRefresh() {
        refreshStops();
    }
    //endregion

    //region Observers
    private final Observer<List<IStopViewModel>> mStopsObserver =
            new Observer<List<IStopViewModel>>() {
        @Override
        public void onSubscribe(Disposable d) {
            // no-op
        }

        @Override
        public void onNext(List<IStopViewModel> stops) {
            if (mPresentation == null) {
                return;
            }

            mPresentation.showProgress(false);
            mPresentation.setListItems(stops);
            mPresentation.displayListItems(stops);
        }

        @Override
        public void onError(Throwable e) {
            if (mPresentation == null) {
                return;
            }

            mPresentation.showError(R.string.stops_list_error_refresh);
        }

        @Override
        public void onComplete() {
            // no-op
        }
    };
    //endregion

    //region Private helpers
    // TODO: refactor this chain to be less of a hack
    // TODO: sort
    private void refreshStops() {
        // get the valid directions for the given route
        mNextripService.getDirections(mRouteId)
                .compose(FlattenCollection.of(DirectionType.class))
                .filter(new Predicate<DirectionType>() {
                    @Override
                    public boolean test(DirectionType directionType) throws Exception {
                        return directionType != null && directionType != DirectionType.UNKNOWN;
                    }
                })
                .flatMap(new Function<DirectionType, ObservableSource<List<IStopViewModel>>>() {
                    @Override
                    public ObservableSource<List<IStopViewModel>> apply(DirectionType directionType) throws Exception {
                        return mNextripService.getStops(mRouteId, directionType.getServerId())
                                .compose(FlattenCollection.of(Stop.class))
                                .filter(FilterNull.of(Stop.class))
                                .map(new StopViewModelMap(directionType))
                                .toList()
                                .toObservable();
                    }
                })
                // gather all responses and combine into a single list
                .toList()
                .toObservable()
                .flatMap(new Function<List<List<IStopViewModel>>, ObservableSource<List<IStopViewModel>>>() {
                    @Override
                    public ObservableSource<List<IStopViewModel>> apply(List<List<IStopViewModel>> stopLists) throws Exception {
                        List<IStopViewModel> finalList = new ArrayList<>();
                        for (List<IStopViewModel> list : stopLists) {
                            finalList.addAll(list);
                        }
                        // use fromIterable so we don't need to flatten again
                        return Observable.just(finalList);
                    }
                })
                .subscribe(mStopsObserver);
    }
    //endregion

    //region Testing
    /* package */ IStopsListView getPresentation() {
        return mPresentation;
    }

    /* package */ void setPresentation(IStopsListView presentation) {
        mPresentation = presentation;
    }

    /* package */ void setNextripService(INextripService nextripService) {
        mNextripService = nextripService;
    }
    //endregion
}
