package edu.hm.hs.application.bean.database;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.hs.application.internal.bean.AbstractBean;
import edu.hm.hs.application.internal.bean.database.ISkillDAOLocal;
import edu.hm.hs.application.internal.object.entity.EntitySkill;

/**
 * Bean für den Datenbankzugriff auf die Skill Entität.
 * 
 * @author Stefan Wörner
 */
@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRED )
public class SkillDAOBean extends AbstractBean implements ISkillDAOLocal
{

	@PersistenceContext( unitName = "ApplicationAppManager" )
	private EntityManager m_em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ISkillDAOLocal#create(edu.hm.hs.application.internal.object.entity.EntitySkill)
	 */
	@Override
	public EntitySkill create( EntitySkill skill )
	{
		m_em.persist( skill );
		return skill;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ISkillDAOLocal#read(java.lang.Long)
	 */
	@Override
	public EntitySkill read( Long id )
	{
		return m_em.find( EntitySkill.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ISkillDAOLocal#readAll()
	 */
	@Override
	public List<EntitySkill> readAll()
	{
		return m_em.createQuery( "FROM EntitySkill", EntitySkill.class ).getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ISkillDAOLocal#update(edu.hm.hs.application.internal.object.entity.EntitySkill)
	 */
	@Override
	public EntitySkill update( EntitySkill skill )
	{
		return m_em.merge( skill );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.hs.application.internal.bean.database.ISkillDAOLocal#delete(edu.hm.hs.application.internal.object.entity.EntitySkill)
	 */
	@Override
	public void delete( EntitySkill skill )
	{
		m_em.remove( skill );
	}
}
