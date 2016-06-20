package hai.tran.mywallet.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import hai.tran.mywallet.R;
import hai.tran.mywallet.fragment.AddNewFragment;

/**
 * Created by hongh on 6/17/2016.
 */
public class AddNewActivity extends CustomActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        Fragment frag = new AddNewFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_add, frag).commit();
    }
}
