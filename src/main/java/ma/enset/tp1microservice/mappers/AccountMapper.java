package ma.enset.tp1microservice.mappers;

import ma.enset.tp1microservice.dtos.BankAccountResponseDTO;
import ma.enset.tp1microservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount toBankAccount(BankAccountResponseDTO bankAccountResponseDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountResponseDTO, bankAccount);
        return bankAccount;
    }
}
