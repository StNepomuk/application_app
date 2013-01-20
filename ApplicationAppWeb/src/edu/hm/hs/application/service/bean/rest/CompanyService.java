package edu.hm.hs.application.service.bean.rest;

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
import edu.hm.hs.application.internal.object.mapper.CompanyMapper;

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
		return CompanyMapper.mapEntityToRessource( eCompanys );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ICompanyService#create(edu.hm.hs.application.api.object.resource.Company)
	 */
	@Override
	public Company create( Company company )
	{
		EntityCompany eCompany = CompanyMapper.mapRessourceToEntity( company );
		eCompany = m_companyDAOBean.create( eCompany );
		return CompanyMapper.mapEntityToRessource( eCompany );
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

		return CompanyMapper.mapEntityToRessource( eCompany );
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

		return CompanyMapper.mapEntityToRessource( eCompany );
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
