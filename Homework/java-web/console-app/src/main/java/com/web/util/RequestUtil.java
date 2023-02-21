package com.web.util;

import java.util.Arrays;
import java.util.List;

public class RequestUtil {

    private RequestUtil() {
    }

    public static Request convert(String requestInStringFormat) {
        String[] requestElements = requestInStringFormat.split(" ", -1);
        if (requestElements.length != 3) {
            throw new IllegalArgumentException(
                "Request must bi in format:"
                    + " \"[request type][space][request path][space][request parameters]\""
            );
        }

        RequestType requestType = getRequestTypeFromString(requestElements[0]);
        RequestPath requestPath = getRequestPathFromString(requestElements[1]);
        List<RequestParameter> requestParameters =
            getRequestParametersFromString(requestElements[2]);

        return new Request(requestType, requestPath, requestParameters);
    }

    private static RequestType getRequestTypeFromString(String requestTypeInStringFormat) {
        try {
            return RequestType.valueOf(requestTypeInStringFormat);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Incorrect request type: " + requestTypeInStringFormat
            );
        }
    }

    private static RequestPath getRequestPathFromString(String requestPathInStringFormat) {
        try {
            return RequestPath.valueOf(requestPathInStringFormat);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Incorrect request path: " + requestPathInStringFormat
            );
        }
    }

    private static List<RequestParameter> getRequestParametersFromString(
        String requestParametersInStringFormat
    ) {
        if (requestParametersInStringFormat.isEmpty()) {
            return List.of();
        }

        String[] requestParameters = requestParametersInStringFormat.split("&");

        return Arrays.stream(requestParameters)
            .map(RequestUtil::parseParameter)
            .toList();
    }

    private static RequestParameter parseParameter(String parameterString) {
        String[] requestParameterElements = parameterString.split("=");

        if (requestParameterElements.length != 2) {
            throw new IllegalArgumentException(
                "RequestParameter must be in format: \"field=value\","
                    + " but was: " + parameterString
            );
        }
        return new RequestParameter(requestParameterElements[0], requestParameterElements[1]);
    }
}
