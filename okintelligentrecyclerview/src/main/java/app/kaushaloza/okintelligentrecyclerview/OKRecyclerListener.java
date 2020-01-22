package app.kaushaloza.okintelligentrecyclerview;

import androidx.annotation.StringRes;

public interface OKRecyclerListener<T> {

    void showEmptyDataView(@StringRes int resId);

    void onRecyclerItemClick(int position, T item);

}
