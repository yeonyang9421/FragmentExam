package kr.co.woobi.imyeon.fragmentexam;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.woobi.imyeon.fragmentexam.databinding.ItemRatingBinding;
import kr.co.woobi.imyeon.fragmentexam.model.CommentList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    List<CommentList> mItem = new ArrayList<>();

    public RecyclerViewAdapter() {
    }

    public void setItems(List<CommentList> items) {
        mItem = items;
        notifyDataSetChanged();
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
        viewHolder.binding.setComment(item);
/*        viewHolder.binding.imagePhoto.setImageResource(item.getWriter_image());
        viewHolder.binding.textUseId.setText(item.getId()+"");
        viewHolder.binding.textTime.setText(item.getTime());
        viewHolder.binding.textComment.setText(item.getContents());
        viewHolder.binding.ratingBar.setRating((float) item.getRating());*/
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRatingBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
