package netcore.network;


import com.frame.fastframelibrary.utils.LogUtils;

/**
 * Created by tongdesheng on 16/1/30.
 */
public class HttpErrorListener {

    public void onError(Object object) {
        LogUtils.e(object.toString());
    }
}
