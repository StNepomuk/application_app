package edu.hm.hs.application.service.bean.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.IContactInfoService;
import edu.hm.hs.application.api.object.resource.ContactInfo;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityContactInfo;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntityUser;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class ContactInfoService extends AbstractBean implements IContactInfoService
{

	@EJB
	private IUserDAOLocal m_userDAOBean;

	@EJB
	private IProfileDAOLocal m_profileDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IContactInfoService#findAll(long)
	 */
	@Override
	public List<ContactInfo> findAll( long userId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();

		for (EntityContactInfo eContactInfo : eUser.getProfile().getContactInfos())
		{
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setId( eContactInfo.getId() );
			contactInfo.setType( eContactInfo.getType() );
			contactInfo.setValue( eContactInfo.getName() );

			contactInfos.add( contactInfo );
		}

		return contactInfos;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IContactInfoService#create(long,
	 *      edu.hm.hs.application.api.object.resource.ContactInfo)
	 */
	@Override
	public ContactInfo create( long userId, ContactInfo contactInfo )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		EntityContactInfo eContactInfo = new EntityContactInfo();
		eContactInfo.setType( contactInfo.getType() );
		eContactInfo.setName( contactInfo.getValue() );

		if (eUser.getProfile().getContactInfo( eContactInfo ) != null)
		{
			throw new BadRequestException( "contactInfo already assigned" );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.addContactInfo( eContactInfo );

		eContactInfo = m_profileDAOBean.update( eProfile ).getContactInfo( eContactInfo );

		contactInfo = new ContactInfo();
		contactInfo.setId( eContactInfo.getId() );
		contactInfo.setType( eContactInfo.getType() );
		contactInfo.setValue( eContactInfo.getName() );

		return contactInfo;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IContactInfoService#find(long, long)
	 */
	@Override
	public ContactInfo find( long userId, long contactInfoId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		if (eUser.getProfile().getContactInfo( contactInfoId ) == null)
		{
			throw new NotFoundException( "contactInfo with id " + contactInfoId + " for user with userId:" + userId );
		}

		EntityContactInfo eContactInfo = eUser.getProfile().getContactInfo( contactInfoId );

		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId( eContactInfo.getId() );
		contactInfo.setType( eContactInfo.getType() );
		contactInfo.setValue( eContactInfo.getName() );

		return contactInfo;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IContactInfoService#update(long, long,
	 *      edu.hm.hs.application.api.object.resource.ContactInfo)
	 */
	@Override
	public ContactInfo update( long userId, long contactInfoId, ContactInfo contactInfo )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		if (eUser.getProfile().getContactInfo( contactInfoId ) == null)
		{
			throw new NotFoundException( "contactInfo with id " + contactInfoId + " for user with userId:" + userId );
		}

		EntityProfile eProfile = eUser.getProfile();
		EntityContactInfo eContactInfo = new EntityContactInfo();
		eContactInfo.setType( contactInfo.getType() );
		eContactInfo.setName( contactInfo.getValue() );

		if (eProfile.getContactInfo( eContactInfo ) != null)
		{
			throw new BadRequestException( "contactInfo already assigned" );
		}

		eContactInfo = eProfile.getContactInfo( contactInfoId );
		eContactInfo.setType( contactInfo.getType() );
		eContactInfo.setName( contactInfo.getValue() );

		eContactInfo = m_profileDAOBean.update( eProfile ).getContactInfo( eContactInfo );

		contactInfo = new ContactInfo();
		contactInfo.setId( eContactInfo.getId() );
		contactInfo.setType( eContactInfo.getType() );
		contactInfo.setValue( eContactInfo.getName() );

		return contactInfo;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.IContactInfoService#remove(long, long)
	 */
	@Override
	public void remove( long userId, long contactInfoId )
	{
		EntityUser eUser = m_userDAOBean.read( userId );

		if (eUser == null)
		{
			throw new NotFoundException( "user with id:" + userId );
		}

		if (eUser.getProfile() == null)
		{
			throw new NotFoundException( "profile for user with userId:" + userId );
		}

		if (eUser.getProfile().getContactInfo( contactInfoId ) == null)
		{
			throw new NotFoundException( "contactInfo with id " + contactInfoId + " for user with userId:" + userId );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.removeContactInfo( eProfile.getContactInfo( contactInfoId ) );

		m_profileDAOBean.update( eProfile );
	}
}
