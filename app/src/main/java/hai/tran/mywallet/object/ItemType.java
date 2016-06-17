package hai.tran.mywallet.object;

/**
 * Created by hai.tran on 6/17/2016.
 */

public enum ItemType {
    INCOME(0), EXPENSE(1);

    private int mValue;

    ItemType(int type) {
        mValue = type;
    }

    public int getValue() {
        return mValue;
    }
}
