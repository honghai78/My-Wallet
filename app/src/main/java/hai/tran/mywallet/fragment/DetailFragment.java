package hai.tran.mywallet.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.adapter.ListViewDetailAdapter;
import hai.tran.mywallet.adapter.MonthsAdapter;
import hai.tran.mywallet.data.DataSQLLite;

/**
 * Created by hongh on 6/19/2016.
 */
public class DetailFragment extends CustomFragment {

    private String mMonth = "";
    private String mDate ="";
    private ListView mListView;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Detail);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        mView = container.getRootView();
        mListView = (ListView) view.findViewById(R.id.listView_detail);
        final Calendar calendar = Calendar.getInstance();
       final DataSQLLite dataSQLLite = new DataSQLLite(getContext());
       // int month = calendar.get(Calendar.MONTH) + 1;
        //String date = calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE);
        List list = dataSQLLite.getObjectWithMonth(mDate);
        final ListViewDetailAdapter listViewDetailAdapter = new ListViewDetailAdapter(getActivity(), list);
        mListView.setAdapter(listViewDetailAdapter);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        final String[] monthsIcon= {"jan", "feb", "mac", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        MonthsAdapter monthsAdapter = new MonthsAdapter(getContext(), monthsIcon);
      //  ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, months);
        spinner.setAdapter(monthsAdapter);
        spinner.setSelection(getMonth(months, mMonth));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int month = spinner.getSelectedItemPosition()+1;
                String date = calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE);
                List list = dataSQLLite.getObjectWithMonth(date);
                listViewDetailAdapter.appendList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void configToolbar() {
        setTitle("Detail for " + mMonth);
        mBtAdd.setVisibility(View.INVISIBLE);
    }

    private int getMonth(String []months, String month)
    {
        for(int i=0; i<months.length; i++)
        {
            if(months[i].equalsIgnoreCase(month))
                return i;
        }
        return 0;
    }
    public void setMonth(String month) {
        mMonth = month;
    }
    public void setDate(String date){mDate=date;}

}
