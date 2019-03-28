package kr.co.woobi.imyeon.fragmentexam.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    private int id;
    private String title;
    @SerializedName("title_eng")
    private String titleEng;
    private String date;
    @SerializedName("user_rating")
    private double userRating;
    @SerializedName("audience_rating")
    private double audienceRating;
    @SerializedName("reviewer_rating")
    private double reviewerRating;
    @SerializedName("reservation_rate")
    private double reservationRate;
    @SerializedName("reservation_grade")
    private int reservationGrade;
    private int grade;
    private String thumb;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getAudienceRating() {
        return audienceRating;
    }

    public void setAudienceRating(double audienceRating) {
        this.audienceRating = audienceRating;
    }

    public double getReviewerRating() {
        return reviewerRating;
    }

    public void setReviewerRating(double reviewerRating) {
        this.reviewerRating = reviewerRating;
    }

    public double getReservationRate() {
        return reservationRate;
    }

    public void setReservationRate(double reservationRate) {
        this.reservationRate = reservationRate;
    }

    public int getReservationGrade() {
        return reservationGrade;
    }

    public void setReservationGrade(int reservationGrade) {
        this.reservationGrade = reservationGrade;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", titleEng='").append(titleEng).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", userRating=").append(userRating);
        sb.append(", audienceRating=").append(audienceRating);
        sb.append(", reviewerRating=").append(reviewerRating);
        sb.append(", reservationRate=").append(reservationRate);
        sb.append(", reservationGrade=").append(reservationGrade);
        sb.append(", grade=").append(grade);
        sb.append(", thumb='").append(thumb).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
