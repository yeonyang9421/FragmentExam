package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.woobi.imyeon.fragmentexam.databinding.ActivityOneLineRatingBinding;
import kr.co.woobi.imyeon.fragmentexam.model.CreateComment;
import kr.co.woobi.imyeon.fragmentexam.model.MovieDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OneLineRatingActivity extends AppCompatActivity implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {
    ActivityOneLineRatingBinding mBinding;
    private MovieIntroFragment mMovieIntroFragemnet;
    private int mId;
    MovieDetail mMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_one_line_rating);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_one_line_rating);

        Intent intent = getIntent();
        mMovieDetail = (MovieDetail) intent.getSerializableExtra("movieDetail");
        mBinding.imageOnelineRated.setImageResource(intent.getIntExtra("rated", 0));
        mBinding.textOnelineTitle.setText(mMovieDetail.getTitle());
        if (mMovieDetail.getGrade() == 12) {
            mBinding.imageOnelineRated.setImageResource(R.drawable.ic_12);
        } else if (mMovieDetail.getGrade() == 15) {
            mBinding.imageOnelineRated.setImageResource(R.drawable.ic_15);
        } else if (mMovieDetail.getGrade() == 19) {
            mBinding.imageOnelineRated.setImageResource(R.drawable.ic_19);
        } else {
            mBinding.imageOnelineRated.setImageResource(R.drawable.ic_all);
        }

        mId = intent.getIntExtra("id", 0);
        mBinding.buttonSave.setOnClickListener(this);
        mBinding.buttonCancel.setOnClickListener(this);
        mBinding.ratingBar.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_save:
                String comment = mBinding.editComment.getText().toString();
                double numStarts = mBinding.ratingBar.getRating();
                String writer = mBinding.editWriter.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time = format.format(new Date());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Service service = retrofit.create(Service.class);
                service.createComment(mMovieDetail.getId(), writer, time, numStarts, comment).enqueue(new Callback<CreateComment>() {
                    @Override
                    public void onResponse(Call<CreateComment> call, Response<CreateComment> response) {

                        if (response.body() != null) {
                            CreateComment status = response.body();
                            if (status.getStatus() == 200) {
                                Toast.makeText(OneLineRatingActivity.this, "성공!" + status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(OneLineRatingActivity.this, "실패!" + status, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateComment> call, Throwable t) {
                        Toast.makeText(v.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(this, mBinding.ratingBar.getRating() + "", Toast.LENGTH_SHORT).show();
    }
}
