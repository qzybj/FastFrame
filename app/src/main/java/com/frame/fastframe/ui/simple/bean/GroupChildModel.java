package com.frame.fastframe.ui.simple.bean;

import com.frame.fastframe.view.popwinimpl.adapter.GroupAdapter;

import java.util.ArrayList;

/**
 * Created by ZhangYuanBo on 2016/5/6.
 */
public class GroupChildModel implements GroupAdapter.IGroupChildModel {
    private String name;
    private String id;
    
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

}
