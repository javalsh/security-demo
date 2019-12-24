package com.lsh.security.mapper;

import com.lsh.security.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author LSH
 * @Date 2019/12/24 15:38
 */
public interface RoleMapper {

    @Select("select r.id , r.name from role r left join user_role ur on ur.role_id =r.id where ur.user_id= #{userId}")
    List<Role> getRolesByUserId(@Param("userId") Long userId);
}
