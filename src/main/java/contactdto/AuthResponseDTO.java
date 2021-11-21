package contactdto;
//{
//        "token": "string"
//        }

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AuthResponseDTO {
    String token;
}
