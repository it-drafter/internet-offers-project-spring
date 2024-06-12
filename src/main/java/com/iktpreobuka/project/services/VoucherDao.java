package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.VoucherEntity;

import java.util.List;

public interface VoucherDao {
    public Iterable<VoucherEntity> getAllVouchers();

    public VoucherEntity addNewVoucher(VoucherEntity newVoucher, String offerId, String buyerId);

    public VoucherEntity modifyExistingVoucher(String id, VoucherEntity changedVoucher);

    public VoucherEntity deleteVoucherById(String id);

    public List<VoucherEntity> getVouchersByBuyer(String buyerId);

    public List<VoucherEntity> getVouchersByOffer(String offerId);

    public List<VoucherEntity> getNonExpiredVouchers();
}
