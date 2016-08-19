package brady.com.appframe.common.ui.fragment.recyclerview.adapter.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class BaseSectionItem extends SectionEntity<String> {
    private boolean isMroe;
    public BaseSectionItem(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMroe = isMroe;
    }

    public BaseSectionItem(String t) {
        super(t);
    }

    public boolean isMroe() {
        return isMroe;
    }

    public void setMroe(boolean mroe) {
        isMroe = mroe;
    }
}
