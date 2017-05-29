package com.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.spider.entity.Csdnblog;
import com.spider.dao.CsdnblogDao;

@Service
public class CsdnblogService{

    @Autowired
    private CsdnblogDao csdnblogDao;

    public int insert(Csdnblog pojo){
        int result = -1;
        try {
            result = csdnblogDao.insert(pojo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int insertSelective(Csdnblog pojo){
        return csdnblogDao.insertSelective(pojo);
    }

    public int insertList(List<Csdnblog> pojos){
        return csdnblogDao.insertList(pojos);
    }

    public int update(Csdnblog pojo){
        return csdnblogDao.update(pojo);
    }
}
