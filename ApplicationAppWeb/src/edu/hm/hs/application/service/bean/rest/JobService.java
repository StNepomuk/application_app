package edu.hm.hs.application.service.bean.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IJobService;
import edu.hm.hs.application.api.object.resource.Address;
import edu.hm.hs.application.api.object.resource.Application;
import edu.hm.hs.application.api.object.resource.ContactInfo;
import edu.hm.hs.application.api.object.resource.Job;
import edu.hm.hs.application.api.object.resource.Profile;
import edu.hm.hs.application.api.object.resource.Skill;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal;
import edu.hm.hs.application.internal.bean.database.IJobDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityApplication;
import edu.hm.hs.application.internal.object.entity.EntityCompany;
import edu.hm.hs.application.internal.object.entity.EntityContactInfo;
import edu.hm.hs.application.internal.object.entity.EntityJob;
import edu.hm.hs.application.internal.object.entity.EntitySkill;

/**
 * REST-Service für den Job.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class JobService extends AbstractBean implements IJobService
{

	@EJB
	private ICompanyDAOLocal m_companyDAOBean;

	@EJB
	private IJobDAOLocal m_jobDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#findAll(long)
	 */
	@Override
	public List<Job> findAll( long companyId )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		List<Job> jobs = new ArrayList<Job>();

		for (EntityJob eJob : eCompany.getJobs())
		{
			Job job = new Job();
			job.setId( eJob.getId() );
			job.setName( eJob.getName() );

			jobs.add( job );
		}

		return jobs;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#create(long,
	 *      edu.hm.hs.application.api.object.resource.Job)
	 */
	@Override
	public Job create( long companyId, Job job )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		EntityJob eJob = new EntityJob();
		eJob.setName( job.getName() );
		eJob.setCompany( eCompany );
		eJob = m_jobDAOBean.create( eJob );

		eCompany.addJob( eJob );
		eCompany = m_companyDAOBean.update( eCompany );

		job = new Job();
		job.setId( eJob.getId() );
		job.setName( eJob.getName() );

		return job;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#find(long, long)
	 */
	@Override
	public Job find( long companyId, long jobId )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		if (eCompany.getJob( jobId ) == null)
		{
			throw new NotFoundException( "job with id " + jobId + " for company with companyId:" + companyId );
		}

		EntityJob eJob = eCompany.getJob( jobId );

		Job job = new Job();
		job.setId( eJob.getId() );
		job.setName( eJob.getName() );

		return job;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#update(long, long,
	 *      edu.hm.hs.application.api.object.resource.Job)
	 */
	@Override
	public Job update( long companyId, long jobId, Job job )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		if (eCompany.getJob( jobId ) == null)
		{
			throw new NotFoundException( "job with id " + jobId + " for company with companyId:" + companyId );
		}

		EntityJob eJob = eCompany.getJob( jobId );
		eJob.setName( job.getName() );

		eCompany = m_companyDAOBean.update( eCompany );

		job = new Job();
		job.setId( eJob.getId() );
		job.setName( eJob.getName() );

		return job;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#remove(long, long)
	 */
	@Override
	public void remove( long companyId, long jobId )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		if (eCompany.getJob( jobId ) == null)
		{
			throw new NotFoundException( "job with id " + jobId + " for company with companyId:" + companyId );
		}

		eCompany.removeJob( eCompany.getJob( jobId ) );
		m_companyDAOBean.update( eCompany );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IJobService#findAllApplications(long, long)
	 */
	@Override
	public List<Application> findAllApplications( long companyId, long jobId )
	{
		EntityCompany eCompany = m_companyDAOBean.read( companyId );

		if (eCompany == null)
		{
			throw new NotFoundException( "company with id:" + companyId );
		}

		List<Application> applications = new ArrayList<Application>();

		for (EntityApplication eApplication : eCompany.getJob( jobId ).getApplications())
		{
			Profile profile = new Profile();
			profile.setId( eApplication.getProfile().getId() );
			profile.setFirstname( eApplication.getProfile().getFirstname() );
			profile.setLastname( eApplication.getProfile().getLastname() );
			profile.setGender( eApplication.getProfile().getGender() );
			profile.seteMailAddress( eApplication.getProfile().geteMailAddress() );
			profile.setDateOfBirth( eApplication.getProfile().getDateOfBirth() );

			if (eApplication.getProfile().getAddress() != null)
			{
				Address address = new Address();
				address.setId( eApplication.getProfile().getAddress().getId() );
				address.setStreet( eApplication.getProfile().getAddress().getStreet() );
				address.setHouseNumber( eApplication.getProfile().getAddress().getHouseNumber() );
				address.setZipCode( eApplication.getProfile().getAddress().getZipCode() );
				address.setCity( eApplication.getProfile().getAddress().getCity() );
				profile.setAddress( address );
			}

			for (EntitySkill eSkill : eApplication.getProfile().getSkills())
			{
				Skill skill = new Skill();
				skill.setId( eSkill.getId() );
				skill.setValue( eSkill.getName() );

				profile.addSkill( skill );
			}

			for (EntityContactInfo eContactInfo : eApplication.getProfile().getContactInfos())
			{
				ContactInfo contactInfo = new ContactInfo();
				contactInfo.setId( eContactInfo.getId() );
				contactInfo.setType( eContactInfo.getType() );
				contactInfo.setValue( eContactInfo.getName() );

				profile.addContactInfo( contactInfo );
			}

			Application application = new Application();
			application.setId( eApplication.getId() );
			application.setDate( eApplication.getDate() );
			application.setProfile( profile );

			applications.add( application );
		}

		return applications;
	}
}
