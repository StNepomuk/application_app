package edu.hm.hs.application.bean.database;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntityProfile;

/**
 * Bean für den Datenbankzugriff auf die Profile Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class ProfileDAOBean extends AbstractBean implements IProfileDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IProfileDAOLocal#create(edu.hm.hs.application.internal.object.entity.EntityProfile)
	 */
	@Override
	public EntityProfile create( EntityProfile profile )
	{
		m_em.persist( profile );
		return profile;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IProfileDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntityProfile read( Long id )
	{
		return m_em.find( EntityProfile.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IProfileDAOLocal#update(edu.hm.hs.application.internal.object.entity.EntityProfile)
	 */
	@Override
	public EntityProfile update( EntityProfile profile )
	{
		return m_em.merge( profile );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IProfileDAOLocal#delete(edu.hm.hs.application.internal.object.entity.EntityProfile)
	 */
	@Override
	public void delete( EntityProfile profile )
	{
		m_em.remove( profile );
	}
}
