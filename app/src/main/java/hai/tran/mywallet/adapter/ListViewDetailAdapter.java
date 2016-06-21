package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.CategoriesValue;
import hai.tran.mywallet.object.FormatString;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

/**
 * Created by hongh on 6/19/2016.
 */
public class ListViewDetailAdapter extends BaseAdapterEx {
    private static final String TAG = ListViewAdapter.class.getSimpleName();
    private Categories mCategories[];

    public ListViewDetailAdapter(Context context, List list) {
        super(context, list);
    }


    public void reLoad() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        try {
            CategoriesValue categories = (CategoriesValue) get(position);
            row = getLayoutInflater().inflate(R.layout.item_catelory_value, parent, false);
            ViewHolderCategories viewHolderCategories = new ViewHolderCategories(row);
            int id = getContext().getResources().getIdentifier(categories.getIconName(), "drawable", getContext().getPackageName());
            viewHolderCategories.imageView.setImageResource(id);
            viewHolderCategories.textViewName.setText(categories.getName());
            if (categories.getmItemType() == ItemType.INCOME)
                viewHolderCategories.textViewValue.setTextColor(getContext().getResources().getColor(R.color.colorBtClick));
            else
                viewHolderCategories.textViewValue.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            viewHolderCategories.textViewValue.setText(FormatString.format(categories.getmValue() + "") + ",000 VNĐ");
        } catch (ClassCastException c) {
            Item item = (Item) get(position);
            row = getLayoutInflater().inflate(R.layout.item_item, parent, false);
            ViewHolderItem viewHolderItem = new ViewHolderItem(row);
            viewHolderItem.textViewNote.setText(item.getmNote());
            viewHolderItem.textViewDate.setText(item.getmDate());
            if (item.getmType() == ItemType.INCOME.getValue()) {
                viewHolderItem.textViewUnit.setTextColor(getContext().getResources().getColor(R.color.colorBtClick));
                viewHolderItem.textViewValue.setTextColor(getContext().getResources().getColor(R.color.colorBtClick));
            } else {
                viewHolderItem.textViewUnit.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                viewHolderItem.textViewValue.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            }
            viewHolderItem.textViewUnit.setText("VNĐ");
            viewHolderItem.textViewValue.setText(FormatString.format(item.getmValue() + "") + ",000");
        }

        return row;
    }

}

class ViewHolderCategories {
    public ImageView imageView;
    public TextView textViewName, textViewValue;

    public ViewHolderCategories(View view) {
        imageView = (ImageView) view.findViewById(R.id.item_im_ca);
        textViewName = (TextView) view.findViewById(R.id.item_te_ca);
        textViewValue = (TextView) view.findViewById(R.id.item_value_ca);
    }
}

class ViewHolderItem {
    public TextView textViewNote, textViewValue, textViewDate, textViewUnit;

    public ViewHolderItem(View view) {
        textViewNote = (TextView) view.findViewById(R.id.item_notice_it);
        textViewDate = (TextView) view.findViewById(R.id.item_date_it);
        textViewValue = (TextView) view.findViewById(R.id.item_money_it);
        textViewUnit = (TextView) view.findViewById(R.id.item_unit_it);
    }
}