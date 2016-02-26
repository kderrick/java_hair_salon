import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyATFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void equalsReturnsTrueIfNamesAreTheSame () {
    Client testClient1 = new Client("Billy");
    Client testClient2 = new Client("Billy");
    assertTrue(testClient1.equals(testClient2));
  }
}
