package com.web.util;

import java.util.Objects;

public class Response {

    private final Object data;
    private final ResponseStatus status;

    private Response(Object data, ResponseStatus status) {
        this.data = data;
        this.status = status;
    }

    public static Response ok(Object data) {
        return new Response(data, ResponseStatus.SUCCESS);
    }

    public static Response fail(Object data) {
        return new Response(data, ResponseStatus.FAIL);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response response = (Response) o;
        return Objects.equals(data, response.data) && status == response.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, status);
    }
}
