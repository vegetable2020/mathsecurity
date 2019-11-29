package cn.math.controller;


import cn.math.dto.User;
import cn.math.service.UserService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @DeleteMapping("/user/{id:\\d+}")
    public void delectInfo(@PathVariable Long id){
        userService.delete(id);
        System.out.println("操作成功！！！");
    }

    @GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping("/user")//新增
    //@ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public String create(@Valid @RequestBody User user,BindingResult ex){
        System.out.println(ReflectionToStringBuilder.toString(ex, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("错误对象列表：");
        if(ex.hasErrors()){
            ex.getAllErrors().stream().forEach(e->{
                System.out.println(ReflectionToStringBuilder.toString(e, ToStringStyle.MULTI_LINE_STYLE));
            });
            throw new RuntimeException("操作失败！");
        }
        userService.save(user);
        return "操作成功!";
    }

    @PutMapping("/user")//修改
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String Update(@Valid @RequestBody User user,BindingResult ex){
        System.out.println(ReflectionToStringBuilder.toString(ex, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("错误对象列表：");
        if(ex.hasErrors()){
            ex.getAllErrors().stream().forEach(e->{
                System.out.println(ReflectionToStringBuilder.toString(e, ToStringStyle.MULTI_LINE_STYLE));
            });
            throw new RuntimeException("操作失败！");
        }
        userService.save(user);
        return "操作成功!";
    }

    @GetMapping("/user")
    public List<User> query(@RequestBody List<User> listUser){//List<User> listUser
        for(User user:listUser){
            logger.info("username:"+user.getUsername());
            logger.info("password:"+user.getPassword());
            logger.info("id:"+user.getId());
        }
        //logger.info("birthday:"+);

        List<User>list=new ArrayList<>();
        list.add(new User(1l,"tom1","123456",true,true,true,true));
        list.add(new User(2l,"tom2","123456",true,true,true,true));
        list.add(new User(3l,"tom3","123456",true,true,true,true));
        return list;
    }

    @GetMapping("/user/{id:\\d+}")//详情查询

    public User getInfo(@PathVariable Long id){
        System.out.println("id:"+id);
        return userService.queryById(id);
    }

}
