package io.rollhax.wheniworkchallenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.view.IStopItemView;
import io.rollhax.wheniworkchallenge.view.StopView;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public class StopsListAdapter extends RecyclerView.Adapter<StopsListAdapter.ViewHolder> {

    private List<IStopViewModel> mStops;
    private IStopClickListener mListener;

    public StopsListAdapter(List<IStopViewModel> stops, IStopClickListener listener) {
        mStops = stops;
        mListener = listener;
    }

    //region Adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StopView view = (StopView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_stop_inflatable, parent, false);
        view.setStopClickListener(mListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IStopViewModel viewModel = mStops.get(position);
        holder.mView.showStop(viewModel);
    }

    @Override
    public int getItemCount() {
        return mStops != null ?
                mStops.size() : 0;
    }
    //endregion

    public void setStops(List<IStopViewModel> stops) {
        mStops = stops;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        IStopItemView mView;

        ViewHolder(View itemView) {
            super(itemView);

            mView = (IStopItemView) itemView;
        }
    }
}
