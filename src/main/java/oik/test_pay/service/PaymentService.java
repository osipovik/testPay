package oik.test_pay.service;

import oik.test_pay.model.PaymentStateEnum;
import oik.test_pay.model.entity.PaymentEntity;
import oik.test_pay.model.request.PaymentRequest;
import oik.test_pay.model.response.PaymentResponse;
import oik.test_pay.repository.PaymentRepository;
import oik.test_pay.task.PaymentAsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentAsyncTask paymentAsyncTask;

    public PaymentResponse payment(PaymentRequest request) {
        PaymentEntity payment = paymentRepository.save(new PaymentEntity(request));

        paymentAsyncTask.startTaskForPayment(payment);

        return new PaymentResponse(
                payment.getPaymentId(),
                LocalDateTime.now().toString(),
                PaymentStateEnum.created
        );
    }

}
