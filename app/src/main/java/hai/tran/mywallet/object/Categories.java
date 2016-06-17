package hai.tran.mywallet.object;

import java.io.Serializable;

/**
 * Created by hai.tran on 6/17/2016.
 */
public class Categories implements Serializable{
    /*ID: Category ID.
    Name: Category name.
    IconName: Category icon name.*/
    private int mID;
    private String mName, mIconName;

    public Categories(int id, String name, String iconName) {
        this.mID = id;
        this.mName = name;
        this.mIconName = iconName;
    }

    public String getIconName() {
        return mIconName;
    }

    public int getId() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public void setIconName(String iconName) {
        this.mIconName = iconName;
    }

    public void setId(int id) {
        this.mID = id;
    }

    public void setName(String name) {
        this.mName = name;
    }

}
