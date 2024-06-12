package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.BankAccountEntity;
import com.iktpreobuka.project.entities.dto.BankAccountDto;

public interface BankAccountDao {
    public Iterable<BankAccountEntity> getAllBankAccounts();

    public BankAccountDto addNewBankAccount(BankAccountDto bankAccountDto, String userId);

    public BankAccountDto modifyExistingBankAccount(String id, BankAccountDto changedBankAccount);

    public BankAccountDto deleteBankAccountById(String id);
}
