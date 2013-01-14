package edu.hm.hs.application.api.communication.request;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.hs.application.api.object.resource.Application;
import edu.hm.hs.application.api.object.resource.Job;

/**
 * REST-Service für den Job.
 * 
 * @author Stefan Wörner
 */
@Local
@Path( "/companies/{company_id}/jobs" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public interface IJobService
{

	/**
	 * Liste aller Jobs.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @return Jobliste
	 */
	@GET
	@Path( "" )
	List<Job> findAll( @PathParam( "company_id" ) long companyId );

	/**
	 * Erzeugt ein neues Job-Objekt.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @param job
	 *            Job
	 * @return erzeugtes Job
	 */
	@POST
	@Path( "" )
	Job create( @PathParam( "company_id" ) long companyId, Job job );

	/**
	 * Liefert den Job.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @param jobId
	 *            Jobidentifikator
	 * @return Jobs
	 */
	@GET
	@Path( "{job_id}" )
	Job find( @PathParam( "company_id" ) long companyId, @PathParam( "job_id" ) long jobId );

	/**
	 * Überschreibe Job.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @param jobId
	 *            Jobidentifikator
	 * @param job
	 *            Job
	 * @return Job
	 */
	@PUT
	@Path( "{job_id}" )
	Job update( @PathParam( "company_id" ) long companyId, @PathParam( "job_id" ) long jobId, Job job );

	/**
	 * Job löschen.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @param jobId
	 *            Jobidentifikator
	 */
	@DELETE
	@Path( "{job_id}" )
	void remove( @PathParam( "company_id" ) long companyId, @PathParam( "job_id" ) long jobId );

	/**
	 * Liste aller Bewerbungen.
	 * 
	 * @param companyId
	 *            Companyidentifikator
	 * @param jobId
	 *            Jobidentifikator
	 * @return Bewerbungen
	 */
	@GET
	@Path( "{job_id}/applications" )
	List<Application> findAllApplications( @PathParam( "company_id" ) long companyId, @PathParam( "job_id" ) long jobId );
}
