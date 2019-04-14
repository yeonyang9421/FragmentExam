package kr.co.woobi.imyeon.fragmentexam;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapterUtil {
    @BindingAdapter("photo")
    public static void showPhoto(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @BindingAdapter("grade")
    public static void showGrade(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
