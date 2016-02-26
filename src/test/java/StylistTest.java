import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfStylistToList() {
    Stylist testStylist = new Stylist("Billy");
    Stylist testStylist1 = new Stylist("John");
    testStylist.save();
    testStylist1.save();
    assertEquals(2, Stylist.all().size());
  }

  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    testStylist.delete();
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void equalsReturnsTrueIfStylistNamesAreTheSame () {
    Stylist testStylist1 = new Stylist("Billy");
    Stylist testStylist2 = new Stylist("Billy");
    assertTrue(testStylist1.equals(testStylist2));
  }

  @Test
  public void find_findsInstanceOfStylistById() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    assertEquals(Stylist.find(testStylist.getId()), testStylist);
}

  @Test
  public void update_changesStylistName() {
    Stylist testStylist = new Stylist("Billy");
    testStylist.save();
    testStylist.updateStylistName("John");
    Stylist savedStylist = Stylist.find(testStylist.getId());
    assertEquals("John", savedStylist.getStylistName());
  }

}
