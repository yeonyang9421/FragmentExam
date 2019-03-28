package kr.co.woobi.imyeon.fragmentexam;

public class ReadMovie {
    private String id;
    private String name;
    private String title;
    private String date;
    private double user_rating;
    private double reservation_rate;
    private int resetvation_grade;
    private int grade;
    private String thumb;
    private String image;
    private String genre;
    private String synopsis;
    private String director;
    private String actor;
    private int lite;
    private int dislike;


    public ReadMovie(String id, String name, String title, String date, double user_rating, double reservation_rate, int resetvation_grade, int grade, String thumb, String image, String genre, String synopsis, String director, String actor, int lite, int dislike) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.date = date;
        this.user_rating = user_rating;
        this.reservation_rate = reservation_rate;
        this.resetvation_grade = resetvation_grade;
        this.grade = grade;
        this.thumb = thumb;
        this.image = image;
        this.genre = genre;
        this.synopsis = synopsis;
        this.director = director;
        this.actor = actor;
        this.lite = lite;
        this.dislike = dislike;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(double user_rating) {
        this.user_rating = user_rating;
    }

    public double getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(double reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public int getResetvation_grade() {
        return resetvation_grade;
    }

    public void setResetvation_grade(int resetvation_grade) {
        this.resetvation_grade = resetvation_grade;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public int getLite() {
        return lite;
    }

    public void setLite(int lite) {
        this.lite = lite;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
