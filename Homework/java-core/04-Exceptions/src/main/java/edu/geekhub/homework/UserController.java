package edu.geekhub.homework;

import edu.geekhub.controller.Controller;
import edu.geekhub.homework.exceptions.RepositorySavingException;
import edu.geekhub.homework.validation.RequestValidator;
import edu.geekhub.homework.validation.exception.ValidationException;
import edu.geekhub.models.User;
import edu.geekhub.models.request.Request;
import edu.geekhub.models.request.Response;

// Don't move this class
public class UserController implements Controller {

    private final UserService service = new UserService();
    private final RequestValidator requestValidator = new RequestValidator();

    @Override
    public Response process(Request request) {
        try {
            requestValidator.validateResponseData(request);

            boolean isUserNotSaveInRepository =
                !(tryToSaveUserInRepository((User) request.getData()));
            if (isUserNotSaveInRepository) {
                return Response.fail(request.getData());
            }

            return Response.ok(request.getData());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return Response.fail();
        }
    }

    private boolean tryToSaveUserInRepository(User user) {
        try {
            service.saveUserToRepository(user);
            return true;
        } catch (RepositorySavingException e) {
            return false;
        }
    }
}
