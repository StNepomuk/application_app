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

import edu.hm.hs.application.api.object.resource.ContactInfo;

/**
 * REST-Service für die ContactInfo.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/users/{user_id}/profile/contactinfos" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface IContactInfoService
{

	/**
	 * Liste aller ContactInfos.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @return ContactInfoliste
	 */
	@GET
	@Path( "" )
	List<ContactInfo> findAll( @PathParam( "user_id" ) long userId );

	/**
	 * Erzeugt ein neues ContactInfo-Objekt.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param contactInfo
	 *            ContactInfo
	 * @return erzeugtes Benutzer-Profil
	 */
	@POST
	@Path( "" )
	ContactInfo create( @PathParam( "user_id" ) long userId, ContactInfo contactInfo );

	/**
	 * Liefert den ContactInfo.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param contactInfoId
	 *            ContactInfoidentifikator
	 * @return ContactInfo
	 */
	@GET
	@Path( "{cinfo_id}" )
	ContactInfo find( @PathParam( "user_id" ) long userId, @PathParam( "cinfo_id" ) long contactInfoId );

	/**
	 * Überschreibe ContactInfo.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param contactInfoId
	 *            ContactInfoidentifikator
	 * @param contactInfo
	 *            ContactInfo
	 * @return ContactInfo
	 */
	@PUT
	@Path( "{cinfo_id}" )
	ContactInfo update( @PathParam( "user_id" ) long userId, @PathParam( "cinfo_id" ) long contactInfoId, ContactInfo contactInfo );

	/**
	 * ContactInfo löschen.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param contactInfoId
	 *            ContactInfoidentifikator
	 */
	@DELETE
	@Path( "{cinfo_id}" )
	void remove( @PathParam( "user_id" ) long userId, @PathParam( "cinfo_id" ) long contactInfoId );
}
