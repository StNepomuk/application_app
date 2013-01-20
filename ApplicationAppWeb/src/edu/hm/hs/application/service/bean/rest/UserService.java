package edu.hm.hs.application.service.bean.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IUserService;
import edu.hm.hs.application.api.object.resource.User;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityUser;
import edu.hm.hs.application.internal.object.mapper.UserMapper;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class UserService extends AbstractBean implements IUserService
{

	@EJB
	private IUserDAOLocal m_userDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IUserService#findAll()
	 */
	@Override
	public List<User> findAll()
	{
		List<EntityUser> eUsers = m_userDAOBean.readAll();
		return UserMapper.mapEntityToRessource( eUsers );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IUserService#create(edu.hm.hs.application.api.object.resource.User)
	 */
	@Override
	public User create( User user )
	{
		EntityUser eUser = UserMapper.mapRessourceToEntity( user );
		eUser = m_userDAOBean.create( eUser );
		return UserMapper.mapEntityToRessource( eUser );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IUserService#find(long)
	 */
	@Override
	public User find( long id )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		return UserMapper.mapEntityToRessource( eUser );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IUserService#update(long,
	 *      edu.hm.hs.application.api.object.resource.User)
	 */
	@Override
	public User update( long id, User user )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		eUser.setUsername( user.getUsername() );
		eUser.setPassword( user.getPassword() );

		eUser = m_userDAOBean.update( eUser );

		return UserMapper.mapEntityToRessource( eUser );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IUserService#remove(long)
	 */
	@Override
	public void remove( long id )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		m_userDAOBean.delete( eUser );
	}
}
