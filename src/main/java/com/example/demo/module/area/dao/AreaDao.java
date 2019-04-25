package com.example.demo.module.area.dao;

import com.example.demo.module.area.entity.Area;

import java.util.List;

public interface AreaDao {

    //查询所有地区
    List<Area> queryArea();
    //查询单条数据
    Area queryAreaById(int areaId);
    //插入一条数据
    int  insertArea(Area area);
    //更新一条数据
    int  updataArea(Area area);
    //删除一条数据
    int delectArea(int areaId);


}
