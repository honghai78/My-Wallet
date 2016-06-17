package hai.tran.mywallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.calendarview.widget.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hai.tran.mywallet.adapter.ListViewAdapter;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

public class DateFragment extends CustomFragment {
    CalendarView mCalendarView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_fragment, container, false);
        mView=container.getRootView();
        return view;
    }

    @Override
    public void configToolbar()
    {
        setTitle(getString(R.string.main_tile));
        setmBtAddImage(R.drawable.ic_add);
        mBtAdd.setVisibility(View.VISIBLE);
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
                Toast.makeText(getActivity(), date+"",Toast.LENGTH_LONG).show();
              //  textView.setText(df.format(selectedDate));
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

        DayView dayView = mCalendarView.findViewByDate(new Date(System.currentTimeMillis()));
        if (null != dayView)
            Toast.makeText(getActivity(), "Today is: " + dayView.getText().toString() + "/" + mCalendarView.getCurrentMonth() + "/" + mCalendarView.getCurrentYear(), Toast.LENGTH_SHORT).show();

        listView = (ListView) view.findViewById(R.id.listView_main);
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, ItemType.EXPENSE, "Note 1", "22/11/2015", 20000, 2));
        list.add(new Item(2, ItemType.INCOME, "Note 2", "23/11/2015", 25000, 2));
        list.add(new Item(2, ItemType.INCOME, "Note 2", "22/1/2015", 27000, 2));
        list.add(new Item(2, ItemType.EXPENSE, "Note 2", "28/11/2015", 29000, 2));
        list.add(new Item(1, ItemType.EXPENSE, "Note 1", "22/11/2015", 20000, 2));
        list.add(new Item(2, ItemType.INCOME, "Note 2", "23/11/2015", 25000, 2));
        list.add(new Item(2, ItemType.INCOME, "Note 2", "22/1/2015", 27000, 2));
        list.add(new Item(2, ItemType.EXPENSE, "Note 2", "28/11/2015", 29000, 2));
        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), list);
        listView.setAdapter(listViewAdapter);
    }

}
