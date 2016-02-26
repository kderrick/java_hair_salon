import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int stylist_id;
  private String name;

  public Cuisine (String name) {
    this.name = name;
  }

  public int getId() {
    return stylist_id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }
//
//   //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylists(name) VALUES (:name)";
    this.stylist_id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }
//
//   //READ
  public static List<Stylist> all() {
    String sql = "SELECT stylist_id, type FROM stylist";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
//
//   //UPDATE
  public void update(String newName) {
    String sql = "UPDATE stylists SET name = :name WHERE stylist_id = :stylist_id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("stylist_id", stylist_id)
        .executeUpdate();
    }
  }
// //
//   //DELETE
  public void delete() {
    this.clearAllAssigned();
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE stylist_id = :stylist_id";
        con.createQuery(sql)
          .addParameter("stylist_id", stylist_id)
          .executeUpdate();
    }
  }
//
  public static Stylist find(int stylist_id) {
    String sql = "SELECT stylist_id, type FROM stylists WHERE stylist_id = :stylist_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetchFirst(Stylist.class);
    }
  }
//
  public List<Client> getClients() {
    String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetch(Client.class);
    }
  }
//
  public static List<Client> getUnassignedClients() {
    String sql = "SELECT * FROM clients WHERE stylist_id IS NULL";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }
//
  public static void deleteAll(){
  try(Connection con = DB.sql2o.open()) {
    String deleteStylistQuery = "DELETE FROM stylists *;";
    con.createQuery(deleteStylistQuery).executeUpdate();
    }
  }
//
  public void clearAllAssigned() {
    List<Client> clientsToClear = this.getClients();
    for (Client client : clientsToClear) {
      client.clearStylist();
    }
  }
}
