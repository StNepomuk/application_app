package edu.hm.hs.application.internal.bean.database;

import java.util.List;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntityCompany;

/**
 * Interface der Bean für den Datenbankzugriff auf die Company Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface ICompanyDAOLocal
{

	/**
	 * Estellt einen neuen Unternehmen in der Datenbank.
	 * 
	 * @param company
	 *            Zu erstellender Unternehmen
	 * @return Erstellter Unternehmen
	 */
	EntityCompany create( EntityCompany company );

	/**
	 * Liest einen Unternehmen anhand der Unternehmenid aus der Datenbank aus.
	 * 
	 * @param id
	 *            Unternehmenid
	 * @return Gefundener Unternehmen oder NULL
	 */
	EntityCompany read( Long id );

	/**
	 * Liest alle Unternehmen aus.
	 * 
	 * @return Liste mit gefundenen Unternehmenn
	 */
	List<EntityCompany> readAll();

	/**
	 * Aktualisiert einen Unternehmen in der Datenbank.
	 * 
	 * @param company
	 *            Zu aktualisierender Unternehmen
	 * @return Aktualiserter Unternehmen
	 */
	EntityCompany update( EntityCompany company );

	/**
	 * Entfernt einen Unternehmen aus der Datenbank.
	 * 
	 * @param company
	 *            Zu entfernender Unternehmen
	 */
	void delete( EntityCompany company );
}
