package kr.co.woobi.imyeon.fragmentexam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/readMovieList")
    Call <ReadMovieList> listMoiveList();


    @GET("movie/readCommentList")
    Call <List<ReadCommentList>> listComment();

    @GET("createComment")
    Call <Object> createComment(@Query("id") int id,
                                @Query("writer") String writer,
                                @Query("time") String time,
                                @Query("rating") double rating,
                                @Query("contents") String contents);
}