package kr.co.woobi.imyeon.fragmentexam;

public class IncreaseLikeDislike {
    private String id;
    private int likeyn;
    private  int dislikeyn;

    public IncreaseLikeDislike(String id, int likeyn, int dislikeyn) {
        this.id = id;
        this.likeyn = likeyn;
        this.dislikeyn = dislikeyn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikeyn() {
        return likeyn;
    }

    public void setLikeyn(int likeyn) {
        this.likeyn = likeyn;
    }

    public int getDislikeyn() {
        return dislikeyn;
    }

    public void setDislikeyn(int dislikeyn) {
        this.dislikeyn = dislikeyn;
    }
}
