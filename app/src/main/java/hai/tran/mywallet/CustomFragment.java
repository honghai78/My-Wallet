package hai.tran.mywallet;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by hongh on 6/16/2016.
 */


public abstract class CustomFragment extends Fragment {

    protected ImageView mBtAdd;
    protected Context mContext;
    protected View mView;
    protected TextView mTitle;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        mTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mBtAdd = (ImageView) mView.findViewById(R.id.btAdd);
        configToolbar();
    }

    protected void setTitle(String text) {
        mTitle.setText(text);
    }

    protected void setmBtAddImage(int resource)
    {
        mBtAdd.setImageResource(resource);
    }
    protected abstract void configToolbar();
}

