import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfClientToList() {
    Client testClient = new Client("Tina");
    Client testClient1 = new Client("Jean");
    testClient.save();
    testClient1.save();
    assertEquals(2, Client.all().size());
  }

  @Test
  public void updateName_changesClientName() {
    Client testClient = new Client("Tina");
    testClient.save();
    testClient.updateName("Jean");
    Client savedClient = Client.find(testClient.getId());
    assertEquals("Jean", savedClient.getName());
  }
//
  @Test
  public void delete_removesClientFromDatabase() {
    Client testClient = new Client("Tina");
    testClient.save();
    testClient.delete();
    assertEquals(0, Client.all().size());
  }
//
  @Test
  public void find_findsInstanceOfClientById() {
    Client testClient = new Client("Tina");
    testClient.save();
    assertEquals(Client.find(testClient.getId()), testClient);
  }
//
  @Test
  public void assignStylist_assignsStylistToTheClient() {
    Client testClient = new Client("Tina");
    testClient.save();
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    testClient.assignStylist(testStylist.getId());
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }
//
  @Test
  public void clearStylist_removesStylistFromClient() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    Client testClient = new Client("Tina");
    testClient.save();
    testClient.assignStylist(testStylist.getId());
    testClient.clearStylist();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), 0);
  }
}
