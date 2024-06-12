package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.UserRoleEntity;
import com.iktpreobuka.project.entities.VoucherEntity;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import com.iktpreobuka.project.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoucherDaoImpl implements VoucherDao {
    @Autowired
    protected VoucherRepository voucherRepository;

    @Autowired
    protected OfferRepository offerRepository;

    @Autowired
    protected UserRepository userRepository;

    @Override
    public Iterable<VoucherEntity> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public VoucherEntity addNewVoucher(VoucherEntity newVoucher, String offerId, String buyerId) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(offerId)).orElse(null);
        UserEntity userDb = userRepository.findById(Integer.parseInt(buyerId)).orElse(null);

        if (offerDb != null && userDb != null && userDb.getUserRole() == UserRoleEntity.UserRole.ROLE_CUSTOMER) {
            newVoucher.setOffer(offerDb);
            newVoucher.setUser(userDb);

            voucherRepository.save(newVoucher);

            return newVoucher;
        }

        return null;
    }

    @Override
    public VoucherEntity modifyExistingVoucher(String id, VoucherEntity changedVoucher) {
        VoucherEntity voucherDb = voucherRepository.findById(Integer.parseInt(id)).orElse(null);
        OfferEntity offerDb = offerRepository.findById(changedVoucher.getOffer().getId()).orElse(null);
        UserEntity userDb = userRepository.findById(changedVoucher.getUser().getId()).orElse(null);

        if (voucherDb != null) {
            if (changedVoucher.getExpirationDate() != null) {
                voucherDb.setExpirationDate(changedVoucher.getExpirationDate());
            }

            if (changedVoucher.getIsUsed() != null) {
                voucherDb.setIsUsed(changedVoucher.getIsUsed());
            }

            if (offerDb != null) {
                voucherDb.setOffer(offerDb);
            }

            if (userDb != null && userDb.getUserRole() == UserRoleEntity.UserRole.ROLE_CUSTOMER) {
                voucherDb.setUser(userDb);
            }

            voucherRepository.save(voucherDb);

            return voucherDb;
        }

        return null;
    }

    @Override
    public VoucherEntity deleteVoucherById(String id) {
        VoucherEntity voucherDb = voucherRepository.findById(Integer.parseInt(id)).orElse(null);

        if (voucherDb != null) {
            voucherRepository.delete(voucherDb);

            return voucherDb;
        }

        return null;
    }

    @Override
    public List<VoucherEntity> getVouchersByBuyer(String buyerId) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(buyerId)).orElse(null);

        if (userDb != null) {
            List<VoucherEntity> foundVouchers = voucherRepository.findAllByUser(userDb);

            if (foundVouchers != null && !foundVouchers.isEmpty()) {
                return foundVouchers;
            }
        }

        return null;
    }

    @Override
    public List<VoucherEntity> getVouchersByOffer(String offerId) {
        OfferEntity offerDb = offerRepository.findById(Integer.parseInt(offerId)).orElse(null);

        if (offerDb != null) {
            List<VoucherEntity> foundVouchers = voucherRepository.findAllByOffer(offerDb);

            if (foundVouchers != null && !foundVouchers.isEmpty()) {
                return foundVouchers;
            }
        }

        return null;
    }

    @Override
    public List<VoucherEntity> getNonExpiredVouchers() {
        return voucherRepository.findAllByExpirationDateAfter(new Date());
    }


}
