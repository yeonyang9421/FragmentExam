package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.CommentList;
import kr.co.woobi.imyeon.fragmentexam.model.MovieDetail;
import kr.co.woobi.imyeon.fragmentexam.model.MovieInfo;
import kr.co.woobi.imyeon.fragmentexam.model.ReadCommentList;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovie;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovieList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayAllActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1000;
    private TextView mTextTitle;
    private ImageView mImageViewGrade;
    private String mNewComment;
    private double mNewNumStars;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private Intent mIntent;

    private int mId = 1;
    MovieDetail mMovieDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();

        mIntent = getIntent();
        mId=mIntent.getIntExtra("id",0);
        mMovieDetail= (MovieDetail) mIntent.getSerializableExtra("movieDetail");
//        mTextTitle.setText(mIntent.getStringExtra("title"));
//        mImageRated.setImageResource(mIntent.getIntExtra("movieGrade", 0));
//        mNewNumStars = mIntent.getDoubleExtra("numStars", 0);
        mTextTitle = findViewById(R.id.text_displayall_title);
        mImageViewGrade = findViewById(R.id.image_displayall_rated);
        mRecyclerView = findViewById(R.id.recycler_displayall);
        mTextTitle.setText(mMovieDetail.getTitle());
        if (mMovieDetail.getGrade() == 12) {
            mImageViewGrade.setImageResource(R.drawable.ic_12);
        } else if (mMovieDetail.getGrade() == 15) {
            mImageViewGrade.setImageResource(R.drawable.ic_15);
        } else if (mMovieDetail.getGrade() == 19) {
            mImageViewGrade.setImageResource(R.drawable.ic_19);
        } else {
            mImageViewGrade.setImageResource(R.drawable.ic_all);
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        service.readCommentList(mId, 5, 5).enqueue(new Callback<List<ReadCommentList>>() {
            @Override
            public void onResponse(Call<List<ReadCommentList>> call, Response<List<ReadCommentList>> response) {

                if (response.body() != null) {
                    List<CommentList> commentLists = response.body().get(0).getResult();
                    Toast.makeText(DisplayAllActivity.this, "성공" + response.body().get(0).getResult(), Toast.LENGTH_SHORT).show();

                    mAdapter = new RecyclerViewAdapter(commentLists);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ReadCommentList>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("commentList", "onResponse: connect failed" + t.getLocalizedMessage());
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void writtingComment(View view) {
        Intent intent = new Intent(this, OneLineRatingActivity.class);
        intent.putExtra("movieDetail", mMovieDetail);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mAdapter.notifyDataSetChanged();
            setResult(RESULT_OK);
        }
    }
}
