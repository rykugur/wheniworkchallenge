package io.rollhax.wheniworkchallenge.presentation.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rollhax.nextripdomain.GsonFactory;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.nextripservice.service.NextripRetroService;
import io.rollhax.utils.filters.FilterNull;
import io.rollhax.utils.transformers.FlattenCollection;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.util.mapper.RouteViewModelMap;
import io.rollhax.wheniworkchallenge.view.activity.IRoutesListView;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public class RoutesPresenter implements IRoutesPresenter {

    private static final String TAG = RoutesPresenter.class.getSimpleName();

    private IRoutesListView mPresentation;
    private INextripService mNextripService;

    //region IRoutesListView
    @Override
    public void onCreate(IRoutesListView presentation) {
        mPresentation = presentation;

        // TODO: this and Gson could be provided with dagger
        mNextripService = new NextripRetroService(GsonFactory.getDefault());
    }
    //endregion

    //region IRefreshable
    @Override
    public void onRefresh() {
        refreshRoutes();
    }

    @Override
    public void onSwipeToRefresh() {
        refreshRoutes();
    }
    //endregion

    //region Listeners/observers
    private final Observer<List<IRouteViewModel>> mRoutesObserver = new Observer<List<IRouteViewModel>>() {
        @Override
        public void onSubscribe(Disposable d) {
            // no-op
        }

        @Override
        public void onNext(List<IRouteViewModel> routes) {
            if (mPresentation == null) {
                return;
            }

            mPresentation.showProgress(false);
            mPresentation.setListItems(routes);
            mPresentation.displayListItems(routes);
        }

        @Override
        public void onError(Throwable e) {
            if (mPresentation == null) {
                return;
            }

            mPresentation.showError(R.string.routes_list_error_refresh);
        }

        @Override
        public void onComplete() {
            // no-op
        }
    };
    //endregion

    //region Private helpers
    // TODO: sorting
    private void refreshRoutes() {
        mNextripService.getRoutes()
                .compose(FlattenCollection.of(Route.class))
                .filter(FilterNull.of(Route.class))
                .map(new RouteViewModelMap())
                .toList()
                .toObservable()
                .subscribe(mRoutesObserver);
    }
    //endregion

    //region Testing
    /* package */ IRoutesListView getPresentation() {
        return mPresentation;
    }

    /* package */ void setPresentation(IRoutesListView presentation) {
        mPresentation = presentation;
    }

    /* package */ void setNextripService(INextripService nextripService) {
        mNextripService = nextripService;
    }
    //endregion
}
