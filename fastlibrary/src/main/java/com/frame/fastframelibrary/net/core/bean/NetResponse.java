package com.frame.fastframelibrary.net.core.bean;


import com.frame.fastframelibrary.net.core.interfaces.IErrorInfo;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Encapsulates a parsed response for delivery.
 * @param <T> Parsed type of this response
 */
public class NetResponse<T> implements Serializable {

    private static final long serialVersionUID = -3440061414071692254L;

    /** Parsed response, or null in the case of error. */
    @SerializedName("data")
    public  T result;

    /** Detailed error information if <code>errorCode != OK</code>. */
    public IErrorInfo error;

    @SerializedName("code")
    private int code = 0;
    @SerializedName("message")
    private String message=null;

    private NetResponse(T result) {
        this.result = result;
        this.error = null;
    }

    private NetResponse(IErrorInfo error) {
        this.result = null;
        this.error = error;
    }

    /** Returns a successful response containing the parsed result. */
    public static <T> NetResponse<T> success(T result) {
        return new NetResponse<T>(result);
    }

    /**
     * Returns a failed response containing the given error code and an optional
     * localized message displayed to the user.
     */
    public static  NetResponse error(IErrorInfo error) {
        return new NetResponse(error);
    }

    public static  IErrorInfo errorInfo(int errorCode,String message) {
        return new ErrorInfo(errorCode,message);
    }
    public IErrorInfo errorInfo() {
        return new ErrorInfo(code,message);
    }


    /**
     * Returns whether this response is considered successful.
     */
    public boolean isSuccess() {
        return error == null;
    }

    /** Callback interface for delivering parsed responses. */
    public interface Listener<T> {
        /** Called when a response is received. */
        void onResponse(T response);
    }

    /** Callback interface for delivering error responses. */
    public interface ErrorListener {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        void onErrorResponse(IErrorInfo error);
    }
}
