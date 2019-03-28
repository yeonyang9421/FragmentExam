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

public class MoviePosterFragment extends Fragment {
    private static final String TAG = MovieIntroFragment.class.getSimpleName();
    private MovieInfo mMovieInfo;

    public MoviePosterFragment() {
    }


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


        //============================================================================================

        TextView textTitle = view.findViewById(R.id.text_title);
        textTitle.setText(mMovieInfo.getTitle());
        TextView textContent = view.findViewById(R.id.text_content);

        String content = String.format("예매율 %f %d세 관람가 D%d", mMovieInfo.getReservationRate(), mMovieInfo.getGrade(), -1);
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

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image11, "군도", R.drawable.ic_15));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
