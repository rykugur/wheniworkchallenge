package io.rollhax.wheniworkchallenge.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.wheniworkchallenge.view.activity.IRoutesListView;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoutesPresenterTest {

    @Mock
    IRoutesListView mPresentation;
    @Mock
    INextripService mNextripService;

    RoutesPresenter mPresenter;

    @Before
    public void setup() {
        mPresenter = new RoutesPresenter();
        mPresenter.setPresentation(mPresentation);
        mPresenter.setNextripService(mNextripService);

        List<Route> routes = new ArrayList<>();
        routes.add(mock(Route.class));
        when(mNextripService.getRoutes()).thenReturn(Observable.just(routes));
    }

    @Test
    public void onCreate() throws Exception {
        mPresenter = new RoutesPresenter();
        assertNull(mPresenter.getPresentation());

        mPresenter.onCreate(mPresentation);

        assertNotNull(mPresenter.getPresentation());
        assertEquals(mPresentation, mPresenter.getPresentation());
        verifyZeroInteractions(mPresentation, mNextripService);
    }

    @Test
    public void onRefresh() throws Exception {
        mPresenter.onRefresh();

        verify(mNextripService).getRoutes();
        verify(mPresentation).showProgress(anyBoolean());
        verify(mPresentation).setListItems(ArgumentMatchers.<IRouteViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IRouteViewModel>anyList());
        verifyNoMoreInteractions(mPresentation, mNextripService);
    }

    @Test
    public void onSwipeToRefresh() throws Exception {
        mPresenter.onSwipeToRefresh();

        verify(mNextripService).getRoutes();
        verify(mPresentation).showProgress(anyBoolean());
        verify(mPresentation).setListItems(ArgumentMatchers.<IRouteViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IRouteViewModel>anyList());
        verifyNoMoreInteractions(mPresentation, mNextripService);
    }

}