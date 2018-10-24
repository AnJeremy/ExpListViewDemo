package cn.syxg.explistviewdemo;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */

public class ParentEntity {

    private int id;
    private String name;
    private List<ChildEntity> children;

    public static class ChildEntity{
        private int id;
        private String name;


        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }
}
