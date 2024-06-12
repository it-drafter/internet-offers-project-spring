package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.BankAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccountEntity, Integer> {
}
