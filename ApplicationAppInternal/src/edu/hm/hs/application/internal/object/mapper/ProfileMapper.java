package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.ContactInfo;
import edu.hm.hs.application.api.object.resource.Profile;
import edu.hm.hs.application.api.object.resource.Skill;
import edu.hm.hs.application.internal.object.entity.EntityContactInfo;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntitySkill;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class ProfileMapper
{

	private ProfileMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param profile
	 *            Ressource
	 * @param eUser
	 *            Entity
	 * @return Entity
	 */
	public static EntityProfile mapRessourceToEntity( Profile profile, EntityUser eUser )
	{
		EntityProfile eProfile = new EntityProfile();
		eProfile.setUser( eUser );
		eProfile.setFirstname( profile.getFirstname() );
		eProfile.setLastname( profile.getLastname() );
		eProfile.setGender( profile.getGender() );
		eProfile.seteMailAddress( profile.geteMailAddress() );
		eProfile.setDateOfBirth( profile.getDateOfBirth() );

		if (profile.getAddress() != null)
		{
			eProfile.setAddress( AddressMapper.mapRessourceToEntity( profile.getAddress() ) );
		}

		for (Skill skill : profile.getSkills())
		{
			eProfile.addSkill( SkillMapper.mapRessourceToEntity( skill ) );
		}

		for (ContactInfo contactInfo : profile.getContactInfos())
		{
			eProfile.addContactInfo( ContactInfoMapper.mapRessourceToEntity( contactInfo ) );
		}

		return eProfile;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eProfiles
	 *            Entities
	 * @return Resources
	 */
	public static List<Profile> mapEntityToRessource( List<EntityProfile> eProfiles )
	{
		List<Profile> profiles = new ArrayList<Profile>();

		for (EntityProfile eProfile : eProfiles)
		{
			profiles.add( mapEntityToRessource( eProfile ) );
		}

		return profiles;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eProfile
	 *            Entity
	 * @return Ressource
	 */
	public static Profile mapEntityToRessource( EntityProfile eProfile )
	{
		Profile profile = new Profile();
		profile.setId( eProfile.getId() );
		profile.setFirstname( eProfile.getFirstname() );
		profile.setLastname( eProfile.getLastname() );
		profile.setGender( eProfile.getGender() );
		profile.seteMailAddress( eProfile.geteMailAddress() );
		profile.setDateOfBirth( eProfile.getDateOfBirth() );

		if (eProfile.getAddress() != null)
		{
			profile.setAddress( AddressMapper.mapEntityToRessource( eProfile.getAddress() ) );
		}

		for (EntitySkill eSkill : eProfile.getSkills())
		{
			profile.addSkill( SkillMapper.mapEntityToRessource( eSkill ) );
		}

		for (EntityContactInfo eContactInfo : eProfile.getContactInfos())
		{
			profile.addContactInfo( ContactInfoMapper.mapEntityToRessource( eContactInfo ) );
		}

		return profile;
	}
}
