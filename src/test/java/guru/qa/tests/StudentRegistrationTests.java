package guru.qa.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationTests extends TestBase {

  @Test
  @Disabled
  void fillFormTest() {
    open("/automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    $("#firstName").setValue("Petr");
    $("#lastName").setValue("Ivanov");
    $("#userEmail").setValue("Ivanov@mail.ru");
    $("[name=gender][value=Female]").parent().click();
    $("#userNumber").setValue("7589654123");
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption("July");
    $(".react-datepicker__year-select").selectOption("2005");
    $(".react-datepicker__day--003:not(.react-datepicker__day--outside-month)").click();
    $("#subjectsInput").setValue("m");
    $(byText("Chemistry")).click();
    $("#hobbiesWrapper").$(byText("Reading")).click();
    $("#uploadPicture").uploadFromClasspath("img/1.jpg");
    $("#currentAddress").val("Москва, Южнопортовый проезд, д. 5, к. 2А");
    $("#state").scrollIntoView(true).click();
    $("#stateCity-wrapper").$(byText("Haryana")).click();
    $("#city").click();
    $("#stateCity-wrapper").$(byText("Panipat")).click();
    $("#submit").click();

    $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    $(".table-responsive").shouldHave(text("Petr Ivanov"), text("Ivanov@mail.ru"), text("Male"),
            text("7589654123"), text("03 July,2005"), text("Chemistry"), text("Reading"), text("1.jpg"),
            text("Москва, Южнопортовый проезд, д. 5, к. 2А"), text("Haryana Panipat"));
  }
}
