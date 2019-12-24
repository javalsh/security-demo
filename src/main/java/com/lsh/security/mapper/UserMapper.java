package com.lsh.security.mapper;

import com.lsh.security.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id , username , password from user where username = #{username} ")
    User loadUserByUsername(@Param("username") String username);
}
