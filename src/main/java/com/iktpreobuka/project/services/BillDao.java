package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.BillEntity;

import java.text.ParseException;
import java.util.List;

public interface BillDao {
    public Iterable<BillEntity> getAllBills();

    public BillEntity addNewBill(BillEntity newBill, String offerId, String buyerId);

    public BillEntity modifyExistingBill(String id, BillEntity changedBill);

    public BillEntity deleteBillById(String id);

    public List<BillEntity> getBillsByBuyer(String buyerId);

    public List<BillEntity> getBillsByCategory(String categoryId);

    public List<BillEntity> getBillsByDateInterval(String startDate, String endDate) throws ParseException;
}
