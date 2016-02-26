import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
  }
//
  @Test
  public void save_addsAllInstancesOfStylistToList() {
    Stylist testStylist = new Stylist("Billy");
    Stylist testStylist1 = new Stylist("John");
    testStylist.save();
    testStylist1.save();
    assertEquals(2, Stylist.all().size());
  }
//
  @Test
  public void update_changesStylistType() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    testStylist.update("John");
    Stylist savedStylist = Stylist.find(testStylist.getId());
    assertEquals("John", savedStylist.getName());
  }
//
  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    testStylist.delete();
    assertEquals(0, Stylist.all().size());
  }
//
  @Test
  public void delete_removesStylistFromClientsAssignedWithIt() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    Client testClient = new Client("Tina");
    testClient.save();
    testClient.assignStylist(testStylist.getId());
    testStylist.delete();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), 0);
  }
//
  @Test
  public void find_findsInstanceOfStylistById() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    assertEquals(Stylist.find(testStylist.getId()), testStylist);
  }
//
  @Test
  public void getClients_getAllClientsWithinAStylist() {
    Stylist testStylist = new Stylist("Sandwiches");
    testStylist.save();
    Client testClient = new Client("Bunk", "Sandwich shop");
    Client testClient1 = new Client("Lardo", "Pork-centric sandwich shop");
    testClient.save();
    testClient1.save();
    testClient.assignStylist(testStylist.getId());
    testClient1.assignStylist(testStylist.getId());
    assertEquals(2,testStylist.getClients().size());
  }
//
  @Test
  public void getUnassignedClients_getAllClientsWithoutAStylist() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    Client testClient = new Client("Tina");
    Client testClient1 = new Client("Jean");
    testClient.save();
    testClient1.save();
    testClient.assignStylist(testStylist.getId());
    assertEquals(1,Cuisine.getUnassignedClients().size());
  }
//
  @Test
  public void clearAllAssigned_unassignsAllClientsAssignedToStylist() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    Client testClient = new Client("Tina");
    Client testClient1 = new Client("Jean");
    testClient.save();
    testClient1.save();
    testClient.assignStylist(testStylist.getId());
    testClient1.assignStylist(testStylist.getId());
    testStylist.clearAllAssigned();
    assertEquals(0, testStylist.getClients().size());
  }
}
