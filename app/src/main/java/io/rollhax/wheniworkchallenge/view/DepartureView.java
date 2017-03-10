package io.rollhax.wheniworkchallenge.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

public class DepartureView extends LinearLayout implements IDepartureItemView {

    @BindView(R.id.view_departure_description_text)
    TextView mDescriptionText;
    @BindView(R.id.view_departure_time_text)
    TextView mDepartureTimeText;

    private Context mContext;
    private IDepartureViewModel mDeparture;

    //region Constructors
    public DepartureView(Context context) {
        super(context);
        init(context);
    }

    public DepartureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DepartureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DepartureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    //endregion

    //region IDepartureItemView
    @Override
    public void showDeparture(IDepartureViewModel departure) {
        mDeparture = departure;

        mDescriptionText.setText(mDeparture.getDescription());
        mDepartureTimeText.setText(mDeparture.getDepartureText());
    }
    //endregion

    //region Private helpers
    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_departure, this);

        ButterKnife.bind(this);
    }
    //endregion
}
