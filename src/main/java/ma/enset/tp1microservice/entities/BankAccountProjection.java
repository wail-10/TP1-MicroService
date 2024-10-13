package ma.enset.tp1microservice.entities;

import ma.enset.tp1microservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1", types = BankAccount.class)
public interface BankAccountProjection {
    public String getId();
    public Double getBalance();
    public AccountType getType();
}
