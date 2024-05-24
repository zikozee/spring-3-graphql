package com.gql.graghql.exception;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
