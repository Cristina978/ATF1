package demoqa.project.utils;

import demoqa.project.configurations.properties.PropertiesManager;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import java.util.Random;

import java.util.regex.Pattern;

public class FakeDataGenerator {
    private static final Faker FAKER = new Faker();
    private static final Pattern NAME_PATTERN = Pattern.compile(PropertiesManager.getProperty("NAME_PATTERN.regexp"));
    private static final Pattern EMAIL_PATTERN = Pattern.compile(PropertiesManager.getProperty("EMAIL_PATTERN.regexp"));
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PropertiesManager.getProperty("PASSWORD_PATTERN.regexp"));

    public static String generateRandomEmail() {
        String email;
        do {
            email = FAKER.internet().emailAddress();
        } while (!EMAIL_PATTERN.matcher(email).matches());
        LogManager.getLogger().info("The random email was generated: {}", email);
        return email;
    }

    private static String generateRandomName(String nameType) {
        String name;
        do {
            name = (nameType.equals("first name")) ? FAKER.name().firstName() : FAKER.name().lastName();
        } while (!NAME_PATTERN.matcher(name).matches());
        LogManager.getLogger().info("The random {} was generated: {}", nameType, name);
        return name;
    }

    public static String generateRandomFirstName() {
        return generateRandomName("first name");
    }

    public static String generateRandomLastName() {
        return generateRandomName("last name");
    }

    public static Integer generateRandomAge() {
        return FAKER.number().numberBetween(18, 80);
    }

    public static Integer generateRandomSalary() {
        return FAKER.number().numberBetween(10000, 40000);
    }

    public static String generateRandomDepartment() {
        return FAKER.company().industry();
    }

    public static String generateRandomPassword() {
        String password;
        do {
            password = FAKER.internet().password();
        } while (!PASSWORD_PATTERN.matcher(password).matches());
        LogManager.getLogger().info("The random password was generated: {} ", password);
        return password;
    }
}
