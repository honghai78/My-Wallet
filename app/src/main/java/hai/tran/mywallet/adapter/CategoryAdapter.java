package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.Item;

/**
 * Created by hongh on 6/18/2016.
 */
public class CategoryAdapter extends BaseAdapterEx {
    private static final String TAG = ListViewAdapter.class.getSimpleName();

    public CategoryAdapter(Context context, List<Categories> list) {
        super(context, list);
    }


    public void reLoad() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final CategoryHolder viewHolder;
        if (convertView == null) {
            row = getLayoutInflater().inflate(R.layout.catelory_item, parent, false);
            viewHolder = new CategoryHolder(row);
            row.setTag(viewHolder);
        } else {
            row = convertView;
            viewHolder = (CategoryHolder) row.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setData(getContext(), (Categories) get(position));
        }

        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;
        final CategoryHolder viewHolder;
        if (convertView == null) {
            row = getLayoutInflater().inflate(R.layout.catelory_item_drop, parent, false);
            viewHolder = new CategoryHolder(row);
            row.setTag(viewHolder);
        } else {
            row = convertView;
            viewHolder = (CategoryHolder) row.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setData(getContext(), (Categories) get(position));
        }
        return row;
        //  return super.getDropDownView(position, convertView, parent);
    }
}
