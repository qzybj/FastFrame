package com.frame.fastframelibrary.net.core.interfaces;

public interface IRequestListener {
    /** Called when a response is received. */
    <T> void onResponse(int requestCode,T response);
    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     */
    void onErrorResponse(int requestCode,IErrorInfo error);
}

