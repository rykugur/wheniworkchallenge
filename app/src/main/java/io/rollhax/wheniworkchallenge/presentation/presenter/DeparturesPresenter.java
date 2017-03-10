package io.rollhax.wheniworkchallenge.presentation.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rollhax.nextripdomain.GsonFactory;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.nextripservice.service.NextripRetroService;
import io.rollhax.utils.transformers.FlattenCollection;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.util.mapper.DepartureViewModelMap;
import io.rollhax.wheniworkchallenge.view.activity.IDeparturesListView;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

public class DeparturesPresenter implements IDeparturesPresenter {

    private static final String TAG = DeparturesPresenter.class.getSimpleName();

    private IDeparturesListView mPresentation;
    private INextripService mNextripService;
    private String mRoute;
    private DirectionType mDirectionType;
    private String mStopId;

    //region IDeparturesPresenter
    @Override
    public void onCreate(IDeparturesListView presentation, String route, DirectionType directionType, String stopId) {
        mPresentation = presentation;
        mRoute = route;
        mDirectionType = directionType;
        mStopId = stopId;

        mNextripService = new NextripRetroService(GsonFactory.getDefault());
    }
    //endregion

    //region IRefreshable
    @Override
    public void onRefresh() {
        refreshDepartures();
    }

    @Override
    public void onSwipeToRefresh() {
        refreshDepartures();
    }
    //endregion

    //region Observers
    private final Observer<List<IDepartureViewModel>> mDeparturesObserver = new Observer<List<IDepartureViewModel>>() {
        @Override
        public void onSubscribe(Disposable d) {
            // no-op
        }

        @Override
        public void onNext(List<IDepartureViewModel> departures) {
            if (mPresentation == null) {
                return;
            }

            mPresentation.showProgress(false);
            mPresentation.setListItems(departures);
            mPresentation.displayListItems(departures);
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
    private void refreshDepartures() {
        mNextripService.getDepartures(mRoute, mDirectionType.getServerId(), mStopId)
                .compose(FlattenCollection.of(Departure.class))
                .map(new DepartureViewModelMap())
                .toList()
                .toObservable()
                .subscribe(mDeparturesObserver);
    }
    //endregion

    //region Testing
    /* package */ IDeparturesListView getPresentation() {
        return mPresentation;
    }

    /* package */ void setPresentation(IDeparturesListView presentation) {
        mPresentation = presentation;
    }

    /* package */ void setNextripService(INextripService nextripService) {
        mNextripService = nextripService;
    }

    /* package */ void setRoute(String route) {
        mRoute = route;
    }

    /* package */ void setDirectionType(DirectionType directionType) {
        mDirectionType = directionType;
    }

    /* package */ void setStopId(String stopId) {
        mStopId = stopId;
    }
    //endregion
}
