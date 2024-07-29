package com.graphQL.demo.config;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import com.graphQL.demo.exceptions.NoDataFoundException;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof NoDataFoundException) {
            ErrorType errorType;
            if (((NoDataFoundException) ex).getStatusCode() == 400) {
                errorType = ErrorType.BAD_REQUEST;
                return graphQLError(errorType, (NoDataFoundException) ex, env);
            }
            if (((NoDataFoundException) ex).getStatusCode() == 404) {
                errorType = ErrorType.NOT_FOUND;
                return graphQLError(errorType, (NoDataFoundException) ex, env);
            } else {
                return GraphqlErrorBuilder.newError().build();
            }

        } else {
            return GraphqlErrorBuilder.newError().build();
        }
    }

    private GraphQLError graphQLError(ErrorType errorType, NoDataFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
