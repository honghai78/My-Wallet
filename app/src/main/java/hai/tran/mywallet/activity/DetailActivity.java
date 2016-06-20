package hai.tran.mywallet.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import hai.tran.mywallet.R;
import hai.tran.mywallet.fragment.DetailFragment;

public class DetailActivity extends CustomActivity {

    private ListView mListView;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Fragment frag = new DetailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_detail, frag).commit();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}
