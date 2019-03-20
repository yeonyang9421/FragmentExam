package kr.co.woobi.imyeon.fragmentexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    List<ListItem> mItem = new ArrayList<>();

    public RecyclerViewAdapter(List<ListItem> item) {
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
        ListItem item = mItem.get(i);
        viewHolder.mImage.setImageResource(item.getResId());
        viewHolder.mTextId.setText(item.getId());
        viewHolder.mTextTime.setText(item.getTime());
        viewHolder.mTextComment.setText(item.getComment());
        viewHolder.mRatingBar.setRating((float) item.getNumStar());

        Log.d(TAG, "onBindViewHolder: " + item.getNumStar());
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
