package ee.sandra.veebipood.repository;

import ee.sandra.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPerson_Id(Long id);
}
