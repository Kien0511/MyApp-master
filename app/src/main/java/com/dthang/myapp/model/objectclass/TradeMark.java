package com.dthang.myapp.model.objectclass;

public class TradeMark {
    private int trademarkID;
    private String trademarkName,trademakImage;

    public TradeMark(int trademarkID, String trademarkName, String trademakImage) {
        this.trademarkID = trademarkID;
        this.trademarkName = trademarkName;
        this.trademakImage = trademakImage;
    }

    public int getTrademarkID() {
        return trademarkID;
    }

    public void setTrademarkID(int trademarkID) {
        this.trademarkID = trademarkID;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getTrademakImage() {
        return trademakImage;
    }

    public void setTrademakImage(String trademakImage) {
        this.trademakImage = trademakImage;
    }
}
