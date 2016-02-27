import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylistName = request.queryParams("stylistName");
      Stylist newStylist = new Stylist(stylistName);
      newStylist.save();
      List<Stylist> stylistList = newStylist.all();
      model.put("stylists", stylistList);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Stylist.deleteStylist(id);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      List<Client> clients = stylist.getClients();
      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/addClient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      int id = Integer.parseInt(request.queryParams("stylistId"));
      String clientName = request.queryParams("clientName");
      Client newClient = new Client(clientName, id);
      newClient.save();
      List<Client> clients = stylist.getClients();
      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/addClient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Client.delete(id);
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      List<Client> clients = stylist.getClients();
      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/addClient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
