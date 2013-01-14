package edu.hm.hs.application.api.communication.request;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.hs.application.api.object.resource.Application;

/**
 * REST-Service für die Bewerbung.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/users/{user_id}" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface IApplicationService
{

	/**
	 * Liste aller Bewerbungen.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @return Bewerbungsliste
	 */
	@GET
	@Path( "applications" )
	List<Application> findAll( @PathParam( "user_id" ) long userId );

	/**
	 * Erzeugt ein neues Bewerbungs-Objekt.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param jobId
	 *            Jobidentifikator
	 * @return erzeugte Bewerbung
	 */
	@POST
	@Path( "apply/job/{job_id}" )
	Application create( @PathParam( "user_id" ) long userId, @PathParam( "job_id" ) long jobId );

	/**
	 * Liefert die Bewerbung.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param applicationId
	 *            Bewerbungidentifikator
	 * @return Bewerbung
	 */
	@GET
	@Path( "applications/{application_id}" )
	Application find( @PathParam( "user_id" ) long userId, @PathParam( "application_id" ) long applicationId );

	/**
	 * Bewerbung löschen.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param applicationId
	 *            Bewerbungidentifikator
	 */
	@DELETE
	@Path( "applications/{application_id}" )
	void remove( @PathParam( "user_id" ) long userId, @PathParam( "application_id" ) long applicationId );
}
