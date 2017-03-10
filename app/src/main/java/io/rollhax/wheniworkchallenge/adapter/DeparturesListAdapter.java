package io.rollhax.wheniworkchallenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.view.IStopItemView;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

public class DeparturesListAdapter extends RecyclerView.Adapter<DeparturesListAdapter.ViewHolder> {

    private List<IDepartureViewModel> mDepartures;

    public DeparturesListAdapter(List<IDepartureViewModel> stops) {
        mDepartures = stops;
    }

    //region Adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        StopView view = (StopView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.view_stop_inflatable, parent, false);
//        view.setStopClickListener(mListener);
//        return new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        IStopViewModel viewModel = mDepartures.get(position);
//        holder.mView.showStop(viewModel);
    }

    @Override
    public int getItemCount() {
        return mDepartures != null ?
                mDepartures.size() : 0;
    }
    //endregion

    public void setDepartures(List<IDepartureViewModel> departures) {
        mDepartures = departures;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
//        IStopItemView mView;

        ViewHolder(View itemView) {
            super(itemView);

//            mView = (IStopItemView) itemView;
        }
    }
}
