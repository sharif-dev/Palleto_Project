package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IntroduceActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private LinearLayout mlinearLayout;
    SlideAdapter slideAdapter;
    private TextView[] mDots;
    private Button nextbtn ;
    private Button backbtn;
    private int mCurrentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        mlinearLayout = findViewById(R.id.linearlayout);
        viewPager =findViewById(R.id.viewpager);
        nextbtn = findViewById(R.id.next_button);
        backbtn = findViewById(R.id.back_button);
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);
        addDotsindicator(0);
        viewPager.addOnPageChangeListener(viewlistener);

        nextbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mCurrentpage == mDots.length-1)
                {

                    Intent intent2 = new Intent(getApplicationContext() , MainActivity.class);
                     startActivity(intent2);
                     onStop();
                }
                viewPager.setCurrentItem(mCurrentpage+1);

            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentpage-1);
            }
        });
    }




    public void addDotsindicator(int position){

        mDots = new TextView[4];
        mlinearLayout.removeAllViews();
        for (int i = 0; i<mDots.length;i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.whitetransparent));

            mlinearLayout.addView(mDots[i]);
        }


        if(mDots.length >0){

            mDots[position].setTextColor(Color.rgb(255,255,255));
        }
    }


    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
                addDotsindicator(position);
                mCurrentpage = position;
                if( position ==0){
                    backbtn.setEnabled(false);
                    nextbtn.setEnabled(true);
                    backbtn.setVisibility(View.INVISIBLE);

                    nextbtn.setText("Next");
                    backbtn.setText("");
                }
           else if (position == mDots.length-1){

                    backbtn.setEnabled(true);
                    nextbtn.setEnabled(true);
                    backbtn.setVisibility(View.VISIBLE);

                    nextbtn.setText("Finish");
                    backbtn.setText("Back");

                    nextbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(IntroduceActivity.this , MainActivity.class));
                            finish();
                        }
                    });


                }
           else{


                    backbtn.setEnabled(true);
                    nextbtn.setEnabled(true);
                    backbtn.setVisibility(View.VISIBLE);

                    nextbtn.setText("Next");
                    backbtn.setText("Back");
                }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
