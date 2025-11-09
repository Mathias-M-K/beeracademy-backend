package dk.mathiaskofod.providers.exeptions.mappers;

import dk.mathiaskofod.providers.exeptions.BaseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException exception) {
        return Response.status(exception.httpStatus)
                .entity(new ExceptionResponse(exception.getMessage()))
                .build();
    }
}
