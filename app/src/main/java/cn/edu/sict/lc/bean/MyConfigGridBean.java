package cn.edu.sict.lc.bean;

public class MyConfigGridBean {
    int imgSrc_config;
    String title_config;

    public MyConfigGridBean() {
    }

    public MyConfigGridBean(int imgSrc_config, String title_config) {
        this.imgSrc_config = imgSrc_config;
        this.title_config = title_config;
    }

    public int getImgSrc_config() {
        return imgSrc_config;
    }

    public void setImgSrc_config(int imgSrc_config) {
        this.imgSrc_config = imgSrc_config;
    }

    public String getTitle_config() {
        return title_config;
    }

    public void setTitle_config(String title_config) {
        this.title_config = title_config;
    }
}
