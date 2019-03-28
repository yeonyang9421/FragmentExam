package kr.co.woobi.imyeon.fragmentexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.model.CommentList;
import kr.co.woobi.imyeon.fragmentexam.model.ReadCommentList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    List<CommentList> mItem = new ArrayList<>();

    public RecyclerViewAdapter(List<CommentList> item) {

        this.mItem = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rating, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CommentList item = mItem.get(i);
        viewHolder.mImage.setImageResource(R.drawable.user1);
        viewHolder.mTextId.setText(item.getId()+"");
        viewHolder.mTextTime.setText(item.getTime());
        viewHolder.mTextComment.setText(item.getContents());
        viewHolder.mRatingBar.setRating((float) item.getRating());
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView mImage;
        TextView mTextId;
        TextView mTextTime;
        RatingBar mRatingBar;
        TextView mTextComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_photo);
            mTextId = itemView.findViewById(R.id.text_useId);
            mTextTime = itemView.findViewById(R.id.text_time);
            mRatingBar = itemView.findViewById(R.id.ratingBar);
            mTextComment = itemView.findViewById(R.id.text_comment);
        }
    }
}
