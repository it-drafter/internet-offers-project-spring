package com.iktpreobuka.project.entities.mapper;

import com.iktpreobuka.project.entities.BankAccountEntity;
import com.iktpreobuka.project.entities.dto.BankAccountDto;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccountDto toDtoFromBankAccountEntity(BankAccountEntity bankAccountEntity) {
        BankAccountDto bankAccountDto = new BankAccountDto();

        bankAccountDto.setAccountNumber(bankAccountEntity.getAccountNumber());
        bankAccountDto.setAccountType(bankAccountEntity.getAccountType());
        bankAccountDto.setId(bankAccountEntity.getId());
        bankAccountDto.setActive(bankAccountEntity.getActive());
        bankAccountDto.setDateClosed(bankAccountEntity.getDateClosed());
        bankAccountDto.setDateCreated(bankAccountEntity.getDateCreated());
        bankAccountDto.setUser(bankAccountEntity.getUser());

        return bankAccountDto;
    }

    public BankAccountEntity toEntityFromBankAccountDto(BankAccountDto bankAccountDto) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();

        bankAccountEntity.setAccountNumber(bankAccountDto.getAccountNumber());
        bankAccountEntity.setAccountType(bankAccountDto.getAccountType());
        bankAccountEntity.setActive(bankAccountDto.getActive());
        bankAccountEntity.setDateClosed(bankAccountDto.getDateClosed());
        bankAccountEntity.setDateCreated(bankAccountDto.getDateCreated());
        bankAccountEntity.setUser(bankAccountDto.getUser());

        return bankAccountEntity;
    }
}
