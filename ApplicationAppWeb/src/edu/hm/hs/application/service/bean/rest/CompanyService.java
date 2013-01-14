package edu.hm.hs.application.service.bean.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.ICompanyService;
import edu.hm.hs.application.api.object.resource.Company;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityCompany;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class CompanyService extends AbstractBean implements ICompanyService
{

	@EJB
	private ICompanyDAOLocal m_companyDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#findAll()
	 */
	@Override
	public List<Company> findAll()
	{
		List<EntityCompany> eCompanys = m_companyDAOBean.readAll();
		List<Company> companys = new ArrayList<Company>();

		for (EntityCompany eCompany : eCompanys)
		{
			Company company = new Company();
			company.setId( eCompany.getId() );
			company.setName( eCompany.getName() );

			companys.add( company );
		}

		return companys;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#create(edu.hm.hs.application.api.object.resource.Company)
	 */
	@Override
	public Company create( Company company )
	{
		EntityCompany eCompany = new EntityCompany();
		eCompany.setName( company.getName() );

		eCompany = m_companyDAOBean.create( eCompany );

		company = new Company();
		company.setId( eCompany.getId() );
		company.setName( eCompany.getName() );

		return company;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#find(long)
	 */
	@Override
	public Company find( long id )
	{
		EntityCompany eCompany = m_companyDAOBean.read( id );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + id );
		}

		Company company = new Company();
		company.setId( eCompany.getId() );
		company.setName( eCompany.getName() );

		return company;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#update(long,
	 *      edu.hm.hs.application.api.object.resource.Company)
	 */
	@Override
	public Company update( long id, Company company )
	{
		EntityCompany eCompany = m_companyDAOBean.read( id );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + id );
		}

		eCompany.setName( company.getName() );

		eCompany = m_companyDAOBean.update( eCompany );

		company = new Company();
		company.setId( eCompany.getId() );
		company.setName( eCompany.getName() );

		return company;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#remove(long)
	 */
	@Override
	public void remove( long id )
	{
		EntityCompany eCompany = m_companyDAOBean.read( id );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + id );
		}

		m_companyDAOBean.delete( eCompany );
	}
}
