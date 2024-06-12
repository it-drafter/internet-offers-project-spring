package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.BankAccountEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.dto.BankAccountDto;
import com.iktpreobuka.project.entities.mapper.BankAccountMapper;
import com.iktpreobuka.project.repositories.BankAccountRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankAccountDaoImpl implements BankAccountDao {
    @Autowired
    protected BankAccountRepository bankAccountRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected BankAccountMapper bankAccountMapper;

    @Override
    public Iterable<BankAccountEntity> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccountDto addNewBankAccount(BankAccountDto bankAccountDto, String userId) {
        UserEntity userDb = userRepository.findById(Integer.parseInt(userId)).orElse(null);

        if (userDb != null) {
            BankAccountEntity bankAccountEntity = bankAccountMapper.toEntityFromBankAccountDto(bankAccountDto);

            bankAccountEntity.setUser(userDb);
            bankAccountEntity.setDateCreated(new Date());

            bankAccountRepository.save(bankAccountEntity);

            BankAccountDto bankAccountDtoResponse = bankAccountMapper.toDtoFromBankAccountEntity(bankAccountEntity);

            return bankAccountDtoResponse;
        }

        return null;
    }

    //Without DTO:
//    @Override
//    public BankAccountEntity modifyExistingBankAccount(String id, BankAccountEntity changedBankAccount) {
//        BankAccountEntity bankAccountDb = bankAccountRepository.findById(Integer.parseInt(id)).orElse(null);
//
//        if (bankAccountDb != null) {
//            if (changedBankAccount.getAccountNumber() != null) {
//                bankAccountDb.setAccountNumber(changedBankAccount.getAccountNumber());
//            }
//
//            if (changedBankAccount.getAccountType() != null) {
//                bankAccountDb.setAccountType(changedBankAccount.getAccountType());
//            }
//
//            if (changedBankAccount.getDateCreated() != null) {
//                bankAccountDb.setDateCreated(changedBankAccount.getDateCreated());
//            }
//
//            if (changedBankAccount.getDateClosed() != null) {
//                bankAccountDb.setDateClosed(changedBankAccount.getDateClosed());
//            }
//
//            if (changedBankAccount.getActive() != null) {
//                bankAccountDb.setActive(changedBankAccount.getActive());
//            }
//
//            bankAccountRepository.save(bankAccountDb);
//
//            return bankAccountDb;
//        }
//
//        return null;
//    }

    //With DTO:
    @Override
    public BankAccountDto modifyExistingBankAccount(String id, BankAccountDto changedBankAccount) {
        BankAccountEntity bankAccountDb = bankAccountRepository.findById(Integer.parseInt(id)).orElse(null);

        if (bankAccountDb != null) {
            if (changedBankAccount.getAccountNumber() != null) {
                bankAccountDb.setAccountNumber(changedBankAccount.getAccountNumber());
            }

            if (changedBankAccount.getAccountType() != null) {
                bankAccountDb.setAccountType(changedBankAccount.getAccountType());
            }

            if (changedBankAccount.getDateCreated() != null) {
                bankAccountDb.setDateCreated(changedBankAccount.getDateCreated());
            }

            if (changedBankAccount.getDateClosed() != null) {
                bankAccountDb.setDateClosed(changedBankAccount.getDateClosed());
            }

            if (changedBankAccount.getActive() != null) {
                bankAccountDb.setActive(changedBankAccount.getActive());
            }

            bankAccountRepository.save(bankAccountDb);

            BankAccountDto bankAccountDtoResponse = bankAccountMapper.toDtoFromBankAccountEntity(bankAccountDb);

            return bankAccountDtoResponse;
        }

        return null;
    }

    @Override
    public BankAccountDto deleteBankAccountById(String id) {
        BankAccountEntity bankAccountDb = bankAccountRepository.findById(Integer.parseInt(id)).orElse(null);

        if (bankAccountDb != null) {
            bankAccountRepository.delete(bankAccountDb);

            BankAccountDto bankAccountDtoResponse = bankAccountMapper.toDtoFromBankAccountEntity(bankAccountDb);

//            return bankAccountDb;
            return bankAccountDtoResponse;
        }

        return null;
    }
}
