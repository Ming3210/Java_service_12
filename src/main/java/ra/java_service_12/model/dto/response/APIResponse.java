package ra.java_service_12.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class APIResponse <T>{
    private Boolean sucess;
    private String message;
    private T data;
    private HttpStatus status;
}
