package app.kaushaloza.okintelligentrecyclerview.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import app.kaushaloza.okintelligentrecyclerview.OKRecyclerView;
import app.kaushaloza.okintelligentrecyclerview.R;

public interface BaseViewInterface extends BaseMethodsInterface {

    default void initializeOkRecyclerView(boolean showLineDecorView, Context context, View view, boolean isContainSwipeRefreshLayout, int recyclerViewOtherId) {
        try {
            OKRecyclerView okRecyclerView;
            if (recyclerViewOtherId != 0) {
                okRecyclerView = view.findViewById(recyclerViewOtherId);
            } else {
                okRecyclerView = view.findViewById(R.id.okRecyclerView);
            }

            setOkRecyclerView(okRecyclerView, null);
            TextView tvNoDataFound = view.findViewById(R.id.tvNoDataFound);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            okRecyclerView.setLayoutManager(linearLayoutManager);
            if (showLineDecorView) {
                okRecyclerView.setItemAnimator(new DefaultItemAnimator());
                okRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            }
            okRecyclerView.setEmptyMsgHolder(tvNoDataFound);

            if (isContainSwipeRefreshLayout) {
                SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.okSwipeRefreshLayout);
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            onSwipeRefresh();
                        }
                    });
                    setOkRecyclerView(okRecyclerView, swipeRefreshLayout);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
