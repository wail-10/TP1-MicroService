package ma.enset.tp1microservice;

import ma.enset.tp1microservice.entities.BankAccount;
import ma.enset.tp1microservice.entities.Customer;
import ma.enset.tp1microservice.enums.AccountType;
import ma.enset.tp1microservice.repositories.BankAccountRepository;
import ma.enset.tp1microservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp1MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp1MicroServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {
			Stream.of("Mohammed", "Yassine", "Omar", "Imane", "Houda").forEach(name -> {
				Customer customer = Customer.builder()
						.name(name)
						.build();
				customerRepository.save(customer);
			});

			customerRepository.findAll().forEach(customer -> {
				for (int i = 0; i < 10; i++) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT: AccountType.SAVINGS_ACCOUNT)
							.balance(10000 + Math.random()*90000)
							.createdAt(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});
		};
	}

}
