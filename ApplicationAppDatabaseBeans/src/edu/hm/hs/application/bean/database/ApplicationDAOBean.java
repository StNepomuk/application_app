package edu.hm.hs.application.bean.database;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntityApplication;

/**
 * Bean für den Datenbankzugriff auf die Application Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class ApplicationDAOBean extends AbstractBean implements IApplicationDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal
	 *      #create(edu.hm.hs.application.internal.object.entity.EntityApplication)
	 */
	@Override
	public EntityApplication create( EntityApplication application )
	{
		m_em.persist( application );
		return application;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntityApplication read( Long id )
	{
		return m_em.find( EntityApplication.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal#readAll()
	 */
	@Override
	public List<EntityApplication> readAll()
	{
		return m_em.createQuery( "FROM Application", EntityApplication.class ).getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal
	 *      #update(edu.hm.hs.application.internal.object.entity.EntityApplication)
	 */
	@Override
	public EntityApplication update( EntityApplication application )
	{
		application = m_em.merge( application );
		return application;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal
	 *      #delete(edu.hm.hs.application.internal.object.entity.EntityApplication)
	 */
	@Override
	public void delete( EntityApplication application )
	{
		m_em.remove( application );
	}
}
