package ma.enset.tp1microservice.repositories;

import ma.enset.tp1microservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
