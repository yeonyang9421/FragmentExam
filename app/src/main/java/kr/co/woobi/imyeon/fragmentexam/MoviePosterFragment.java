package kr.co.woobi.imyeon.fragmentexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.MovieDetail;
import kr.co.woobi.imyeon.fragmentexam.model.MovieInfo;
import kr.co.woobi.imyeon.fragmentexam.model.ReadMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePosterFragment extends Fragment {
    private static final String TAG = MovieIntroFragment.class.getSimpleName();
    private MovieInfo mMovieInfo;
    private int mId;

    public static MoviePosterFragment newInstance(MovieInfo movieInfo) {
        MoviePosterFragment movieListFragment = new MoviePosterFragment();
        Bundle args = new Bundle();
        args.putSerializable("movieInfo", movieInfo);
        movieListFragment.setArguments(args);
        return movieListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieInfo = (MovieInfo) getArguments().getSerializable("movieInfo");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);

        TextView textTitle = view.findViewById(R.id.text_title);
        textTitle.setText(mMovieInfo.getTitle());
        mId = mMovieInfo.getId();
        TextView textContent = view.findViewById(R.id.text_content);

        String content = String.format("예매율 %.2f | %d세 | 관람가 D%d", mMovieInfo.getReservationRate(), mMovieInfo.getGrade(), -1);
        textContent.setText(content);

        ImageView posterImageView = view.findViewById(R.id.image_Poster1);
        // TODO : Glide
        Glide.with(view)
                .load(mMovieInfo.getImage())
                .into(posterImageView);

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO : 영화 상세정보

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                Service service = retrofit.create(Service.class);
                service.readMovie(mId).enqueue(new Callback<ReadMovie>() {
                    @Override
                    public void onResponse(Call<ReadMovie> call, Response<ReadMovie> response) {

                        if (response.body() != null) {
                            List<MovieDetail> movieDetail = response.body().getResult();

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.frame_container, MovieIntroFragment.newInstance(movieDetail.get(0)));
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }
                    }

                    @Override
                    public void onFailure(Call<ReadMovie> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onResponse: connect failed" + t.getLocalizedMessage());
                    }
                });
            }
        });
        return view;
    }
}
