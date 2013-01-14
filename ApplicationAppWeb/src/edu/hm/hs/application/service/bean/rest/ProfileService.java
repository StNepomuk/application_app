package edu.hm.hs.application.service.bean.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IProfileService;
import edu.hm.hs.application.api.object.resource.Address;
import edu.hm.hs.application.api.object.resource.Profile;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityAddress;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class ProfileService extends AbstractBean implements IProfileService
{

	@EJB
	private IUserDAOLocal m_userDAOBean;

	@EJB
	private IProfileDAOLocal m_profileDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#create(long,
	 *      edu.hm.hs.application.api.object.resource.Profile)
	 */
	@Override
	public Profile create( long id, Profile profile )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		if (eUser.getProfile() != null)
		{
			throw new BadRequestException( "profile already exists" );
		}

		EntityProfile eProfile = new EntityProfile();
		eProfile.setUser( eUser );
		eProfile.setFirstname( profile.getFirstname() );
		eProfile.setLastname( profile.getLastname() );
		eProfile.setGender( profile.getGender() );
		eProfile.seteMailAddress( profile.geteMailAddress() );
		eProfile.setDateOfBirth( profile.getDateOfBirth() );

		if (profile.getAddress() != null)
		{
			EntityAddress eAddress = new EntityAddress();
			eAddress.setStreet( profile.getAddress().getStreet() );
			eAddress.setHouseNumber( profile.getAddress().getHouseNumber() );
			eAddress.setZipCode( profile.getAddress().getZipCode() );
			eAddress.setCity( profile.getAddress().getCity() );
			eProfile.setAddress( eAddress );
		}

		// for (Skill skill : profile.getSkills())
		// {
		// EntitySkill eSkill = new EntitySkill();
		// eSkill.setName( skill.getValue() );
		//
		// eProfile.addSkill( eSkill );
		// }
		//
		// for (ContactInfo contactInfo : profile.getContactInfos())
		// {
		// EntityContactInfo eContactInfo = new EntityContactInfo();
		// eContactInfo.setType( contactInfo.getType() );
		// eContactInfo.setName( contactInfo.getValue() );
		//
		// eProfile.addContactInfo( eContactInfo );
		// }

		eProfile = m_profileDAOBean.create( eProfile );

		profile = new Profile();
		profile.setId( eProfile.getId() );
		profile.setFirstname( eProfile.getFirstname() );
		profile.setLastname( eProfile.getLastname() );
		profile.setGender( eProfile.getGender() );
		profile.seteMailAddress( eProfile.geteMailAddress() );
		profile.setDateOfBirth( eProfile.getDateOfBirth() );

		if (eProfile.getAddress() != null)
		{
			Address address = new Address();
			address.setId( eProfile.getAddress().getId() );
			address.setStreet( eProfile.getAddress().getStreet() );
			address.setHouseNumber( eProfile.getAddress().getHouseNumber() );
			address.setZipCode( eProfile.getAddress().getZipCode() );
			address.setCity( eProfile.getAddress().getCity() );
			profile.setAddress( address );
		}

		// for (EntitySkill eSkill : eProfile.getSkills())
		// {
		// Skill skill = new Skill();
		// skill.setId( eSkill.getId() );
		// skill.setValue( eSkill.getName() );
		//
		// profile.addSkill( skill );
		// }
		//
		// for (EntityContactInfo eContactInfo : eProfile.getContactInfos())
		// {
		// ContactInfo contactInfo = new ContactInfo();
		// contactInfo.setId( eContactInfo.getId() );
		// contactInfo.setType( eContactInfo.getType() );
		// contactInfo.setValue( eContactInfo.getName() );
		//
		// profile.addContactInfo( contactInfo );
		// }

		return profile;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#find(long)
	 */
	@Override
	public Profile find( long id )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with id:" + id );
		}

		EntityProfile eProfile = eUser.getProfile();

		Profile profile = new Profile();
		profile.setId( eProfile.getId() );
		profile.setFirstname( eProfile.getFirstname() );
		profile.setLastname( eProfile.getLastname() );
		profile.setGender( eProfile.getGender() );
		profile.seteMailAddress( eProfile.geteMailAddress() );
		profile.setDateOfBirth( eProfile.getDateOfBirth() );

		if (eProfile.getAddress() != null)
		{
			Address address = new Address();
			address.setId( eProfile.getAddress().getId() );
			address.setStreet( eProfile.getAddress().getStreet() );
			address.setHouseNumber( eProfile.getAddress().getHouseNumber() );
			address.setZipCode( eProfile.getAddress().getZipCode() );
			address.setCity( eProfile.getAddress().getCity() );
			profile.setAddress( address );
		}

		// for (EntitySkill eSkill : eProfile.getSkills())
		// {
		// Skill skill = new Skill();
		// skill.setId( eSkill.getId() );
		// skill.setValue( eSkill.getName() );
		//
		// profile.addSkill( skill );
		// }
		//
		// for (EntityContactInfo eContactInfo : eProfile.getContactInfos())
		// {
		// ContactInfo contactInfo = new ContactInfo();
		// contactInfo.setId( eContactInfo.getId() );
		// contactInfo.setType( eContactInfo.getType() );
		// contactInfo.setValue( eContactInfo.getName() );
		//
		// profile.addContactInfo( contactInfo );
		// }

		return profile;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#update(long,
	 *      edu.hm.hs.application.api.object.resource.Profile)
	 */
	@Override
	public Profile update( long id, Profile profile )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with id:" + id );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.setFirstname( profile.getFirstname() );
		eProfile.setLastname( profile.getLastname() );
		eProfile.setGender( profile.getGender() );
		eProfile.seteMailAddress( profile.geteMailAddress() );
		eProfile.setDateOfBirth( profile.getDateOfBirth() );

		if (profile.getAddress() != null)
		{
			EntityAddress eAddress = eProfile.getAddress();
			if (eAddress == null)
			{
				eAddress = new EntityAddress();
			}

			eAddress.setStreet( profile.getAddress().getStreet() );
			eAddress.setHouseNumber( profile.getAddress().getHouseNumber() );
			eAddress.setZipCode( profile.getAddress().getZipCode() );
			eAddress.setCity( profile.getAddress().getCity() );
			eProfile.setAddress( eAddress );
		}
		else
		{
			eProfile.setAddress( null );
		}

		// if (profile.getSkills().size() == 0)
		// {
		// eProfile.removeAllSkills();
		// }
		// else
		// {
		// eProfile.removeAllSkills();
		//
		// for (Skill skill : profile.getSkills())
		// {
		// EntitySkill eSkill = new EntitySkill();
		// eSkill.setName( skill.getValue() );
		//
		// eProfile.addSkill( eSkill );
		// }
		// }
		//
		// if (profile.getContactInfos().size() == 0)
		// {
		// eProfile.removeAllContactInfos();
		// }
		//
		// for (ContactInfo contactInfo : profile.getContactInfos())
		// {
		// EntityContactInfo eContactInfo = new EntityContactInfo();
		// eContactInfo.setType( contactInfo.getType() );
		// eContactInfo.setName( contactInfo.getValue() );
		//
		// eProfile.addContactInfo( eContactInfo );
		// }

		m_profileDAOBean.update( eProfile );

		profile = new Profile();
		profile.setId( eProfile.getId() );
		profile.setFirstname( eProfile.getFirstname() );
		profile.setLastname( eProfile.getLastname() );
		profile.setGender( eProfile.getGender() );
		profile.seteMailAddress( eProfile.geteMailAddress() );
		profile.setDateOfBirth( eProfile.getDateOfBirth() );

		if (eProfile.getAddress() != null)
		{
			Address address = new Address();
			address.setId( eProfile.getAddress().getId() );
			address.setStreet( eProfile.getAddress().getStreet() );
			address.setHouseNumber( eProfile.getAddress().getHouseNumber() );
			address.setZipCode( eProfile.getAddress().getZipCode() );
			address.setCity( eProfile.getAddress().getCity() );
			profile.setAddress( address );
		}

		// for (EntitySkill eSkill : eProfile.getSkills())
		// {
		// Skill skill = new Skill();
		// skill.setId( eSkill.getId() );
		// skill.setValue( eSkill.getName() );
		//
		// profile.addSkill( skill );
		// }
		//
		// for (EntityContactInfo eContactInfo : eProfile.getContactInfos())
		// {
		// ContactInfo contactInfo = new ContactInfo();
		// contactInfo.setId( eContactInfo.getId() );
		// contactInfo.setType( eContactInfo.getType() );
		// contactInfo.setValue( eContactInfo.getName() );
		//
		// profile.addContactInfo( contactInfo );
		// }

		return profile;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#remove(long)
	 */
	@Override
	public void remove( long id )
	{
		EntityUser eUser = m_userDAOBean.read( id );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + id );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with id:" + id );
		}

		m_profileDAOBean.delete( eUser.getProfile() );
	}
}
