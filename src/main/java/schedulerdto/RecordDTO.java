package schedulerdto;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class RecordDTO {
    int breaks;
    String currency;
    DateDTO date;
    int hours;
    int id;
    String timeFrom;
    String timeTo;
    String title;
    int totalSalary;
    String type;
    int wage;

}
