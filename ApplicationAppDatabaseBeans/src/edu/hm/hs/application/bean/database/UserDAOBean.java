package edu.hm.hs.application.bean.database;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * Bean für den Datenbankzugriff auf die User Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class UserDAOBean extends AbstractBean implements IUserDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IUserDAOLocal#create(edu.hm.hs.application.internal.object.entity.EntityUser)
	 */
	@Override
	public EntityUser create( EntityUser user )
	{
		m_em.persist( user );
		return user;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IUserDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntityUser read( Long id )
	{
		return m_em.find( EntityUser.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IUserDAOLocal#readAll()
	 */
	@Override
	public List<EntityUser> readAll()
	{
		return m_em.createQuery( "SELECT u FROM EntityUser u", EntityUser.class ).getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IUserDAOLocal#update(edu.hm.hs.application.internal.object.entity.EntityUser)
	 */
	@Override
	public EntityUser update( EntityUser user )
	{
		return m_em.merge( user );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IUserDAOLocal#delete(edu.hm.hs.application.internal.object.entity.EntityUser)
	 */
	@Override
	public void delete( EntityUser user )
	{
		m_em.remove( user );
	}

}
