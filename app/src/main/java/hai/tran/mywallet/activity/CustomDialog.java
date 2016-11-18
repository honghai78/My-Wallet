package hai.tran.mywallet.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.fragment.DateFragment;
import hai.tran.mywallet.fragment.DetailFragment;
import hai.tran.mywallet.object.DataMonth;
import hai.tran.mywallet.object.FormatString;

/**
 * Created by hongh on 6/19/2016.
 */
public class CustomDialog extends Dialog {

    private Activity mActivity;
    private Dialog mDialog;
    private ImageView mImageView;
    private TextView mTextViewTotal, mTextViewIncome, mTextViewExpense, mTextViewTile;
    private String mMonth;
    private String mDate;
    private DataSQLLite mDataSQLLite;

    public CustomDialog(Activity activity, String date) {
        super(activity);
        this.mActivity = activity;
        mDate = date;
        mMonth = getMonthForInt(getMonthInt(mDate));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mTextViewTotal = (TextView) findViewById(R.id.textTotal);
        mTextViewExpense = (TextView) findViewById(R.id.textExpense);
        mTextViewIncome = (TextView) findViewById(R.id.textIncome);
        mTextViewTile = (TextView) findViewById(R.id.textTile);

        DataSQLLite dataSQLLite = new DataSQLLite(getContext());
        DataMonth dataMonth = dataSQLLite.getDataValueMonth(mDate);
        mTextViewIncome.setText(FormatString.format(dataMonth.getmValueIncome() + "") + ",000 VNĐ");
        mTextViewExpense.setText(FormatString.format(dataMonth.getmValueExpense() + "") + ",000 VNĐ");
        mTextViewTotal.setText(FormatString.format(dataMonth.getmValueTotal() + "") + ",000 VNĐ");
        mTextViewTile.setText("Sumary for " + mMonth);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                DetailFragment frag = new DetailFragment();
                frag.setMonth(getMonthForInt(getMonthInt(mDate)));
                frag.setDate(mDate);
                FragmentTransaction fragmentTransaction = ((MainActivity) mActivity).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.transition.slide_in, R.transition.slide_out);
                fragmentTransaction.replace(R.id.fragment_main, frag).commit();
            }
        });
    }

    private String getMonthForInt(int num) {
        String month = "wrong";
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (num >= 0 && num <= 11) {
            month = months[num - 1];
        }
        return month;
    }

    private int getMonthInt(String date) {
        String dateArray[] = date.trim().split("-");
        return Integer.parseInt(dateArray[1]);
    }
}