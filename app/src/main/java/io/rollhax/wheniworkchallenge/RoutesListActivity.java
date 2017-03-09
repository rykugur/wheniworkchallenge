package io.rollhax.wheniworkchallenge;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rollhax.wheniworkchallenge.adapter.RoutesListAdapter;
import io.rollhax.wheniworkchallenge.listener.IRouteClickListener;
import io.rollhax.wheniworkchallenge.presentation.presenter.IRoutesPresenter;
import io.rollhax.wheniworkchallenge.presentation.presenter.RoutesPresenter;
import io.rollhax.wheniworkchallenge.view.IRoutesListView;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public class RoutesListActivity extends AppCompatActivity implements IRoutesListView {

    private static final String TAG = RoutesListActivity.class.getSimpleName();

    private IRoutesPresenter mRoutesPresenter;
    private RoutesListAdapter mRoutesListAdapter;

    private List<IRouteViewModel> mRoutes;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    //region IRoutesListView
    @Override
    public void setRoutes(List<IRouteViewModel> routes) {
        mRoutes = routes;
    }

    @Override
    public void displayRoutes(List<IRouteViewModel> routes) {
        mRoutesListAdapter.setRoutes(routes);
    }

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
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mSwipeRefresh.setOnRefreshListener(mSwipeRefreshListener);

        mRoutesListAdapter = new RoutesListAdapter(mRoutes, mRouteClickListener);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mRoutesListAdapter);

        mRoutesPresenter = new RoutesPresenter();
        mRoutesPresenter.onCreate(this);
        mRoutesPresenter.onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_routes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Listeners
    private final SwipeRefreshLayout.OnRefreshListener mSwipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mRoutesPresenter.onRefresh();
        }
    };

    private final IRouteClickListener mRouteClickListener = new IRouteClickListener() {
        @Override
        public void onRouteClicked(IRouteViewModel route) {
            Log.d(TAG, "onRouteClicked: route=" + route.getRoute());
        }
    };
    //endregion
}
