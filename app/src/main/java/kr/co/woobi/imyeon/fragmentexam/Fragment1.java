package kr.co.woobi.imyeon.fragmentexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment1 extends Fragment {
    private int mImage;
    private String mTitle;
    private String mContent;
    private Button mButton;
    private int position;

    public Fragment1() {
    }

    public static Fragment1 newInstance(int image, String title, String content, int position) {
        Fragment1 fragment1 = new Fragment1();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putString("title", title);
        args.putString("content", content);
        args.putInt("position", position);
        fragment1.setArguments(args);
        return fragment1;
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

        mButton = view.findViewById(R.id.button1);
        ImageView imageView = view.findViewById(R.id.image_Poster1);
        imageView.setImageResource(mImage);
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
