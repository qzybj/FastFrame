package com.frame.fastframe.ui.simple.bean;

import com.frame.fastframe.view.popwinimpl.adapter.GroupAdapter;

import java.util.ArrayList;

/**
 * Created by ZhangYuanBo on 2016/5/6.
 */
public class GroupModel implements GroupAdapter.IGroupModel {
    private String name;
    private String id;
    private ArrayList<GroupAdapter.IGroupChildModel> childs;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public ArrayList<GroupAdapter.IGroupChildModel> getChilds() {
        return childs;
    }
    public void setChilds(ArrayList<GroupAdapter.IGroupChildModel> childs) {
        this.childs = childs;
    }
}
