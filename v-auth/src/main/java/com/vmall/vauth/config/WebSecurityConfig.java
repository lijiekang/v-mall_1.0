//package com.vmall.vauth.config;
//
//import com.vmall.vauth.service.BackUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
///**
// * @author 陈
// * SpringSecurity安全验证
// * 2019.5.10
// */
////@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    BackUserService backUserService;
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(backUserService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http)throws Exception{
//        /*http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/db/**").hasRole("dba")
//                .antMatchers("/user/**").hasRole("user")
//                .anyRequest().authenticated()*/
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setSecurityMetadataSource(cfisms());
//                        object.setAccessDecisionManager(cadm());
//                        return object;
//                    }
//                })
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login").permitAll()
//                .and()
//                .csrf()
//                .disable();
//    }
//
//    @Bean
//    CustomFilterInvocationSecurityMetadataSource cfisms(){
//        return new CustomFilterInvocationSecurityMetadataSource();
//    }
//
//    @Bean
//    CustomAccessDecisionManager cadm(){
//        return new CustomAccessDecisionManager();
//    }
//
//    @Bean
//    RoleHierarchy roleHierarchy(){
//        RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
//        String hierarchy="ROLE_admin > ROLE_user_admin ROLE_admin > ROLE_order";
//        roleHierarchy.setHierarchy(hierarchy);
//        return roleHierarchy;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/index.html", "/static/**");
//    }
//}
