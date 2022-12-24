package edu.geekhub.homework;

import edu.geekhub.exceptions.ConnectionInterruptedException;
import edu.geekhub.homework.exceptions.RepositorySavingException;
import edu.geekhub.homework.validation.user.UserValidator;
import edu.geekhub.homework.validation.user.exceptions.UserValidationException;
import edu.geekhub.models.User;
import edu.geekhub.storage.MemoryStorage;
import edu.geekhub.storage.Repository;

// Don't move this class
public class UserService {

    private final Repository repository;
    private final UserValidator userValidator;

    public UserService(Repository repository) {
        this.repository = repository;
        userValidator = new UserValidator(repository);
    }

    public void saveUserToRepository(User user) {
        try {
            userValidator.validateUser(user);
            repository.tryToAdd(user);
        } catch (ConnectionInterruptedException e) {
            System.out.println(e.getMessage());
            throw new RepositorySavingException(e.getMessage(), e);
        } catch (UserValidationException e) {
            e.printExceptionInfo();
            throw new RepositorySavingException(e.getMessage(), e);
        }
    }
}
