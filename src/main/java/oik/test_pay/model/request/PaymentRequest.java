package oik.test_pay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PaymentRequest implements Serializable {
    private static final long serialVersionUID = -6184894298135735353L;

    @JsonProperty("intent")
    @NonNull
    public String intent;
    @JsonProperty("notification_url")
    @NonNull
    public String notificationUrl;
    @JsonProperty("payer")
    @NonNull
    public Payer payer;
    @JsonProperty("transaction")
    @NonNull
    public Transaction transaction;

    @Data
    public static class Payer implements Serializable {
        private static final long serialVersionUID = 2172448758536343537L;

        @JsonProperty("email")
        @NonNull
        public String email;
    }

    @Data
    public static class Transaction implements Serializable {
        private static final long serialVersionUID = 7692804436262353424L;

        @JsonProperty("external_id")
        public String externalId;
        @JsonProperty("amount")
        @NonNull
        public Amount amount;
        @JsonProperty("description")
        public String description;

        @Data
        public static class Amount implements Serializable {
            private static final long serialVersionUID = -3503450360198760437L;

            @JsonProperty("value")
            @NonNull
            public BigDecimal value;
            @JsonProperty("currency")
            @NonNull
            public String currency;

        }
    }
}
