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

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName()) &&
        this.getId() == newStylist.getId();
  }
}

  public static List<Stylist> all() {
    String sql = "SELECT id, stylistName FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
  //CREATE
  public void save() {
    String sql = "INSERT INTO stylists(stylistName) VALUES (:stylistName)";
    try(Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery(sql,true)
      .addParameter("stylistName", stylistName)
      .executeUpdate()
      .getKey();
    }
  }
  ///DELETE
  public void delete() {
    String sql = "DELETE FROM stylists WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  //UPDATE

}
