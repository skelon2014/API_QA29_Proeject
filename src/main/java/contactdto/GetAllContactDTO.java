package contactdto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class GetAllContactDTO {
    List<ContactDTO> contacts;
}
