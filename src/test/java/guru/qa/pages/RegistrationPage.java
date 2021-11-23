package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationPage {

  // locators & elements
  private final String FORM_TITLE = "Student Registration Form";
  private final String RESULT_FORM_TITLE = "Thanks for submitting the form";
  private SelenideElement
          formTitle = $(".practice-form-wrapper"),
          firstNameInput = $("#firstName"),
          lastNameInput = $("#lastName"),
          emailInput = $("#userEmail"),
  // genderRadio = $("[name=gender][value=" + testData.gender + "]"),
  userNumberInput = $("[placeholder = \"Mobile Number\"]"),
          subjectsInput = $("#subjectsInput"),
          hobbiesCheckbox = $("#hobbiesWrapper"),
          pictureInput = $("#uploadPicture"),
          addressTextArea = $("#currentAddress"),
          stateContainer = $("#state"),
          stateCityWrapper = $("#stateCity-wrapper"),
          cityContainer = $("#city"),
          submitButton = $("#submit"),
          resultFormTitle = $(".modal-title"),
          resultsTable = $(".table-responsive");
  private CalendarComponent calendar = new CalendarComponent();


  public RegistrationPage setDateOfBirth(String day, String month, String year) {
    calendar.setDate(day, month, year);
    return this;
  }

  // actions
  public void openPage() {
    open("/automation-practice-form");
    formTitle.shouldHave(text(FORM_TITLE));
  }

  public RegistrationPage typeFirstName(String firstName) {
    firstNameInput.setValue(firstName);
    return this;
  }

  public RegistrationPage typeLastName(String lastName) {
    lastNameInput.setValue(lastName);
    return this;
  }

  public RegistrationPage typeEmail(String email) {
    emailInput.setValue(email);
    return this;
  }

  public RegistrationPage typeMobileNumber(String userNumber) {
    userNumberInput.setValue(userNumber);
    return this;
  }

  public RegistrationPage selectGender(String gender) {
    $("[name=gender][value=" + gender + "]").parent().click();
    return this;
  }

  public RegistrationPage selectSubjectInput(String subject) {
    subjectsInput.setValue(subject).pressEnter();
    return this;
  }

  public RegistrationPage selectHobbiesCheckbox(String hobby) {
    hobbiesCheckbox.$(byText(hobby)).click();
    return this;
  }

  public RegistrationPage uploadPicture(String fileName) {
    pictureInput.uploadFromClasspath("img/" + fileName);
    return this;
  }

  public RegistrationPage typeAddress(String address) {
    addressTextArea.val(address);
    return this;
  }

  public RegistrationPage selectState(String state) {
    stateContainer.scrollIntoView(true).click();
    stateCityWrapper.$(byText(state)).click();
    return this;
  }

  public RegistrationPage selectCity(String city) {
    cityContainer.click();
    stateCityWrapper.$(byText(city)).click();
    return this;
  }

  public void submit() {
    submitButton.click();
  }

  public void checkResultsValue(Map<String, String> expectedData) {
    resultFormTitle.shouldHave(text(RESULT_FORM_TITLE));

    ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

    SoftAssertions softly = new SoftAssertions();

    for (SelenideElement line : lines) {
      String key = line.$("td").text(); // Student Name
      String expectedValue = expectedData.get(key);
      String actualValue = line.$("td", 1).text();

      softly.assertThat(actualValue)
              .as(format("Result in line %s was %s, but expected %s", key, actualValue, expectedValue))
              .isEqualTo(expectedValue);
    }
    softly.assertAll();
  }
}



