package edu.hm.hs.application.internal.bean.database;

import java.util.List;

import javax.ejb.Local;

import edu.hm.hs.application.internal.object.entity.EntitySkill;

/**
 * Interface der Bean für den Datenbankzugriff auf die Skill Entität.
 * 
 * @author Stefan Wörner
 */
@Local
public interface ISkillDAOLocal
{

	/**
	 * Estellt einen neuen Skill in der Datenbank.
	 * 
	 * @param skill
	 *            Zu erstellender Skill
	 * @return Erstellter Skill
	 */
	EntitySkill create( EntitySkill skill );

	/**
	 * Liest einen Skill anhand der Skillid aus der Datenbank aus.
	 * 
	 * @param id
	 *            Skillid
	 * @return Gefundener Skill oder NULL
	 */
	EntitySkill read( Long id );

	/**
	 * Liest alle Skill aus.
	 * 
	 * @return Liste mit gefundenen Skilln
	 */
	List<EntitySkill> readAll();

	/**
	 * Aktualisiert einen Skill in der Datenbank.
	 * 
	 * @param skill
	 *            Zu aktualisierender Skill
	 * @return Aktualiserter Skill
	 */
	EntitySkill update( EntitySkill skill );

	/**
	 * Entfernt einen Skill aus der Datenbank.
	 * 
	 * @param skill
	 *            Zu entfernender Skill
	 */
	void delete( EntitySkill skill );
}
