package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.FormatString;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

/**
 * Created by hai.tran on 6/17/2016.
 */
public class ListViewHolder {
    public ImageView mIcon, mBtDelete, mBtEdit;
    private TextView mNote, mDate, mValue, mSubValue, mUnit;
    private static final String TAG = ListViewHolder.class.getSimpleName();
    private Item mItem;
    private Context mContext = null;
    private String mIconName;
    private int mPoint = 0;
    public LinearLayout linearLayoutTotal, mLinearLayoutItem;

    protected ListViewHolder(View row) {
        try {
            mIcon = (ImageView) row.findViewById(R.id.item_icon);
            mNote = (TextView) row.findViewById(R.id.item_notice);
            mDate = (TextView) row.findViewById(R.id.item_date);
            mValue = (TextView) row.findViewById(R.id.item_money);
            mSubValue = (TextView) row.findViewById(R.id.item_sub);
            mUnit = (TextView) row.findViewById(R.id.item_unit);
            mBtDelete = (ImageView) row.findViewById(R.id.item_delete);
            mBtEdit = (ImageView) row.findViewById(R.id.item_edit);
            linearLayoutTotal = (LinearLayout) row.findViewById(R.id.item_linear1);
            mLinearLayoutItem = (LinearLayout) row.findViewById(R.id.lin_main);
        } catch (NullPointerException n) {
        }
    }

    public static ListViewHolder getViewHolder(View row) {
        return new ListViewHolder(row);
    }

    public boolean isShowBtAction() {
        if (mBtDelete.getVisibility() == View.VISIBLE)
            return true;
        else return false;
    }

    protected void updateInfoData(Item item, Context context, String iconName) {
        mContext = context;
        mItem = item;
        mIconName = iconName;
        updateViewData(mItem, mContext, mIconName);
    }

    protected void updateViewData(Item item, final Context context, String iconName) {
        int id = mContext.getResources().getIdentifier(iconName, "drawable", mContext.getPackageName());
        mIcon.setImageResource(id);
        mNote.setText(item.getmNote());
        mDate.setText(item.getmDate());
        mValue.setText(FormatString.format(item.getmValue() + ""));

        if (item.getmType() == ItemType.INCOME.getValue()) {
            mValue.setTextColor(context.getResources().getColor(R.color.colorBtClick));
            mSubValue.setTextColor(context.getResources().getColor(R.color.colorBtClick));
            mUnit.setTextColor(context.getResources().getColor(R.color.colorBtClick));
        } else {
            mValue.setTextColor(context.getResources().getColor(R.color.colorAccent));
            mSubValue.setTextColor(context.getResources().getColor(R.color.colorAccent));
            mUnit.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }

}
