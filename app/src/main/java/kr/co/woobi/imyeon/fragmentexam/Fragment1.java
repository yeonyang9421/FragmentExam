package kr.co.woobi.imyeon.fragmentexam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Fragment1 extends Fragment {
    private int mImage;
    private String mTitle;
    private String mContent;

    public Fragment1() {
    }

    public static Fragment1 newInstance(int image, String title, String content) {
        Fragment1 fragment1 = new Fragment1();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putString("title", title);
        args.putString("content", content);
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
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        Button btn1 = view.findViewById(R.id.button1);
        ImageView imageView = view.findViewById(R.id.image_Poster1);
        imageView.setImageResource(mImage);
        TextView textTitle = view.findViewById(R.id.text_title);
        textTitle.setText(mTitle);
        TextView textContent = view.findViewById(R.id.text_content);
        textContent.setText(mContent);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment11 fragment11 = new Fragment11();
                transaction.replace(R.id.frame_container, fragment11);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
