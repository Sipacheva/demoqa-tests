package guru.qa.tests;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestData {

  Faker faker = new Faker(new Locale("ru"));
  Faker fakerEN = new Faker(new Locale("en"));

  public String genderForName = faker.options().option("female", "male");
  public String firstName = faker.expression("#{name." + genderForName + "_first_name}");
  public String lastName = faker.expression("#{name." + genderForName + "_last_name}");
  public String email = fakerEN.internet().emailAddress();
  public String gender = faker.options().option("Female", "Male", "Other");
  public String mobile = faker.number().digits(10);
  public String day = "03";
  public String month = "July";
  public String year = "2005";
  public String subject = faker.options().option("Chemistry", "Maths", "Computer Science");
  public String hobby = faker.options().option("Sports", "Reading", "Music");
  public String fileName = ("1.jpg");
  public String address = faker.address().streetAddress();
  public String state = ("Haryana");
  public String city = ("Panipat");

  private Map<String, String> expectedData = new HashMap<String, String>() {{
    put("Student Name", firstName + " " + lastName);
    put("Student Email", email);
    put("Gender", gender);
    put("Mobile", mobile);
    put("Date of Birth", day + " " + month + "," + year);
    put("Subjects", subject);
    put("Hobbies", hobby);
    put("Picture", fileName);
    put("Address", address);
    put("State and City", state + " " + city);
  }};

  public Map<String, String> getExpectedData() {
    return expectedData;
  }
}
