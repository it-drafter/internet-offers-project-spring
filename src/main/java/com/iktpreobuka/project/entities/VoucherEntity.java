package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Version
    protected Integer version;

    protected Date expirationDate;
    protected Boolean isUsed;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "offer")
    protected OfferEntity offer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    protected UserEntity user;


    public VoucherEntity() {
    }

//    public VoucherEntity(Integer id, Integer version, Date expirationDate, Boolean isUsed) {
//        this.setId(id);
//        this.setVersion(version);
//        this.setExpirationDate(expirationDate);
//        this.setUsed(isUsed);
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

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(Boolean used) {
        this.isUsed = used;
    }

    public OfferEntity getOffer() {
        return this.offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
