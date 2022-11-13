package edu.geekhub.homework.validation;

import edu.geekhub.homework.validation.exception.RequestValidationException;
import edu.geekhub.models.User;
import edu.geekhub.models.request.Request;
import java.util.Objects;

public class RequestValidator {

    Request request;

    public void validateResponseData(Request request) {
        this.request = request;
        if (Objects.isNull(request)) {
            throw  new RequestValidationException("Request equal \"null\"",
                new NullPointerException());
        }

        if (checkIsRequestDataNotInstanceOfUser()) {
            throw  new RequestValidationException("Request data isn't instant of User");
        }
    }

    private boolean checkIsRequestDataNotInstanceOfUser() {
        return !(request.getData() instanceof User);
    }
}
