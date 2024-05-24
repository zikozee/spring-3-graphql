package com.gql.graghql.exception;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
