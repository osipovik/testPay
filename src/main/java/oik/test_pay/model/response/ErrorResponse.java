package oik.test_pay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -4160306949997547791L;

    @NonNull
    private String error;
    @JsonProperty("error_description")
    @NonNull
    private String errorDescription;
}
