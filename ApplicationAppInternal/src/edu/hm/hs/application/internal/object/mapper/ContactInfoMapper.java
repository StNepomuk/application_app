package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.ContactInfo;
import edu.hm.hs.application.internal.object.entity.EntityContactInfo;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class ContactInfoMapper
{

	private ContactInfoMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param contactInfo
	 *            Ressource
	 * @return Entity
	 */
	public static EntityContactInfo mapRessourceToEntity( ContactInfo contactInfo )
	{
		EntityContactInfo eContactInfo = new EntityContactInfo();
		eContactInfo.setType( contactInfo.getType() );
		eContactInfo.setName( contactInfo.getValue() );

		return eContactInfo;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eContactInfos
	 *            Entities
	 * @return Resources
	 */
	public static List<ContactInfo> mapEntityToRessource( List<EntityContactInfo> eContactInfos )
	{
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();

		for (EntityContactInfo eContactInfo : eContactInfos)
		{
			contactInfos.add( mapEntityToRessource( eContactInfo ) );
		}

		return contactInfos;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eContactInfo
	 *            Entity
	 * @return Ressource
	 */
	public static ContactInfo mapEntityToRessource( EntityContactInfo eContactInfo )
	{
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId( eContactInfo.getId() );
		contactInfo.setType( eContactInfo.getType() );
		contactInfo.setValue( eContactInfo.getName() );

		return contactInfo;
	}
}
