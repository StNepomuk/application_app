package edu.hm.hs.application.bean.database;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntityCompany;

/**
 * Bean für den Datenbankzugriff auf die Company Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class CompanyDAOBean extends AbstractBean implements ICompanyDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal#create(edu.hm.hs.application.internal.object.entity.EntityCompany)
	 */
	@Override
	public EntityCompany create( EntityCompany company )
	{
		m_em.persist( company );
		return company;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntityCompany read( Long id )
	{
		return m_em.find( EntityCompany.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal#readAll()
	 */
	@Override
	public List<EntityCompany> readAll()
	{
		return m_em.createQuery( "FROM Company", EntityCompany.class ).getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal#update(edu.hm.hs.application.internal.object.entity.EntityCompany)
	 */
	@Override
	public EntityCompany update( EntityCompany company )
	{
		company = m_em.merge( company );
		return company;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal#delete(edu.hm.hs.application.internal.object.entity.EntityCompany)
	 */
	@Override
	public void delete( EntityCompany company )
	{
		m_em.remove( company );
	}
}
