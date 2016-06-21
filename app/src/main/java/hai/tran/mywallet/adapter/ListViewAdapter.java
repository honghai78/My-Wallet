package hai.tran.mywallet.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

import hai.tran.mywallet.activity.MainActivity;
import hai.tran.mywallet.R;
import hai.tran.mywallet.data.DataSharedPreferences;
import hai.tran.mywallet.fragment.AddNewFragment;
import hai.tran.mywallet.fragment.DateFragment;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.Item;


/**
 * An adapter that will display main in a {@link android.widget.GridView}.
 */
public class ListViewAdapter extends BaseAdapterEx<Item> {

    private static final String TAG = ListViewAdapter.class.getSimpleName();
    private Categories mCategories[];

    public ListViewAdapter(Context context, Categories categories[], List<Item> list) {
        super(context, list);
        mCategories = categories;
    }


    public void reLoad() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final ListViewHolder viewHolder;
        if (convertView == null) {
            row = getLayoutInflater().inflate(R.layout.listview_main_item, parent, false);
            viewHolder = new ListViewHolder(row);
            row.setTag(viewHolder);
        } else {
            row = convertView;
            viewHolder = (ListViewHolder) row.getTag();
        }
        Item item = get(position);
        String nameIcon = "";
        for (int i = 0; i < mCategories.length; i++) {
            if (item.getmCategoriesID() == mCategories[i].getId())
                nameIcon = mCategories[i].getIconName();
        }
        if (viewHolder != null) {
            viewHolder.updateInfoData(item, getContext(), nameIcon);
        }
        viewHolder.mBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).getmSqLite().deleteItem(get(position).getmID());
                remove(position);
                notifyDataSetChanged();
                //  viewHolder.showBtAction(false);

            }
        });

        viewHolder.mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewFragment frag = new AddNewFragment();
                frag.setItem(get(position));
                FragmentTransaction fragmentTransaction = ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.transition.slide_in, R.transition.slide_out);
                fragmentTransaction.replace(R.id.fragment_main, frag).commit();
            }
        });
        return row;
    }

}
