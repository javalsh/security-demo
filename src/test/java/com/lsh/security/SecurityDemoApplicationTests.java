package com.lsh.security;

import com.lsh.security.entity.User;
import com.lsh.security.mapper.UserMapper;
import com.sun.jmx.snmp.internal.SnmpOutgoingRequest;
import javafx.print.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1(){
        User user = userMapper.loadUserByUsername("user");
        System.out.println(user);
    }
}
