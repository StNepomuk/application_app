package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.Address;
import edu.hm.hs.application.internal.object.entity.EntityAddress;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class AddressMapper
{

	private AddressMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param address
	 *            Ressource
	 * @return Entity
	 */
	public static EntityAddress mapRessourceToEntity( Address address )
	{
		EntityAddress eAddress = new EntityAddress();
		eAddress.setStreet( address.getStreet() );
		eAddress.setHouseNumber( address.getHouseNumber() );
		eAddress.setZipCode( address.getZipCode() );
		eAddress.setCity( address.getCity() );

		return eAddress;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eAddresss
	 *            Entities
	 * @return Resources
	 */
	public static List<Address> mapEntityToRessource( List<EntityAddress> eAddresss )
	{
		List<Address> addresss = new ArrayList<Address>();

		for (EntityAddress eAddress : eAddresss)
		{
			addresss.add( mapEntityToRessource( eAddress ) );
		}

		return addresss;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eAddress
	 *            Entity
	 * @return Ressource
	 */
	public static Address mapEntityToRessource( EntityAddress eAddress )
	{
		Address address = new Address();
		address.setId( eAddress.getId() );
		address.setStreet( eAddress.getStreet() );
		address.setHouseNumber( eAddress.getHouseNumber() );
		address.setZipCode( eAddress.getZipCode() );
		address.setCity( eAddress.getCity() );

		return address;
	}
}
