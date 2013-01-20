package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.hm.hs.application.api.object.resource.Application;
import edu.hm.hs.application.api.object.resource.Job;
import edu.hm.hs.application.internal.object.entity.EntityApplication;
import edu.hm.hs.application.internal.object.entity.EntityJob;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class ApplicationMapper
{

	private ApplicationMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param eUser
	 *            Ressource
	 * @param eJob
	 *            Entity
	 * @return Entity
	 */
	public static EntityApplication mapRessourceToEntity( EntityUser eUser, EntityJob eJob )
	{
		EntityApplication eApplication = new EntityApplication();
		eApplication.setDate( new Date() );
		eApplication.setProfile( eUser.getProfile() );
		eApplication.setJob( eJob );

		return eApplication;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eApplications
	 *            Entities
	 * @return Resources
	 */
	public static List<Application> mapEntityToRessourceWithJob( List<EntityApplication> eApplications )
	{
		List<Application> applications = new ArrayList<Application>();

		for (EntityApplication eApplication : eApplications)
		{
			applications.add( mapEntityToRessourceWithJob( eApplication ) );
		}

		return applications;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eApplication
	 *            Entity
	 * @return Ressource
	 */
	public static Application mapEntityToRessourceWithJob( EntityApplication eApplication )
	{
		Application application = new Application();
		application.setId( eApplication.getId() );
		application.setDate( eApplication.getDate() );
		application.setJob( JobMapper.mapEntityToRessource( eApplication.getJob() ) );

		return application;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eApplications
	 *            Entities
	 * @return Resources
	 */
	public static List<Application> mapEntityToRessourceWithProfile( List<EntityApplication> eApplications )
	{
		List<Application> applications = new ArrayList<Application>();

		for (EntityApplication eApplication : eApplications)
		{
			applications.add( mapEntityToRessourceWithProfile( eApplication ) );
		}

		return applications;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eApplication
	 *            Entity
	 * @return Ressource
	 */
	public static Application mapEntityToRessourceWithProfile( EntityApplication eApplication )
	{
		Application application = new Application();
		application.setId( eApplication.getId() );
		application.setDate( eApplication.getDate() );
		application.setProfile( ProfileMapper.mapEntityToRessource( eApplication.getProfile() ) );

		return application;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eApplication
	 *            Entity
	 * @param eJob
	 *            Entity
	 * @return Ressource
	 */
	public static Application mapEntityToRessource( EntityApplication eApplication, EntityJob eJob )
	{
		Job job = new Job();
		job.setId( eJob.getId() );
		job.setName( eJob.getName() );

		Application application = new Application();
		application.setId( eApplication.getId() );
		application.setDate( eApplication.getDate() );
		application.setJob( job );

		return application;
	}
}
