package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.helpers.Attach;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase {

  RegistrationPage registrationPage = new RegistrationPage();
  TestData testData = new TestData();

  @BeforeAll
  static void beforeAll() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.startMaximized = true;
    Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);

    Configuration.browserCapabilities = capabilities;
  }

  @AfterEach
  public void tearDown() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }
}
