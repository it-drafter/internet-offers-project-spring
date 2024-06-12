package com.iktpreobuka.project.controllers;

import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/project/users")
public class UserController {
    @Autowired
    protected UserDao userService;
//    private List<UserEntity> users = new ArrayList<>();

    // 1.2 u paketu com.iktpreobuka.project.controllers napraviti klasu UserController sa metodom getDB() koja vraća
    // listu svih korisnika aplikacije
//    private List<UserEntity> getDb() {
//        if (users != null && users.isEmpty()) {
//            final Integer numberOfUsers = 6;
//
//            for (int i = 1; i <= numberOfUsers; i++) {
//                users.add(new UserEntity(
//                        i,
//                        "FirstName" + i,
//                        "LastName" + i,
//                        "username" + i,
//                        "password" + i,
//                        "email" + i + "@email.email",
//                        i == 1 ?
//                                UserRoleEntity.UserRole.ROLE_ADMIN :
//                                i == 2 || i == 3 ?
//                                        UserRoleEntity.UserRole.ROLE_SELLER :
//                                        UserRoleEntity.UserRole.ROLE_CUSTOMER
//                ));
//            }
//        }
//
//        return users;
//    }

    // 1.3 kreirati REST endpoint koji vraća listu korisnika aplikacije
    // - putanja /project/users
    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<UserEntity> getAllUsers() {
//        return getDb();
        return userService.getAllUsers();
    }

    // 1.4 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog ID-a
    // - putanja /project/users/{id}
    // - u slučaju da ne postoji korisnik sa traženom vrednošću ID-a vratiti null
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserEntity getUserById(@PathVariable String id) {
//        for (UserEntity user : getDb()) {
//            if (user.getId().equals(Integer.parseInt(id))) {
//                return user;
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;
        return userService.getUserById(id);
    }

    // 1.5 kreirati REST endpoint koji omogućava dodavanje novog korisnika
    // - putanja /project/users
    // - u okviru ove metode postavi vrednost atribut auser role na ROLE_CUSTOMER
    // - metoda treba da vrati dodatog korisnika
    // Request body:
//     {
//        "firstName": "NewUserFirstName1",
//        "lastName": "NewUserLastName1",
//        "username": "NewUserUsername1",
//        "password": "NewUserPassword1",
//        "email": "newuseremail1@email.email",
//     }
    @RequestMapping(method = RequestMethod.POST, value = "/jobTitle/{jobTitleId}")
    public UserEntity addNewUser(@RequestBody UserEntity newUser, @PathVariable String jobTitleId) {
//        getDb();
//
//        newUser.setId(users.size() + 1);
//        newUser.setUserRole(UserRoleEntity.UserRole.ROLE_CUSTOMER);
//
//        users.add(newUser);
//
//        return newUser;
        return userService.addNewUser(newUser, jobTitleId);
    }

    // 1.6 kreirati REST endpoint koji omogućava izmenu postojećeg korisnika
    // - putanja /project/users/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednom korisnik umetoda treba da vrati null, a u suprotnom
    //   vraća podatke korisnika sa izmenjenim vrednostima
    // - NAPOMENA: u okviru ove metode ne menjati vrednost atributa user role i password
    // Request body:
    // {
    //    "firstName": "ChangedUserFirstName1",
    //    "lastName": "ChangedUserLastName1",
    //    "username": "ChangedUserUsername1",
    //    "email": "changeduseremail1@email.email"
    // }
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public UserEntity modifyExistingUser(@PathVariable String id, @RequestBody UserEntity changedUser) {
//        for (UserEntity user : getDb()) {
//            if (user.getId().equals(Integer.parseInt(id))) {
//                if (changedUser.getFirstName() != null) {
//                    user.setFirstName(changedUser.getFirstName());
//                }
//
//                if (changedUser.getLastName() != null) {
//                    user.setLastName(changedUser.getLastName());
//                }
//
//                if (changedUser.getUsername() != null) {
//                    user.setUsername(changedUser.getUsername());
//                }
//
//                if (changedUser.getEmail() != null) {
//                    user.setEmail(changedUser.getEmail());
//                }
//
//                return user;
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;
        return userService.modifyExistingUser(id, changedUser);
    }

    // 1.7 kreirati REST endpoint koji omogućava izmenu atributa user_role postojećeg korisnika
    // - putanja/project/users/change/{id}/role/{role}
    // - ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom
    //   vraća podatke korisnika sa izmenjenom vrednošću atributa user role
    @RequestMapping(method = RequestMethod.PATCH, value = "/change/{id}/role/{role}")
    public UserEntity modifyExistingUserRole(@PathVariable String id, @PathVariable String role) {
//        for (UserEntity user : getDb()) {
//            if (user.getId().equals(Integer.parseInt(id))) {
//                switch (role) {
//                    case "ROLE_CUSTOMER":
//                        user.setUserRole(UserRoleEntity.UserRole.ROLE_CUSTOMER);
//                        break;
//                    case "ROLE_ADMIN":
//                        user.setUserRole(UserRoleEntity.UserRole.ROLE_ADMIN);
//                        break;
//                    case "ROLE_SELLER":
//                        user.setUserRole(UserRoleEntity.UserRole.ROLE_SELLER);
//                        break;
//                    default:
//                        System.out.println("Error! " + role + " is not a valid role!");
//                        return null;
//                }
//
//                return user;
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;

        return userService.modifyExistingUserRole(id, role);
    }

    // 1.8 kreirati REST endpoint koji omogućava izmenu vrednosti atributa password postojećeg korisnika
    // - putanja /project/users/changePassword/{id}
    // - kao RequestParam proslediti vrednosti stare i nove lozinke
    // - ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom
    //   vraća podatke korisnika sa izmenjenom vrednošću atributa password
    // - NAPOMENA: da bi vrednost atributa password mogla da bude zamenjena sa novom vrednošću, neophodno je da
    //             se vrednost stare lozinke korisnika poklapa sa vrednošću stare lozinke prosleđene kao RequestParam
    @RequestMapping(method = RequestMethod.PATCH, value = "/changePassword/{id}")
    public UserEntity modifyExistingUserPassword(
            @PathVariable String id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

//        for (UserEntity user : getDb()) {
//            if (user.getId().equals(Integer.parseInt(id))) {
//                if (user.getPassword().equals(oldPassword)) {
//                    user.setPassword(newPassword);
//
//                    return user;
//                } else {
//                    System.out.println("Wrong old password");
//                }
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;
        return userService.modifyExistingUserPassword(id, oldPassword, newPassword);
    }

    // 1.9 kreirati REST endpoint koji omogućava brisanje postojećeg korisnika
    // - putanja /project/users/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom
    //   vraća podatke o korisniku koji je obrisan
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public UserEntity deleteUserById(@PathVariable String id) {
//        for (UserEntity user : getDb()) {
//            if (user.getId().equals(Integer.parseInt(id))) {
//                getDb().remove(user);
//
//                return user;
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;

        return userService.deleteUserById(id);
    }

    // 1.10 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog username-a
    // - putanja /project/users/by-username/{username}
    // - u slučaju da ne postoji korisnik sa traženim username-om vratiti null
    @RequestMapping(method = RequestMethod.GET, value = "/by-username/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
//        for (UserEntity user : getDb()) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
//
//        System.out.println("No such user.");
//        return null;

        return userService.getUserByUsername(username);
    }
}
