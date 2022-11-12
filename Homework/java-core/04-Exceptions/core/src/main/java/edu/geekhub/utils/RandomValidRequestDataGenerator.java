package edu.geekhub.utils;

import edu.geekhub.models.User;
import edu.geekhub.models.request.Request;

import java.security.SecureRandom;
import java.util.UUID;

import static edu.geekhub.utils.RandomUtils.getRandomElement;
import static edu.geekhub.utils.RandomUtils.getRandomString;

public class RandomValidRequestDataGenerator implements RequestDataGenerator {

    private static final String[] VALID_DOMAINS = new String[]{"test.stub", "test-stub.bar", "foo.bar"};
    private static final String[] INVALID_DOMAINS = new String[]{"test,stub", "\"test.stub\"", "test .stub", "tes`t.stub",
        "test$bar", " foo.bar", " foo bar ", "'foo.bar'", "@@foo.bar"};
    private static final String[] NAMES = new String[]{"James", "John", "Emily", "Token", "Butters", "Cartman", "Bond"};

    private final SecureRandom random = new SecureRandom();

    @Override
    public Request generate() {
        Object requestData = generateRequestData();

        return new Request(requestData);
    }

    private Object generateRequestData() {
        return generateUserData();
    }

    public User generateUserData() {
        return User.toBuilder()
            .withId(getRandomId())
            .withEmail(getRandomEmail())
            .withUserName(getRandomUsername())
            .withFullName(getRandomFullName())
            .withAge(getRandomAge())
            .withNotes(getRandomNotes())
            .withAmountOfFollowers(getRandomAmountOfFollowers())
            .build();
    }

    private UUID getRandomId() {
        return UUID.randomUUID();
    }

    private String getRandomEmail() {
        return generateRandomEmail();
    }

    private String generateRandomEmail() {
        StringBuilder emailBuilder = new StringBuilder();

        emailBuilder.append(getRandomString(10));

        emailBuilder.append("@");

        String domain = getRandomElement(VALID_DOMAINS);
        emailBuilder.append(domain);

        return emailBuilder.toString();
    }

    private String getRandomUsername() {
        return getRandomString(random.nextInt(0, 30));
    }

    private String getRandomFullName() {
//        return null;
        return generateRandomFullName();
    }

    private String generateRandomFullName() {
        StringBuilder fullNameBuilder = new StringBuilder();


        String firstName = getRandomElement(NAMES);
        fullNameBuilder.append(firstName);

        fullNameBuilder.append(" ");

        String lastName = getRandomElement(NAMES);
        fullNameBuilder.append(lastName);

        String fullName = fullNameBuilder.toString();

        fullName = fullName.toLowerCase();

        return fullName;
    }


    private Integer getRandomAge() {
//        return null;
        return random.nextInt(-100, 1000);
    }

    private String getRandomNotes() {
//        return null;
        return getRandomString(100);
    }

    private Long getRandomAmountOfFollowers() {
//        Long result = 10000000l;
//        return null;
        return random.nextLong(-100, 10000000);
    }
}
