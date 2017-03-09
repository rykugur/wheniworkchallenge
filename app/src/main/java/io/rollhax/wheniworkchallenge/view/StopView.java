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
import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public class StopView extends LinearLayout implements IStopItemView {

    @BindView(R.id.view_stop_description_text)
    TextView mDescriptionText;

    private Context mContext;
    private IStopClickListener mListener;
    private IStopViewModel mStop;

    //region Constructors
    public StopView(Context context) {
        super(context);
        init(context);
    }

    public StopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    //endregion

    //region IStopItemView
    @Override
    public void showStop(IStopViewModel stop) {
        mStop = stop;

        mDescriptionText.setText(stop.getStopDescription());
    }

    @Override
    public void setStopClickListener(IStopClickListener listener) {
        mListener = listener;
    }
    //endregion

    //region Listeners
    private final OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onStopClicked(mStop);
            }
        }
    };
    //endregion

    //region Private helpers
    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_stop, this);

        ButterKnife.bind(this);

        setOnClickListener(mOnClickListener);
    }
    //endregion
}
