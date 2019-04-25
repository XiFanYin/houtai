package com.example.demo.module.area.web;

import com.example.demo.handler.ErrorMessage;
import com.example.demo.handler.LogicException;
import com.example.demo.module.area.entity.Area;
import com.example.demo.module.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/listarea")
    private Map<String, Object> listArea() {
        HashMap map = new HashMap();
        List<Area> areas = areaService.queryArea();
        map.put("arealist", areas);
        return map;
    }


    @RequestMapping("/areabyid")
    private Map<String, Object> getAreaById(Integer areaId) {
        HashMap map = new HashMap();
        Area area = areaService.queryAreaById(areaId);
        map.put("area", area);
        if (areaId<=0){
            throw  LogicException.le(ErrorMessage.MOBILE_ALREADY_REGISTER);
        }
        return map;
    }

    @RequestMapping(value = "/insertarea", method = RequestMethod.POST)
    private Map<String, Object> insertArea(Area area) {
        HashMap map = new HashMap();
        boolean isok = areaService.insertArea(area);
        map.put("insertOK", isok);

        return map;
    }

    @RequestMapping(value = "/upareadata", method = RequestMethod.POST)
    private Map<String, Object> upAreaData(Area area) {
        HashMap map = new HashMap();
        boolean isok = areaService.updataArea(area);
        map.put("upData", isok);
        return map;
    }


    @RequestMapping(value = "/delectdata", method = RequestMethod.POST)
    private Map<String, Object> delectData(Integer areaId) {
        HashMap map = new HashMap();
        boolean isok = areaService.delectArea(areaId);
        map.put("delectOK", isok);
        return map;
    }

}
