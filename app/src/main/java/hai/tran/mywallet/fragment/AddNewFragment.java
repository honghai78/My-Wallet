package hai.tran.mywallet.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.adapter.CategoryAdapter;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.FormatString;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

/**
 * Created by hongh on 6/17/2016.
 */
public class AddNewFragment extends CustomFragment implements View.OnClickListener {

    private LinearLayout mLinearLayoutRight, mLinearLayoutLeft;
    private TextView mBtIn, mBtEx;
    private ImageView imageViewPick, mImageViewSpin;
    private EditText editTextDate, editTextNote;
    private Spinner mSpinner;
    private ItemType mItemType = ItemType.INCOME;
    private TextView mValue, mSub, mB1, mB2, mB3, mB4, mB5, mB6, mB7, mB8, mB9, mB10, mB0, mBOK, mBSp;
    int mCount = 0;
    private static boolean UPDATE = false;
    private Item mItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);
        mView = container.getRootView();
        return view;
    }

    @Override
    public void configToolbar() {
        setTitle("Add New");
        mBtAdd.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mB1 = (TextView) view.findViewById(R.id.kb1);
        mB2 = (TextView) view.findViewById(R.id.kb2);
        mB3 = (TextView) view.findViewById(R.id.kb3);
        mB4 = (TextView) view.findViewById(R.id.kb4);
        mB5 = (TextView) view.findViewById(R.id.kb5);
        mB6 = (TextView) view.findViewById(R.id.kb6);
        mB7 = (TextView) view.findViewById(R.id.kb7);
        mB8 = (TextView) view.findViewById(R.id.kb8);
        mB9 = (TextView) view.findViewById(R.id.kb9);
        mB10 = (TextView) view.findViewById(R.id.kb10);
        mB0 = (TextView) view.findViewById(R.id.kb0);
        mBSp = (TextView) view.findViewById(R.id.spa);
        mBOK = (TextView) view.findViewById(R.id.kbok);
        mB1.setOnClickListener(this);
        mB2.setOnClickListener(this);
        mB3.setOnClickListener(this);
        mB4.setOnClickListener(this);
        mB5.setOnClickListener(this);
        mB6.setOnClickListener(this);
        mB7.setOnClickListener(this);
        mB8.setOnClickListener(this);
        mB9.setOnClickListener(this);
        mB0.setOnClickListener(this);
        mBSp.setOnClickListener(this);
        mBOK.setOnClickListener(this);
        editTextNote = (EditText) view.findViewById(R.id.add_ed1);
        mLinearLayoutLeft = (LinearLayout) view.findViewById(R.id.lin_bt_in);
        mLinearLayoutRight = (LinearLayout) view.findViewById(R.id.lin_bt_ex);
        mValue = (TextView) view.findViewById(R.id.add_value);
        mSub = (TextView) view.findViewById(R.id.add_sub);
        mBtIn = (TextView) view.findViewById(R.id.bt_in);
        mBtEx = (TextView) view.findViewById(R.id.bt_ex);
        mLinearLayoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtEx.setTextColor(getResources().getColor(android.R.color.black));
                mLinearLayoutRight.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                mBtIn.setTextColor(getResources().getColor(android.R.color.white));
                mLinearLayoutLeft.setBackgroundResource(R.drawable.bg_add_new_bt_left);
                mValue.setTextColor(getResources().getColor(R.color.colorBtClick));
                mSub.setTextColor(getResources().getColor(R.color.colorBtClick));
                mItemType = ItemType.INCOME;

            }
        });
        mLinearLayoutRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtEx.setTextColor(getResources().getColor(android.R.color.white));
                mLinearLayoutLeft.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                mBtIn.setTextColor(getResources().getColor(android.R.color.black));
                mLinearLayoutRight.setBackgroundResource(R.drawable.bg_add_new_bt_right);
                mValue.setTextColor(getResources().getColor(R.color.colorAccent));
                mSub.setTextColor(getResources().getColor(R.color.colorAccent));
                mItemType = ItemType.EXPENSE;

            }
        });

        imageViewPick = (ImageView) view.findViewById(R.id.add_pic);
        editTextDate = (EditText) view.findViewById(R.id.add_ed2);
        final Calendar calendar = Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        editTextDate.setText(calendar.get(Calendar.YEAR)+"-"+month+"-"+calendar.get(Calendar.DATE));
        imageViewPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear += 1;
                        String date = year + "-" + monthOfYear + "-" + dayOfMonth;
                        editTextDate.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
            }
        });

        DataSQLLite dataSQLLite = new DataSQLLite(getContext());
        List<Categories> categoriesList = dataSQLLite.getDataCategory();
        mSpinner = (Spinner) view.findViewById(R.id.add_ed3);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoriesList);
        mSpinner.setAdapter(categoryAdapter);
        mSpinner.setSelection(0);
        mImageViewSpin = (ImageView) view.findViewById(R.id.add_ca);
        mImageViewSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpinner.performClick();
            }
        });

        Intent intent = getActivity().getIntent();
        Item item = (Item) intent.getSerializableExtra("ITEM_SEND");
        if(item!=null)
        {
            mItem = item;
            UPDATE = true;
            editTextNote.setText(mItem.getmNote());
            editTextDate.setText(mItem.getmDate());
            mSpinner.setSelection(mItem.getmCategoriesID()-1);
            mValue.setText(mItem.getmValue()+"");
            if(mItem.getmType()==ItemType.EXPENSE.getValue())
                mLinearLayoutRight.performClick();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.kb0:
            case R.id.kb1:
            case R.id.kb2:
            case R.id.kb3:
            case R.id.kb4:
            case R.id.kb5:
            case R.id.kb6:
            case R.id.kb7:
            case R.id.kb8:
            case R.id.kb9: {
                long kt = Long.parseLong(FormatString.notFormat(mValue.getText()+""));
                if (mCount == 0 && kt ==0) {
                    mValue.setText(FormatString.format(((TextView) v).getText().toString()));
                    if (id != R.id.kb0)
                        mCount++;
                } else
                {
                    mCount=1;
                    mValue.setText(FormatString.format(FormatString.notFormat(mValue.getText()+"") + ((TextView) v).getText().toString()));
                }
                break;
            }
            case R.id.spa: {
                if (FormatString.notFormat(mValue.getText()+"").length() > 3)
                {
                    mValue.setText(FormatString.format((FormatString.notFormat(mValue.getText()+"").subSequence(0, FormatString.notFormat(mValue.getText()+"").length() - 1)).toString()));}
                else if(FormatString.notFormat(mValue.getText()+"").length() >= 1)
                    mValue.setText((mValue.getText()+"").subSequence(0, mValue.getText().length() - 1));
                if (mValue.getText().length() == 0) {
                    mValue.setText("0");
                    mCount = 0;
                }
                break;
            }
            case R.id.kbok: {
                long kt = Long.parseLong(FormatString.notFormat(mValue.getText()+""));
                if(kt==0) {
                    Toast.makeText(getActivity(), "Not save! Please enter value", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!UPDATE) {
                    int idItem = 1;
                    if (mCount > 0) {
                        List<Item> list = getmSqLite().getDataItem();
                        if (list.size() > 0)
                            idItem = list.get(list.size() - 1).getmID() + 1;
                        Item item = new Item(idItem, mItemType, editTextNote.getText().toString(), editTextDate.getText().toString(), Long.parseLong(FormatString.notFormat(mValue.getText().toString())), mSpinner.getSelectedItemPosition() + 1);
                        if (getmSqLite().pushItem(item)) {
                            Toast.makeText(getActivity(), "Save!", Toast.LENGTH_LONG).show();
                            editTextNote.setText("");
                            editTextNote.setHint("Enter your note!");
                            mValue.setText("0");
                            mCount = 0;
                        } else
                            Toast.makeText(getActivity(), "Error! Can not save", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Item item = new Item(mItem.getmID(),mItemType,editTextNote.getText().toString(), editTextDate.getText().toString(), Long.parseLong(FormatString.notFormat(mValue.getText().toString())), mSpinner.getSelectedItemPosition()+1);
                    getmSqLite().updateItem(item);
                    mCount=0;
                    UPDATE=false;
                    getActivity().finish();
                }
                break;
            }
        }
    }

}
