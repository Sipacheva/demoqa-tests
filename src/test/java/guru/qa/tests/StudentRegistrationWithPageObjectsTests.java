package guru.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;


public class StudentRegistrationWithPageObjectsTests extends TestBase {

  @Test
  void fillFormTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    registrationPage.openPage();
    registrationPage.typeFirstName(testData.firstName)
            .typeLastName(testData.lastName)
            .typeEmail(testData.email)
            .selectGender(testData.gender)
            .typeMobileNumber(testData.mobile)
            .setDateOfBirth(testData.day, testData.month, testData.year)
            .selectSubjectInput(testData.subject)
            .selectHobbiesCheckbox(testData.hobby)
            .uploadPicture(testData.fileName)
            .typeAddress(testData.address)
            .selectState(testData.state)
            .selectCity(testData.city)
            .submit();

    registrationPage.checkResultsValue(testData.getExpectedData());
  }
}
