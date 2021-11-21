package schedulerdto;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class AuthResponseDTO {
    boolean registration;
    String status;
    String token;
}
