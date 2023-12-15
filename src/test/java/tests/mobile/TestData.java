package tests.mobile;

import com.github.javafaker.Faker;
public class TestData {
    Faker faker = new Faker();
    String username = faker.name().firstName();
    String email = faker.internet().emailAddress();
    String wrongEmail = faker.lorem().characters(9);
    String password = faker.random().hex(9);
    String shortPassword = faker.random().hex(3);


}
