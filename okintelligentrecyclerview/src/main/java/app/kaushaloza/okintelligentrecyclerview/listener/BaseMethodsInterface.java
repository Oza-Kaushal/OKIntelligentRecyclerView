package app.kaushaloza.okintelligentrecyclerview.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import app.kaushaloza.okintelligentrecyclerview.OKRecyclerView;

public interface BaseMethodsInterface extends View.OnClickListener {

    void setOkRecyclerView(OKRecyclerView okRecyclerView, SwipeRefreshLayout swipeRefreshLayout);

    int setLayout();

    Activity getCurrentActivity();

    Context getCurrentContext();

    Fragment getCurrentFragment();

    View getParentView();

    void onSwipeRefresh();
}
