package app.kaushaloza.okandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import app.kaushaloza.okintelligentrecyclerview.BaseActivity;
import app.kaushaloza.okintelligentrecyclerview.OKRecyclerListener;

public class MainActivity extends BaseActivity implements OKRecyclerListener<MyModel> {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeOkRecyclerView(true, getCurrentContext(), getParentView(), false, 0);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this, MainActivity.this);
        ArrayList<MyModel> myModelArrayList = new ArrayList<>();
        myModelArrayList.add(new MyModel("kaushal"));
        myModelArrayList.add(new MyModel("kaushal 1"));
        myModelArrayList.add(new MyModel("kaushal 2"));
        myModelArrayList.add(new MyModel("kaushal 3"));
        myModelArrayList.add(new MyModel("kaushal 4"));
        myModelArrayList.add(new MyModel("kaushal 5"));
        myAdapter.addItems(myModelArrayList);
        okRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void showEmptyDataView(int resId) {

    }

    @Override
    public void onRecyclerItemClick(int position, MyModel item) {

    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Activity getCurrentActivity() {
        return MainActivity.this;
    }

    @Override
    public Context getCurrentContext() {
        return MainActivity.this;
    }

    @Override
    public Fragment getCurrentFragment() {
        return null;
    }

    @Override
    public void onSwipeRefresh() {

    }

    @Override
    public void onClick(View v) {

    }

    public class OKViewHolder {

        TextView textView;
    }
}
