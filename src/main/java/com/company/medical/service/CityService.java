package com.company.medical.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.company.medical.domain.City;
import com.company.medical.entity.CityEntity;
import com.company.medical.mapper.CityMapper;
import com.company.medical.mapper.MedicalPolicyMapper;
import com.company.medical.model.CityModel;
import com.company.medical.util.Msg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;
    @Autowired
    MedicalPolicyMapper medicalPolicyMapper;
    public PageInfo<CityModel> getCityWithPage(Integer pn, Integer size, String name) {
        if (pn == null && size == null) {
            pn=1;
            size=0;
        }
        PageHelper.startPage(pn, size);
        List<CityModel> list = cityMapper.getAllCity(name);
        PageInfo<CityModel> info = new PageInfo<>(list,5);
        return info;
    }

    public Msg getCityById(Integer id) {
        City city = cityMapper.getCityById(id);

        if(city == null) {
            return Msg.fail().mess("没有找到");
        }
        return Msg.success().data("city", city);
    }

    /**
     * 添加一个城市
     * @param cityNumber
     * @return
     */
    public Msg saveCity(Integer cityNumber) {
        City city = new City();
        Date d = new Date();
        city.setCityNumber(cityNumber);
        city.setCreatetime(d);
        city.setUpdatetime(d);
        CityEntity ce = new CityEntity();
        BeanUtils.copyProperties(city,ce);//对象拷贝
        int i = cityMapper.saveCity(ce);
        if(i>0){
            Long num = ce.getTotal() % 5 == 0 ? (ce.getTotal() / 5) : (ce.getTotal() / 5)+1;
            return Msg.success().data("pages",num).mess("添加成功");
        }
        return Msg.fail().mess("添加失败");
    }

    public Msg deleteCityById(Integer id) {
        int i = cityMapper.deleteCityById(id);
        medicalPolicyMapper.deleteByCity(id);
        if (i > 0) {
            return Msg.success().mess("删除成功");
        } else {
            return Msg.fail().mess("删除失败");
        }

    }

//检查城市是否存在方法通过dao层方法查询对应城市信息个数并返回，具体代码如下：
    /**
     * 检查城市名是否存在
     * @param number
     * @return
     */
    public int checkCityByName(Integer number) {
        return cityMapper.checkCityByName(number);
    }


}
