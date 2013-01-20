package edu.hm.hs.application.service.bean.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import edu.hm.hs.application.api.communication.request.ISkillService;
import edu.hm.hs.application.api.object.resource.Skill;
import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.IProfileDAOLocal;
import edu.hm.hs.application.internal.bean.database.IUserDAOLocal;
import edu.hm.hs.application.internal.interceptor.LoggingInterceptor;
import edu.hm.hs.application.internal.object.entity.EntityProfile;
import edu.hm.hs.application.internal.object.entity.EntitySkill;
import edu.hm.hs.application.internal.object.entity.EntityUser;
import edu.hm.hs.application.internal.object.mapper.SkillMapper;

/**
 * REST-Service für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class SkillService extends AbstractBean implements ISkillService
{

	@EJB
	private IUserDAOLocal m_userDAOBean;

	@EJB
	private IProfileDAOLocal m_profileDAOBean;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ISkillService#findAll(long)
	 */
	@Override
	public List<Skill> findAll( long userId )
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

		return SkillMapper.mapEntityToRessource( eUser.getProfile().getSkills() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ISkillService#create(long,
	 *      edu.hm.hs.application.api.object.resource.Skill)
	 */
	@Override
	public Skill create( long userId, Skill skill )
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

		EntitySkill eSkill = SkillMapper.mapRessourceToEntity( skill );

		if (eUser.getProfile().getSkill( eSkill ) != null)
		{
			throw new BadRequestException( "skill already assigned" );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.addSkill( eSkill );

		eSkill = m_profileDAOBean.update( eProfile ).getSkill( eSkill );

		return SkillMapper.mapEntityToRessource( eSkill );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ISkillService#find(long, long)
	 */
	@Override
	public Skill find( long userId, long skillId )
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

		if (eUser.getProfile().getSkill( skillId ) == null)
		{
			throw new NotFoundException( "skill with id " + skillId + " for user with userId:" + userId );
		}

		return SkillMapper.mapEntityToRessource( eUser.getProfile().getSkill( skillId ) );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ISkillService#update(long, long,
	 *      edu.hm.hs.application.api.object.resource.Skill)
	 */
	@Override
	public Skill update( long userId, long skillId, Skill skill )
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

		if (eUser.getProfile().getSkill( skillId ) == null)
		{
			throw new NotFoundException( "skill with id " + skillId + " for user with userId:" + userId );
		}

		EntityProfile eProfile = eUser.getProfile();
		EntitySkill eSkill = new EntitySkill();
		eSkill.setName( skill.getValue() );

		if (eProfile.getSkill( eSkill ) != null)
		{
			throw new BadRequestException( "skill already assigned" );
		}

		eSkill = eProfile.getSkill( skillId );
		eSkill.setName( skill.getValue() );

		eSkill = m_profileDAOBean.update( eProfile ).getSkill( eSkill );

		return SkillMapper.mapEntityToRessource( eSkill );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.api.communication.request.ISkillService#remove(long, long)
	 */
	@Override
	public void remove( long userId, long skillId )
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

		if (eUser.getProfile().getSkill( skillId ) == null)
		{
			throw new NotFoundException( "skill with id " + skillId + " for user with userId:" + userId );
		}

		EntityProfile eProfile = eUser.getProfile();
		eProfile.removeSkill( eProfile.getSkill( skillId ) );

		m_profileDAOBean.update( eProfile );
	}
}
