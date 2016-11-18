package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.Objects;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Categories;

/**
 * Created by hai.tran on 6/23/2016.
 */
public class MonthsAdapter extends BaseAdapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();
    String string[];
    Context mContext;

    public MonthsAdapter(Context context, String[] string) {
        this.mContext = context;
        this.string = string;
    }


    @Override
    public String getItem(int po) {
        return string[po];
    }

    @Override
    public long getItemId(int po) {
        return po;
    }

    @Override
    public int getCount() {
        return string.length;
    }

    public void reLoad() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        row = layoutInflater.inflate(R.layout.item_months, parent, false);
        int id = mContext.getResources().getIdentifier(string[position] + "", "drawable", mContext.getPackageName());
        ImageView imageView = (ImageView) row.findViewById(R.id.item_moths);
        imageView.setImageResource(id);
        return row;
    }
}
