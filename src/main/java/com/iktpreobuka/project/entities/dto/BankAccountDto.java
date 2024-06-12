package com.iktpreobuka.project.entities.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.json.CustomDateDeserializer;
import com.iktpreobuka.project.entities.json.CustomDateSerializer;
import com.iktpreobuka.project.security.Views;

import java.util.Date;

public class BankAccountDto {
    @JsonView(Views.Customer.class)
    protected Integer id;
    @JsonView(Views.Customer.class)
    protected String accountNumber;
    @JsonView(Views.Customer.class)
    protected String accountType; // this sholud be an enum, but I am simplifying it

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    @JsonView(Views.Admin.class)
    protected Date dateCreated;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonView(Views.Seller.class)
    protected Date dateClosed;

    @JsonView(Views.Customer.class)
    protected Boolean isActive;

    // User won't show. Don't know why. Need to look into this.
    @JsonBackReference
    @JsonView(Views.Customer.class)
    protected UserEntity user;

    public BankAccountDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public Boolean getActive() {
        return this.isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
