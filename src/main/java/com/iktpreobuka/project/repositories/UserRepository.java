package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
