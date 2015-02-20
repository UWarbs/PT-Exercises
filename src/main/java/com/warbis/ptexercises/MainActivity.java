package com.warbis.ptexercises;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }

    public void createData(){
        String[] bodyParts = this.getResources().getStringArray(R.array.bodyParts);
        String[] neckExercises = this.getResources().getStringArray(R.array.neckExercises);

        //SETUP EXERCISE BASED ON CHILD NAME
//            switch(children){
//                case "Extensor Stretch":
//                    String[] resArray = getResources().getStringArray(R.array.extensorStretch);
//                    String exerciseDescription = resArray[0];
//                    int[] exerciseUris = getResources().getIntArray(R.array.uri);
//
//            }

        for (int j = 0; j < bodyParts.length; j++){
            Group group = new Group(bodyParts[j]);

            if (j == 0){
                for(int i = 0; i < neckExercises.length; i++){
                    group.children.add(neckExercises[i]);
                }
//                for(int i = 0; i < neckExercises.size(); i++){
//                    Exercise tempEx = neckExercises.get(i);
//                    String name = tempEx.getName();
//                    String desc = tempEx.getDescription();
//                    int[] uris = tempEx.getUris();
//                    group.children.add(name);
//                }
            }else {
                for (int i = 0; i < 3; i++) {
                    group.children.add("Sub Item " + i);
                }
            }
            groups.append(j, group);
        }
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

    public class Exercise{
        private String name;
        private String description;
        private int[] imageUris;

        public Exercise(String name, String description, int[] uris){
            this.name = name;
            this.description = description;
            this.imageUris = uris;
        }

        public String getName(){
            return name;
        }

        public String getDescription(){
            return description;
        }

        public int[] getUris(){
            return imageUris;
        }
    }

    public class Group{
        public String name;
        public final List<String> children = new ArrayList<String>();

        public Group(String name){
            this.name = name;
        }

    }

    public class MyExpandableListAdapter extends BaseExpandableListAdapter{
        private final SparseArray<Group> groups;
        public LayoutInflater inflater;
        public Activity activity;

        public MyExpandableListAdapter(Activity act, SparseArray<Group> groups){
            activity = act;
            this.groups = groups;
            inflater = act.getLayoutInflater();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition){
            return groups.get(groupPosition).children.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition){
            return 0;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
            final String children = (String) getChild(groupPosition, childPosition);
            TextView text = null;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.listrow_details, null);
            }
            text = (TextView) convertView.findViewById(R.id.textView1);
            text.setText(children);


            String exerciseDescription = "description";
            int[] exerciseUris = {R.drawable.evil_smile, R.drawable.ic_launcher, R.drawable.evil_smile};
            final Exercise exercise = new Exercise(children, exerciseDescription, exerciseUris );

            //END SETUP

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DisplayExerciseActivity.class);
                    intent.putExtra("exercise", exercise.getName());
                    intent.putExtra("description", exercise.getDescription());
                    intent.putExtra("uris", exercise.getUris());

                    startActivity(intent);
                    System.out.println("EX NAME");
                    System.out.println(exercise.getName());
                    //This is what pops up that little bubble showing what was clicked.
                    Toast.makeText(activity, exercise.getName(), Toast.LENGTH_SHORT).show();
                }
            });
          return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition){
            return groups.get(groupPosition).children.size();
        }

        @Override
        public Object getGroup(int groupPosition){
            return groups.get(groupPosition);
        }

        @Override
        public int getGroupCount(){
            return groups.size();
        }

        @Override
        public void onGroupCollapsed(int groupPosition){
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition){
            super.onGroupExpanded(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition){
            return 0;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = inflater.inflate(R.layout.listrow_group, null);
            }

            Group group = (Group) getGroup(groupPosition);
            ((CheckedTextView) convertView).setText(group.name);
            ((CheckedTextView) convertView).setChecked(isExpanded);
            return convertView;
        }

        @Override
        public boolean hasStableIds(){
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition){
            return false;
        }
    }

}
