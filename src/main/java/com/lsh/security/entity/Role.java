package com.lsh.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description TODO
 * @Author LSH
 * @Date 2019/12/24 15:13
 */
@Data
public class Role implements GrantedAuthority {
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
