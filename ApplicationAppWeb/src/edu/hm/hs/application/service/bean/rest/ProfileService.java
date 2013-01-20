package edu.hm.hs.application.service.bean.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IProfileService;
import edu.hm.hs.application.api.object.resource.ContactInfo;
import edu.hm.hs.application.api.object.resource.Profile;
import edu.hm.hs.application.api.object.resource.Skill;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityAddress;
import edu.hm.hs.application.internal.object.entity.EntityContactInfo;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntitySkill;
import edu.hm.hs.application.internal.object.entity.EntityUser;
import edu.hm.hs.application.internal.object.mapper.ProfileMapper;

/**
 * REST-Service für den Benutzer-Profil.
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
	public Profile create( long userId, Profile profile )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with userId:" + userId );
		}

		if (eUser.getProfile() != null)
		{
			throw new BadRequestException( "profile already exists" );
		}

		EntityProfile eProfile = ProfileMapper.mapRessourceToEntity( profile, eUser );
		eProfile = m_profileDAOBean.create( eProfile );
		return ProfileMapper.mapEntityToRessource( eProfile );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#find(long)
	 */
	@Override
	public Profile find( long userId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with userId:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		return ProfileMapper.mapEntityToRessource( eUser.getProfile() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#update(long,
	 *      edu.hm.hs.application.api.object.resource.Profile)
	 */
	@Override
	public Profile update( long userId, Profile profile )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with userId:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
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

		for (Skill skill : profile.getSkills())
		{
			EntitySkill eSkill = new EntitySkill();
			eSkill.setName( skill.getValue() );

			eProfile.addSkill( eSkill );
		}

		for (ContactInfo contactInfo : profile.getContactInfos())
		{
			EntityContactInfo eContactInfo = new EntityContactInfo();
			eContactInfo.setType( contactInfo.getType() );
			eContactInfo.setName( contactInfo.getValue() );

			eProfile.addContactInfo( eContactInfo );
		}

		m_profileDAOBean.update( eProfile );

		return ProfileMapper.mapEntityToRessource( eProfile );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IProfileService#remove(long)
	 */
	@Override
	public void remove( long userId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with userId:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		m_profileDAOBean.delete( eUser.getProfile() );
	}
}
