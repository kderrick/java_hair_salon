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

  @Test
  public void saveAddsAllInstancesOfClientToList () {
    Client testClient1 = new Client("Billy");
    testClient1.save();
    assertTrue(Client.all().get(0).equals(testClient1));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("John");
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void findLocatesASpecificInstanceOfClientBasedOffId() {
    Client newClient = new Client("John");
    newClient.save();
    assertEquals(Client.find(newClient.getId()), newClient);

  }

  @Test
  public void updateName_changesClientName() {
    Client testClient = new Client("Billy");
    testClient.save();
    testClient.updateName("Charles");
    Client savedClient = Client.find(testClient.getId());
    assertEquals("Charles", savedClient.getName());
  }

  @Test
  public void deleteRemovesClientFromDatabase() {
    Client testClient = new Client("Billy");
    testClient.save();
    testClient.deleteClient();
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void deleteAllRemovesAllClients_forClearingProductionDatabase() {
    Client testClient = new Client("Billy");
    testClient.save();
    Client testClient1 = new Client("John");
    testClient1.save();
    Client.deleteAll();
    assertEquals(Client.all().size(), 0);
  }
}
