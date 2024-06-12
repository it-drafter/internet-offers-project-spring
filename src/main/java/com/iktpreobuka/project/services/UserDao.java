package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.UserEntity;

public interface UserDao {
    public Iterable<UserEntity> getAllUsers();

    public UserEntity getUserById(String id);

    public UserEntity addNewUser(UserEntity newUser, String jobTitleId);

    public UserEntity modifyExistingUser(String id, UserEntity changedUser);

    public UserEntity modifyExistingUserRole(String id, String role);

    public UserEntity modifyExistingUserPassword(String id, String oldPassword, String newPassword);

    public UserEntity deleteUserById(String id);

    public UserEntity getUserByUsername(String username);
}
