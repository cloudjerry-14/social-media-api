package com.api.socialmedia.dao;

import org.springframework.data.repository.CrudRepository;
import com.api.socialmedia.entity.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long>{

}
