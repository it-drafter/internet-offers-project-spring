package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.JobTitleEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.UserRoleEntity;
import com.iktpreobuka.project.repositories.JobTitleRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    private EntityManager em;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JobTitleRepository jobTitleRepository;

    @Override
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(String id) {
        return userRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @Override
    public UserEntity addNewUser(UserEntity newUser, String jobTitleId) {
        JobTitleEntity jobTitleDb = jobTitleRepository.findById(Integer.parseInt(jobTitleId)).orElse(null);

        if (jobTitleDb != null) {
            newUser.setJobTitle(jobTitleDb);

            userRepository.save(newUser);
        }
        
        return newUser;
    }

    @Override
    public UserEntity modifyExistingUser(String id, UserEntity changedUser) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(id)).orElse(null);

        if (userDb != null) {
            if (changedUser.getFirstName() != null) {
                userDb.setFirstName(changedUser.getFirstName());
            }

            if (changedUser.getLastName() != null) {
                userDb.setLastName(changedUser.getLastName());
            }

            if (changedUser.getUsername() != null) {
                userDb.setUsername(changedUser.getUsername());
            }

            if (changedUser.getEmail() != null) {
                userDb.setEmail(changedUser.getEmail());
            }

            userRepository.save(userDb);

            return userDb;
        }

        return new UserEntity();
    }

    @Override
    public UserEntity modifyExistingUserRole(String id, String role) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(id)).orElse(null);

        if (userDb != null) {
            switch (role) {
                case "ROLE_CUSTOMER":
                    userDb.setUserRole(UserRoleEntity.UserRole.ROLE_CUSTOMER);
                    break;
                case "ROLE_ADMIN":
                    userDb.setUserRole(UserRoleEntity.UserRole.ROLE_ADMIN);
                    break;
                case "ROLE_SELLER":
                    userDb.setUserRole(UserRoleEntity.UserRole.ROLE_SELLER);
                    break;
                default:
                    System.out.println("Error! " + role + " is not a valid role!");
                    return null;
            }

            userRepository.save(userDb);

            return userDb;
        }

        return null;
    }

    @Override
    public UserEntity modifyExistingUserPassword(String id, String oldPassword, String newPassword) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(id)).orElse(null);

        if (userDb != null) {
            if (userDb.getPassword().equals(oldPassword)) {
                userDb.setPassword(newPassword);

                userRepository.save(userDb);

                return userDb;
            } else {
                System.out.println("Wrong old password");
            }
        }

        return null;
    }

    @Override
    public UserEntity deleteUserById(String id) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(id)).orElse(null);

        if (userDb != null) {
            if (userDb.getId().equals(Integer.parseInt(id))) {
                userRepository.delete(userDb);

                return userDb;
            }
        }

        return null;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity foundUser = userRepository.findByUsername(username);

        if (foundUser != null) {
            return foundUser;
        }

        return null;
    }


}
