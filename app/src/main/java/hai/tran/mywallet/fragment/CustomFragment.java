package hai.tran.mywallet.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.Item;


/**
 * Created by hongh on 6/16/2016.
 */


public abstract class CustomFragment extends Fragment {

    protected ImageView mBtProfile;
    protected Context mContext;
    protected View mView;
    protected TextView mTitle;
    protected DataSQLLite mSqLite;
    private Categories mCategories[] = {new Categories(1, "Baby", "ic_01"), new Categories(2, "Cinema", "ic_02"),
            new Categories(3, "Home", "ic_03"), new Categories(4, "Market", "ic_04"), new Categories(5, "Restaurant", "ic_06"),
            new Categories(6, "Travel", "ic_08"), new Categories(7, "Others", "ic_05"),
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        mSqLite = new DataSQLLite(getActivity());
        mTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mBtProfile = (ImageView) mView.findViewById(R.id.btAdd);
        configToolbar();
        setmBtAddImage(R.drawable.ic_person);
        mBtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAppLicense();
            }
        });
    }

    protected void setTitle(String text) {
        mTitle.setText(text);
    }

    protected void setmBtAddImage(int resource) {
        mBtProfile.setImageResource(resource);
    }

    public String getIconName(Item item) {
        for (int i = 0; i < mCategories.length; i++) {
            if (item.getmCategoriesID() == mCategories[i].getId())
                return mCategories[i].getIconName();
        }
        return null;
    }

    public Categories[] getmCategories() {
        return mCategories;
    }

    public DataSQLLite getmSqLite() {
        return mSqLite;
    }

    protected abstract void configToolbar();

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment frag = new DateFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                    fragmentTransaction.replace(R.id.fragment_main, frag).commit();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Displays the app's license in an AlertDialog.
     */
    private void displayAppLicense() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.app_license)
                .setNeutralButton(R.string.i_agree, null)
                .setCancelable(false)    // do not allow the user to click outside the dialog or press the back button
                .show();
    }
}

