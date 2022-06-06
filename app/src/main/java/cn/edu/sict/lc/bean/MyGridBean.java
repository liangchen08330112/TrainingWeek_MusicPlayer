package cn.edu.sict.lc.bean;

public class MyGridBean {
    int imgSrc;
    String title;

    public MyGridBean() {
    }

    public MyGridBean(int imgSrc, String title) {
        this.imgSrc = imgSrc;
        this.title = title;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
