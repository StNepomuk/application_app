package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.Job;
import edu.hm.hs.application.internal.object.entity.EntityCompany;
import edu.hm.hs.application.internal.object.entity.EntityJob;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class JobMapper
{

	private JobMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param job
	 *            Ressource
	 * @param eCompany
	 *            Entity
	 * @return Entity
	 */
	public static EntityJob mapRessourceToEntity( Job job, EntityCompany eCompany )
	{
		EntityJob eJob = new EntityJob();
		eJob.setName( job.getName() );
		eJob.setCompany( eCompany );

		return eJob;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eJobs
	 *            Entities
	 * @return Resources
	 */
	public static List<Job> mapEntityToRessource( List<EntityJob> eJobs )
	{
		List<Job> jobs = new ArrayList<Job>();

		for (EntityJob eJob : eJobs)
		{
			jobs.add( mapEntityToRessource( eJob ) );
		}

		return jobs;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eJob
	 *            Entity
	 * @return Ressource
	 */
	public static Job mapEntityToRessource( EntityJob eJob )
	{
		Job job = new Job();
		job.setId( eJob.getId() );
		job.setName( eJob.getName() );

		return job;
	}
}
