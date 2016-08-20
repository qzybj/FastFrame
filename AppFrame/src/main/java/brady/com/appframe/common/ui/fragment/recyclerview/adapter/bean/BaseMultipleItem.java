package brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseMultipleItem extends MultiItemEntity {
    public static final int STYLE_1 = 1;
    public static final int STYLE_2 = 2;

    private String name;
    public BaseMultipleItem(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
