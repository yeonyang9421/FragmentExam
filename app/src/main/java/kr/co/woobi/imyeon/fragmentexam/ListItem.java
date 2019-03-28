package kr.co.woobi.imyeon.fragmentexam;

import kr.co.woobi.imyeon.fragmentexam.model.CommentList;

public class ListItem extends CommentList {
    private int id;
    private String time;
    private String comment;
    private double numStar;
    private int resId;

    public ListItem(int id, String time, String comment, int resId, double numStar) {
        this.id = id;
        this.time = time;
        this.comment = comment;
        this.resId = resId;
        this.numStar = numStar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getNumStar() {
        return numStar;
    }

    public void setNumStar(double numStar) {
        this.numStar = numStar;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

}

