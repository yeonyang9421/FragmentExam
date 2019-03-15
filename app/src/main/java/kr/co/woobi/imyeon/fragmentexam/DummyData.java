package kr.co.woobi.imyeon.fragmentexam;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    public static List<ListItem> sDummyDatas = new ArrayList<>();

    static {
        sDummyDatas.add(new ListItem("kim", "10분전", "재밌다", R.drawable.user1,5));
        sDummyDatas.add(new ListItem("yeon", "20분전", "재미없다", R.drawable.user1,4));
    }
}
