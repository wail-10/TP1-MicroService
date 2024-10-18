package ma.enset.tp1microservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.tp1microservice.dtos.BankAccountRequestDTO;
import ma.enset.tp1microservice.dtos.BankAccountResponseDTO;
import ma.enset.tp1microservice.entities.BankAccount;
import ma.enset.tp1microservice.repositories.BankAccountRepository;
import ma.enset.tp1microservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;

    @QueryMapping
    public List<BankAccount> accounts() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount account(@Argument String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Bank account %s not found", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        accountService.deleteAccount(id);
        return true;
    }
}

