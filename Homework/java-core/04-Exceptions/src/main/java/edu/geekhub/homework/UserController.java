package edu.geekhub.homework;

import edu.geekhub.controller.Controller;
import edu.geekhub.models.request.Request;
import edu.geekhub.models.request.Response;
import edu.geekhub.models.request.ResponseStatus;
import edu.geekhub.utils.RandomRequestDataGenerator;
import edu.geekhub.utils.RequestDataGenerator;

// Don't move this class
public class UserController implements Controller {

    private final UserService service = new UserService();

    @Override
    public Response process(Request request) {

        //return  new Response(request.getData(), ResponseStatus.SUCCESS);
        return Response.ok(request.getData());
    }
}
