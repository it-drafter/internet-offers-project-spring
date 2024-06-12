// 3.1 u paketu com.iktpreobuka.project.entities kreirati klasu BillEntity sasledećim atributima:
// - id –automatski generisan surogatni ključ (celi broj),
// - paymentMade - da li je novac uplaćen (true ili false),
// - paymentCanceled - da li je kupovina otkazana (true ili false),
// - billCreated - datum kada je račun napravljen.

package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Version
    protected Integer version;

    protected Boolean paymentMade;
    protected Boolean paymentCanceled;
    protected Date billCreated;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "offer")
    protected OfferEntity offer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    protected UserEntity user;

    public BillEntity() {
    }

//    public BillEntity(Integer id, Integer version, Boolean paymentMade, Boolean paymentCanceled, Date billCreated) {
//        this.setId(id);
//        this.setVersion(version);
//        this.setPaymentMade(paymentMade);
//        this.setPaymentCanceled(paymentCanceled);
//        this.setBillCreated(billCreated);
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

    public Boolean getPaymentMade() {
        return this.paymentMade;
    }

    public void setPaymentMade(Boolean paymentMade) {
        this.paymentMade = paymentMade;
    }

    public Boolean getPaymentCanceled() {
        return this.paymentCanceled;
    }

    public void setPaymentCanceled(Boolean paymentCanceled) {
        this.paymentCanceled = paymentCanceled;
    }

    public Date getBillCreated() {
        return this.billCreated;
    }

    public void setBillCreated(Date billCreated) {
        this.billCreated = billCreated;
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
