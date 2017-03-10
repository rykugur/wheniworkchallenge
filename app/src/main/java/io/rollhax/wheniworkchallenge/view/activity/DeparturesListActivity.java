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
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.wheniworkchallenge.BuildConfig;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.adapter.DeparturesListAdapter;
import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.presentation.presenter.DeparturesPresenter;
import io.rollhax.wheniworkchallenge.presentation.presenter.IDeparturesPresenter;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public class DeparturesListActivity extends AppCompatActivity implements IDeparturesListView {

    private static final String TAG = DeparturesListActivity.class.getSimpleName();

    private static final String EXTRAS_ROUTE = BuildConfig.APPLICATION_ID + ".DeparturesListActivity.route";
    private static final String EXTRAS_DIRECTION = BuildConfig.APPLICATION_ID + ".DeparturesListActivity.direction";
    private static final String EXTRAS_STOP_ID = BuildConfig.APPLICATION_ID + ".DeparturesListActivity.stopId";
    private static final String EXTRAS_DEPARTURES = BuildConfig.APPLICATION_ID + ".DeparturesListActivity.departures";

    public static void startDeparturesListActivity(@NonNull Context context, @NonNull String route,
                                                   @NonNull DirectionType directionType, @NonNull String stopId) {
        Intent intent = new Intent(context, DeparturesListActivity.class);
        intent.putExtra(EXTRAS_ROUTE, route);
        intent.putExtra(EXTRAS_DIRECTION, directionType.getServerId());
        intent.putExtra(EXTRAS_STOP_ID, stopId);
        context.startActivity(intent);
    }

    private IDeparturesPresenter mDeparturesPresenter;
    private DeparturesListAdapter mDepartuesListAdapter;

    private String mRoute;
    private DirectionType mDirectionType;
    private String mStopId;
    private List<IDepartureViewModel> mDepartures;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    //region IListView
    @Override
    public void setListItems(List<IDepartureViewModel> departures) {
        mDepartures = departures;
    }

    @Override
    public void displayListItems(List<IDepartureViewModel> departures) {
        mDepartuesListAdapter.setDepartures(departures);
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

        setContentView(R.layout.activity_departures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.departures_list_title));
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mSwipeRefresh.setOnRefreshListener(mSwipeRefreshListener);

        mDepartuesListAdapter = new DeparturesListAdapter(mDepartures);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mDepartuesListAdapter);

        Bundle args = getIntent().getExtras();
        if (savedInstanceState != null) {
            loadFromBundle(savedInstanceState);
        } else if (args != null) {
            loadFromBundle(args);
        }

        mDeparturesPresenter = new DeparturesPresenter();
        mDeparturesPresenter.onCreate(this, mRoute, mDirectionType, mStopId);

        if (mDepartures != null) {
            displayListItems(mDepartures);
        } else {
            mDeparturesPresenter.onRefresh();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
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
        saveToBundle(outState);

    }
    //endregion

    //region Listeners
    private final SwipeRefreshLayout.OnRefreshListener mSwipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mDeparturesPresenter.onSwipeToRefresh();
        }
    };
    //endregion

    //region Private helpers
    private void saveToBundle(@NonNull Bundle bundle) {
        bundle.putParcelableArrayList(EXTRAS_DEPARTURES, (ArrayList) mDepartures);
        bundle.putString(EXTRAS_ROUTE, mRoute);
        bundle.putInt(EXTRAS_DIRECTION, mDirectionType.getServerId());
        bundle.putString(EXTRAS_STOP_ID, mStopId);
    }

    private void loadFromBundle(@NonNull Bundle bundle) {
        mRoute = bundle.getString(EXTRAS_ROUTE);
        mDirectionType = DirectionType.from(bundle.getInt(EXTRAS_DIRECTION));
        mStopId = bundle.getString(EXTRAS_STOP_ID);
        mDepartures = bundle.getParcelableArrayList(EXTRAS_DEPARTURES);
    }
    //endregion
}
