package io.rollhax.wheniworkchallenge.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rollhax.wheniworkchallenge.BuildConfig;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.adapter.StopsListAdapter;
import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.presentation.presenter.IStopsPresenter;
import io.rollhax.wheniworkchallenge.presentation.presenter.StopsPresenter;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public class StopsListActivity extends AppCompatActivity implements IStopsListView {

    private static final String TAG = StopsListActivity.class.getSimpleName();

    private static final String EXTRAS_ROUTE = BuildConfig.APPLICATION_ID + ".StopsListActivity.route";
    private static final String EXTRAS_STOPS = BuildConfig.APPLICATION_ID + ".StopsListActivity.stops";

    public static void startStopsListActivity(@NonNull Context context, @NonNull String route) {
        Intent intent = new Intent(context, StopsListActivity.class);
        intent.putExtra(EXTRAS_ROUTE, route);
        context.startActivity(intent);
    }

    private IStopsPresenter mStopsPresenter;
    private StopsListAdapter mStopsListAdapter;

    private String mRoute;
    private List<IStopViewModel> mStops;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    //region IListView
    @Override
    public void setListItems(List<IStopViewModel> stops) {
        mStops = stops;
    }

    @Override
    public void displayListItems(List<IStopViewModel> stops) {
        mStopsListAdapter.setStops(stops);
    }
    //endregion

    //region IBaseView
    @Override
    public void showProgress(boolean show) {
        mSwipeRefresh.setRefreshing(show);
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.stops_list_title));
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mSwipeRefresh.setOnRefreshListener(mSwipeRefreshListener);

        mStopsListAdapter = new StopsListAdapter(mStops, mStopClickListener);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mStopsListAdapter);

        Bundle args = getIntent().getExtras();
        if (savedInstanceState != null) {
            mRoute = savedInstanceState.getString(EXTRAS_ROUTE);
            mStops = savedInstanceState.getParcelableArrayList(EXTRAS_STOPS);
        } else if (args != null) {
            mRoute = args.getString(EXTRAS_ROUTE);
        }

        mStopsPresenter = new StopsPresenter();
        mStopsPresenter.onCreate(this);
        mStopsPresenter.setRoute(mRoute);

        if (mStops != null) {
            displayListItems(mStops);
        } else {
            mStopsPresenter.onRefresh();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_routes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EXTRAS_ROUTE, mRoute);
        outState.putParcelableArrayList(EXTRAS_STOPS, (ArrayList) mStops);
    }
    //endregion

    //region Listeners
    private final SwipeRefreshLayout.OnRefreshListener mSwipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mStopsPresenter.onSwipeToRefresh();
        }
    };

    private final IStopClickListener mStopClickListener = new IStopClickListener() {
        @Override
        public void onStopClicked(IStopViewModel stop) {
            Log.d(TAG, "onStopClicked: stop=" + stop.getStopId());
        }
    };
    //endregion
}
