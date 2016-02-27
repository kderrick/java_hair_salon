import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#stylistName").with("Billy");
    submit(".btn-primary");
    assertThat(pageSource()).contains("Billy");
  }

  @Test
  public void clientIsCreatedTest() {
    goTo("http://localhost:4567/");
    Stylist myStylist = new Stylist("Billy");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Billy");
    Client clients = new Client("Tina", myStylist.getId());
    clients.save();
    goTo(stylistPath);
    assertThat(pageSource()).contains("Tina");
  }

  @Test
  public void stylistIsDeletedTest() {
    String path = "http://localhost:4567/";
    goTo(path);
    Stylist myStylist = new Stylist("Billy");
    myStylist.save();
    goTo(path);
    myStylist.deleteStylist(myStylist.getId());
    goTo(path);
    assertThat((pageSource()).contains("Billy") == false);
  }

  @Test
  public void clientIsDeletedTest() {
    goTo("http://localhost:4567/");
    Stylist myStylist = new Stylist("Billy");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Billy");
    Client clients = new Client("Tina", myStylist.getId());
    clients.save();
    goTo(stylistPath);
    assertThat(pageSource()).contains("Tina");
    clients.delete(clients.getId());
    goTo(stylistPath);
    assertThat((pageSource()).contains("Tina") == false);
  }
}
