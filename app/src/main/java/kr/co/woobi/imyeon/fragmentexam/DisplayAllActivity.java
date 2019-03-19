package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class DisplayAllActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1000;
    String mNewComment;
    double mNewNumStars;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        mRecyclerView = findViewById(R.id.recycler_displayall);
        mAdapter = new RecyclerViewAdapter(DummyData.sDummyDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        Intent intent = getIntent();
        mNewComment = intent.getStringExtra("comment");
        mNewNumStars = intent.getDoubleExtra("numStars",0);
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
