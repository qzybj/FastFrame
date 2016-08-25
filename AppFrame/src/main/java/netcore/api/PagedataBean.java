package netcore.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tongdesheng on 16/2/3.
 */
public class PagedataBean {

    /**
     * 当前页码
     */
    @SerializedName("page_no")
    public int pageNo;

    /**
     * 每页的条数
     */
    @SerializedName("page_size")
    public int pageSize;

    /**
     * 总页数
     */
    @SerializedName("total_page_num")
    public int totalPageNum;

}
