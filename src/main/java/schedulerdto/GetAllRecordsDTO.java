package schedulerdto;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class GetAllRecordsDTO {
    List<RecordDTO> records;
}
