import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Jane");
    Stylist secondStylist = new Stylist("Jane");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist zStylist = new Stylist("Zoe");
    zStylist.save();
    Stylist myStylist = new Stylist("Jane");
    myStylist.save();
    Stylist aStylist = new Stylist("Anne");
    aStylist.save();
    assertTrue(Stylist.all().get(1).equals(myStylist));
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Jane");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Jane");
    myStylist.save();
    Client firstClient = new Client("John Smith", 1);
    firstClient.save();
    Client secondClient = new Client("Kelly Smith", 1);
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
