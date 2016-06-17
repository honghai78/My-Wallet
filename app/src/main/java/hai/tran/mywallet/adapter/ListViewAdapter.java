package hai.tran.mywallet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Item;


/**
 * An adapter that will display main in a {@link android.widget.GridView}.
 */
public class ListViewAdapter extends BaseAdapterEx<Item> {

    private static final String TAG = ListViewAdapter.class.getSimpleName();


    public ListViewAdapter(Context context,  List<Item> list) {
        super(context, list);
    }


    public void reLoad() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

        if (viewHolder != null) {
            viewHolder.updateInfoData(get(position), getContext());
        }
        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewHolder.showBtAction(true);
                return false;
            }
        });
        return row;
    }

}
