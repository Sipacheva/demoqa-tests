package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

  RegistrationPage registrationPage = new RegistrationPage();
  TestData testData = new TestData();

  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.startMaximized = true;
  }
}
