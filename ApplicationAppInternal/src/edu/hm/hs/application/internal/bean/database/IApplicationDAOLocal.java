package edu.hm.hs.application.internal.bean.database;

import java.util.List;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntityApplication;

/**
 * Interface der Bean für den Datenbankzugriff auf die Application Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface IApplicationDAOLocal
{

	/**
	 * Estellt einen neuen Application in der Datenbank.
	 * 
	 * @param application
	 *            Zu erstellender Application
	 * @return Erstellter Application
	 */
	EntityApplication create( EntityApplication application );

	/**
	 * Liest einen Application anhand der Applicationid aus der Datenbank aus.
	 * 
	 * @param id
	 *            Applicationid
	 * @return Gefundener Application oder NULL
	 */
	EntityApplication read( Long id );

	/**
	 * Liest alle Application aus.
	 * 
	 * @return Liste mit gefundenen Applicationn
	 */
	List<EntityApplication> readAll();

	/**
	 * Aktualisiert einen Application in der Datenbank.
	 * 
	 * @param application
	 *            Zu aktualisierender Application
	 * @return Aktualiserter Application
	 */
	EntityApplication update( EntityApplication application );

	/**
	 * Entfernt einen Application aus der Datenbank.
	 * 
	 * @param application
	 *            Zu entfernender Application
	 */
	void delete( EntityApplication application );
}
