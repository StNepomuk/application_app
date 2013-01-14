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

import edu.hm.hs.application.api.object.resource.Company;

/**
 * REST-Service für das Unternehmen.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/companies" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface ICompanyService
{

	/**
	 * Liste aller Unternehmen.
	 * 
	 * @return Unternehmenliste
	 */
	@GET
	@Path( "" )
	List<Company> findAll();

	/**
	 * Erzeugt ein neues Unternehmen-Objekt.
	 * 
	 * @param company
	 *            Unternehmen
	 * @return erzeugte Unternehmen
	 */
	@POST
	@Path( "" )
	Company create( Company company );

	/**
	 * Liefere Unternehmen anhand seiner id.
	 * 
	 * @param id
	 *            Unternehmenidentifikator
	 * @return Unternehmen
	 */
	@GET
	@Path( "{id}" )
	Company find( @PathParam( "id" ) long id );

	/**
	 * Überschreibe Unternehmendaten.
	 * 
	 * @param id
	 *            Unternehmenidentifikator
	 * @param company
	 *            Unternehmen
	 * @return Unternehmen
	 */
	@PUT
	@Path( "{id}" )
	Company update( @PathParam( "id" ) long id, Company company );

	/**
	 * Unternehmen löschen.
	 * 
	 * @param id
	 *            Unternehmenidentifikator
	 */
	@DELETE
	@Path( "{id}" )
	void remove( @PathParam( "id" ) long id );
}
