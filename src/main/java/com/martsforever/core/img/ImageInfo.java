package com.martsforever.core.img;

public class ImageInfo {
    private String name;
    private String size;
    private String midSize;
    private String smallSize;
    private String type;
    private String dataUrl;
    private String path;
    private String midPath;
    private String smallPath;

    public String getMidSize() {
        return midSize;
    }

    public void setMidSize(String midSize) {
        this.midSize = midSize;
    }

    public String getSmallSize() {
        return smallSize;
    }

    public void setSmallSize(String smallSize) {
        this.smallSize = smallSize;
    }

    public String getMidPath() {
        return midPath;
    }

    public void setMidPath(String midPath) {
        this.midPath = midPath;
    }

    public String getSmallPath() {
        return smallPath;
    }

    public void setSmallPath(String smallPath) {
        this.smallPath = smallPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
