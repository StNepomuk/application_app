package edu.hm.hs.application.service.bean.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IApplicationService;
import edu.hm.hs.application.api.object.resource.Application;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IApplicationDAOLocal;
import edu.hm.hs.application.internal.bean.database.IJobDAOLocal;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityApplication;
import edu.hm.hs.application.internal.object.entity.EntityJob;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntityUser;
import edu.hm.hs.application.internal.object.mapper.ApplicationMapper;

/**
 * REST-Service für die Bewerbung.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class ApplicationService extends AbstractBean implements IApplicationService
{

	@EJB
	private IJobDAOLocal m_jobDAOBean;

	@EJB
	private IUserDAOLocal m_userDAOBean;

	@EJB
	private IProfileDAOLocal m_profileDAOBean;

	@EJB
	private IApplicationDAOLocal m_applicationDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IApplicationService#findAll(long)
	 */
	@Override
	public List<Application> findAll( long userId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		return ApplicationMapper.mapEntityToRessourceWithJob( eUser.getProfile().getApplications() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IApplicationService#create(long, long)
	 */
	@Override
	public Application create( long userId, long jobId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		EntityJob eJob = m_jobDAOBean.read( jobId );

		if (eJob == null)
		{
			throw new NotFoundException( "job with id:" + jobId );
		}

		if (eUser.getProfile().getApplicationForJob( eJob.getId() ) != null)
		{
			throw new BadRequestException( "already applied for job" );
		}

		EntityApplication eApplication = ApplicationMapper.mapRessourceToEntity( eUser, eJob );
		eApplication = m_applicationDAOBean.create( eApplication );

		EntityProfile eProfile = eUser.getProfile();
		eProfile.addApplication( eApplication );
		eProfile = m_profileDAOBean.update( eProfile );

		eJob.addApplication( eApplication );
		eJob = m_jobDAOBean.update( eJob );

		return ApplicationMapper.mapEntityToRessource( eApplication, eJob );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IApplicationService#find(long, long)
	 */
	@Override
	public Application find( long userId, long applicationId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		if (eUser.getProfile().getApplication( applicationId ) == null)
		{
			throw new NotFoundException( "application with id " + applicationId + " for user with userId:" + userId );
		}

		return ApplicationMapper.mapEntityToRessourceWithJob( eUser.getProfile().getApplication( applicationId ) );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IApplicationService#remove(long, long)
	 */
	@Override
	public void remove( long userId, long applicationId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		if (eUser.getProfile().getApplication( applicationId ) == null)
		{
			throw new NotFoundException( "application with id " + applicationId + " for user with userId:" + userId );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.removeApplication( eProfile.getApplication( applicationId ) );

		m_profileDAOBean.update( eProfile );
	}
}
