package earlll.com.testdemoall.core.ui.reciverui.bean;


import com.frame.fastframelibrary.utils.jump.JumpBaseUtils;

public class MainItemBean {
    private String title;
    private String content;
    private String date;
    private String imageUrl;
    private JumpBaseUtils.IJumpInfo jumpInfo;
    private boolean isShowFlag;

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public JumpBaseUtils.IJumpInfo getJumpInfo() {
        return jumpInfo;
    }
    public void setJumpInfo(JumpBaseUtils.IJumpInfo jumpInfo) {
        this.jumpInfo = jumpInfo;
    }

    public boolean isShowFlag() {
        return isShowFlag;
    }

    public void setShowFlag(boolean showFlag) {
        isShowFlag = showFlag;
    }
}
