package com.hackathondemo;

public class MyModel {
    private String areaName;
    private String qrCode;
    private MyModel en;
    private MyModel ar;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public MyModel getEn() {
        return en;
    }

    public void setEn(MyModel en) {
        this.en = en;
    }

    public MyModel getAr() {
        return ar;
    }

    public void setAr(MyModel ar) {
        this.ar = ar;
    }
}
