package oik.test_pay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRequest implements Serializable {
    private static final long serialVersionUID = -1789965729384320819L;

    private String currency;
    private BigDecimal amount;
    private String id;
    @JsonProperty("external_id")
    private String externalId;
    private String status;
    private String sha2sig;
}
