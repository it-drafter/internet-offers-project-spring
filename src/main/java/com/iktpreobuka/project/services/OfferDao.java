package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.OfferEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OfferDao {
    public Iterable<OfferEntity> getAllOffers();

    public OfferEntity addNewOffer(OfferEntity newOffer, String categoryId, String sellerId);

    public OfferEntity modifyExistingOffer(String id, String categoryId, OfferEntity changedOffer);

    public OfferEntity deleteOfferById(String id);

    public OfferEntity getOfferById(String id);

    public OfferEntity modifyExistingOfferStatus(String id, String status);

    public List<OfferEntity> getOffersByActionPrice(String lowerPrice, String upperPrice);

    public void modifyAvailableBoughtOffers(Integer id, Integer boughtOffers, Integer availableOffers);
}
