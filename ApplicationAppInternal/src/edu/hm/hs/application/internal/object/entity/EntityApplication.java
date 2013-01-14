package edu.hm.hs.application.internal.object.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für die Bewerbung.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Application" )
@Table( name = "applications" )
public class EntityApplication extends AbstractEntityObject
{

	private static final long serialVersionUID = 2294978356193439551L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "date", nullable = false )
	private Date m_date;

	// Bidirektionale n:1 Beziehung (Besitzende Seite)
	@ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "job_id", nullable = false )
	private EntityJob m_job;

	// Bidirektionale n:1 Beziehung (Besitzende Seite)
	@ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "profile_id", nullable = false )
	private EntityProfile m_profile;

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
	 * Liefert das Attribut date.
	 * 
	 * @return date
	 */
	public Date getDate()
	{
		return m_date;
	}

	/**
	 * Setzt das Attribut date.
	 * 
	 * @param date
	 *            zu setzender Wert für das Attribut date
	 */
	public void setDate( Date date )
	{
		m_date = date;
	}

	/**
	 * Liefert das Attribut job.
	 * 
	 * @return job
	 */
	public EntityJob getJob()
	{
		return m_job;
	}

	/**
	 * Setzt das Attribut job.
	 * 
	 * @param job
	 *            zu setzender Wert für das Attribut job
	 */
	public void setJob( EntityJob job )
	{
		m_job = job;
	}

	/**
	 * Liefert das Attribut profile.
	 * 
	 * @return profile
	 */
	public EntityProfile getProfile()
	{
		return m_profile;
	}

	/**
	 * Setzt das Attribut profile.
	 * 
	 * @param profile
	 *            zu setzender Wert für das Attribut profile
	 */
	public void setProfile( EntityProfile profile )
	{
		m_profile = profile;
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
				EntityApplication.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityApplication.class, getExclusionList() );
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
		rsb.setUpToClass( EntityApplication.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
