// 1.1 u paketu com.iktpreobuka.project.entities napraviti klasu UserEntity sa sledećim atributima:
// - id, first name, last name, username, password, email i user role
// - user role može da ima sledeć evrednosti: ROLE_CUSTOMER, ROLE_ADMIN i ROLE_SELLER (koristiti enumeraciju), dok
//   svi ostali atributi, sem id-a treba da budu tekstualnog tipa

package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.*;
import com.iktpreobuka.project.security.Views;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
//    public enum UserRole {ROLE_ADMIN, ROLE_SELLER, ROLE_CUSTOMER}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Version
    protected Integer version;

    @JsonView(Views.Customer.class)
    protected String firstName;
    @JsonView(Views.Customer.class)
    protected String lastName;
    protected String username;
    protected String password;
    protected String email;

    //    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    //    @JoinColumn(name = "userRole")
    protected UserRoleEntity.UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<OfferEntity> offers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<BillEntity> bills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<VoucherEntity> vouchers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JsonIgnore
    @JsonProperty("bankAccounts")
    protected List<BankAccountEntity> bankAccountEntities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "jobTitle")
//    @JsonBackReference //DTO takes care of this
    protected JobTitleEntity jobTitle;

    public UserEntity() {
    }

//    public UserEntity(Integer id,
//                      String firstName,
//                      String lastName,
//                      String username,
//                      String password,
//                      String email,
//                      UserRoleEntity.UserRole userRole,) {
//        this.setId(id);
//        this.setFirstName(firstName);
//        this.setLastName(lastName);
//        this.setUsername(username);
//        this.setPassword(password);
//        this.setEmail(email);
//        this.setUserRole(userRole);
//    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoleEntity.UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRoleEntity.UserRole userRole) {
        this.userRole = userRole;
    }

    public List<OfferEntity> getOffers() {
        return this.offers;
    }

    public void setOffers(List<OfferEntity> offers) {
        this.offers = offers;
    }

    public List<BillEntity> getBills() {
        return this.bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }

    public List<VoucherEntity> getVouchers() {
        return this.vouchers;
    }

    public void setVouchers(List<VoucherEntity> vouchers) {
        this.vouchers = vouchers;
    }

    public List<BankAccountEntity> getBankAccountEntities() {
        return bankAccountEntities;
    }

    public void setBankAccountEntities(List<BankAccountEntity> bankAccountEntities) {
        this.bankAccountEntities = bankAccountEntities;
    }

    public JobTitleEntity getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleEntity jobTitle) {
        this.jobTitle = jobTitle;
    }


}
