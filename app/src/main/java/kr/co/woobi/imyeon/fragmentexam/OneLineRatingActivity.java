package kr.co.woobi.imyeon.fragmentexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class OneLineRatingActivity extends AppCompatActivity implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {
    public static final int REQUEST_CODE = 1000;
    private RatingBar mRatingBar;
    private EditText mEditTextComment;
    private Button mButtonSave;
    private Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_line_rating);
        mRatingBar = findViewById(R.id.ratingBar);
        mEditTextComment = findViewById(R.id.edit_comment);
        mButtonSave = findViewById(R.id.button_save);
        mButtonCancel = findViewById(R.id.button_cancel);
        mButtonSave.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);

        mRatingBar.setOnRatingBarChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                String comment = mEditTextComment.getText().toString();
                double numStarts = mRatingBar.getRating();

                DummyData.sDummyDatas.add(0, new ListItem("11", "1분전", comment, R.drawable.user1, numStarts));
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        final int numStarts2 = mRatingBar.getNumStars();
        Toast.makeText(this, mRatingBar.getRating() + "", Toast.LENGTH_SHORT).show();
    }
}
