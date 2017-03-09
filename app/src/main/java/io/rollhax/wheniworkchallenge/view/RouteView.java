package io.rollhax.wheniworkchallenge.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rollhax.wheniworkchallenge.R;

public class RouteView extends LinearLayout implements IRouteItemView {

    @BindView(R.id.view_route_description_text)
    TextView mDescriptionText;

    private Context mContext;

    //region Constructors
    public RouteView(Context context) {
        super(context);
        init(context);
    }

    public RouteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RouteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RouteView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    //endregion

    //region IRouteItemView
    @Override
    public void setDescriptionText(String descriptionText) {
        mDescriptionText.setText(descriptionText);
    }
    //endregion

    //region Private helpers
    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_route, this);

        ButterKnife.bind(this);
    }
    //endregion
}
