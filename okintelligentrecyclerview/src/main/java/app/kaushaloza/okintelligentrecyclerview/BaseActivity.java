package app.kaushaloza.okintelligentrecyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import app.kaushaloza.okintelligentrecyclerview.listener.BaseInterFace;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterFace {

    @SuppressLint("StaticFieldLeak")
    public static TextView tvNoDataFound;
    public String arg0 = "";
    protected OKRecyclerView okRecyclerView, okRecyclerView2;
    protected SwipeRefreshLayout swipeRefreshLayout;

    int id = R.id.parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(setLayout());
    }

    @Override
    public View getParentView() {
        return findViewById(id);
    }

    @Override
    public void setOkRecyclerView(OKRecyclerView okRecyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.okRecyclerView = okRecyclerView;
    }
}
