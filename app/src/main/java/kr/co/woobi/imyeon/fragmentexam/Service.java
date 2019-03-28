package kr.co.woobi.imyeon.fragmentexam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("movie/readMovieList")
    Call <ReadMovieList> listMoiveList();


    @GET("movie/readCommentList")
    Call <List<ReadCommentList>> listComment();
}