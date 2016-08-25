package netcore.api;


public interface ApiDelegate<T> {
     /** Ruequest success calback */
     void onSuccess(T rspData);
     /** Ruequest failed calback */
     void onError(TipBean rspData);
}