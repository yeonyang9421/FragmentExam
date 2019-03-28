package kr.co.woobi.imyeon.fragmentexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListFragment extends Fragment {
    private static final String TAG = MovieIntroFragment.class.getSimpleName();
    private int mImage;
    private String mTitle;
    private String mContent;
    private Button mButton;
    private int position;
    private int mId;
    private ReadMovieList movieLists;

    public MovieListFragment() {
    }


    public static MovieListFragment newInstance(int image, String title, String content, int position) {
        MovieListFragment movieListFragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putString("title", title);
        args.putString("content", content);
        args.putInt("position", position);
        movieListFragment.setArguments(args);
        return movieListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt("image");
            mTitle = getArguments().getString("title");
            mContent = getArguments().getString("content");
            position = getArguments().getInt("position");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);


        //============================================================================================

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        service.listMoiveList().enqueue(new Callback<ReadMovieList>() {
            @Override
            public void onResponse(Call<ReadMovieList> call, Response<ReadMovieList> response) {
                 movieLists = response.body();
                Log.d(TAG, "onResponse: " + movieLists);

            }

            @Override
            public void onFailure(Call<ReadMovieList> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: connect failed" + t.getLocalizedMessage());
            }
        });
//============================================================================================
        final ReadMovieList readMovieList=movieLists;
        mButton = view.findViewById(R.id.button1);
        ImageView imageView = view.findViewById(R.id.image_Poster1);
        Glide.with(view)
                .load(readMovieList.getResult().get(1).getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

//        imageView.setImageResource(mImage);
        TextView textTitle = view.findViewById(R.id.text_title);
        textTitle.setText(mTitle);
        TextView textContent = view.findViewById(R.id.text_content);
        textContent.setText(mContent);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                int current = getArguments().getInt("position");
                switch (current) {
                    case 1:
                        transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image11, "군도", R.drawable.ic_15));
                        break;
                    case 2:
                        transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image22, "공조", R.drawable.ic_12));
                        break;
                    case 3:
                        transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image33, "더킹", R.drawable.ic_19));
                        break;
                    case 4:
                        transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image44, "레지던트이블", R.drawable.ic_15));
                        break;
                    case 5:
                        transaction.replace(R.id.frame_container, MovieIntroFragment.newInstance(R.drawable.image55, "럭키", R.drawable.ic_12));
                        break;
                    default:
                }
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
