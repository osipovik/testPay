package oik.test_pay.webhook;

import oik.test_pay.model.entity.PaymentEntity;
import oik.test_pay.model.request.WebhookRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class WebhookSender {

    private static final Logger LOG = LogManager.getLogger(WebhookSender.class);

    private RestTemplate restTemplate;

    public void send(PaymentEntity payment) {
        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //hardcoded secret word
        String secretWord = "secret";
        String secretSha2Key = getSha256String(secretWord).toUpperCase();


        StringBuilder contatenateData = new StringBuilder();
        contatenateData
                .append(payment.getTransactionAmountCurrency())
                .append(payment.getTransactionAmountValue())
                .append(payment.getPaymentId())
                .append(payment.getTransactionExternalId())
                .append(payment.getState().toString())
                .append(secretSha2Key);

        WebhookRequest request = new WebhookRequest(
                payment.getTransactionAmountCurrency(),
                payment.getTransactionAmountValue(),
                payment.getPaymentId(),
                payment.getTransactionExternalId(),
                payment.getState().toString(),
                getSha256String(contatenateData.toString())
        );

        RequestEntity<WebhookRequest> requestEntity = new RequestEntity<>(
                request,
                headers,
                HttpMethod.POST,
                URI.create(payment.getNotificationUrl())
        );

        LOG.debug(requestEntity.toString());

        try {
            ResponseEntity<Object> responseEntity =
                    restTemplate.exchange(requestEntity, Object.class);
            LOG.debug(responseEntity);
        } catch (HttpServerErrorException | HttpClientErrorException
                | UnknownHttpStatusCodeException e) {

            LOG.error("send request error. Msg: {}, responseHeaders: {}, responseBody: {}",
                    e.getMessage(), e.getResponseHeaders(), e.getResponseBodyAsString());
            LOG.error("Error", e);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }

    private String getSha256String(String str) {
        MessageDigest md;
        StringBuilder hexString = new StringBuilder();

        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] mdBytes = md.digest(str.getBytes());
            for (int i=0;i<mdBytes.length;i++) {
                hexString.append(Integer.toHexString(0xFF & mdBytes[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }
}
