package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.*;
import com.iktpreobuka.project.repositories.BillRepository;
import com.iktpreobuka.project.repositories.CategoryRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillDaoImpl implements BillDao {
    @Autowired
    protected BillRepository billRepository;

    @Autowired
    protected OfferRepository offerRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected OfferDao offerService;


    @Override
    public Iterable<BillEntity> getAllBills() {
        return billRepository.findAll();
    }

    // 5.1 proširiti metodu za dodavanje računa tako da se smanji broj dostupnih
    //     ponuda ponude sa računa, odnosno poveća broj kupljenih
//    @Transactional
    @Override
    public BillEntity addNewBill(BillEntity newBill, String offerId, String buyerId) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(offerId)).orElse(null);
        UserEntity userDb = userRepository.findById(Integer.parseInt(buyerId)).orElse(null);

        if (offerDb != null && userDb != null && userDb.getUserRole() == UserRoleEntity.UserRole.ROLE_CUSTOMER) {
            newBill.setOffer(offerDb);
            newBill.setUser(userDb);
            newBill.setBillCreated(new Date());

            billRepository.save(newBill);

            // 2.2 u metodi za dodavanje računa u okviru BillController-a potrebno je za izmenu broja
            // dostupnih/kupljenih ponuda pozvati odgovarajuću metodu servisa zaduženog za rad sa ponudama

//            offerDb.setAvailableOffers(offerDb.getAvailableOffers() - 1);
//            offerDb.setBoughtOffers(offerDb.getBoughtOffers() + 1);
//
//            offerRepository.save(offerDb);

            offerService.modifyAvailableBoughtOffers(
                    offerDb.getId(),
                    offerDb.getBoughtOffers() + 1,
                    offerDb.getAvailableOffers() - 1
            );


            return newBill;
        }

        return null;
    }

    // 5.2 proširiti metodu za izmenu računa tako da ukoliko se račun proglašava otkazanim tada treba
    //     povećati broj dostupnih ponuda ponude sa računa, odnosno smanjiti broj kupljenih
//    @Transactional
//    public void updateOfferForModifyExistingBillMethod(OfferEntity offerDb) {
//        offerDb.setAvailableOffers(offerDb.getAvailableOffers() + 1);
//        offerDb.setBoughtOffers(offerDb.getBoughtOffers() - 1);
//    }


    @Override
    public BillEntity modifyExistingBill(String id, BillEntity changedBill) {
        BillEntity billDb = billRepository.findById(Integer.parseInt(id)).orElse(null);
        OfferEntity offerDb = offerRepository.findById(changedBill.getOffer().getId()).orElse(null);
        UserEntity userDb = userRepository.findById(changedBill.getUser().getId()).orElse(null);

        if (billDb != null) {
            if (changedBill.getPaymentMade() != null) {
                billDb.setPaymentMade(changedBill.getPaymentMade());
            }

            if (changedBill.getPaymentCanceled() != null) {
                if (changedBill.getPaymentCanceled() == true && billDb.getPaymentCanceled() == false) {
//                    offerDb.setAvailableOffers(offerDb.getAvailableOffers() + 1);
//                    offerDb.setBoughtOffers(offerDb.getBoughtOffers() - 1);

                    // this.updateOfferForModifyExistingBillMethod(offerDb);
                    // @Transactional self-invocation (in effect, a method within the target object calling another
                    // method of the target object) does not lead to an actual transaction at runtime

//                    offerRepository.save(offerDb);


                    // 2.3 u metodi za izmenu računa u okviru BillController-a potrebno je nakon što se račun
                    // proglasi otkazanim za izmenu broja dostupnih/kupljenih ponuda pozvati odgovarajuću metodu
                    // servisa zaduženog za rad sa ponudama

                    offerService.modifyAvailableBoughtOffers(
                            offerDb.getId(),
                            offerDb.getBoughtOffers() - 1,
                            offerDb.getAvailableOffers() + 1
                    );
                }

                billDb.setPaymentCanceled(changedBill.getPaymentCanceled());
            }

            if (changedBill.getBillCreated() != null) {
                billDb.setBillCreated(changedBill.getBillCreated());
            }

            if (offerDb != null) {
                billDb.setOffer(offerDb);
            }

            if (userDb != null && userDb.getUserRole() == UserRoleEntity.UserRole.ROLE_CUSTOMER) {
                billDb.setUser(userDb);
            }

            billRepository.save(billDb);

            return billDb;
        }

        return null;
    }

    @Override
    public BillEntity deleteBillById(String id) {
        BillEntity billDb = billRepository.findById(Integer.parseInt(id)).orElse(null);

        if (billDb != null) {
            billRepository.delete(billDb);

            return billDb;
        }

        return null;
    }

    @Override
    public List<BillEntity> getBillsByBuyer(String buyerId) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(buyerId)).orElse(null);

        if (userDb != null) {
            List<BillEntity> foundBills = billRepository.findAllByUser(userDb);

            if (foundBills != null && !foundBills.isEmpty()) {
                return foundBills;
            }
        }

        return null;
    }

    @Override
    public List<BillEntity> getBillsByCategory(String categoryId) {
        CategoryEntity categoryDb = categoryRepository.findById(Integer.parseInt(categoryId)).orElse(null);

        if (categoryDb != null) {
            List<OfferEntity> foundOffers = offerRepository.findAllByCategory(categoryDb);

            List<BillEntity> foundBills = new ArrayList<>();

            for (int i = 0; i < foundOffers.size(); i++) {
                foundBills.addAll(billRepository.findAllByOffer(foundOffers.get(i)));
            }

            if (foundBills != null && !foundBills.isEmpty()) {
                return foundBills;
            }
        }

        return null;
    }

    // 2.4 u servisu zaduženom za rad sa računima, napisati metodu koja za prosleđene datume vraća račune koji
    //     se nalaze u datom periodu
    // - pozvati je u okviru metode BillController-a za pronalazak svih računa koji su kreirani u odgovarajućem
    //   vremenskom periodu
    @Override
    public List<BillEntity> getBillsByDateInterval(String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateObject = formatter.parse(startDate);
        Date endDateObject = formatter.parse(endDate);

        return billRepository.findAllByBillCreatedBetween(startDateObject, endDateObject);
    }
}
