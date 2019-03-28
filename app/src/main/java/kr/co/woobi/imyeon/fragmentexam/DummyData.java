package kr.co.woobi.imyeon.fragmentexam;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.CommentList;
import kr.co.woobi.imyeon.fragmentexam.model.CreateComment;
import kr.co.woobi.imyeon.fragmentexam.model.MovieInfo;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovieList;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DummyData {

    public static List<CommentList> sDummyDatas = new ArrayList<>();

    static {


        sDummyDatas.add(new ListItem(1, "10분전", "재밌다", R.drawable.user1,5));
        sDummyDatas.add(new ListItem(2, "20분전", "재미없다", R.drawable.user1,4));
    }
}
