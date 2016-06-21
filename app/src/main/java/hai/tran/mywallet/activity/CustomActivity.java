package hai.tran.mywallet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import hai.tran.mywallet.R;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.Item;

/**
 * Created by hongh on 6/18/2016.
 */
public class CustomActivity extends AppCompatActivity {
    private DataSQLLite mSqLite;
    private Categories mCategories[] = {new Categories(1, "Baby", "ic_01"), new Categories(2, "Cinema", "ic_02"),
            new Categories(3, "Home", "ic_03"), new Categories(4, "Market", "ic_04"), new Categories(5, "Restaurant", "ic_06"),
            new Categories(6, "Travel", "ic_08"), new Categories(7, "Others", "ic_05"),
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        mSqLite = new DataSQLLite(this);
        if (mSqLite.getDataCategory().size() < 1) {
            for (int i = 0; i < mCategories.length; i++) {
                mSqLite.pushCategories(mCategories[i]);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    public DataSQLLite getmSqLite() {
        return mSqLite;
    }

    public Categories[] getmCategories() {
        return mCategories;
    }

    public String getIconName(Item item) {
        for (int i = 0; i < mCategories.length; i++) {
            if (item.getmCategoriesID() == mCategories[i].getId())
                return mCategories[i].getIconName();
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        overridePendingTransition(R.transition.sli_re_in, R.transition.sli_re_out);
        super.onRestart();
    }
}
