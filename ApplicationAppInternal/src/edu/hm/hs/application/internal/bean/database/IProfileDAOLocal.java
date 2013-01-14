package edu.hm.hs.application.internal.bean.database;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntityProfile;

/**
 * Interface der Bean für den Datenbankzugriff auf die Profile Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface IProfileDAOLocal
{

	/**
	 * Estellt ein neues Benutzer-Profil in der Datenbank.
	 * 
	 * @param profile
	 *            Zu erstellendes Benutzer-Profil
	 * @return Erstelltes Benutzer-Profil
	 */
	EntityProfile create( EntityProfile profile );

	/**
	 * Liest ein Benutzer-Profil anhand der Benutzer-Profilid aus der Datenbank aus.
	 * 
	 * @param id
	 *            Benutzer-Profilid
	 * @return Gefundenes Benutzer-Profil oder NULL
	 */
	EntityProfile read( Long id );

	/**
	 * Aktualisiert ein Benutzer-Profil in der Datenbank.
	 * 
	 * @param profile
	 *            Zu aktualisierendes Benutzer-Profil
	 * @return Aktualisertes Benutzer-Profil
	 */
	EntityProfile update( EntityProfile profile );

	/**
	 * Entfernt ein Benutzer-Profil aus der Datenbank.
	 * 
	 * @param profile
	 *            Zu entfernendes Benutzer-Profil
	 */
	void delete( EntityProfile profile );
}
