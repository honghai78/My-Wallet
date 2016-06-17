package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

/**
 * Created by hai.tran on 6/17/2016.
 */
public class ListViewHolder {
    private ImageView mIcon, mBtDelete, mBtEdit;
    private TextView mNote, mDate, mValue, mSubValue, mUnit;
    private static final String TAG = ListViewHolder.class.getSimpleName();
    private Item mItem;
    private Context mContext = null;

    protected ListViewHolder(View row)
    {
        mIcon = (ImageView) row.findViewById(R.id.item_icon);
        mNote = (TextView) row.findViewById(R.id.item_notice);
        mDate = (TextView) row.findViewById(R.id.item_date);
        mValue = (TextView) row.findViewById(R.id.item_money);
        mSubValue = (TextView) row.findViewById(R.id.item_sub);
        mUnit = (TextView) row.findViewById(R.id.item_unit);
        mBtDelete=(ImageView) row.findViewById(R.id.item_delete);
        mBtEdit = (ImageView) row.findViewById(R.id.item_edit);
    }

    protected  void showBtAction(boolean b)
    {
        if(b)
        {
            mBtDelete.setVisibility(View.VISIBLE);
            mBtEdit.setVisibility(View.VISIBLE);
        }
        else
        {
            mBtEdit.setVisibility(View.GONE);
            mBtDelete.setVisibility(View.GONE);
        }
    }
    protected void updateInfoData(Item item, Context context)
    {
        mContext=context;
        mItem = item;
        updateViewData(mItem, mContext);
    }

    protected void updateViewData(Item item, Context context)
    {
        mNote.setText(item.getmNote());
        mDate.setText(item.getmDate());
        mValue.setText(String.format("%,d", item.getmValue()));

        if(item.getmType() == ItemType.INCOME) {
            mValue.setTextColor(context.getResources().getColor(R.color.colorBtClick));
            mSubValue.setTextColor(context.getResources().getColor(R.color.colorBtClick));
            mUnit.setTextColor(context.getResources().getColor(R.color.colorBtClick));
        } else {
            mValue.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            mSubValue.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            mUnit.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }
}
