//package com.vmall.vauth.config;
//
//import com.vmall.mapper.menu.MenuMapper;
//import com.vmall.pojo.VMenu;
//import com.vmall.pojo.VRole;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author 陈
// * SpringSecurity安全验证
// * 2019.5.10
// */
////@Component
//public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//    AntPathMatcher antPathMatcher=new AntPathMatcher();
//    @Autowired
//    MenuMapper menuMapper;
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        String requestUrl=((FilterInvocation) object).getRequestUrl();
//        List<VMenu> allMenus=menuMapper.getAllMenus();
//        for (VMenu menu:allMenus){
//            if(antPathMatcher.match(menu.getVUrl(),requestUrl)){
//                List<VRole> roles=menu.getRoles();
//                String[] roleArr=new String[roles.size()];
//                for (int i=0;i<roleArr.length;i++){
//                    roleArr[i] =roles.get(i).getvName();
//                }
//                return SecurityConfig.createList(roleArr);
//            }
//        }
//        return SecurityConfig.createList("ROLE_LOGIN");
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return FilterInvocation.class.isAssignableFrom(aClass);
//    }
//}
