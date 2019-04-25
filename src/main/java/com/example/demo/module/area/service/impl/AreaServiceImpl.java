package com.example.demo.module.area.service.impl;

import com.example.demo.handler.ErrorMessage;
import com.example.demo.handler.LogicException;
import com.example.demo.module.area.dao.AreaDao;
import com.example.demo.module.area.entity.Area;
import com.example.demo.module.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> queryArea() {


        return areaDao.queryArea();
    }

    @Override
    public Area queryAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }
    //开始事务管理
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insertArea(Area area) {

        if (area.getAreaName()!=null&&!"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int i = areaDao.insertArea(area);
//                测试事务管理是否管用
//                int a = 10/0;
                if (i>0){
                    return true;
                }else {
                    throw LogicException.le(ErrorMessage.INSERT_ERROR_FAIL);
                }
            }catch (Exception e){
                throw LogicException.le(ErrorMessage.INSERT_ERROR_FAIL);
            }
        }else {
            throw LogicException.le(ErrorMessage.INSERT_ERROR_EMPTY);
        }

    }

    @Override
    public boolean updataArea(Area area) {
    if (area.getAreaId()!=null&&area.getAreaId()>0){
        area.setLastEditTime(new Date());
        try {
            int i = areaDao.updataArea(area);
            if (i>0){
                return true;
            }else {
                throw new  RuntimeException("更新区域信息失败~");
            }
        }catch (Exception e){
            throw new  RuntimeException("更新区域信息失败!"+e.getMessage());
        }

    }else {
        throw new  RuntimeException("更新区域信息不能为空");
    }

    }

    @Override
    public boolean delectArea(int areaId) {
        if (areaId>0){
            try {
                int i = areaDao.delectArea(areaId);
                if (i>0){
                    return true;
                }else {
                    throw new  RuntimeException("插入区域信息失败");
                }
            }catch (Exception e){
                throw new  RuntimeException("插入区域信息失败");
            }

        }else {
            throw new  RuntimeException("插入区域信息不能为空");
        }

    }
}
