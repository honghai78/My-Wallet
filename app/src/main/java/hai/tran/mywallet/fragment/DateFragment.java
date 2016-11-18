package hai.tran.mywallet.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.samsistemas.calendarview.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hai.tran.mywallet.R;
import hai.tran.mywallet.activity.CustomDialog;
import hai.tran.mywallet.adapter.ListViewAdapter;
import hai.tran.mywallet.adapter.ListViewHolder;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.data.DataSharedPreferences;
import hai.tran.mywallet.object.Item;

public class DateFragment extends CustomFragment {
    @Bind(R.id.listView_main)
    ListView mListView;
    @Bind(R.id.nodata)
    TextView mEditText;
    @Bind(R.id.btnew)
    ImageView mImageView;
    @Bind(R.id.calendar)
    CalendarView mCalendarView;
    private List<Item> mList = null;
    private ListViewAdapter mListViewAdapter;
    DataSQLLite mDataSQLLite;
    View mRowView = null;
    private Handler mTimerHandler = null;
    private static int VISIBILITY_TIMEOUT = 2000;
    private String mDateSelect = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_fragment, container, false);
        mView = container.getRootView();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void configToolbar() {
        setTitle(getString(R.string.main_tile));
        mSqLite = new DataSQLLite(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mCalendarView = (CalendarView) view.findViewById(R.id.calendar);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        mCalendarView.setBackButtonColor(R.color.colorAccent);
        mCalendarView.setNextButtonColor(R.color.colorAccent);
        mCalendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));

        mCalendarView.setOnDateLongClickListener(new CalendarView.OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull Date selectedDate) {
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                String date = df.format(selectedDate);
//                Toast.makeText(getActivity(), date + "", Toast.LENGTH_LONG).show();
//                //  textView.setText(df.format(selectedDate));
            }
        });
        mDataSQLLite = new DataSQLLite(getActivity());
        mCalendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date selectedDate) {
                int year = selectedDate.getYear() + 1900;
                int month = selectedDate.getMonth() + 1;
                String date = year + "-" + month + "-" + selectedDate.getDate();
                mDateSelect = date;
                mList = mDataSQLLite.getDataItem(date);
                if (mList.size() > 0) {
                    setListViewShow(true);
                    mListViewAdapter.appendList(mList);
                } else {
                    setListViewShow(false);
                }


                DataSharedPreferences.getDataSharedPreferences(getContext()).setPreferencesString("DATE_SE", date);
            }
        });

        mCalendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date monthDate) {
                int year = monthDate.getYear() + 1900;
                int month = monthDate.getMonth() + 1;
                String date = year + "-" + month + "-" + monthDate.getDate();
                mDateSelect = date;
                mList = mDataSQLLite.getDataItem(date);
                if (mList.size() > 0) {
                    setListViewShow(true);
                    mListViewAdapter.appendList(mList);
                } else {
                    setListViewShow(false);
                }
            }
        });

        final Calendar calendar = Calendar.getInstance();
        mList = mDataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
        mListViewAdapter = new ListViewAdapter(getActivity(), getmCategories(), mList);
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                //======================================================================
                ListViewHolder.getViewHolder(view).linearLayoutTotal.animate().translationX(0 - ListViewHolder.getViewHolder(view).mLinearLayoutItem.getWidth());
                ListViewHolder.getViewHolder(view).mLinearLayoutItem.animate().translationX(view.getWidth() - ListViewHolder.getViewHolder(view).linearLayoutTotal.getWidth());

                //======================================================

                mRowView = view;
                mTimerHandler = new Handler();
                mTimerHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mRowView != null) {
                            ListViewHolder.getViewHolder(mRowView).linearLayoutTotal.animate().translationX(0);
                            ListViewHolder.getViewHolder(mRowView).mLinearLayoutItem.animate().translationX(view.getWidth() + ListViewHolder.getViewHolder(mRowView).mLinearLayoutItem.getWidth());
                        }
                        mRowView = null;
                        mTimerHandler = null;
                    }
                }, VISIBILITY_TIMEOUT);
                return false;
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mRowView != null) {
                    ListViewHolder.getViewHolder(mRowView).linearLayoutTotal.animate().translationX(0);
                    ListViewHolder.getViewHolder(mRowView).mLinearLayoutItem.animate().translationX(300);
                }

                mRowView = null;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(getActivity(),
                        mDateSelect);
                customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;
                customDialog.show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mDataSQLLite = new DataSQLLite(getActivity());
        String intent = DataSharedPreferences.getDataSharedPreferences(getContext()).getPreferencesString("DATE_SE");
        List<Item> list;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (intent.length() > 1)
            list = mDataSQLLite.getDataItem(intent);
        else
            list = mDataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE));
        if (list.size() > 0) {
            mListViewAdapter = new ListViewAdapter(getContext(), getmCategories(), list);
            mListView.setAdapter(mListViewAdapter);

        } else setListViewShow(false);
        mDateSelect = calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataSQLLite = new DataSQLLite(getActivity());
        String intent = DataSharedPreferences.getDataSharedPreferences(getContext()).getPreferencesString("DATE_SE");
        List<Item> list;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (intent.length() > 1)
            list = mDataSQLLite.getDataItem(intent);
        else
            list = mDataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE));
        if (list.size() > 0) {
            mListViewAdapter = new ListViewAdapter(getContext(), getmCategories(), list);
            mListView.setAdapter(mListViewAdapter);

        } else setListViewShow(false);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().onBackPressed();
                    return true;
                }
                return false;
            }
        });
    }

    public void setListViewShow(boolean b) {
        if (b) {
            mListView.setVisibility(View.VISIBLE);
            mEditText.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.GONE);
            mEditText.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Fragment frag = new AddNewFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.transition.slide_in, R.transition.slide_out);
        fragmentTransaction.replace(R.id.fragment_main, frag).commit();
    }
}
