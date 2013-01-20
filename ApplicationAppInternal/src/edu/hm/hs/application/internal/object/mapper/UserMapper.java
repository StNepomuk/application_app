package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.User;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class UserMapper
{

	private UserMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param user
	 *            Ressource
	 * @return Entity
	 */
	public static EntityUser mapRessourceToEntity( User user )
	{
		EntityUser eUser = new EntityUser();
		eUser.setUsername( user.getUsername() );
		eUser.setPassword( user.getPassword() );

		return eUser;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eUsers
	 *            Entities
	 * @return Resources
	 */
	public static List<User> mapEntityToRessource( List<EntityUser> eUsers )
	{
		List<User> users = new ArrayList<User>();

		for (EntityUser eUser : eUsers)
		{
			users.add( mapEntityToRessource( eUser ) );
		}

		return users;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eUser
	 *            Entity
	 * @return Ressource
	 */
	public static User mapEntityToRessource( EntityUser eUser )
	{
		User user = new User();
		user.setId( eUser.getId() );
		user.setUsername( eUser.getUsername() );
		user.setPassword( eUser.getPassword() );

		return user;
	}
}
