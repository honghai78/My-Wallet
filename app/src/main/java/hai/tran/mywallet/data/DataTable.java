package hai.tran.mywallet.data;

/**
 * Created by hongh on 6/18/2016.
 */
public class DataTable {

    public static final String TABLE_CATEGORY_NAME = "Category";
    public static final String TABLE_ITEM_NAME = "Item";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_ICON_NAME = "IconName";
    public static final String COL_ITEM_ID = "ID";
    public static final String COL_ITEM_TYPE = "Type";
    public static final String COL_ITEM_NOTE = "Note";
    public static final String COL_ITEM_DATE = "Date";
    public static final String COL_ITEM_VALUE = "Value";
    public static final String COL_ITEM_CAID = "CategoryID";

    public static String getCreateTableCategory() {
        return "CREATE TABLE " + TABLE_CATEGORY_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY ASC, " +
                COL_NAME + " TEXT UNIQUE NOT NULL, " +
                COL_ICON_NAME + " TEXT " +
                " )";
    }

    public static String getCreateTableItem() {
        return "CREATE TABLE " + TABLE_ITEM_NAME + " (" +
                COL_ITEM_ID + " INTEGER PRIMARY KEY ASC, " +
                COL_ITEM_TYPE + " INTEGER, " +
                COL_ITEM_NOTE + " TEXT, " +
                COL_ITEM_DATE + " TEXT, " +
                COL_ITEM_VALUE + " TEXT, " +
                COL_ITEM_CAID + " INTEGER " +
                " )";
    }
}
