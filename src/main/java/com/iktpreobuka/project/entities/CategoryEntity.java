// 2.1 u paketu com.iktpreobuka.project.entities napraviti klasu CategoryEntity sa sledećim atributima:
// - id, category name i category description
// - svi atributi, sem id-a treba da budu tekstualnog tipa

package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Version
    protected Integer version;

    protected String categoryName;
    protected String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<OfferEntity> offers;

    public CategoryEntity() {
    }

//    public CategoryEntity(Integer id, String categoryName, String categoryDescription) {
//        this.setId(id);
//        this.setCategoryName(categoryName);
//        this.setCategoryDescription(categoryDescription);
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

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return this.categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<OfferEntity> getOffers() {
        return this.offers;
    }

    public void setOffers(List<OfferEntity> offers) {
        this.offers = offers;
    }

    
}
