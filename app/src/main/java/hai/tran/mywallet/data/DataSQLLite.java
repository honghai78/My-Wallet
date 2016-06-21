package hai.tran.mywallet.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hai.tran.mywallet.object.Categories;
import hai.tran.mywallet.object.CategoriesValue;
import hai.tran.mywallet.object.DataMonth;
import hai.tran.mywallet.object.Item;
import hai.tran.mywallet.object.ItemType;

/**
 * Created by hai.tran on 6/17/2016.
 */

public class DataSQLLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "walletsql.db";
    private Context mContext;
    private ItemType ITEM_TYPE = ItemType.INCOME;
    public DataSQLLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataTable.getCreateTableCategory());
        db.execSQL(DataTable.getCreateTableItem());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Categories> getDataCategory() {
        ArrayList<Categories> stringDataSearch = new ArrayList<>();
        Cursor c = getReadableDatabase().query(DataTable.TABLE_CATEGORY_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        try {
            while (!c.isAfterLast()) {
                stringDataSearch.add(new Categories(c.getInt(0), c.getString(1), c.getString(2)));
                c.moveToNext();
            }
        } catch (CursorIndexOutOfBoundsException cursorIndexOutOfBoundsException) {
            Toast.makeText(null, "Read data error!", Toast.LENGTH_LONG).show();
            c.close();
        } finally {
            c.close();
        }
        return stringDataSearch;
    }

    public boolean pushCategories(Categories categories) {
        ContentValues values = new ContentValues();
        values.put(DataTable.COL_ID, categories.getId());
        values.put(DataTable.COL_NAME, categories.getName());
        values.put(DataTable.COL_ICON_NAME, categories.getIconName());
        return getWritableDatabase().insert(DataTable.TABLE_CATEGORY_NAME, null, values) != -1;
    }

    public boolean pushItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DataTable.COL_ITEM_ID, item.getmID());
        values.put(DataTable.COL_ITEM_TYPE, item.getmType());
        values.put(DataTable.COL_ITEM_NOTE, item.getmNote());
        values.put(DataTable.COL_ITEM_DATE, item.getmDate());
        values.put(DataTable.COL_ITEM_VALUE, item.getmValue());
        values.put(DataTable.COL_ITEM_CAID, item.getmCategoriesID());
        return getWritableDatabase().insert(DataTable.TABLE_ITEM_NAME, null, values) != -1;
    }

    public List<Item> getDataItem() {
        ArrayList<Item> stringDataSearch = new ArrayList<>();
        Cursor c = getReadableDatabase().query(DataTable.TABLE_ITEM_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        try {
            while (!c.isAfterLast()) {
                int type = c.getInt(1);
                ItemType itemType = ItemType.INCOME;
                if (type == ItemType.EXPENSE.getValue())
                    itemType = ItemType.EXPENSE;
                stringDataSearch.add(new Item(c.getInt(0), itemType, c.getString(2), c.getString(3), c.getLong(4), c.getInt(5)));
                c.moveToNext();
            }
        } catch (CursorIndexOutOfBoundsException cursorIndexOutOfBoundsException) {
            Toast.makeText(null, "Read data error!", Toast.LENGTH_LONG).show();
            c.close();
        } finally {
            c.close();
        }
        return stringDataSearch;
    }

    public List<Item> getDataItem(String date) {
        ArrayList<Item> list = new ArrayList<>();
        Cursor c = getReadableDatabase().query(DataTable.TABLE_ITEM_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        try {
            while (!c.isAfterLast()) {
                int type = c.getInt(1);
                ItemType itemType = ItemType.INCOME;
                if (type == ItemType.EXPENSE.getValue())
                    itemType = ItemType.EXPENSE;
                if(c.getString(3).equals(date))
                list.add(new Item(c.getInt(0), itemType, c.getString(2), c.getString(3), c.getLong(4), c.getInt(5)));
                c.moveToNext();
            }
        } catch (CursorIndexOutOfBoundsException cursorIndexOutOfBoundsException) {
            Toast.makeText(null, "Read data error!", Toast.LENGTH_LONG).show();
            c.close();
        } finally {
            c.close();
        }
//        ArrayList<Item> stringDataSearch = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getmDate().equalsIgnoreCase(date))
//                stringDataSearch.add(list.get(i));
//        }
//        return stringDataSearch;
        return list;
    }

    public int deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(DataTable.TABLE_ITEM_NAME, DataTable.COL_ITEM_ID + " = ?",
                new String[]{id + ""});
        db.close();
        return i;
    }

    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataTable.COL_ITEM_TYPE, item.getmType());
        values.put(DataTable.COL_ITEM_NOTE, item.getmNote());
        values.put(DataTable.COL_ITEM_DATE, item.getmDate());
        values.put(DataTable.COL_ITEM_VALUE, item.getmValue());
        values.put(DataTable.COL_ITEM_CAID, item.getmCategoriesID());

        return db.update(DataTable.TABLE_ITEM_NAME, values, DataTable.COL_ITEM_ID + " = ?",
                new String[]{String.valueOf(item.getmID())});
    }

    private String getMonth(String date)
    {
       return  date.trim().split("-")[1];
    }

    private String getYear(String date)
    {
        return date.trim().split("-")[0];
    }
    public DataMonth getDataValueMonth(String date) {
        long tt = 0, in = 0, ex = 0;
        String month, year;
        int temp = 1+Integer.parseInt(date.trim().split("-")[1]);
        month = temp+"";
        year = getYear(date);
        List<Item> itemList = getDataItem();
        for(int i=0; i<itemList.size(); i++)
        {
            Item item = itemList.get(i);
            if(getMonth(item.getmDate()).equals(month) && getYear(item.getmDate()).equals(year))
            {
                if(item.getmType() == ItemType.INCOME.getValue())
                    in = in+item.getmValue();
                else
                    ex = ex+item.getmValue();
            }
        }
        tt = in - ex;
        return new DataMonth(tt, in, ex);
    }
    public List getObjectWithMonth(String date)
    {
        int tt, in, ex;
        String month, year;
        int temp = 1+Integer.parseInt(date.trim().split("-")[1]);
        month = temp+"";
        year = getYear(date);
        List<Item> tem = null;
        List list = new ArrayList();
        List<Categories> categoriList = getDataCategory();
        List itemList = getDataItem();

        for(int i=0; i<categoriList.size(); i++)
        {
           tem = getItemWithCategories(categoriList.get(i).getId());
            if(tem.size()>0)
            {
                Categories categories = categoriList.get(i);
                list.add(new CategoriesValue(categories.getId(),categories.getName(), categories.getIconName(), getValue(tem), ITEM_TYPE));
                for(int j=0; j<tem.size(); j++)
                    list.add(tem.get(j));
            }
        }
        return list;
    }

    public long getValue(List<Item> itemList)
    {
        long  in=0, ex=0;
        for(int i=0; i<itemList.size(); i++)
        {
            Item item = itemList.get(i);
                if(item.getmType() == ItemType.INCOME.getValue()) {
                    in = in + item.getmValue();
                }
                else {
                    ex = ex + item.getmValue();
                }

        }
        if(ex>0)
            ITEM_TYPE = ItemType.EXPENSE;
        else ITEM_TYPE = ItemType.INCOME;
        return in - ex;
    }
    public List getItemWithCategories(int idCategories)
    {
        ArrayList<Item> stringDataSearch = new ArrayList<>();
        Cursor c = getReadableDatabase().query(DataTable.TABLE_ITEM_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        try {
            while (!c.isAfterLast()) {
                int type = c.getInt(1);
                ItemType itemType = ItemType.INCOME;
                if (type == ItemType.EXPENSE.getValue())
                    itemType = ItemType.EXPENSE;
                int idC = c.getInt(5);
                if(idC==idCategories)
                stringDataSearch.add(new Item(c.getInt(0), itemType, c.getString(2), c.getString(3), c.getLong(4), idC));
                c.moveToNext();
            }
        } catch (CursorIndexOutOfBoundsException cursorIndexOutOfBoundsException) {
            Toast.makeText(null, "Read data error!", Toast.LENGTH_LONG).show();
            c.close();
        } finally {
            c.close();
        }
        return stringDataSearch;
    }
}
