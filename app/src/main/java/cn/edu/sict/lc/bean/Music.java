package cn.edu.sict.lc.bean;

public class Music {
    String name,singer,path;

    public Music() {
    }

    public Music(String name, String singer, String path) {
        this.name = name;
        this.singer = singer;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}