package hai.tran.mywallet.object;

/**
 * Created by hongh on 6/19/2016.
 */
public class DataMonth {
    private long mValueTotal = 0, mValueIncome = 0, mValueExpense = 0;

    public DataMonth(long tt, long in, long ex) {
        mValueExpense = ex;
        mValueIncome = in;
        mValueTotal = tt;
    }

    public long getmValueExpense() {
        return mValueExpense;
    }

    public long getmValueIncome() {
        return mValueIncome;
    }

    public long getmValueTotal() {
        return mValueTotal;
    }
}
