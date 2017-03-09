package io.rollhax.wheniworkchallenge.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rollhax.wheniworkchallenge.R;
import io.rollhax.wheniworkchallenge.listener.IRouteClickListener;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public class RouteView extends LinearLayout implements IRouteItemView {

    @BindView(R.id.view_route_description_text)
    TextView mDescriptionText;

    private Context mContext;
    private IRouteClickListener mListener;
    private IRouteViewModel mRoute;

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
    public void showRoute(IRouteViewModel route) {
        mRoute = route;

        mDescriptionText.setText(mRoute.getDescription());
    }

    @Override
    public void setRouteClickListener(IRouteClickListener listener) {
        mListener = listener;
    }
    //endregion

    //region Listeners
    private final OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onRouteClicked(mRoute);
            }
        }
    };
    //endregion

    //region Private helpers
    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_route, this);

        ButterKnife.bind(this);

        setOnClickListener(mOnClickListener);
    }
    //endregion
}
