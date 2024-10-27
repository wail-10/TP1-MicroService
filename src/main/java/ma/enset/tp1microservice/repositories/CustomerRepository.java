package ma.enset.tp1microservice.repositories;

import ma.enset.tp1microservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
