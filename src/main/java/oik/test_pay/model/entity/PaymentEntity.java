package oik.test_pay.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import oik.test_pay.model.PaymentIntentEnum;
import oik.test_pay.model.PaymentStateEnum;
import oik.test_pay.model.request.PaymentRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 7490310662588070413L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;
    private PaymentIntentEnum intent;
    @Column(name = "notification_url")
    private String notificationUrl;
    @Column(name = "payer_email")
    private String payerEmail;
    @Column(name = "transaction_external_id")
    private String transactionExternalId;
    @Column(name = "transaction_amount_value")
    private BigDecimal transactionAmountValue;
    @Column(name = "transaction_amount_currency")
    private String transactionAmountCurrency;
    @Column(name = "transaction_description")
    private String transactionDescription;
    @Column(name = "state")
    private PaymentStateEnum state;

    public PaymentEntity(PaymentRequest request) {
        intent = PaymentIntentEnum.valueOf(request.getIntent());
        notificationUrl = request.getNotificationUrl();
        payerEmail = request.getPayer().getEmail();
        transactionExternalId = request.getTransaction().getExternalId();
        transactionAmountValue = request.getTransaction().getAmount().getValue();
        transactionAmountCurrency = request.getTransaction().getAmount().getCurrency();
        transactionDescription = request.getTransaction().getDescription();
        state = PaymentStateEnum.created;
    }
}
