package edu.hm.hs.application.service.bean.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IJobService;
import edu.hm.hs.application.api.object.resource.Job;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.ICompanyDAOLocal;
import edu.hm.hs.application.internal.bean.database.IJobDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityCompany;
import edu.hm.hs.application.internal.object.entity.EntityJob;

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
}
