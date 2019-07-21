package com.javayh.repository;

import com.javayh.config.MongodbBaseDao;
import com.javayh.entity.SysMenu;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: MgRepository
 * @Title: MgRepository
 * @ProjectName javayh-cloud
 * @date 2019/7/21 17:25
 */
@Repository
public class MgRepository  extends MongodbBaseDao<SysMenu> {

    @Override
    protected Class getEntityClass() {
        return SysMenu.class;
    }

}

