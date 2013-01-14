package edu.hm.hs.application.internal.bean.database;

import java.util.List;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntityJob;

/**
 * Interface der Bean für den Datenbankzugriff auf die Job Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface IJobDAOLocal
{

	/**
	 * Estellt einen neuen Job in der Datenbank.
	 * 
	 * @param job
	 *            Zu erstellender Job
	 * @return Erstellter Job
	 */
	EntityJob create( EntityJob job );

	/**
	 * Liest einen Job anhand der Jobid aus der Datenbank aus.
	 * 
	 * @param id
	 *            Jobid
	 * @return Gefundener Job oder NULL
	 */
	EntityJob read( Long id );

	/**
	 * Liest alle Job aus.
	 * 
	 * @return Liste mit gefundenen Jobn
	 */
	List<EntityJob> readAll();

	/**
	 * Aktualisiert einen Job in der Datenbank.
	 * 
	 * @param job
	 *            Zu aktualisierender Job
	 * @return Aktualiserter Job
	 */
	EntityJob update( EntityJob job );

	/**
	 * Entfernt einen Job aus der Datenbank.
	 * 
	 * @param job
	 *            Zu entfernender Job
	 */
	void delete( EntityJob job );
}
