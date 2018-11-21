package com.fds.mx.expandablelistviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ExpandableListView listView;
    private ExpandableListAdapter adapter;
    private List<String> sections;
    private HashMap<String,List<String>> childs;
    private int previousGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setListeners();
    }

    public void findViews(){
        listView = findViewById(R.id.expandable_list);
    }

    public void setListeners(){
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(getApplicationContext(),"OnGroupExpand",Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                if(expandableListView.isGroupExpanded(i)){
                    expandableListView.collapseGroup(i);
                    previousGroup = -1;
                }else {
                    expandableListView.expandGroup(i,true);
                    if(previousGroup!=-1){
                        expandableListView.collapseGroup(previousGroup);
                    }
                    previousGroup = i;
                }

                return true;
            }
        });


        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(getApplicationContext(),"OnGroupCollapsedListener",Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getApplicationContext(),"OnChildClick",Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }

    public void init(){
        loadListData();
        adapter = new ExpandableListAdapter(this,sections,childs);
        listView.setAdapter(adapter);
    }

    private void loadListData() {
        sections = new ArrayList<String>();
        childs = new HashMap<String, List<String>>();

        // Adding group header data
        sections.add("61");
        sections.add("62");
        sections.add("63");

        // Adding child data
        List<String> movies = new ArrayList<String>();
        movies.add("6100001234");
        movies.add("6100001234");
        movies.add("6100001234");
        movies.add("6100001234");
        movies.add("6100001234");

        List<String> music = new ArrayList<String>();
        music.add("6200001234");
        music.add("6200001234");
        music.add("6200001234");
        music.add("6200001234");
        music.add("6200001234");

        List<String> books = new ArrayList<String>();
        books.add("6300001234");
        books.add("6300001234");
        books.add("6300001234");
        books.add("6300001234");
        books.add("6300001234");

        // Group header, Child data
        childs.put(sections.get(0), movies);
        childs.put(sections.get(1), music);
        childs.put(sections.get(2), books);
    }





}
