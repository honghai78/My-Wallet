package hai.tran.mywallet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

import hai.tran.mywallet.R;
import hai.tran.mywallet.adapter.ListViewDetailAdapter;
import hai.tran.mywallet.data.DataSQLLite;

/**
 * Created by hongh on 6/19/2016.
 */
public class DetailFragment extends CustomFragment {

    String mMonth="";
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        mView = container.getRootView();
        mListView = (ListView) view.findViewById(R.id.listView_detail);
        Calendar calendar = Calendar.getInstance();
        DataSQLLite dataSQLLite = new DataSQLLite(getContext());
        int month = calendar.get(Calendar.MONTH)+1;
        String date = calendar.get(Calendar.YEAR)+"-"+month+"-"+calendar.get(Calendar.DATE);
        List list = dataSQLLite.getObjectWithMonth(date);
        ListViewDetailAdapter listViewDetailAdapter = new ListViewDetailAdapter(getActivity(), list);
        mListView.setAdapter(listViewDetailAdapter);
        return view;
    }

    @Override
    public void configToolbar() {
        setTitle("Detail for "+mMonth);
        mBtAdd.setVisibility(View.INVISIBLE);
    }
    public void setMonth(String month)
    {
        mMonth=month;
    }

}
