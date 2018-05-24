package com.martsforever.core.test;

public class HgStoreImage {

    private String storeCode;       //门店编码
    private String path;            //保存路径

    public HgStoreImage(String storeCode, String path) {
        this.storeCode = storeCode;
        this.path = path;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "HgStoreImage{" +
                "storeCode='" + storeCode + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
