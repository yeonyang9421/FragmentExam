package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.woobi.imyeon.fragmentexam.model.MovieDetail;
import kr.co.woobi.imyeon.fragmentexam.model.ReadCommentList;
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

    MovieDetail mMovieDetail;
    private Service mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        mIntent = getIntent();
        mMovieDetail = (MovieDetail) mIntent.getSerializableExtra("movieDetail");
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

        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(Service.class);

        queryCommentList();
    }

    private void queryCommentList() {
        mService.readCommentList(mMovieDetail.getId()).enqueue(new Callback<ReadCommentList>() {
            @Override
            public void onResponse(Call<ReadCommentList> call, Response<ReadCommentList> response) {
                if (response.body() != null) {
                    mAdapter.setItems(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ReadCommentList> call, Throwable t) {
                Toast.makeText(DisplayAllActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
            queryCommentList();
            setResult(RESULT_OK);
        }
    }
}
