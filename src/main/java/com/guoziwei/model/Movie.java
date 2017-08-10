package com.guoziwei.model;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/8/9.
 */
public class Movie {

    /**
     * rating : {"max":10,"average":7.5,"stars":"40","min":0}
     * genres : ["动作"]
     * title : 战狼2
     * casts : [{"alt":"https://movie.douban.com/celebrity/1000525/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/39105.jpg","large":"https://img3.doubanio.com/img/celebrity/large/39105.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/39105.jpg"},"name":"吴京","id":"1000525"},{"alt":"https://movie.douban.com/celebrity/1100321/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1415801312.29.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1415801312.29.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1415801312.29.jpg"},"name":"弗兰克·格里罗","id":"1100321"},{"alt":"https://movie.douban.com/celebrity/1274840/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1401440361.14.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1401440361.14.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1401440361.14.jpg"},"name":"吴刚","id":"1274840"}]
     * collect_count : 264209
     * original_title : 战狼2
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1000525/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/39105.jpg","large":"https://img3.doubanio.com/img/celebrity/large/39105.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/39105.jpg"},"name":"吴京","id":"1000525"}]
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2485983612.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2485983612.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2485983612.webp"}
     * alt : https://movie.douban.com/subject/26363254/
     * id : 26363254
     */

    private Rating rating;
    private String title;
    private int collect_count;
    private String subtype;
    private String year;
    private String alt;
    private String id;
    private java.util.List<String> genres;
    private java.util.List<Person> casts;
    private java.util.List<Person> directors;
    private Map<String,String> images;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Person> getCasts() {
        return casts;
    }

    public void setCasts(List<Person> casts) {
        this.casts = casts;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", collect_count=" + collect_count +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }
}
