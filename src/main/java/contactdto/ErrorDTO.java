package contactdto;
//{
//        "code": 0,
//        "details": "string",
//        "message": "string",
//        "timestamp": "2021-11-18T11:56:35.782Z"
//        }
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ErrorDTO {
    int code;
    String details;
    String message;
}
