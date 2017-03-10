package io.rollhax.wheniworkchallenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.view.DepartureView;
import io.rollhax.wheniworkchallenge.view.IDepartureItemView;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

public class DeparturesListAdapter extends RecyclerView.Adapter<DeparturesListAdapter.ViewHolder> {

    private List<IDepartureViewModel> mDepartures;

    public DeparturesListAdapter(List<IDepartureViewModel> stops) {
        mDepartures = stops;
    }

    //region Adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DepartureView view = (DepartureView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_departure_inflatable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IDepartureViewModel viewModel = mDepartures.get(position);
        holder.mView.showDeparture(viewModel);
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
        IDepartureItemView mView;

        ViewHolder(View itemView) {
            super(itemView);

            mView = (IDepartureItemView) itemView;
        }
    }
}
