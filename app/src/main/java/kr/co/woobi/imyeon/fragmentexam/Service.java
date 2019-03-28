package kr.co.woobi.imyeon.fragmentexam;

import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.CreateComment;
import kr.co.woobi.imyeon.fragmentexam.model.IncreaseLikeDislike;
import kr.co.woobi.imyeon.fragmentexam.model.IncreaseRecommend;
import kr.co.woobi.imyeon.fragmentexam.model.ReadCommentList;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovie;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/readMovieList")
    Call<ReadMovieList> listMoiveList();


    @GET("movie/readMovie")
    Call<ReadMovie> readMovie(@Query("id") int id);


    @GET("movie/readCommentList")
    Call<List<ReadCommentList>> readCommentList(@Query("id") int id,
                                                @Query("startIndex") int startIndex,
                                                @Query("length") int length);

    @GET("movie/createComment")
    Call<CreateComment> createComment(@Query("id") int id,
                                      @Query("writer") String writer,
                                      @Query("time") String time,
                                      @Query("rating") double rating,
                                      @Query("contents") String contents);

    @GET("movie/increaseRecommend")
    Call<IncreaseRecommend> increaseRecommend(@Query("review_id") String revier_id,
                                              @Query("writer") String writer);

    @GET("movie/increaseLikeDisLike")
    Call<IncreaseLikeDislike> increaseLikeDislike(@Query("id") int id,
                                                  @Query("likeyn") String likeyn,
                                                  @Query("likeyn") String dislikeyn);
}