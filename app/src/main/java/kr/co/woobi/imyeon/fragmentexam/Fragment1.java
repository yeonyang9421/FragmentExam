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

public class Fragment1 extends Fragment {

    public Fragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        Button btn1= view.findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
               Fragment11 fragment11=new Fragment11();
               transaction.replace(R.id.frame_container, fragment11);
               transaction.addToBackStack(null);
               transaction.commit();
           }
       });


        return view;



    }
}
