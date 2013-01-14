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

import edu.hm.hs.application.api.object.resource.Skill;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/user/{user_id}/profile/skills" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface ISkillService
{

	/**
	 * Liste aller Skills.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @return Skillliste
	 */
	@GET
	@Path( "" )
	List<Skill> findAll( @PathParam( "user_id" ) long userId );

	/**
	 * Erzeugt ein neues Skill-Objekt.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param skill
	 *            Skill
	 * @return erzeugtes Benutzer-Profil
	 */
	@POST
	@Path( "" )
	Skill create( @PathParam( "user_id" ) long userId, Skill skill );

	/**
	 * Liefert den Skill.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param skillId
	 *            Skillidentifikator
	 * @return Skill
	 */
	@GET
	@Path( "{skill_id}" )
	Skill find( @PathParam( "user_id" ) long userId, @PathParam( "skill_id" ) long skillId );

	/**
	 * Überschreibe Skill.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param skillId
	 *            Skillidentifikator
	 * @param skill
	 *            Skill
	 * @return Skill
	 */
	@PUT
	@Path( "{skill_id}" )
	Skill update( @PathParam( "user_id" ) long userId, @PathParam( "skill_id" ) long skillId, Skill skill );

	/**
	 * Skill löschen.
	 * 
	 * @param userId
	 *            Benutzeridentifikator
	 * @param skillId
	 *            Skillidentifikator
	 */
	@DELETE
	@Path( "{skill_id}" )
	void remove( @PathParam( "user_id" ) long userId, @PathParam( "skill_id" ) long skillId );
}
