package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayAllActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1000;
    private TextView mTextTitle;
    private ImageView mImageRated;
    private String mNewComment;
    private double mNewNumStars;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        mTextTitle=findViewById(R.id.text_displayall_title);
        mImageRated=findViewById(R.id.image_displayall_rated);

        mRecyclerView = findViewById(R.id.recycler_displayall);
        mAdapter = new RecyclerViewAdapter(DummyData.sDummyDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mIntent = getIntent();
        mTextTitle.setText(mIntent.getStringExtra("title"));
        mImageRated.setImageResource(mIntent.getIntExtra("rated",0));
        mNewComment = mIntent.getStringExtra("comment");
        mNewNumStars = mIntent.getDoubleExtra("numStars", 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void writtingComment(View view) {
        Intent intent = new Intent(this, OneLineRatingActivity.class);
        intent.putExtra("title",mTextTitle.getText().toString());
       intent.putExtra("rated",mIntent.getIntExtra("rated",0));
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mAdapter.notifyDataSetChanged();
            setResult(RESULT_OK);
        }
    }
}
