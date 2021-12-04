package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import guru.qa.helpers.Attach;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;


public class TestBase {
  RegistrationPage registrationPage = new RegistrationPage();
  TestData testData = new TestData();

  @BeforeAll
  static void beforeAll() {
    CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);
    SelenideLogger.addListener("allure", new AllureSelenide());
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.startMaximized = true;
    String login = credentials.login();
    String password = credentials.password();
    String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");
    Configuration.remote = format("https://%s:%s@%s", login, password, url);

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
