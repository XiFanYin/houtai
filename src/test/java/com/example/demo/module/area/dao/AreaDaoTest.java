package com.example.demo.module.area.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryArea() {


//        Area areas = areaDao.queryAreaById(1);

        System.out.print(areaDao.toString());
    }

    @Test
    public void queryAreaById() {
    }

    @Test
    public void insertArea() {
    }

    @Test
    public void updataArea() {
    }

    @Test
    public void delectArea() {
    }
}