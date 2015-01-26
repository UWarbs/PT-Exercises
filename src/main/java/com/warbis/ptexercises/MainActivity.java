package com.warbis.ptexercises;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //Method for every view or one method that somehow knows which view?
    public void backView(View view){
        Intent intent = new Intent(this, DisplayBackActivity.class);
        startActivity(intent);
    }
    public void kneeView(View view){
        Intent intent = new Intent(this, DisplayKneeActivity.class);
        startActivity(intent);
    }
    public void ankleView(View view){
        Intent intent = new Intent(this, DisplayAnkleActivity.class);
        startActivity(intent);
    }
    public void neckView(View view){
        Intent intent = new Intent(this, DisplayNeckActivity.class);
        startActivity(intent);
    }
    public void shoulderView(View view){
        Intent intent = new Intent(this, DisplayShoulderActivity.class);
        startActivity(intent);
    }
}
