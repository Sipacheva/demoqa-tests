package guru.qa.tests.properties;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
  @Disabled
  @Test
  @Tag("properties")
  void selectBowser() {
    String value = System.getProperty("browser", "chrome");
    System.out.println(value);
  }
}
