package kr.co.woobi.imyeon.fragmentexam;

public class EventItem {
    private int image;

    public int getPosition() {
        return image;
    }

    public void setPosition(int position) {
        this.image = position;
    }

    public EventItem(int position) {
        this.image = position;
    }
}

