package com.fds.mx.expandablelistviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> sections;
    private HashMap<String, List<String>> childs;

    public ExpandableListAdapter(Context mContext, List<String> sections, HashMap<String, List<String>> childs) {
        this.mContext = mContext;
        this.sections = sections;
        this.childs = childs;
    }

    @Override
    public int getGroupCount() {
        return sections.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childs.get(sections.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return sections.get(i);
    }

    @Override
    public Object getChild(int section, int child) {
        return childs.get(sections.get(section)).get(child);
    }

    @Override
    public long getGroupId(int id) {
        return id;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int sectionPositition, boolean b, View view, ViewGroup viewGroup) {
        String section = sections.get(sectionPositition);

        if(view==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.group_item,null);
        }
        TextView tvSection = view.findViewById(R.id.card_item_type);
        tvSection.setText(section);

        return view;
    }

    @Override
    public View getChildView(int sectionPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        String child =(String)getChild(sectionPosition,childPosition);
        if(view==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item,null);
        }

        TextView tvChild = view.findViewById(R.id.item);
        tvChild.setText(child);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
