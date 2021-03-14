package com.dao;

import com.bean.User;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user")
    List <User> findAll();//查找所有用户信息

    void saveUser(User use);//保存用户信息

    void updateUser(User user);//更新用户信息

    void deleteUser(Integer id);//根据id删除用户

    User findByid(Integer id);

    List<User> findByname(String name);
}
