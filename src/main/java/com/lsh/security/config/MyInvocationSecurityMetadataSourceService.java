package com.lsh.security.config;

import com.lsh.security.entity.RolePermisson;
import com.lsh.security.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description TODO
 * @Author LSH
 * @Date 2019/12/24 15:56
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    private static HashMap<String,Collection<ConfigAttribute>> map = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            if (new AntPathRequestMatcher(entry.getKey()).matches(request)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void loadResourceDefine(){
        map = new HashMap<>(16);
        List<RolePermisson> rolePermissions = permissionMapper.getRolePermissions();

        for (RolePermisson rolePermission : rolePermissions) {
            String url = rolePermission.getUrl();
            String roleName = rolePermission.getRoleName();
            ConfigAttribute role = new SecurityConfig(roleName);
            if (map.containsKey(url)){
                map.get(url).add(role);
            }else {
                List<ConfigAttribute> list = new ArrayList<>();
                list.add(role);
                map.put(url,list);
            }
        }
    }
}
