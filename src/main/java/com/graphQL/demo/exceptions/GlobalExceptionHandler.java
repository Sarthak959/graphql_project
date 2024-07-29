package com.graphQL.demo.exceptions;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import graphql.GraphQLError;

@ControllerAdvice
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleNoDataFoundException(NoDataFoundException ex) {
        return GraphQLError.newError().message(ex.getMessage())
                .build();
    }
}
