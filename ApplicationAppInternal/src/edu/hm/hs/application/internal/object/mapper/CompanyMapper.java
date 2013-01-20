package edu.hm.hs.application.internal.object.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.hm.hs.application.api.object.resource.Company;
import edu.hm.hs.application.internal.object.entity.EntityCompany;

/**
 * Entity / Ressource Mapper.
 * 
 * @author Stefan Wörner
 */
public final class CompanyMapper
{

	private CompanyMapper()
	{

	}

	/**
	 * Mapper auf Entity.
	 * 
	 * @param company
	 *            Ressource
	 * @return Entity
	 */
	public static EntityCompany mapRessourceToEntity( Company company )
	{
		EntityCompany eCompany = new EntityCompany();
		eCompany.setName( company.getName() );

		return eCompany;
	}

	/**
	 * Mapper auf Ressources.
	 * 
	 * @param eCompanys
	 *            Entities
	 * @return Resources
	 */
	public static List<Company> mapEntityToRessource( List<EntityCompany> eCompanys )
	{
		List<Company> companys = new ArrayList<Company>();

		for (EntityCompany eCompany : eCompanys)
		{
			companys.add( mapEntityToRessource( eCompany ) );
		}

		return companys;
	}

	/**
	 * Mapper auf Ressource.
	 * 
	 * @param eCompany
	 *            Entity
	 * @return Ressource
	 */
	public static Company mapEntityToRessource( EntityCompany eCompany )
	{
		Company company = new Company();
		company.setId( eCompany.getId() );
		company.setName( eCompany.getName() );

		return company;
	}
}
