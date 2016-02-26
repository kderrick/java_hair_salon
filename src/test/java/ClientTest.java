import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // @Test
  // public void all_emptyATFirst() {
  //   assertEquals(0, Patient.all().size());
  // }
}
