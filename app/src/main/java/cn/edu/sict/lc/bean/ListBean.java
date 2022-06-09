package cn.edu.sict.lc.bean;

public class ListBean {
    int imgSrc_list;
    String title_list;

    public ListBean() {
    }

    public ListBean(int imgSrc_list, String title_list) {
        this.imgSrc_list = imgSrc_list;
        this.title_list = title_list;
    }

    public int getImgSrc_list() {
        return imgSrc_list;
    }

    public void setImgSrc_list(int imgSrc_list) {
        this.imgSrc_list = imgSrc_list;
    }

    public String getTitle_list() {
        return title_list;
    }

    public void setTitle_list(String title_list) {
        this.title_list = title_list;
    }
}
