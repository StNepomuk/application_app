package edu.hm.hs.application.bean.database;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IJobDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntityJob;

/**
 * Bean für den Datenbankzugriff auf die Job Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class JobDAOBean extends AbstractBean implements IJobDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IJobDAOLocal#create(edu.hm.hs.application.internal.object.entity.EntityJob)
	 */
	@Override
	public EntityJob create( EntityJob job )
	{
		m_em.persist( job );
		return job;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IJobDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntityJob read( Long id )
	{
		return m_em.find( EntityJob.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IJobDAOLocal#readAll()
	 */
	@Override
	public List<EntityJob> readAll()
	{
		return m_em.createQuery( "FROM Job", EntityJob.class ).getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IJobDAOLocal#update(edu.hm.hs.application.internal.object.entity.EntityJob)
	 */
	@Override
	public EntityJob update( EntityJob job )
	{
		job = m_em.merge( job );
		return job;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.IJobDAOLocal#delete(edu.hm.hs.application.internal.object.entity.EntityJob)
	 */
	@Override
	public void delete( EntityJob job )
	{
		m_em.remove( job );
	}
}
