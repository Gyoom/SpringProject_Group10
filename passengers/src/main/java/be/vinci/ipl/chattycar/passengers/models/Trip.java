package be.vinci.ipl.chattycar.passengers.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trip {
  private int id;
  private Position origin;
  private Position destination;
  private LocalDate departure;
  private int driverId;
  private int available_seating;
}
