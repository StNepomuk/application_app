package edu.hm.hs.application.internal.bean.database;

import java.util.List;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * Interface der Bean für den Datenbankzugriff auf die User Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface IUserDAOLocal
{

	/**
	 * Estellt einen neuen Benutzer in der Datenbank.
	 * 
	 * @param user
	 *            Zu erstellender Benutzer
	 * @return Erstellter Benutzer
	 */
	EntityUser create( EntityUser user );

	/**
	 * Liest einen Benutzer anhand des Benutzernamens aus der Datenbank aus.
	 * 
	 * @param id
	 *            Benutzerid
	 * @return Gefundener Benutzer oder NULL
	 */
	EntityUser read( Long id );

	/**
	 * Liest alle Benutzer aus.
	 * 
	 * @return Liste mit gefundenen Benutzern
	 */
	List<EntityUser> readAll();

	/**
	 * Aktualisiert einen Benutzer in der Datenbank.
	 * 
	 * @param user
	 *            Zu aktualisierender Benutzer
	 * @return Aktualiserter Benutzer
	 */
	EntityUser update( EntityUser user );

	/**
	 * Entfernt einen Benutzer aus der Datenbank.
	 * 
	 * @param user
	 *            Zu entfernender Benutzer
	 */
	void delete( EntityUser user );
}
