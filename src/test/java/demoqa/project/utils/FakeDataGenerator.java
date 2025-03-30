package demoqa.project.utils;

import demoqa.project.configurations.properties.PropertiesManager;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import java.util.regex.Pattern;

public class FakeDataGenerator {
    private static final Faker FAKER = new Faker();
    private static final Pattern NAME_PATTERN = Pattern.compile(PropertiesManager.getProperty("NAME_PATTERN.regexp"));
    private static final Pattern EMAIL_PATTERN = Pattern.compile(PropertiesManager.getProperty("EMAIL_PATTERN.regexp"));


    private static String generateRandomName(String nameType) {
        String name;
        do {
            name = (nameType.equals("first name")) ? FAKER.name().firstName() : FAKER.name().lastName();
        } while (!NAME_PATTERN.matcher(name).matches());
        LogManager.getLogger().info("The random {} was generated: {}", nameType, name);
        return name;
    }


    public static String generateRandomEmail() {
        String email;
        do {
            email = FAKER.internet().emailAddress();
        } while (!EMAIL_PATTERN.matcher(email).matches());
        LogManager.getLogger().info("The random email was generated: {}", email);
        return email;
    }


    public static String generateRandomFirstName() { return generateRandomName("first name");}

    public static String generateRandomLastName() {
        return generateRandomName("last name");
    }

    public static Integer generateRandomAge() {
        return FAKER.number().numberBetween(1, 99);
    }

    public static Integer generateRandomSalary() {
        return FAKER.number().numberBetween(1, 1000);
    }

    public static String generateRandomDepartment() {
        return FAKER.company().industry();
    }
}
