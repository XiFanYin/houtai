package com.example.demo.module.area.service;

import com.example.demo.module.area.entity.Area;

import java.util.List;

/*地区service层*/
public interface AreaService {


    //查询所有地区
    List<Area> queryArea();
    //查询单条数据
    Area queryAreaById(int areaId);
    //插入一条数据
    boolean  insertArea(Area area);
    //更新一条数据
    boolean  updataArea(Area area);
    //删除一条数据
    boolean delectArea(int areaId);



}
