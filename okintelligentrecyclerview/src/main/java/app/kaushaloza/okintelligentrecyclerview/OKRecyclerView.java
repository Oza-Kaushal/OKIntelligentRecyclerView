package app.kaushaloza.okintelligentrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class OKRecyclerView extends RecyclerView {

    private TextView tvEmptyMsgHolder;

    public OKRecyclerView(Context context) {
        super(context);
    }

    public OKRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OKRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View getEmptyMsgHolder() {
        return tvEmptyMsgHolder;
    }

    public void setEmptyMsgHolder(TextView tvEmptyMsgHolder) {
        this.tvEmptyMsgHolder = tvEmptyMsgHolder;
    }

    public void showEmptyDataView(String errorMessage) {
        if (tvEmptyMsgHolder != null) {
            if (getAdapter() != null && getAdapter().getItemCount() == 0) {

                tvEmptyMsgHolder.setVisibility(View.VISIBLE);
                tvEmptyMsgHolder.setText(errorMessage);

            } else {
                tvEmptyMsgHolder.setVisibility(View.GONE);
            }
        }
    }
}
