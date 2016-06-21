package hai.tran.mywallet.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.object.Categories;

/**
 * Created by hongh on 6/18/2016.
 */
public class CategoryHolder {
    ImageView mImageView;
    TextView mTextView;

    protected CategoryHolder(View row) {
        mImageView = (ImageView) row.findViewById(R.id.item_im);
        mTextView = (TextView) row.findViewById(R.id.item_te);
    }

    protected void setData(Context context, Categories categories) {
        mImageView.setImageResource(context.getResources().getIdentifier(categories.getIconName(), "drawable", context.getPackageName()));
        mTextView.setText(categories.getName());
    }
}
