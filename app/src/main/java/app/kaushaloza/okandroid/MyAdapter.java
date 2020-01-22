package app.kaushaloza.okandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.kaushaloza.okintelligentrecyclerview.OKRecyclerListener;
import app.kaushaloza.okintelligentrecyclerview.OKRecyclerViewAdapter;

public class MyAdapter extends OKRecyclerViewAdapter<MyModel, MyAdapter.OKViewHolder, OKRecyclerListener<MyModel>> {

    MyAdapter(OKRecyclerListener<MyModel> listener, Activity activity) {
        super(listener, activity);
    }

    @Override
    public void onBindViewHolder(OKViewHolder viewClass, MyModel item, int position) {
        viewClass.textView.setText(item.getA());
    }

    @Override
    public void initView(View itemView, OKViewHolder okViewHolder) {
        okViewHolder.textView = itemView.findViewById(R.id.adapterTV);
    }

    @Override
    public ArrayList<String> compareFieldValue(MyModel item, ArrayList<String> searchItemList) {
        return null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OKViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false));
    }


    public class OKViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        OKViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView, OKViewHolder.this);
        }
    }
}
