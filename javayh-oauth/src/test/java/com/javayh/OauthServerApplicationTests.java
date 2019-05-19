package com.javayh;

import com.javayh.mapper.UserDao;
import com.javayh.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OauthServerApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() {
        SysUserVO admin = userDao.findByUserName("admin");
        log.info("test"+admin.toString());
    }

}
