package ma.enset.tp1microservice.service;

import ma.enset.tp1microservice.dtos.BankAccountRequestDTO;
import ma.enset.tp1microservice.dtos.BankAccountResponseDTO;
import ma.enset.tp1microservice.entities.BankAccount;
import ma.enset.tp1microservice.mappers.AccountMapper;
import ma.enset.tp1microservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<BankAccountResponseDTO> getAccounts() {
        List<BankAccountResponseDTO> bankAccountResponseDTOs = new ArrayList<>();
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        for (BankAccount bankAccount : bankAccounts) {
            bankAccountResponseDTOs.add(accountMapper.fromBankAccount(bankAccount));
        }
        return bankAccountResponseDTOs;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency()).build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO getAccount(String id) {
        return accountMapper.fromBankAccount(bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Bank account %s not found", id))));
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Bank account %s not found", id)));
        bankAccount.setBalance(bankAccountDTO.getBalance());
        bankAccount.setCurrency(bankAccountDTO.getCurrency());
        bankAccount.setType(bankAccountDTO.getType());
        bankAccount.setCreatedAt(new Date());
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
