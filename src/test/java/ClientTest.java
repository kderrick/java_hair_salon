import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Client firstClient = new Client("John Smith", 1);
    Client secondClient = new Client("John Smith", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client myClient = new Client("John Smith", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("John Smith", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientsInDatabase_true() {
    Client myClient = new Client("John Smith", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

}
