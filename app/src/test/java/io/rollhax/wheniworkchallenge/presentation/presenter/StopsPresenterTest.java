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
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.wheniworkchallenge.view.IStopsListView;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StopsPresenterTest {

    @Mock
    IStopsListView mPresentation;
    @Mock
    INextripService mNextripService;

    StopsPresenter mPresenter;

    @Before
    public void setup() {
        mPresenter = new StopsPresenter();
        mPresenter.setPresentation(mPresentation);
        mPresenter.setNextripService(mNextripService);

        List<DirectionType> directionTypes = new ArrayList<>();
        directionTypes.add(DirectionType.EAST);
        directionTypes.add(DirectionType.WEST);
        when(mNextripService.getDirections(anyString())).thenReturn(Observable.just(directionTypes));
        List<Stop> stops = new ArrayList<>();
        stops.add(mock(Stop.class));
        when(mNextripService.getStops(anyString(), anyInt())).thenReturn(Observable.just(stops));
    }

    @Test
    public void onCreate() throws Exception {
        mPresenter = new StopsPresenter();
        assertNull(mPresenter.getPresentation());

        mPresenter.onCreate(mPresentation);

        assertNotNull(mPresenter.getPresentation());
        assertEquals(mPresentation, mPresenter.getPresentation());
        verifyZeroInteractions(mPresentation, mNextripService);
    }

    @Test
    public void setRoute() throws Exception {
        mPresenter.setRoute("route");
        verifyZeroInteractions(mPresentation, mNextripService);
    }

    @Test
    public void onRefresh() throws Exception {
        mPresenter.setRoute("route");

        mPresenter.onRefresh();

        verify(mNextripService).getDirections(anyString());
        verify(mNextripService, times(2)).getStops(anyString(), anyInt());
        verify(mPresentation).showProgress(anyBoolean());
        verify(mPresentation).setListItems(ArgumentMatchers.<IStopViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IStopViewModel>anyList());
        verifyNoMoreInteractions(mNextripService, mPresentation);
    }

    @Test
    public void onSwipeToRefresh() throws Exception {
        mPresenter.setRoute("route");

        mPresenter.onSwipeToRefresh();

        verify(mNextripService).getDirections(anyString());
        verify(mNextripService, times(2)).getStops(anyString(), anyInt());
        verify(mPresentation).showProgress(anyBoolean());
        verify(mPresentation).setListItems(ArgumentMatchers.<IStopViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IStopViewModel>anyList());
        verifyNoMoreInteractions(mNextripService, mPresentation);
    }
}