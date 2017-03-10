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
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.nextripservice.service.INextripService;
import io.rollhax.wheniworkchallenge.view.activity.IDeparturesListView;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeparturesPresenterTest {

    @Mock
    IDeparturesListView mPresentation;
    @Mock
    INextripService mNextripService;

    DeparturesPresenter mPresenter;

    @Before
    public void setup() {
        mPresenter = new DeparturesPresenter();
        mPresenter.setPresentation(mPresentation);
        mPresenter.setNextripService(mNextripService);
        mPresenter.setRoute("route");
        mPresenter.setDirectionType(DirectionType.EAST);
        mPresenter.setStopId("stopId");

        List<Departure> departures = new ArrayList<>();
        departures.add(mock(Departure.class));
        when(mNextripService.getDepartures(anyString(), anyInt(), anyString())).thenReturn(Observable.just(departures));
    }

    @Test
    public void onCreate() throws Exception {
        mPresenter = new DeparturesPresenter();
        assertNull(mPresenter.getPresentation());

        mPresenter.onCreate(mPresentation, "route", DirectionType.WEST, "stopId");

        assertNotNull(mPresenter.getPresentation());
        assertEquals(mPresentation, mPresenter.getPresentation());
        verifyZeroInteractions(mPresentation, mNextripService);
    }

    @Test
    public void onRefresh() throws Exception {
        mPresenter.onRefresh();

        verify(mNextripService).getDepartures(anyString(), anyInt(), anyString());
        verify(mPresentation).showProgress(false);
        verify(mPresentation).setListItems(ArgumentMatchers.<IDepartureViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IDepartureViewModel>anyList());
        verifyNoMoreInteractions(mPresentation, mNextripService);
    }

    @Test
    public void onSwipeToRefresh() throws Exception {
        mPresenter.onRefresh();

        verify(mNextripService).getDepartures(anyString(), anyInt(), anyString());
        verify(mPresentation).showProgress(false);
        verify(mPresentation).setListItems(ArgumentMatchers.<IDepartureViewModel>anyList());
        verify(mPresentation).displayListItems(ArgumentMatchers.<IDepartureViewModel>anyList());
        verifyNoMoreInteractions(mPresentation, mNextripService);
    }

}