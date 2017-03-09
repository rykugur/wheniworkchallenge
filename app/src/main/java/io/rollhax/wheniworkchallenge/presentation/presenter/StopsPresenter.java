package io.rollhax.wheniworkchallenge.presentation.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.GsonFactory;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.nextripservice.service.NextripRetroService;
import io.rollhax.utils.filters.FilterNull;
import io.rollhax.utils.transformers.FlattenCollection;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.util.mapper.StopViewModelMap;
import io.rollhax.wheniworkchallenge.view.activity.IStopsListView;
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

        // TODO: this and Gson could be provided with dagger
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
    private final Observer<List<IStopViewModel>> mStopsObserver = new Observer<List<IStopViewModel>>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe: ");
        }

        @Override
        public void onNext(List<IStopViewModel> stops) {
            if (mPresentation == null) {
                return;
            }

            Log.d(TAG, "onNext: stops.size=" + stops.size());

            mPresentation.showProgress(false);
            mPresentation.setListItems(stops);
            mPresentation.displayListItems(stops);
        }

        @Override
        public void onError(Throwable e) {
            if (mPresentation == null) {
                return;
            }

            Log.d(TAG, "onError: e=" + e.getMessage());

            mPresentation.showError(R.string.stops_list_error_refresh);
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: ");
        }
    };
    //endregion

    //region Private helpers
    // This is a mess.
    private void refreshStops() {
        // get the valid directions for the given route
        mNextripService.getDirections(mRouteId)
                // flatten each direction we get back
                .compose(FlattenCollection.of(DirectionType.class))
                // and call getStops(Route, Direction)
                .flatMap(new Function<DirectionType, ObservableSource<List<Stop>>>() {
                    @Override
                    public ObservableSource<List<Stop>> apply(DirectionType directionType) throws Exception {
                        return mNextripService.getStops(mRouteId, String.valueOf(directionType.getServerId()));
                    }
                })
                // join all of the lists we get
                .toList()
                .toObservable()
                // combine the multiple lists into a single list
                .flatMap(new Function<List<List<Stop>>, ObservableSource<Stop>>() {
                    @Override
                    public ObservableSource<Stop> apply(List<List<Stop>> stopLists) throws Exception {
                        List<Stop> finalList = new ArrayList<>();
                        for (List<Stop> list : stopLists) {
                            finalList.addAll(list);
                        }
                        // use fromIterable so we don't need to flatten again
                        return Observable.fromIterable(finalList);
                    }
                })
                .filter(FilterNull.of(Stop.class))
                .map(new StopViewModelMap())
                .toList()
                .toObservable()
                .subscribe(mStopsObserver);
    }
    //endregion
}
