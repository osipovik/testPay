package oik.test_pay.repository;

import oik.test_pay.model.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, String> {

}
