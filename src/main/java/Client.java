import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private int id;
  // private int stylistId;

  public Client (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }


  public static List<Client> all() {
    String sql = "SELECT id, name FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
      this.getId() == newClient.getId();
      // this.getStylistId() == newClient.getStylistId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
       .addParameter("name", this.name)
      //  .addParameter("doctorId", this.doctorId)
       .executeUpdate()
       .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;

    }
  }


}
