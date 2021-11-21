package schedulerdto;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class DateDTO {
    int dayOfMonth;
    String dayOfWeek;
    int month;
    int year;
}
