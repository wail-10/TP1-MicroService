package ma.enset.tp1microservice.service;

import ma.enset.tp1microservice.dtos.BankAccountRequestDTO;
import ma.enset.tp1microservice.dtos.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    public List<BankAccountResponseDTO> getAccounts();
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountResponseDTO getAccount(String id);
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    public void deleteAccount(String id);
}
