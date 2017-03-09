package io.rollhax.wheniworkchallenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rollhax.wheniworkchallenge.listener.IRouteClickListener;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.view.IRouteItemView;
import io.rollhax.wheniworkchallenge.view.RouteView;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public class RoutesListAdapter extends RecyclerView.Adapter<RoutesListAdapter.ViewHolder> {

    private List<IRouteViewModel> mRoutes;
    private IRouteClickListener mListener;

    public RoutesListAdapter(List<IRouteViewModel> routes, IRouteClickListener listener) {
        mRoutes = routes;
        mListener = listener;
    }

    //region Adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RouteView view = (RouteView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_route_inflatable, parent, false);
        view.setRouteClickListener(mListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IRouteViewModel viewModel = mRoutes.get(position);
        holder.mView.showRoute(viewModel);
    }

    @Override
    public int getItemCount() {
        return mRoutes != null ?
                mRoutes.size() : 0;
    }
    //endregion

    public void setRoutes(List<IRouteViewModel> routes) {
        mRoutes = routes;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        IRouteItemView mView;

        ViewHolder(View itemView) {
            super(itemView);

            mView = (IRouteItemView) itemView;
        }
    }
}
