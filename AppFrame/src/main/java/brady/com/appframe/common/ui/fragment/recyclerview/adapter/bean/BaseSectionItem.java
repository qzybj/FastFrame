package brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class BaseSectionItem extends SectionEntity<String> {
    private boolean isMore;
    public BaseSectionItem(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public BaseSectionItem(String t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
