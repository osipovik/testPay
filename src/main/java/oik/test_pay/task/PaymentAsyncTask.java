package oik.test_pay.task;

import oik.test_pay.model.PaymentStateEnum;
import oik.test_pay.model.entity.PaymentEntity;
import oik.test_pay.repository.PaymentRepository;
import oik.test_pay.webhook.WebhookSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PaymentAsyncTask {

    private static final Logger LOG = LogManager.getLogger(PaymentAsyncTask.class);

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private WebhookSender sender;

    @Async
    public void startTaskForPayment(PaymentEntity payment) {
        LOG.debug("some long task");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        payment.setState(PaymentStateEnum.approved);

        paymentRepository.save(payment);

        sender.send(payment);
    }

}
