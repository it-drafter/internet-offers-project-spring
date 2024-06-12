// 3.1 u paketu com.iktpreobuka.project.entities napraviti klasu OfferEntity sa sledećim atributima:
// - id, offer name, offer description, offer created, offer expires, regular price, action price, image path,
//   available offers, bought offers i offer status
// - atribut offer created podrazumeva datum kreiranja ponude, a offer expires datum kada ponuda ističe
// - atribut available offers govori koliko je trenutno ponuda na raspolaganju (broj dostupnih ponuda),
//   dok atribut bought offers govori koliko je ponuda do sad prodato (broj prodatih ponuda)
// - atribut image path podrazumeva putanju do slike i treba da bude tekstualnog tipa
// - offer status može da ima sledeće vrednosti:
//   WAIT_FOR_APPROVING, APPROVED, DECLINED i EXPIRED (koristiti enumeraciju)

package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OfferEntity {
//    public enum OfferStatus {AWAITING_APPROVAL, APPROVED, DECLINED, EXPIRED}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Version
    protected Integer version;

    protected String offerName;
    protected String offerDescription;
    protected Date offerCreated;
    protected Date offerExpires;
    protected Double regularPrice;
    protected Double actionPrice;
    protected String imagePath;
    protected Integer availableOffers;
    protected Integer boughtOffers;
    protected OfferStatusEntity.OfferStatus offerStatus;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    protected CategoryEntity category;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    protected UserEntity user;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<BillEntity> bills;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<VoucherEntity> vouchers;


    public OfferEntity() {
    }

//    public OfferEntity(
//            Integer id,
//            String offerName,
//            String offerDescription,
//            Date offerCreated,
//            Date offerExpires,
//            Double regularPrice,
//            Double actionPrice,
//            String imagePath,
//            Integer availableOffers,
//            Integer boughtOffers,
//            OfferStatusEntity.OfferStatus offerStatus
//    ) {
//        this.setId(id);
//        this.setOfferName(offerName);
//        this.setOfferDescription(offerDescription);
//        this.setOfferCreated(offerCreated);
//        this.setOfferExpires(offerExpires);
//        this.setRegularPrice(regularPrice);
//        this.setActionPrice(actionPrice);
//        this.setImagePath(imagePath);
//        this.setAvailableOffers(availableOffers);
//        this.setBoughtOffers(boughtOffers);
//        this.setOfferStatus(offerStatus);
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

    public String getOfferName() {
        return this.offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return this.offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public Date getOfferCreated() {
        return this.offerCreated;
    }

    public void setOfferCreated(Date offerCreated) {
        this.offerCreated = offerCreated;
    }

    public Date getOfferExpires() {
        return this.offerExpires;
    }

    public void setOfferExpires(Date offerExpires) {
        this.offerExpires = offerExpires;
    }

    public Double getRegularPrice() {
        return this.regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getActionPrice() {
        return this.actionPrice;
    }

    public void setActionPrice(Double actionPrice) {
        this.actionPrice = actionPrice;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getAvailableOffers() {
        return this.availableOffers;
    }

    public void setAvailableOffers(Integer availableOffers) {
        this.availableOffers = availableOffers;
    }

    public Integer getBoughtOffers() {
        return this.boughtOffers;
    }

    public void setBoughtOffers(Integer boughtOffers) {
        this.boughtOffers = boughtOffers;
    }

    public OfferStatusEntity.OfferStatus getOfferStatus() {
        return this.offerStatus;
    }

    public void setOfferStatus(OfferStatusEntity.OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public CategoryEntity getCategory() {
        return this.category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<BillEntity> getBills() {
        return this.bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }
}
