package tests;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();
    public String username = "solonin.vad@gmail.com";
    public String password = "Tt1!esp@ss";
    public String randomUsername = faker.name().firstName();
    public String email = faker.internet().emailAddress();
    public String wrongEmail = faker.lorem().characters(9);
    public String randomPassword = faker.random().hex(9);
    public String shortPassword = faker.random().hex(3);
    public String randomAuthor = faker.book().author();


}
