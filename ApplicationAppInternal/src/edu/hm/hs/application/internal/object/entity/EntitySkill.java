package edu.hm.hs.application.internal.object.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für die Bewerber-Skills.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Skill" )
@Table( name = "skills" )
public class EntitySkill extends AbstractEntityObject
{

	private static final long serialVersionUID = -5573129073334396649L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "name", length = 255, nullable = false )
	private String m_name;

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	public Long getId()
	{
		return m_id;
	}

	/**
	 * Setzt das Attribut id.
	 * 
	 * @param id
	 *            zu setzender Wert für das Attribut id
	 */
	public void setId( Long id )
	{
		m_id = id;
	}

	/**
	 * Liefert das Attribut name.
	 * 
	 * @return name
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * Setzt das Attribut name.
	 * 
	 * @param name
	 *            zu setzender Wert für das Attribut name
	 */
	public void setName( String name )
	{
		m_name = name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return new String[] { "m_id" };
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode( INITIAL_NON_ZERO_ODD_NUMBER, MULTIPLIER_NON_ZERO_ODD_NUMBER, this, true,
				EntitySkill.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntitySkill.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( EntitySkill.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
