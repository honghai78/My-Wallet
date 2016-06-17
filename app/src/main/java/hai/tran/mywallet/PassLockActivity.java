package hai.tran.mywallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import hai.tran.mywallet.adapter.CustomAdapter;
import hai.tran.mywallet.data.DataSharedPreferences;
import hai.tran.mywallet.object.Categories;

/**
 * Created by hai.tran on 6/16/2016.
 */
public class PassLockActivity extends AppCompatActivity {

    private GridView mGridView;

    private TextView mTextViewTile, mTextPass;

    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4, mRadioButton5;

    private String mPass = "", mPassData, mPassOld;

    private int mCount = 0;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_lock);
        init();
        String arrry[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", ""};
        CustomAdapter customAdapter = new CustomAdapter(this, arrry, onClickListener());
        mGridView.setAdapter(customAdapter);
        mTextViewTile.setText(R.string.tile_password);
        mPassData = DataSharedPreferences.getDataSharedPreferences(getBaseContext()).getPreferencesString("PASS");
        if (mPassData.length() < 5) {
            mTextPass.setText(R.string.enter_password1);
            mIntent = getIntent();
            mPassOld = mIntent.getStringExtra("PASSWORD");
            if (mPassOld != null) {
                mTextPass.setText(R.string.enter_password2);
            }
        }
        mRadioButton5.setOnCheckedChangeListener(mRadioButton5Change());
    }


    private void init() {
        mGridView = (GridView) findViewById(R.id.grid1);
        mTextViewTile = (TextView) this.findViewById(R.id.toolbar_title);
        mTextPass = (TextView) this.findViewById(R.id.textTile);
        mRadioButton1 = (RadioButton) this.findViewById(R.id.passcode1);
        mRadioButton2 = (RadioButton) this.findViewById(R.id.passcode2);
        mRadioButton3 = (RadioButton) this.findViewById(R.id.passcode3);
        mRadioButton4 = (RadioButton) this.findViewById(R.id.passcode4);
        mRadioButton5 = (RadioButton) this.findViewById(R.id.passcode5);
    }


    public CompoundButton.OnCheckedChangeListener mRadioButton5Change() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //==================================

                if (mPassData.length() < 5) {

                    if (mPassOld != null) {
                        if (mPass.equals(mPassOld)) {
                            DataSharedPreferences.getDataSharedPreferences(getBaseContext()).setPreferencesString("PASS", mPass);
                            Intent i = new Intent(PassLockActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            return;
                        } else {
                            Toast.makeText(getBaseContext(), "Password not match", Toast.LENGTH_LONG).show();
                            unCheck();
                            mCount = 0;
                            mPass = "";
                            return;
                        }
                    } else {
                        Intent i = new Intent(PassLockActivity.this, PassLockActivity.class);
                        i.putExtra("PASSWORD", mPass);
                        startActivity(i);
                        finish();
                        return;
                    }

                }

                if (mPass.equals(mPassData)) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    Categories categories1[] ={new Categories(1, "a", "A"), new Categories(1, "a", "A")};
                    Categories categories = new Categories(1, "a", "A");
                    i.putExtra("PA", categories1);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    unCheck();
                    mCount = 0;
                    mPass = "";
                }

                //=================================
            }
        };
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPass += ((TextView) v).getText().toString();
                switch (mCount++) {
                    case 0:
                        mRadioButton1.performClick();
                        break;
                    case 1:
                        mRadioButton2.performClick();
                        break;
                    case 2:
                        mRadioButton3.performClick();
                        break;
                    case 3:
                        mRadioButton4.performClick();
                        break;
                    case 4:
                        mRadioButton5.performClick();
                        break;
                }

            }
        };
    }

    private void unCheck() {
        mRadioButton1.setChecked(false);
        mRadioButton2.setChecked(false);
        mRadioButton3.setChecked(false);
        mRadioButton4.setChecked(false);
        mRadioButton5.setChecked(false);
    }
}
