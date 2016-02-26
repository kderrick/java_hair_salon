import java.util.List;
import org.sql2o.*;


public class Stylist {
  private String stylistName;
  private int id;

  public Stylist (String stylistName) {
    this.stylistName = stylistName;
  }

  public String getStylistName() {
    return stylistName;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, stylistName FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO stylists(stylistName) VALUES (:stylistName)";
    try(Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery(sql,true)
      .addParameter("stylistName", stylistName)
      .executeUpdate()
      .getKey();
    }
  }
}
