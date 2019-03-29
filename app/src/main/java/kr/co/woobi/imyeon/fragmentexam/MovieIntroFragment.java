package kr.co.woobi.imyeon.fragmentexam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.CommentList;
import kr.co.woobi.imyeon.fragmentexam.model.MovieDetail;
import kr.co.woobi.imyeon.fragmentexam.model.ReadCommentList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class MovieIntroFragment extends Fragment implements View.OnClickListener, MainActivity.OnBackKeyPressedListener {
    public static final int REQUEST_CODE_MAIN = 2000;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private Button mButton_ThumbUp,mButton_ThumbDown,mButton_comment,mButton_displayAll;
    private TextView mTextViewTitle, mTextViewUp,mTextViewDown, mTextSynop, mTextReservationGrade, mTextReservationRating, mTextDate;
    private TextView mTextGenre, mTextDuration, mTextRateReviewer, mTextAudience, mTextDirector, mTextActor;
    private ImageView mImageViewSmallPoster,mImageViewGrade;

    private boolean mGood_flag = false;
    private boolean mBad_flag = false;
    private int mGoodCount = 0;
    private int mBadCount = 0;

    private MovieDetail mMovieDetail;
    private MoviePosterFragment moviePosterFragment;
    private List<CommentList> commentLists;
    private Service mService;

    public static MovieIntroFragment newInstance(MovieDetail movieDetail) {
        MovieIntroFragment movieIntroFragment = new MovieIntroFragment();
        Bundle args = new Bundle();
        args.putSerializable("movieDetail", movieDetail);
        movieIntroFragment.setArguments(args);
        return movieIntroFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieDetail = (MovieDetail) getArguments().getSerializable("movieDetail");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(Service.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGoodCount = mMovieDetail.getLike();
        mBadCount = mMovieDetail.getDislike();

        queryCommentList();
    }

    private void queryCommentList() {
        mService.readCommentList(mMovieDetail.getId(), 3).enqueue(new Callback<ReadCommentList>() {
            @Override
            public void onResponse(Call<ReadCommentList> call, Response<ReadCommentList> response) {
                if (response.body() != null) {
                    mAdapter.setItems(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ReadCommentList> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_introduction, container, false);
        moviePosterFragment = new MoviePosterFragment();
        mRecyclerView = view.findViewById(R.id.recycler_main);
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mImageViewSmallPoster = view.findViewById(R.id.image_small_poster);
        Glide.with(view)
                .load(mMovieDetail.getImage())
                .into(mImageViewSmallPoster);

        mTextViewTitle = view.findViewById(R.id.text_title);
        mTextViewTitle.setText(mMovieDetail.getTitle());

        mImageViewGrade = view.findViewById(R.id.image_movie_rated);
        if (mMovieDetail.getGrade() == 12) {
            mImageViewGrade.setImageResource(R.drawable.ic_12);
        } else if (mMovieDetail.getGrade() == 15) {
            mImageViewGrade.setImageResource(R.drawable.ic_15);
        } else if (mMovieDetail.getGrade() == 19) {
            mImageViewGrade.setImageResource(R.drawable.ic_19);
        } else {
            mImageViewGrade.setImageResource(R.drawable.ic_all);
        }

        mImageViewGrade.setTag("movieGrade");

        mTextViewUp = view.findViewById(R.id.text_up);
        mTextViewUp.setText(mMovieDetail.getLike() + "");
        mTextViewDown = view.findViewById(R.id.text_down);
        mTextViewDown.setText(mMovieDetail.getDislike() + "");

        mButton_ThumbUp = view.findViewById(R.id.button_thumbUp);
        mButton_ThumbDown = view.findViewById(R.id.button_thumbDown);

        mTextReservationGrade = view.findViewById(R.id.text_reservation_grade);
        mTextReservationGrade.setText(mMovieDetail.getReservation_grade() + "위");

        mTextReservationRating = view.findViewById(R.id.text_reservation_rating);
        mTextReservationRating.setText(mMovieDetail.getReservation_rate() + "%");

        mTextDate = view.findViewById(R.id.text_date);
        mTextDate.setText(mMovieDetail.getDate() + " 개봉");

        mTextGenre = view.findViewById(R.id.text_genre);
        mTextGenre.setText(mMovieDetail.getGenre());

        mTextDuration = view.findViewById(R.id.text_duration);
        mTextDuration.setText(" / " + mMovieDetail.getDuration() + " 분");

        RatingBar ratingBar = view.findViewById(R.id.ratingBar_reviewer);
        ratingBar.setRating((float) mMovieDetail.getReviewer_rating());

        mTextRateReviewer = view.findViewById(R.id.text_reviewer_rate);
        mTextRateReviewer.setText(mMovieDetail.getReviewer_rating() + "");

        mTextAudience = view.findViewById(R.id.text_audience);
        mTextAudience.setText(mMovieDetail.getAudience() + " 명");

        mTextDirector = view.findViewById(R.id.text_director);
        mTextDirector.setText(mMovieDetail.getDirector());
        mTextActor = view.findViewById(R.id.text_actor);
        mTextActor.setText(mMovieDetail.getActor());

        mTextSynop = view.findViewById(R.id.text_synopsis);
        mTextSynop.setText(mMovieDetail.getSynopsis());

        mButton_comment = view.findViewById(R.id.button_comment);
        mButton_displayAll = view.findViewById(R.id.button_displayAll);
        mButton_ThumbUp.setOnClickListener(this);
        mButton_ThumbDown.setOnClickListener(this);
        mButton_comment.setOnClickListener(this);
        mButton_displayAll.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_thumbUp:
//                mGoodCount=mMovieDetail.getLike();
                mGoodCount += 1;
                mGood_flag = true;
                mTextViewUp.setText(String.valueOf(mGoodCount));
                mButton_ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                if (mBadCount > 0 && mBad_flag) {
                    mBadCount -= 1;
                    mTextViewDown.setText(String.valueOf(mBadCount));
                }
                if (mBad_flag) {
                    mBad_flag = false;
                    mButton_ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down);
                }
                break;
            case R.id.button_thumbDown:
                mBadCount += 1;
                mBad_flag = true;
                mTextViewDown.setText(String.valueOf(mBadCount));
                mButton_ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);

                if (mGoodCount > 0 && mGood_flag) {
                    mGoodCount -= 1;
                    mTextViewUp.setText(String.valueOf(mGoodCount));
                }
                if (mGood_flag) {
                    mGood_flag = false;
                    mButton_ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up);
                }
                break;
            case R.id.button_comment:
                Intent intent = new Intent(getActivity(), OneLineRatingActivity.class);
                intent.putExtra("movieDetail", mMovieDetail);
                startActivityForResult(intent, REQUEST_CODE_MAIN);
                Toast.makeText(getActivity(), "작성하기 버튼을 눌러졌습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_displayAll:
                Intent intent1 = new Intent(getActivity(), DisplayAllActivity.class);
                intent1.putExtra("movieDetail", mMovieDetail);
                startActivityForResult(intent1, REQUEST_CODE_MAIN);
                Toast.makeText(getActivity(), "모두보기 버튼을 눌러졌습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MAIN && resultCode == RESULT_OK) {
            queryCommentList();
        }
    }

    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }
}
