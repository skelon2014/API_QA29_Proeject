package contactdto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ContactDTO {
    String address;
    String description;
    String email;
    int id;
    String lastName;
    String name;
    String phone;
}
