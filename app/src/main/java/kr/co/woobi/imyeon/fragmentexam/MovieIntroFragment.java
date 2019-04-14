package kr.co.woobi.imyeon.fragmentexam;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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

import kr.co.woobi.imyeon.fragmentexam.databinding.FragmentMovieIntroductionBinding;
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
    FragmentMovieIntroductionBinding mBinding;
    public static final int REQUEST_CODE_MAIN = 2000;

    private RecyclerViewAdapter mAdapter;
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_introduction, container, false);

        mBinding.setDetail(mMovieDetail);

        moviePosterFragment = new MoviePosterFragment();
        mAdapter = new RecyclerViewAdapter();
        mBinding.recyclerMain.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

//        Glide.with(requireActivity())
//                .load(mMovieDetail.getImage())
//                .into(mBinding.imageSmallPoster);

//        mBinding.textTitle.setText(mMovieDetail.getTitle());

        if (mMovieDetail.getGrade() == 12) {
            mBinding.imageMovieRated.setImageResource(R.drawable.ic_12);
        } else if (mMovieDetail.getGrade() == 15) {
            mBinding.imageMovieRated.setImageResource(R.drawable.ic_15);
        } else if (mMovieDetail.getGrade() == 19) {
            mBinding.imageMovieRated.setImageResource(R.drawable.ic_19);
        } else {
            mBinding.imageMovieRated.setImageResource(R.drawable.ic_all);
        }

//        mBinding.imageMovieRated.setTag("movieGrade");

//        mBinding.textUp.setText(mMovieDetail.getLike() + "");
//        mBinding.textDown.setText(mMovieDetail.getDislike() + "");
//        mBinding.textReservationGrade.setText(mMovieDetail.getReservation_grade() + "위");
//        mBinding.textReservationRating.setText(mMovieDetail.getReservation_rate() + "%");
//       mBinding.textDate.setText(mMovieDetail.getDate() + " 개봉");
//        mBinding.textGenre.setText(mMovieDetail.getGenre());
//        mBinding.textDuration.setText(" / " + mMovieDetail.getDuration() + " 분");
//        mBinding.ratingBarReviewer.setRating((float) mMovieDetail.getReviewer_rating());
//        mBinding.textReviewerRate.setText(mMovieDetail.getReviewer_rating() + "");
//        mBinding.textAudience.setText(mMovieDetail.getAudience() + " 명");
//        mBinding.textDirector.setText(mMovieDetail.getDirector());
//        mBinding.textActor.setText(mMovieDetail.getActor());
//        mBinding.textSynopsis.setText(mMovieDetail.getSynopsis());
        mBinding.buttonThumbUp.setOnClickListener(this);
        mBinding.buttonThumbDown.setOnClickListener(this);
        mBinding.buttonComment.setOnClickListener(this);
        mBinding.buttonDisplayAll.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_thumbUp:
                mGoodCount += 1;
                mGood_flag = true;
                mBinding.textUp.setText(String.valueOf(mGoodCount));
                mBinding.buttonThumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                if (mBadCount > 0 && mBad_flag) {
                    mBadCount -= 1;
                    mBinding.textDown.setText(String.valueOf(mBadCount));
                }
                if (mBad_flag) {
                    mBad_flag = false;
                    mBinding.buttonThumbDown.setBackgroundResource(R.drawable.ic_thumb_down);
                }
                break;
            case R.id.button_thumbDown:
                mBadCount += 1;
                mBad_flag = true;
                mBinding.textDown.setText(String.valueOf(mBadCount));
                mBinding.buttonThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);

                if (mGoodCount > 0 && mGood_flag) {
                    mGoodCount -= 1;
                    mBinding.textUp.setText(String.valueOf(mGoodCount));
                }
                if (mGood_flag) {
                    mGood_flag = false;
                    mBinding.buttonThumbUp.setBackgroundResource(R.drawable.ic_thumb_up);
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
