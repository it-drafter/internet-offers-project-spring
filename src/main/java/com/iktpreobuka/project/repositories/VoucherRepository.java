package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.VoucherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VoucherRepository extends CrudRepository<VoucherEntity, Integer> {
    List<VoucherEntity> findAllByUser(UserEntity user);

    List<VoucherEntity> findAllByOffer(OfferEntity offer);

    List<VoucherEntity> findAllByExpirationDateAfter(Date today);
}
