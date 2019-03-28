package kr.co.woobi.imyeon.fragmentexam.model;

import java.io.Serializable;

public class MovieDetail implements Serializable {

    private int id;
    private String title;
   private String date;
   private int user_rating;
   private double audience_rating;
   private double reviewer_rating;
   private double reservation_rate;
   private int reservation_grade;
   private int grade;
   private String thumb;
   private String image;
   private String photos;
   private String videos;
   private String genre;
   private int duration;
   private int audience;
   private String synopsis;
   private String director;
   private String actor;
   private int like;
   private int dislike;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    public double getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(double audience_rating) {
        this.audience_rating = audience_rating;
    }

    public double getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(double reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public double getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(double reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public void setReservation_grade(int reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
