package edu.hm.hs.application.service.provider;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.hm.hs.application.api.object.resource.error.BasicError;

/**
 * Provider mappt eine ConstraintViolationException in einen entsprechenden HTTP Fehlercode inkl. Fehlermeldung im
 * Content.
 * 
 * @author Stefan Wörner
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>
{

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse( ConstraintViolationException cex )
	{
		BasicError error = new BasicError();

		for (ConstraintViolation<?> violation : cex.getConstraintViolations())
		{
			error.getMessages().add( violation.getMessage() );
		}

		// HTTP Fehlercode 400 := Bad Request
		return Response.status( 400 ).entity( error ).build();
	}
}
