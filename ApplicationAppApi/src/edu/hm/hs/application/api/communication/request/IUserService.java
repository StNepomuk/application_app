package edu.hm.hs.application.api.communication.request;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.hs.application.api.object.resource.User;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/users" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface IUserService
{

	/**
	 * Liste aller Benutzer.
	 * 
	 * @return Benutzerliste
	 */
	@GET
	@Path( "" )
	List<User> findAll();

	/**
	 * Erzeugt ein neues Benutzer-Objekt.
	 * 
	 * @param user
	 *            Benutzer
	 * @return erzeugte Benutzer
	 */
	@POST
	@Path( "" )
	User create( User user );

	/**
	 * Liefere Benutzer anhand seiner id.
	 * 
	 * @param id
	 *            Benutzeridentifikator
	 * @return Benutzer
	 */
	@GET
	@Path( "{id}" )
	User find( @PathParam( "id" ) long id );

	/**
	 * Überschreibe Benutzerdaten.
	 * 
	 * @param id
	 *            Benutzeridentifikator
	 * @param user
	 *            Benutzer
	 * @return Benutzer
	 */
	@PUT
	@Path( "{id}" )
	User update( @PathParam( "id" ) long id, User user );

	/**
	 * Benutzer löschen.
	 * 
	 * @param id
	 *            Benutzeridentifikator
	 */
	@DELETE
	@Path( "{id}" )
	void remove( @PathParam( "id" ) long id );
}
