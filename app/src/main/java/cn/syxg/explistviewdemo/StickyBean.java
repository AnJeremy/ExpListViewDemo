package cn.syxg.explistviewdemo;

/**
 * Created by Administrator on 2018/10/12.
 */

public class StickyBean {

    String autor;
    String name;
    String sticky;

    public StickyBean(String sticky, String name, String autor) {
        this.autor = autor;
        this.name = name;
        this.sticky = sticky;
    }


    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getAutor() {
        return autor;
    }

    public String getName() {
        return name;
    }

    public String getSticky() {
        return sticky;
    }
}
