// 3.2 u paketu com.iktpreobuka.project.repositories napraviti interfejs BillRepository

package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BillRepository extends CrudRepository<BillEntity, Integer> {
    List<BillEntity> findAllByUser(UserEntity user);

    List<BillEntity> findAllByOffer(OfferEntity offer);

    List<BillEntity> findAllByBillCreatedBetween(Date startDate, Date endDate);
}
