package oik.test_pay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJSON(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

}
