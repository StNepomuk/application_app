package edu.hm.hs.application.api.communication.request;

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

import edu.hm.hs.application.api.object.resource.Profile;

/**
 * REST-Service für das Benutzer-Profil.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/users/{user_id}/profile" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface IProfileService
{

	/**
	 * Erzeugt ein neues Benutzer-Profil-Objekt.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param profile
	 *            Benutzer-Profil
	 * @return erzeugtes Benutzer-Profil
	 */
	@POST
	@Path( "" )
	Profile create( @PathParam( "user_id" ) long userId, Profile profile );

	/**
	 * Liefert Benutzer-Profil.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @return Benutzer-Profil
	 */
	@GET
	@Path( "" )
	Profile find( @PathParam( "user_id" ) long userId );

	/**
	 * Überschreibe Benutzer-Profil.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param profile
	 *            Benutzer-Profil
	 * @return Benutzer-Profil
	 */
	@PUT
	@Path( "" )
	Profile update( @PathParam( "user_id" ) long userId, Profile profile );

	/**
	 * Benutzer-Profil löschen.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 */
	@DELETE
	@Path( "" )
	void remove( @PathParam( "user_id" ) long userId );
}
