package edu.geekhub.homework;

import edu.geekhub.homework.validation.UserValidator;
import edu.geekhub.homework.validation.exeption.UserValidationException;
import edu.geekhub.models.User;
import edu.geekhub.storage.MemoryStorage;
import edu.geekhub.storage.Repository;
import edu.geekhub.utils.RandomRequestDataGenerator;
import edu.geekhub.utils.RandomValidRequestDataGenerator;

public class Test {

    public static void main(String[] args) {
        Repository repository = new MemoryStorage();
        RandomRequestDataGenerator requestDataGenerator = new RandomRequestDataGenerator();

//        RandomValidRequestDataGenerator requestDataGenerator = new RandomValidRequestDataGenerator();

//        while (true) {
//            System.out.println(requestDataGenerator.generateUserData());
//            System.out.println();
//        }
//        repository.Add(requestDataGenerator.generateUserData());
//        repository.Add(requestDataGenerator.generateUserData());
//        repository.Add(requestDataGenerator.generateUserData());

        UserValidator userValidator = new UserValidator(repository);

        for (long i = 0; i < 1000000l; i++) {
            User user = requestDataGenerator.generateUserData();
//            System.out.println(user);
//            System.out.println();
            try {
                userValidator.validateUser(user);
                repository.Add(user);
                System.out.println("add");
                System.out.println();
                System.out.println(user);
                System.out.println();
            } catch (UserValidationException e) {
//                System.out.println(e.getMessage());
            }
        }
        System.out.println("done");



//        UserValidator userValidator = new UserValidator(requestDataGenerator.generateUserData(), repository);
//        try {
//            System.out.println(userValidator.validateUser());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

    }
}
