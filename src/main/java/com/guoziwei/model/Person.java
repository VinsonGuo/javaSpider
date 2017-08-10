package com.guoziwei.model;

/**
 * Created by dell on 2017/8/9.
 */
public class Person {

    /**
     * alt : https://movie.douban.com/celebrity/1000525/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/39105.jpg","large":"https://img3.doubanio.com/img/celebrity/large/39105.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/39105.jpg"}
     * name : 吴京
     * id : 1000525
     */

    private String alt;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "alt='" + alt + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
