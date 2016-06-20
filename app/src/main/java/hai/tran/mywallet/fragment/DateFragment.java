package hai.tran.mywallet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsistemas.calendarview.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hai.tran.mywallet.activity.AddNewActivity;
import hai.tran.mywallet.activity.CustomDialog;
import hai.tran.mywallet.R;
import hai.tran.mywallet.adapter.ListViewAdapter;
import hai.tran.mywallet.adapter.ListViewHolder;
import hai.tran.mywallet.data.DataSQLLite;
import hai.tran.mywallet.data.DataSharedPreferences;
import hai.tran.mywallet.object.Item;

public class DateFragment extends CustomFragment {
    CalendarView mCalendarView;
    public static ListView listView;
    private TextView editText;
    private List<Item> list;
    private ListViewAdapter mListViewAdapter;
    DataSQLLite dataSQLLite;
    View mListView = null;
    private ImageView imageView;
    private Handler mTimerHandler = null;
    private static int VISIBILITY_TIMEOUT = 3000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_fragment, container, false);
        mView = container.getRootView();
        return view;
    }

    @Override
    public void configToolbar() {
        setTitle(getString(R.string.main_tile));
        setmBtAddImage(R.drawable.ic_add);
        mBtAdd.setVisibility(View.VISIBLE);
        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNewActivity.class);
                getContext().startActivity(intent);
            }
        });
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
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String date = df.format(selectedDate);
                Toast.makeText(getActivity(), date + "", Toast.LENGTH_LONG).show();
                //  textView.setText(df.format(selectedDate));
            }
        });
        dataSQLLite = new DataSQLLite(getActivity());
        mCalendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date selectedDate) {
                int year = selectedDate.getYear() + 1900;
                int month = selectedDate.getMonth() + 1;
                String date = year + "-" + month + "-" + selectedDate.getDate();
                list = dataSQLLite.getDataItem(date);
                mListViewAdapter.appendList(list);
                DataSharedPreferences.getDataSharedPreferences(getContext()).setPreferencesString("DATE_SE", date);
            }
        });

        mCalendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date monthDate) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String date = df.format(monthDate);
                Toast.makeText(getActivity(), date.toString(), Toast.LENGTH_LONG).show();
            }
        });

        final Calendar calendar = Calendar.getInstance();

        listView = (ListView) view.findViewById(R.id.listView_main);


        list = dataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
        if (list != null) {
            mListViewAdapter = new ListViewAdapter(getActivity(), getmCategories(), list);
            listView.setAdapter(mListViewAdapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListView != null)
                        ListViewHolder.getViewHolder(mListView).showBtAction(false);
                    mListView = view;
                    ListViewHolder.getViewHolder(view).showBtAction(true);
                    mTimerHandler = new Handler();
                    mTimerHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ListViewHolder.getViewHolder(mListView).showBtAction(false);
                            mListView=null;
                            mTimerHandler = null;
                        }
                    }, VISIBILITY_TIMEOUT);
                    return false;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListView != null && mListView!=view) {
                        ListViewHolder.getViewHolder(mListView).showBtAction(false);
                        mListView = null;
                    }
                }
            });

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if (mListView != null)
                        ListViewHolder.getViewHolder(mListView).showBtAction(false);
                    mListView = null;
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });
        } else {
            editText = (TextView) view.findViewById(R.id.nodata);
            editText.setText(getString(R.string.no_data));
            editText.setVisibility(View.VISIBLE);
        }

        imageView = (ImageView) view.findViewById(R.id.btnew);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(getActivity(),
                        calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
                customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;
                customDialog.show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        dataSQLLite = new DataSQLLite(getActivity());
        String intent = DataSharedPreferences.getDataSharedPreferences(getContext()).getPreferencesString("DATE_SE");
        List<Item> list;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (intent.length() > 1)
            list = dataSQLLite.getDataItem(intent);
        else
            list = dataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE));
        mListViewAdapter.appendList(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        dataSQLLite = new DataSQLLite(getActivity());
        String intent = DataSharedPreferences.getDataSharedPreferences(getContext()).getPreferencesString("DATE_SE");
        List<Item> list;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (intent.length() > 1)
            list = dataSQLLite.getDataItem(intent);
        else
            list = dataSQLLite.getDataItem(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE));
        mListViewAdapter.appendList(list);
    }

}
