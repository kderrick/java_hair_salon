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
    return this.name;
  }

  public int getId() {
    return this.id;
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
      return this.getName().equals(newClient.getName());
      // this.getId() == newPatient.getId() &&
      // this.getBirthDate().equals(newPatient.getBirthDate()) &&
      // this.getDoctorId() == newPatient.getDoctorId();
  }
}

}
