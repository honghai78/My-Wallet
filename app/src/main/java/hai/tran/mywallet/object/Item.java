package hai.tran.mywallet.object;

import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by hai.tran on 6/17/2016.
 */
public class Item implements Serializable {

    private int mID;
    private ItemType mType;
    private String mNote;
    private String mDate;
    private long mValue;
    private int mCategoriesID;
    public static int INCOME = 0, EXPENSE = 1;

    public Item(int ID, ItemType type, String note, String date, long value, int categoriesID) {
        this.mID = ID;
        this.mType = type;
        this.mNote = note;
        this.mDate = date;
        this.mValue = value;
        this.mCategoriesID = categoriesID;
    }

    public int getmCategoriesID() {
        return mCategoriesID;
    }

    public int getmID() {
        return mID;
    }

    public long getmValue() {
        return mValue;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmNote() {
        return mNote;
    }

    public int getmType() {
        return mType.getValue();
    }

    public void setmCategoriesID(int mCategoriesID) {
        this.mCategoriesID = mCategoriesID;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public void setmType(ItemType mType) {
        this.mType = mType;
    }

    public void setmValue(long mValue) {
        this.mValue = mValue;
    }
}

