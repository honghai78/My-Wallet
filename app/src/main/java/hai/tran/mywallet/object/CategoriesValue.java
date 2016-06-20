package hai.tran.mywallet.object;

/**
 * Created by hongh on 6/19/2016.
 */
public class CategoriesValue extends Categories {
    private long mValue=0;
    private ItemType mItemType;
    public CategoriesValue(int id, String name, String iconName, long value, ItemType itemType)
    {
        super(id, name, iconName);
        mValue=value;
        mItemType = itemType;
    }

    public ItemType getmItemType() {
        return mItemType;
    }

    public long getmValue() {
        return mValue;
    }
}
