package kr.co.woobi.imyeon.fragmentexam;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class EvaluationItemView extends LinearLayout {
    ImageView imageView;
    TextView text_userId;
    TextView text_time;
    RatingBar ratingBar;
    TextView text_comment;

    public EvaluationItemView(Context context) {
        super(context);
        init(context);
    }

    public EvaluationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private  void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_rating,this,true);

        imageView = findViewById(R.id.image_photo);
        text_userId = findViewById(R.id.text_useId);
        text_time = findViewById(R.id.text_time);
        text_comment = findViewById(R.id.text_comment);



    }

    public void setImageView(int resId) {
        this.imageView.setImageResource(resId);
    }

    public void setUserId(String id){
        text_userId.setText(id);
    }

    public void setText_comment(String comment){
        text_comment.setText(comment);
    }
}