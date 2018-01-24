package oik.test_pay.controller;

import oik.test_pay.model.request.PaymentRequest;
import oik.test_pay.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class PaymentController {

    private static final Logger LOG = LogManager.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payments/payment", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Serializable payment(@RequestBody PaymentRequest request) {
        return paymentService.payment(request);
    }

}
