package earlll.com.testdemoall.module.demo.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ZhangYuanBo on 2016/4/21.
 */
public class AccountDataBean implements Serializable {
    /*	  "onroadcount": 20,//未送达订单
          "needpaycount": 6,//需支付的订单
          "addresscount": 4,//地址数
          "waybillcount": 3,//物流运单数
          "favcount": 0,//储藏架物品数量
          "promotioncount": 26,//优惠券数量
          "setpaypassword": true,是否是第三方用户
          "reason": "",//不能设置支付密码原因
          "msgcount": 0 //消息中心个数
    */
    @SerializedName("onroadcount")
    private int onRoadCount;
    @SerializedName("needpaycount")
    private int needPayCount;
    @SerializedName("addresscount")
    private int addressCount;
    @SerializedName("waybillcount")
    private int wayBillCount;
    @SerializedName("favcount")
    private int favCount;
    @SerializedName("promotioncount")
    private int promotionCount;
    @SerializedName("setpaypassword")
    private boolean setPayPassword;
    @SerializedName("reason")
    private String reason;
    @SerializedName("msgcount")
    private int msgCount;
    @SerializedName("isopenticketscan")
    private int isOpenTicket;
    public int getOnRoadCount() {
        return onRoadCount;
    }
    public void setOnRoadCount(int onRoadCount) {
        this.onRoadCount = onRoadCount;
    }
    public int getNeedPayCount() {
        return needPayCount;
    }
    public void setNeedPayCount(int needPayCount) {
        this.needPayCount = needPayCount;
    }
    public int getAddressCount() {
        return addressCount;
    }
    public void setAddressCount(int addressCount) {
        this.addressCount = addressCount;
    }
    public int getWayBillCount() {
        return wayBillCount;
    }
    public void setWayBillCount(int wayBillCount) {
        this.wayBillCount = wayBillCount;
    }
    public int getFavCount() {
        return favCount;
    }
    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }
    public int getPromotionCount() {
        return promotionCount;
    }
    public void setPromotionCount(int promotionCount) {
        this.promotionCount = promotionCount;
    }

    public boolean isSetPayPassword() {
        return setPayPassword;
    }
    public void setSetPayPassword(boolean setPayPassword) {
        this.setPayPassword = setPayPassword;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public int getMsgCount() {
        return msgCount;
    }
    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }
    public int getIsOpenTicket() {
        return isOpenTicket;
    }
    public void setIsOpenTicket(int isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
    }

}