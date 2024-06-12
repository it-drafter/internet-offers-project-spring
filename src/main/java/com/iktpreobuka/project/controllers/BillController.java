package com.iktpreobuka.project.controllers;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.services.BillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/project/bills")
public class BillController {
    @Autowired
    protected BillDao billService;

    // 3.3 u paketu com.iktpreobuka.project.controllers napraviti klasu BillController sa REST endpoint-om koji vraća listu svih računa
    // - putanja /project/bills
    @GetMapping
    public Iterable<BillEntity> getAllBills() {
        return billService.getAllBills();
    }

    // 3.6 kreirati REST endpoint-ove za dodavanje, izmenu i brisanje računa
    // - putanja /project/bills/{offerId}/buyer/{buyerId} (dodavanje)
    // - putanja /project/bills/{id} (izmena i brisanje)
    @PostMapping("/{offerId}/buyer/{buyerId}")
    public BillEntity addNewBill(@RequestBody BillEntity newBill, @PathVariable String offerId, @PathVariable String buyerId) {
        return billService.addNewBill(newBill, offerId, buyerId);
    }

    @PutMapping("/{id}")
    public BillEntity modifyExistingBill(@PathVariable String id, @RequestBody BillEntity changedBill) {
        return billService.modifyExistingBill(id, changedBill);
    }

    @DeleteMapping("/{id}")
    public BillEntity deleteBillById(@PathVariable String id) {
        return billService.deleteBillById(id);
    }

    // 3.7 kreirati REST endpoint za pronalazak svih računa određenog kupca
    // - putanja /project/bills/findByBuyer/{buyerId}
    @GetMapping("/findByBuyer/{buyerId}")
    public List<BillEntity> getBillsByBuyer(@PathVariable String buyerId) {
        return billService.getBillsByBuyer(buyerId);
    }

    // 3.8 kreirati REST endpoint za pronalazak svih računa određene kategorije
    // - putanja /project/bills/findByCategory/{categoryId}
    @GetMapping("/findByCategory/{categoryId}")
    public List<BillEntity> getBillsByCategory(@PathVariable String categoryId) {
        return billService.getBillsByCategory(categoryId);
    }

    // 3.9 kreirati REST endpoint za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
    // - putanja /project/bills/findByDate/{startDate}/and/{endDate}
    @GetMapping("/findByDate/{startDate}/and/{endDate}")
    public List<BillEntity> getBillsByDateInterval(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        return billService.getBillsByDateInterval(startDate, endDate);
    }
}