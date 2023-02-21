package com.web.util;

import java.util.List;

public record Request(
    RequestType requestType,
    RequestPath requestPath,
    List<RequestParameter> requestParameters
) {

}
