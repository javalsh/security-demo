package com.lsh.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Description TODO
 * @Author LSH
 * @Date 2019/12/24 16:24
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes==null||configAttributes.size()<=0) {
            return;
        }else {
            String needRole;
            for(Iterator<ConfigAttribute> iterator = configAttributes.iterator();iterator.hasNext();){
                needRole = iterator.next().getAttribute();
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    if (needRole.trim().equals(authority.getAuthority().trim())){
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
