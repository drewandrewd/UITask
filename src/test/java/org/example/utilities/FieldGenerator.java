package org.example.utilities;

import java.util.Random;

public class FieldGenerator {

    private final String postCode;
    private final String firstName;
    private final String lastName;

    public FieldGenerator() {
        this.postCode = generateTenDigitPostCode();
        this.firstName = generateFirstNameFromPostCode(this.postCode);
        this.lastName = this.firstName;
    }

    private String generateTenDigitPostCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10)); // 0..9
        }
        return sb.toString();
    }

    private String generateFirstNameFromPostCode(String postCode) {
        StringBuilder name = new StringBuilder(5);
        for (int i = 0; i < 10; i += 2) {
            String pair = postCode.substring(i, i + 2);
            int value = Integer.parseInt(pair);
            int index = value % 26;
            char letter = (char) ('a' + index);
            name.append(letter);
        }
        return name.toString();
    }

    public String getPostCode() {
        return postCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
