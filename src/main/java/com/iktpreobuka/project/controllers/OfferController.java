package com.iktpreobuka.project.controllers;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.OfferStatusEntity;
import com.iktpreobuka.project.services.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project/offers")
public class OfferController {
    @Autowired
    protected OfferDao offerService;

//    List<OfferEntity> offers = new ArrayList<>();

    // 3.2 u paketu com.iktpreobuka.project.controllers napraviti klasu OfferController sa metodom get DB() koja vraća
    //     listu svih ponuda
//    private List<OfferEntity> getDb() {
//        if (offers != null && offers.isEmpty()) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(new Date());
////            cal.add(Calendar.DATE, 5);
//
//
//            final Integer numberOfOffers = 6;
//
//            for (int i = 1; i <= numberOfOffers; i++) {
//                cal.add(Calendar.DATE, i);
//
//                offers.add(new OfferEntity(
//                        i,
//                        "offerName" + i,
//                        "offerDescription" + i,
//                        new Date(),
//                        cal.getTime(),
//                        i * 1000.0,
//                        i * 1000.0 - i * 100.0,
//                        "/imagePath" + i,
//                        i * 100,
//                        i * 10,
//                        i == 1 ?
//                                OfferStatusEntity.OfferStatus.AWAITING_APPROVAL :
//                                i == 2 || i == 3 ?
//                                        OfferStatusEntity.OfferStatus.APPROVED :
//                                        i == 3 || i == 4 ?
//                                                OfferStatusEntity.OfferStatus.DECLINED :
//                                                OfferStatusEntity.OfferStatus.EXPIRED
//                ));
//            }
//
//        }
//
//        return offers;
//    }

    // 3.3 kreirati REST endpoint koja vraća listu svih ponuda
    // - putanja /project/offers
    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<OfferEntity> getAllOffers() {
//        return getDb();
        return offerService.getAllOffers();
    }

    // 3.4 kreirati REST endpoint koji omogućava dodavanje nove ponude
    // - putanja /project/offers
    // - metoda treba da vrati dodatu ponudu
//     {
//        "offerName": "offerName3",
//        "offerDescription": "offerDescription1",
//        "offerCreated": "2024-02-15",
//        "offerExpires": "2024-09-15",
//        "regularPrice": 10.15,
//        "actionPrice": 9.15,
//        "imagePath": "imagePath1",
//        "availableOffers": 100,
//        "boughtOffers": 60,
//        "offerStatus": "APPROVED"
//    }
//    @RequestMapping(method = RequestMethod.POST, value = "")
//    public OfferEntity addNewOffer(@RequestBody OfferEntity newOffer) {
//        getDb();
//
//        newOffer.setId(offers.size() + 1);
//
//        offers.add(newOffer);
//
//        return newOffer;
//        return offerService.addNewOffer(newOffer);
//    }

    @PostMapping("/{categoryId}/seller/{sellerId}")
    public OfferEntity addNewOffer(@RequestBody OfferEntity newOffer, @PathVariable String categoryId, @PathVariable String sellerId) {
        return offerService.addNewOffer(newOffer, categoryId, sellerId);
    }

    // 3.5 kreirati REST endpoint koji omogućava izmenu postojeće ponude
    // - putanja /project/offers/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednoj ponudi treba da vrati null, a u suprotnom
    //   vraća podatke ponude sa izmenjenim vrednostima
    // - NAPOMENA: u okviru ove metode ne menjati vrednost atributa offer status
    //
    // 2.4 omogućiti izmenu kategorije ponude
    // izmeniti prethodnu putanju za izmenu ponude
    // putanja /project/offers/{id}/category/{categoryId}
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/category/{categoryId}")
    public OfferEntity modifyExistingOffer(@PathVariable String id, @PathVariable String categoryId, @RequestBody OfferEntity changedOffer) {
//        for (OfferEntity offer : getDb()) {
//            if (offer.getId().equals(Integer.parseInt(id))) {
//                if (changedOffer.getOfferName() != null) {
//                    offer.setOfferName(changedOffer.getOfferName());
//                }
//
//                if (changedOffer.getOfferDescription() != null) {
//                    offer.setOfferDescription(changedOffer.getOfferDescription());
//                }
//
//                if (changedOffer.getOfferCreated() != null) {
//                    offer.setOfferCreated(changedOffer.getOfferCreated());
//                }
//
//                if (changedOffer.getOfferExpires() != null) {
//                    offer.setOfferExpires(changedOffer.getOfferExpires());
//                }
//
//                if (changedOffer.getRegularPrice() != null) {
//                    offer.setRegularPrice(changedOffer.getRegularPrice());
//                }
//
//                if (changedOffer.getActionPrice() != null) {
//                    offer.setActionPrice(changedOffer.getActionPrice());
//                }
//
//                if (changedOffer.getImagePath() != null) {
//                    offer.setImagePath(changedOffer.getImagePath());
//                }
//
//                if (changedOffer.getAvailableOffers() != null) {
//                    offer.setAvailableOffers(changedOffer.getAvailableOffers());
//                }
//
//                if (changedOffer.getBoughtOffers() != null) {
//                    offer.setBoughtOffers(changedOffer.getBoughtOffers());
//                }
//
//                return offer;
//            }
//        }
//
//        System.out.println("No such offer.");
//        return null;
        return offerService.modifyExistingOffer(id, categoryId, changedOffer);
    }

    // 3.6 kreirati REST endpoint koji omogućava brisanje postojeće ponude
    // - putanja /project/offers/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednoj ponudi metoda treba da vrati null, a u suprotnom
    //   vraća podatke o ponudi koja je obrisana
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public OfferEntity deleteOfferById(@PathVariable String id) {
//        for (OfferEntity offer : getDb()) {
//            if (offer.getId().equals(Integer.parseInt(id))) {
//                this.offers.remove(offer);
//
//                return offer;
//            }
//        }
//
//        System.out.println("No such offer.");
//        return null;

        return offerService.deleteOfferById(id);
    }

    // 3.7 kreirati REST endpoint koji vraća ponudu po vrednosti prosleđenog ID-a
    // - putanja /project/offers/{id}
    // - u slučaju da ne postoji ponuda sa traženom vrednošću ID-a vratiti null
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public OfferEntity getOfferById(@PathVariable String id) {
//        for (OfferEntity offer : getDb()) {
//            if (offer.getId().equals(Integer.parseInt(id))) {
//                return offer;
//            }
//        }
//
//        System.out.println("No such offer.");
//        return null;

        return offerService.getOfferById(id);
    }

    // 3.8 kreirati REST endpoint koji omogućava promenu vrednosti atributa offer status postojeće ponude
    // - putanja /project/offers/changeOffer/{id}/status/{status}
    // - ukoliko je prosleđen ID koji ne pripada nijednoj ponudi metoda treba da vrati null, a u suprotnom vraća
    //   podatke o ponudi koja je modifikovana
    @RequestMapping(method = RequestMethod.PATCH, value = "/changeOffer/{id}/status/{status}")
    public OfferEntity modifyExistingOfferStatus(@PathVariable String id, @PathVariable String status) {
//        for (OfferEntity offer : getDb()) {
//            if (offer.getId().equals(Integer.parseInt(id))) {
//                switch (status) {
//                    case "AWAITING_APPROVAL":
//                        offer.setOfferStatus(OfferStatusEntity.OfferStatus.AWAITING_APPROVAL);
//                        break;
//                    case "APPROVED":
//                        offer.setOfferStatus(OfferStatusEntity.OfferStatus.APPROVED);
//                        break;
//                    case "DECLINED":
//                        offer.setOfferStatus(OfferStatusEntity.OfferStatus.DECLINED);
//                        break;
//                    case "EXPIRED":
//                        offer.setOfferStatus(OfferStatusEntity.OfferStatus.EXPIRED);
//                        break;
//                    default:
//                        System.out.println("Error! " + status + " is not a valid offer status!");
//                        return null;
//                }
//            }
//
//            return offer;
//        }
//
//        System.out.println("No such offer.");
//        return null;
        return offerService.modifyExistingOfferStatus(id, status);
    }

    // 3.9 kreirati REST endpoint koji omogućava pronalazak svih ponuda čija se akcijska cena nalazi u odgovarajućem
    //     rasponu
    // - putanja /project/offers/findByPrice/{lowerPrice}/and/{upperPrice}
    @RequestMapping(method = RequestMethod.GET, path = "/findByPrice/{lowerPrice}/and/{upperPrice}")
    public List<OfferEntity> getOffersByActionPrice(@PathVariable String lowerPrice, @PathVariable String upperPrice) {
//        List<OfferEntity> offersInActionPriceRange = new ArrayList<>();
//
//        for (OfferEntity offer : getDb()) {
//            if (offer.getActionPrice().compareTo(Double.parseDouble(lowerPrice)) >= 0 &&
//                    offer.getActionPrice().compareTo(Double.parseDouble(upperPrice)) <= 0) {
//                offersInActionPriceRange.add(offer);
//            }
//        }
//
//        return offersInActionPriceRange;
        return offerService.getOffersByActionPrice(lowerPrice, upperPrice);
    }

    // 2.1 u servisu zaduženom za rad sa ponudama, napisati metodu koja za prosleđen ID ponude, vrši izmenu
    // broja kupljenih/dostupnih ponuda
//    @PatchMapping("/modifyAvailableBoughtOffers/{id}")
//    public OfferEntity modifyAvailableBoughtOffers(@PathVariable String id, @RequestBody OfferEntity changedOffer) {
//        return offerService.modifyAvailableBoughtOffers(id, changedOffer);
//    }
}
