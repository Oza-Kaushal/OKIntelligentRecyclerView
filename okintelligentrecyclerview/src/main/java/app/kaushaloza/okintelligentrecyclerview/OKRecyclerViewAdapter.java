package app.kaushaloza.okintelligentrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import app.kaushaloza.okintelligentrecyclerview.listener.BaseInterFace;

/**
 * Created By Kaushal Oza
 * <p>
 * Generic Recycler Adapter which handles filtering and contains default reusable methods.
 *
 * @param <ItemType>     Data type of Your List
 * @param <ListenerType> Recycler Item Listener Data Type
 */
public abstract class OKRecyclerViewAdapter<ItemType, ViewClass, ListenerType extends OKRecyclerListener<ItemType>> extends RecyclerView.Adapter implements Filterable, BaseInterFace {

    public View[] dataViews;
    protected ListenerType listener;
    protected OKRecyclerView okRecyclerView;
    protected ArrayList<ItemType> originalList;
    private ArrayList<ItemType> filteredList;
    private ItemFilter itemFilter;
    private String filteredString;
    private int emptyErrorMsg, noSearchDataFoundMsg;
    private Activity activity;
    private Fragment fragment;

    public OKRecyclerViewAdapter(final ListenerType listener, final Activity activity) {
        this.listener = listener;
        this.activity = activity;
        this.filteredString = "";
        this.originalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();

        this.emptyErrorMsg = R.string.no_data_available;
        this.noSearchDataFoundMsg = R.string.no_search_result_available;

        RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if (originalList.size() == 0) {
                    listener.showEmptyDataView(getEmptyErrorMsg());
                } else {
                    listener.showEmptyDataView(getNoSearchDataFoundMsg());
                    dataViews = new View[originalList.size()];
                }
            }
        };

        this.registerAdapterDataObserver(adapterDataObserver);
    }

    public OKRecyclerViewAdapter(final ListenerType listener, final Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
        this.filteredString = "";
        this.originalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();

        this.emptyErrorMsg = R.string.no_data_available;
        this.noSearchDataFoundMsg = R.string.no_search_result_available;

        RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if (originalList.size() == 0) {
                    listener.showEmptyDataView(getEmptyErrorMsg());
                } else {
                    listener.showEmptyDataView(getNoSearchDataFoundMsg());
                    dataViews = new View[originalList.size()];
                }
            }
        };

        this.registerAdapterDataObserver(adapterDataObserver);
    }

    /**
     * Method is used for displaying values to UI
     *
     * @param viewClass Generic ViewHolder
     * @param item      Generic DataType
     */

    public abstract void onBindViewHolder(ViewClass viewClass, ItemType item, int position);

    public abstract void initView(View itemView, ViewClass viewClass);


    /**
     * Overriding default onBindViewHolder
     * <p>This will always call onBindData method to display value</p>
     *
     * @param myViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewHolder, int position) {
        myViewHolder.itemView.setOnClickListener(v -> listener.onRecyclerItemClick(myViewHolder.getAdapterPosition(), filteredList.get(myViewHolder.getAdapterPosition())));
        // implementation of recycler item click event. It will trigger onRecyclerItemClick() method always.
        ViewClass viewClass = (ViewClass) myViewHolder;
        onBindViewHolder(viewClass, filteredList.get(myViewHolder.getAdapterPosition()), myViewHolder.getAdapterPosition());
    }

    /**
     * @return returns items size
     */
    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    /**
     * Add All Items to list
     *
     * @param items
     */
    public void addItems(ArrayList<ItemType> items) {

        this.filteredList.addAll(items);
        this.originalList.addAll(items);

        notifyDataSetChanged();
    }

    public void addItemsWithFilter(ArrayList<ItemType> items) {
        this.originalList.addAll(items);
        if (TextUtils.isEmpty(filteredString)) {
            this.filteredList.addAll(items);
            notifyDataSetChanged();
        } else {
            getFilter().filter(filteredString);
        }
    }

    /**
     * Add Item at Particular index
     *
     * @param position
     * @param item
     */
    public void addItem(int position, ItemType item) {
        originalList.add(item);
        if (position > filteredList.size()) {
            filteredList.add(item);
        } else {
            filteredList.add(position, item);
        }
        notifyDataSetChanged();
    }

    /**
     * Add item at last index
     *
     * @param item
     */
    public void addItem(ItemType item) {
        this.filteredList.add(item);
        this.originalList.add(item);
        notifyDataSetChanged();
    }

    /**
     * Remove Last element of list
     */
    public void removeLastItem() {
        ItemType removedItem = null;
        if (filteredList.size() > 0) {
            removedItem = filteredList.get(filteredList.size() - 1);
            filteredList.remove(filteredList.size() - 1);
            notifyDataSetChanged();
        }
        if (removedItem != null) {
            originalList.remove(removedItem);
        }
    }

    /**
     * Remove Element from particular index
     *
     * @param position
     */
    public void removeItemAt(int position) {
        ItemType removedItem = null;
        if (filteredList.size() > position) {
            removedItem = filteredList.get(position);
            filteredList.remove(position);
            notifyDataSetChanged();
        }
        if (removedItem != null) {
            originalList.remove(removedItem);
        }
    }

    /**
     * remove All items of list
     */
    public void removeAllItems() {
        originalList.clear();
        filteredList.clear();
        notifyDataSetChanged();
    }


    public ArrayList<ItemType> getAllItems() {
        return originalList;
    }

    @Override
    public Filter getFilter() {
        if (itemFilter == null) {
            itemFilter = new ItemFilter();
        }
        return itemFilter;
    }

    /**
     * Put String value which you want to compare inside filtering
     * <p>If you want to compare multiple fields value specify them in comma seperated string</p>
     *
     * @param item
     * @return
     */
    public abstract ArrayList<String> compareFieldValue(ItemType item, ArrayList<String> searchItemList);

    private int getEmptyErrorMsg() {
        return emptyErrorMsg;
    }

    public void setEmptyErrorMsg(int emptyErrorMsg) {
        this.emptyErrorMsg = emptyErrorMsg;
    }

    private int getNoSearchDataFoundMsg() {
        return noSearchDataFoundMsg;
    }

    public void setNoSearchDataFoundMsg(int noSearchDataFoundMsg) {
        this.noSearchDataFoundMsg = noSearchDataFoundMsg;
    }

    public String getFilteredString() {
        return filteredString;
    }

    public void setFilteredString(String filteredString) {
        this.filteredString = filteredString;
    }

    public synchronized void replaceList(ArrayList<ItemType> items) {

        this.filteredList.clear();
        this.originalList.clear();

        this.filteredList.addAll(items);
        this.originalList.addAll(items);

        notifyDataSetChanged();
    }

    @Override
    public void setOkRecyclerView(OKRecyclerView okRecyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.okRecyclerView = okRecyclerView;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSwipeRefresh() {

    }

    @Override
    public View getParentView() {
        return null;
    }

    @Override
    public Activity getCurrentActivity() {
        return this.activity;
    }

    @Override
    public Context getCurrentContext() {
        return this.activity != null ? this.activity : this.fragment != null ? this.fragment.getContext() : null;
    }

    @Override
    public Fragment getCurrentFragment() {
        return this.fragment;
    }

    /**
     * Generic Filterable class which will trigger events
     * occording to input string.
     */
    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            filteredString = charSequence.toString().trim();

            String filterString = filteredString.toLowerCase();
            FilterResults filterResults = new FilterResults();

            final ArrayList<ItemType> mMasterList = originalList;

            final ArrayList<ItemType> mResultedList = new ArrayList<>();

            if (filterString.isEmpty()) {
                mResultedList.clear();
                mResultedList.addAll(mMasterList);
            } else {
                String filterableString;

                for (int i = 0; i < mMasterList.size(); i++) {

                    if (mMasterList.get(i) == null) {
                        continue;
                    }
                    ArrayList<String> compareFields = compareFieldValue(mMasterList.get(i), new ArrayList<String>());

                    if (compareFields != null) {
                        for (String itemValue : compareFields) {
                            filterableString = itemValue.toLowerCase();
                            if (filterableString.contains(filterString)) {
                                mResultedList.add(mMasterList.get(i));
                                break;
                            }
                        }
                    }
                }
            }
            filterResults.values = mResultedList;

            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredList = (ArrayList<ItemType>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public int setLayout() {
        return 0;
    }
}
