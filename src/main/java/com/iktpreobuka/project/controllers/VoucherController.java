package com.iktpreobuka.project.controllers;

import com.iktpreobuka.project.entities.VoucherEntity;
import com.iktpreobuka.project.services.VoucherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/project/vouchers")
public class VoucherController {
    @Autowired
    protected VoucherDao voucherService;

    // 4.3 u paketu com.iktpreobuka.project.controllers napraviti klasu VoucherController sa REST endpoint-om koji vraća listu svih vaučera
    // - putanja/project/vouchers
    @GetMapping
    public Iterable<VoucherEntity> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    // 4.6 kreirati REST endpoint-ove za dodavanje, izmenu i brisanje vaučera
    // - putanja /project/vouchers/{offerId}/buyer/{buyerId}
    // - NAPOMENA: samoko risnik sa ulogom ROLE_CUSTOMER se može naći kao kupac na vaučeru (u suprotnom ne dozvoliti kreiranje vaučera)
    // - putanja /project/vouchers/{id} (izmena)
    // - putanja /project/vouchers/{id} (brisanje)
    @PostMapping("/{offerId}/buyer/{buyerId}")
    public VoucherEntity addNewVoucher(@RequestBody VoucherEntity newVoucher, @PathVariable String offerId, @PathVariable String buyerId) {
        return voucherService.addNewVoucher(newVoucher, offerId, buyerId);
    }

    @PutMapping("/{id}")
    public VoucherEntity modifyExistingVoucher(@PathVariable String id, @RequestBody VoucherEntity changedVoucher) {
        return voucherService.modifyExistingVoucher(id, changedVoucher);
    }

    @DeleteMapping("/{id}")
    public VoucherEntity deleteVoucherById(@PathVariable String id) {
        return voucherService.deleteVoucherById(id);
    }

    // 4.7 kreirati REST endpoint za pronalazak svih vaučera određenog kupca
    // - putanja /project/vouchers/findByBuyer/{buyerId}
    @GetMapping("/findByBuyer/{buyerId}")
    public List<VoucherEntity> getVouchersByBuyer(@PathVariable String buyerId) {
        return voucherService.getVouchersByBuyer(buyerId);
    }

    // 4.8 kreirati REST endpoint za pronalazak svih vaučera određene ponude
    // - putanja /project/vouchers/findByOffer/{offerId}
    @GetMapping("/findByOffer/{offerId}")
    public List<VoucherEntity> getVouchersByOffer(@PathVariable String offerId) {
        return voucherService.getVouchersByOffer(offerId);
    }

    // 4.9 kreirati REST endpoint za pronalazak svih vaučera koji nisu istekli
    // - putanja /project/vouchers/findNonExpiredVouchers
    @GetMapping("/findNonExpiredVouchers")
    public List<VoucherEntity> getNonExpiredVouchers() {
        return voucherService.getNonExpiredVouchers();
    }
}
