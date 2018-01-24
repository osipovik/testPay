package oik.test_pay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oik.test_pay.model.PaymentStateEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse implements Serializable {
    private static final long serialVersionUID = 7876822955305551032L;

    private String id;
    @JsonProperty("create_time")
    private String createTime;
    private PaymentStateEnum state;
}
