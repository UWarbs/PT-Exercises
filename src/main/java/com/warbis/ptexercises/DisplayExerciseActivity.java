package com.warbis.ptexercises;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DisplayExerciseActivity extends Activity {

    ViewPager mViewPager;
    SlideShowPagerAdapter mSlideShowPagerAdapter;
//    public static final List<MainActivity.Exercise> neckExercises = new ArrayList<MainActivity.Exercise>();



    int[] mResources = {
            R.drawable.evil_smile,
            R.drawable.ic_launcher
    };
    int[] uris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercise);

        //Initialize Neck Exercises
        //Maybe this activity should stay the same, and in the DisplayEXerciseActivity
        //It should check the child that was clicked on, and add all the data then.
//        int[] extensorUris = {R.drawable.ic_launcher, R.drawable.evil_smile};
//        Exercise extensorStretch = new Exercise("Extensor Stretch", "This stretches the extensor muscles in your neck", extensorUris);
//        Exercise levatorStretch = new Exercise("Levator Scapula Stretch", "Stretches your Levator scapula, feels good man", extensorUris);
//        neckExercises.add(extensorStretch);
//        neckExercises.add(levatorStretch);

        //See if I can push image URI into mResources array from here
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exercise");
        String description = intent.getStringExtra("description");
        uris = intent.getIntArrayExtra("uris");

        TextView exerciseTitle = (TextView) findViewById(R.id.exerciseName);
        TextView exerciseDescription = (TextView) findViewById(R.id.exerciseDescription);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSlideShowPagerAdapter = new SlideShowPagerAdapter(this);
        mViewPager.setAdapter(mSlideShowPagerAdapter);


        exerciseTitle.setText(exerciseName);
        exerciseDescription.setText(description);
        System.out.println("Exercise title");
        System.out.println(exerciseName);


    }

    class SlideShowPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public SlideShowPagerAdapter(Context context){
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount(){
            return uris.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object){
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(uris[position]);

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((LinearLayout) object);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
