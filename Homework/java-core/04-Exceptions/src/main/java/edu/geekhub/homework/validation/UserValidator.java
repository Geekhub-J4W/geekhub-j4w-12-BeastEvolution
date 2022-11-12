package edu.geekhub.homework.validation;

import edu.geekhub.exceptions.ConnectionInterruptedException;
import edu.geekhub.homework.operations.*;
import edu.geekhub.homework.validation.exeption.*;
import edu.geekhub.models.User;
import edu.geekhub.storage.Repository;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final int LOVER_AGE_VALUE_LIMIT = 18;
    private static final int UPPER_AGE_VALUE_LIMIT = 100;

    private static final int ALLOWED_NUMBER_OF_CHARACTERS_IN_NOTES = 255;

    User user;
    Repository repository;

    public UserValidator(Repository repository) {
        this.repository = repository;
    }

    public void validateUser(User user) {
        if (Objects.isNull(user)) {
            throw  new UserValidationException("User equal \"null\"", new NullPointerException());
        }
        this.user = user;
        try {
            validateUserId();
            validateUserEmail();
            validateUserName();
            validateFullName();
            validateUserAge();
            validateNotes();
            validateAmountOfFollowers();
        } catch (UserValidationException e) {
            throw e;
        }

//        UserValidationException userValidationException =
//            new UserValidationException("User data is not valid");
//        try {
//            validateUserId();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateUserEmail();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateUserName();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateFullName();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateUserAge();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateNotes();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        try {
//            validateAmountOfFollowers();
//        } catch (UserValidationException e) {
//            userValidationException.addUserDataValidationException(e);
//        }
//        if()
    }

    private void validateUserId() {
        if (Objects.isNull(user.getId())) {
            throw  new IdValidationException("User ID equal \"null\"", new NullPointerException());
        }
        if(checkIsIdNotUnique()) {
            throw  new IdValidationException("User ID is not unique");
        }

    }

    private boolean checkIsIdNotUnique() {
        return Ids.contains(Users.getUserIds(getRepositoryData()), user.getId());
    }

    private User[] getRepositoryData() {
        try {
            return repository.tryToGetAll();
        } catch (ConnectionInterruptedException e) {
            return getRepositoryData();
        }
    }

    private void validateUserEmail() {
        if (Objects.isNull(user.getEmail())) {
            throw new EmailValidationException("User email equal \"null\"", new NullPointerException());
        }
        if (checkIsUserEmailNotValid()) {
            throw new EmailValidationException("User email isn't valid");
        }
        if (checkIsUserEmailNotUnique()) {
            throw new EmailValidationException("User email is not unique");
        }

    }

    private boolean checkIsUserEmailNotValid() {
        String regexToCheckIsEmailValid = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regexToCheckIsEmailValid);
        Matcher matcher = pattern.matcher(user.getEmail());

        return !(matcher.matches());
    }

    private boolean checkIsUserEmailNotUnique() {
        return Emails.contains(Users.getUserEmails(getRepositoryData()), user.getEmail());
    }

    private void validateUserName() {
        if (Objects.isNull(user.getUserName())) {
            throw new UserNameValidationException("User name equal \"null\"", new NullPointerException());
        }

        if (checkIsUserNameNotValid()) {
            throw new UserNameValidationException("User name is not valid.");
        }

        if (checkIsUserNameNotUnique()) {
            throw new UserNameValidationException("User name is not unique");
        }
    }

    private boolean checkIsUserNameNotValid() {
        String regexToCheckIsUserNameValid = "^[a-z,\\d]+$";
        Pattern pattern = Pattern.compile(regexToCheckIsUserNameValid);
        Matcher matcher = pattern.matcher(user.getUserName());

        return !(matcher.matches());
    }

    private boolean checkIsUserNameNotUnique() {
        return Names.contains(Users.getUserNames(getRepositoryData()), user.getUserName());
    }

    private void validateFullName() {
        if (Objects.isNull(user.getFullName())) {
            throw new FullNameValidationException("User full name equal \"null\"", new NullPointerException());
        }

        if (checkIsFullNameNotValid()) {
            throw new FullNameValidationException("User full name is not valid.");
        }

        if(checkIsUserNameNotUnique()) {
            throw new FullNameValidationException("User full name is not unique");
        }
    }

    private boolean checkIsFullNameNotValid() {
        String regexToCheckIsFullNameValid = "^[a-z]+[a-zA-Z]* [a-z]+[a-zA-Z]*$";
        Pattern pattern = Pattern.compile(regexToCheckIsFullNameValid);
        Matcher matcher = pattern.matcher(user.getFullName());

        return !(matcher.matches());
    }

    private boolean checkIsFullNameNotUnique() {
        return FullNames.contains(Users.getFullNames(getRepositoryData()), user.getFullName());
    }

    private void validateUserAge() {
        if (Objects.isNull(user.getAge())) {
            throw new UserAgeValidationException("User age equal \"null\"", new NullPointerException());
        }
        if (checkIsAgeNotValid()) {
            throw new UserAgeValidationException("User age is not valid.");
        }
    }

    private boolean checkIsAgeNotValid() {
        if (checkIsAgeNotPositiveInteger()) {
            return true;
        }
        if(checkIsAgeOutsideRangeOfAdmissibleValues()) {
            return true;
        }
        return false;
    }

    private boolean checkIsAgeNotPositiveInteger() {
        String regexToCheckIsUserAgeGreaterZero = "^\\d+$";
        Pattern pattern = Pattern.compile(regexToCheckIsUserAgeGreaterZero);
        Matcher matcher = pattern.matcher(user.getAge().toString());

        return !(matcher.matches());
    }

    private boolean checkIsAgeOutsideRangeOfAdmissibleValues() {
        int ageValue = user.getAge();
        return (ageValue < LOVER_AGE_VALUE_LIMIT || ageValue >= UPPER_AGE_VALUE_LIMIT);
    }

    private void validateNotes() {
        if (Objects.isNull(user.getNotes())) {
            throw new NotesValidationException("User notes equal \"null\"", new NullPointerException());
        }
        if (checkIsNotesNotValid()) {
            throw new NotesValidationException("User notes longer than 255 symbols");
        }
    }

    private boolean checkIsNotesNotValid() {
        return (user.getNotes().length() + 1 > ALLOWED_NUMBER_OF_CHARACTERS_IN_NOTES);
    }

    private void validateAmountOfFollowers() {
        if (Objects.isNull(user.getAmountOfFollowers())) {
            throw new AmountOfFollowersValidationException("User amount of followers equal \"null\"", new NullPointerException());
        }
        if (checkIsAmountOfFollowersNotBiggerZero()) {
            throw new AmountOfFollowersValidationException("User amount of followers is less than zero");
        }
    }

    private boolean checkIsAmountOfFollowersNotBiggerZero() {
        String regexToCheckIsUserAgeGreaterZero = "^\\d+$";
        Pattern pattern = Pattern.compile(regexToCheckIsUserAgeGreaterZero);
        Matcher matcher = pattern.matcher(user.getAmountOfFollowers().toString());

        return !(matcher.matches());
    }
}
