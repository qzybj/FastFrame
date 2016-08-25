package netcore.api;

/**
 * Created by tongdesheng on 16/3/5.
 * 提示信息
 */
public class TipBean {
    /**CODE - 请求成功*/
    public final static int SUCCEED = 200;
    /**CODE - 网络错误*/
    public final static int NET_ERROR = 500;

    public TipBean(int tipCode, String tipMsg) {
        code = tipCode;
        message = tipMsg;
    }

    public int code;

    public String message;

}
