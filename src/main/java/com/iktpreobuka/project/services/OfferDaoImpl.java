package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.*;
import com.iktpreobuka.project.repositories.CategoryRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OfferDaoImpl implements OfferDao {
    @Autowired
    protected OfferRepository offerRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected UserRepository userRepository;


    @Override
    public Iterable<OfferEntity> getAllOffers() {
        return offerRepository.findAll();
    }

    // 2.3 omogućiti dodavanje kategorije i korisnika koji je kreirao ponudu
    // - izmeniti prethodnu putanju za dodavanje ponude
    // - putanja /project/offers/{categoryId}/seller/{sellerId}
    // - NAPOMENA: samo korisnik sa ulogom ROLE_SELLER ima pravo da bude postavljen kao onaj ko je kreirao/napravio
    //   ponudu (u suprotnom ne dozvoliti kreiranje ponude);
    //   Kao datum kreiranja ponude postaviti trenutni datum i ponuda ističe za 10 dana od dana kreiranja
    @Override
    public OfferEntity addNewOffer(OfferEntity newOffer, String categoryId, String sellerId) {
        CategoryEntity categoryDb = categoryRepository.findById(Integer.parseInt(categoryId)).orElse(null);
        UserEntity userDb = userRepository.findById(Integer.parseInt(sellerId)).orElse(null);

        if (categoryDb != null && userDb != null && userDb.getUserRole() == UserRoleEntity.UserRole.ROLE_SELLER) {
            newOffer.setCategory(categoryDb);
            newOffer.setUser(userDb);

            Date dt = new Date();
            newOffer.setOfferCreated(dt);

            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 10);
            dt = c.getTime();

            newOffer.setOfferExpires(dt);

            offerRepository.save(newOffer);

            return newOffer;
        }

        return null;

    }

    // 2.4 omogućiti izmenu kategorije ponude
    // izmeniti prethodnu putanju za izmenu ponude
    // putanja /project/offers/{id}/category/{categoryId}
    @Override
    public OfferEntity modifyExistingOffer(String id, String categoryId, OfferEntity changedOffer) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(id)).orElse(null);
        CategoryEntity categoryDb = categoryRepository.findById(Integer.parseInt(categoryId)).orElse(null);

        if (offerDb != null) {
            if (changedOffer.getOfferName() != null) {
                offerDb.setOfferName(changedOffer.getOfferName());
            }

            if (changedOffer.getOfferDescription() != null) {
                offerDb.setOfferDescription(changedOffer.getOfferDescription());
            }

            if (changedOffer.getOfferCreated() != null) {
                offerDb.setOfferCreated(changedOffer.getOfferCreated());
            }

            if (changedOffer.getOfferExpires() != null) {
                offerDb.setOfferExpires(changedOffer.getOfferExpires());
            }

            if (changedOffer.getRegularPrice() != null) {
                offerDb.setRegularPrice(changedOffer.getRegularPrice());
            }

            if (changedOffer.getActionPrice() != null) {
                offerDb.setActionPrice(changedOffer.getActionPrice());
            }

            if (changedOffer.getImagePath() != null) {
                offerDb.setImagePath(changedOffer.getImagePath());
            }

            if (changedOffer.getAvailableOffers() != null) {
                offerDb.setAvailableOffers(changedOffer.getAvailableOffers());
            }

            if (changedOffer.getBoughtOffers() != null) {
                offerDb.setBoughtOffers(changedOffer.getBoughtOffers());
            }

            if (categoryDb != null) {
                offerDb.setCategory(categoryDb);
            }

            offerRepository.save(offerDb);

            return offerDb;
        }

        return null;
    }

    @Override
    public OfferEntity deleteOfferById(String id) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(id)).orElse(null);

        if (offerDb != null) {
            if (offerDb.getId().equals(Integer.parseInt(id))) {
                offerRepository.delete(offerDb);

                return offerDb;
            }
        }

        return null;
    }

    @Override
    public OfferEntity getOfferById(String id) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(id)).orElse(null);

        if (offerDb != null) {
            if (offerDb.getId().equals(Integer.parseInt(id))) {
                return offerDb;
            }
        }

        return null;
    }

    @Override
    public OfferEntity modifyExistingOfferStatus(String id, String status) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(id)).orElse(null);

        if (offerDb != null) {
            if (offerDb.getId().equals(Integer.parseInt(id))) {
                switch (status) {
                    case "AWAITING_APPROVAL":
                        offerDb.setOfferStatus(OfferStatusEntity.OfferStatus.AWAITING_APPROVAL);
                        break;
                    case "APPROVED":
                        offerDb.setOfferStatus(OfferStatusEntity.OfferStatus.APPROVED);
                        break;
                    case "DECLINED":
                        offerDb.setOfferStatus(OfferStatusEntity.OfferStatus.DECLINED);
                        break;
                    case "EXPIRED":
                        offerDb.setOfferStatus(OfferStatusEntity.OfferStatus.EXPIRED);
                        break;
                    default:
                        System.out.println("Error! " + status + " is not a valid offer status!");
                        return null;
                }
            }

            offerRepository.save(offerDb);

            return offerDb;
        }

        return null;
    }

    @Override
    public List<OfferEntity> getOffersByActionPrice(String lowerPrice, String upperPrice) {
        List<OfferEntity> foundOffers = offerRepository.findAllByActionPriceBetween(Double.parseDouble(lowerPrice), Double.parseDouble(upperPrice));

        if (foundOffers != null && !foundOffers.isEmpty()) {
            return foundOffers;
        }

        return null;
    }

    // 2.1 u servisu zaduženom za rad sa ponudama, napisati metodu koja za prosleđen ID ponude, vrši izmenu
    // broja kupljenih/dostupnih ponuda
    @Override
    public void modifyAvailableBoughtOffers(Integer id, Integer boughtOffers, Integer availableOffers) {
        OfferEntity offerDb = offerRepository.findById(id).orElse(null);

        if (offerDb != null) {
            if (availableOffers != null) {
                offerDb.setAvailableOffers(availableOffers);
            }

            if (boughtOffers != null) {
                offerDb.setBoughtOffers(boughtOffers);
            }

            offerRepository.save(offerDb);

//            return null;
        }

//        return null;
    }


}
