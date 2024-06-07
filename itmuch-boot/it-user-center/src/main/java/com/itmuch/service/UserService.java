package com.itmuch.service;

import com.itmuch.domain.dto.UserDTO;
import com.itmuch.domain.entity.User;
public interface UserService{

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    UserDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
