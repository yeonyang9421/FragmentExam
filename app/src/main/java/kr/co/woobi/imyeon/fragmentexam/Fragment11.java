package kr.co.woobi.imyeon.fragmentexam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

import static android.app.Activity.RESULT_OK;

public class Fragment11 extends Fragment implements View.OnClickListener, MainActivity.OnBackKeyPressedListener {
    public static final int REQUEST_CODE_MAIN = 2000;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private Button mButton_ThumbUp;
    private Button mButton_ThumbDown;
    private Button mButton_comment;
    private Button mButton_displayAll;
    private TextView mTextViewUp;
    private TextView mTextViewDown;

    boolean mGood_flag = false;
    boolean mBad_flag = false;
    int mGoodCount = 0;
    int mBadCount = 0;
    String mNewComment;
    double mNewNumStars;

    public Fragment11() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment11, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_main);
        mAdapter = new RecyclerViewAdapter(DummyData.sDummyDatas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mTextViewUp = view.findViewById(R.id.text_up);
        mTextViewDown = view.findViewById(R.id.text_down);
        mButton_ThumbUp = view.findViewById(R.id.button_thumbUp);
        mButton_ThumbDown = view.findViewById(R.id.button_thumbDown);
        mButton_comment = view.findViewById(R.id.button_comment);
        mButton_displayAll = view.findViewById(R.id.button_displayAll);
        mButton_ThumbUp.setOnClickListener(this);
        mButton_ThumbDown.setOnClickListener(this);
        mButton_comment.setOnClickListener(this);
        mButton_displayAll.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_thumbUp:
                mGoodCount += 1;
                mGood_flag = true;
                mTextViewUp.setText(String.valueOf(mGoodCount));
                mButton_ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                if (mBadCount > 0 && mBad_flag) {
                    mBadCount -= 1;
                    mTextViewDown.setText(String.valueOf(mBadCount));
                }
                if (mBad_flag) {
                    mBad_flag = false;
                    mButton_ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down);
                }
                break;
            case R.id.button_thumbDown:
                mBadCount += 1;
                mBad_flag = true;
                mTextViewDown.setText(String.valueOf(mBadCount));
                mButton_ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);

                if (mGoodCount > 0 && mGood_flag) {
                    mGoodCount -= 1;
                    mTextViewUp.setText(String.valueOf(mGoodCount));
                }
                if (mGood_flag) {
                    mGood_flag = false;
                    mButton_ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up);
                }
                break;
            case R.id.button_comment:
                Intent intent = new Intent(getActivity(), OneLineRatingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAIN);
                Toast.makeText(getActivity(), "작성하기 버튼을 눌러졌습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_displayAll:
                Intent intent1 = new Intent(getActivity(), DisplayAllActivity.class);
                intent1.putExtra("mComment", mNewComment);
                intent1.putExtra("numStars", mNewNumStars);
                startActivityForResult(intent1, REQUEST_CODE_MAIN);
                Toast.makeText(getActivity(), "모두보기 버튼을 눌러졌습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MAIN && resultCode == RESULT_OK) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }

}
