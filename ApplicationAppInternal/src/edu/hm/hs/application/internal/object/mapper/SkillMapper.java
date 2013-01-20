package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.Skill;
import edu.hm.hs.application.internal.object.entity.EntitySkill;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class SkillMapper
{

	private SkillMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param skill
	 *            Ressource
	 * @return Entity
	 */
	public static EntitySkill mapRessourceToEntity( Skill skill )
	{
		EntitySkill eSkill = new EntitySkill();
		eSkill.setName( skill.getValue() );

		return eSkill;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eSkills
	 *            Entities
	 * @return Resources
	 */
	public static List<Skill> mapEntityToRessource( List<EntitySkill> eSkills )
	{
		List<Skill> skills = new ArrayList<Skill>();

		for (EntitySkill eSkill : eSkills)
		{
			skills.add( mapEntityToRessource( eSkill ) );
		}

		return skills;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eSkill
	 *            Entity
	 * @return Ressource
	 */
	public static Skill mapEntityToRessource( EntitySkill eSkill )
	{
		Skill skill = new Skill();
		skill.setId( eSkill.getId() );
		skill.setValue( eSkill.getName() );

		return skill;
	}
}
