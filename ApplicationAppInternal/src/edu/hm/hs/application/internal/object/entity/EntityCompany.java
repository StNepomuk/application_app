package edu.hm.hs.application.internal.object.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für die Firma.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Company" )
@Table( name = "companies" )
public class EntityCompany extends AbstractEntityObject
{

	private static final long serialVersionUID = -3397673168523034531L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "name", length = 255, nullable = false )
	private String m_name;

	// Bidirektionale 1:n Beziehung (Inverse Seite)
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "m_company", orphanRemoval = true, fetch = FetchType.LAZY )
	private List<EntityJob> m_jobs;

	/**
	 * Standardkonstruktor.
	 */
	public EntityCompany()
	{
		m_jobs = new ArrayList<EntityJob>();
	}

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
	 * Liefert das Attribut jobs.
	 * 
	 * @return jobs
	 */
	public List<EntityJob> getJobs()
	{
		return m_jobs;
	}

	/**
	 * Setzt das Attribut jobs.
	 * 
	 * @param jobs
	 *            zu setzender Wert für das Attribut jobs
	 */
	public void setJobs( List<EntityJob> jobs )
	{
		m_jobs = jobs;
	}

	/**
	 * Fügt einen Job hinzu.
	 * 
	 * @param job
	 *            Hinzuzufügender Job
	 */
	public void addJob( EntityJob job )
	{
		job.setCompany( this );
		m_jobs.add( job );
	}

	/**
	 * Liefert den passenden Job.
	 * 
	 * @param jobId
	 *            Gesuchter Job
	 * @return Job
	 */
	public EntityJob getJob( Long jobId )
	{
		Iterator<EntityJob> iterator = m_jobs.iterator();

		while (iterator.hasNext())
		{
			EntityJob contJob = iterator.next();
			try
			{
				if (contJob.getId().equals( jobId ))
				{
					return contJob;
				}
			}
			catch (NullPointerException e)
			{
				continue;
			}
		}

		return null;
	}

	/**
	 * Löscht den Job.
	 * 
	 * @param job
	 *            Zu löschender Job
	 */
	public void removeJob( EntityJob job )
	{
		m_jobs.remove( job );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return new String[] { "m_id", "m_jobs" };
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
				EntityCompany.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityCompany.class, getExclusionList() );
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
		rsb.setUpToClass( EntityCompany.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		rsb.setExcludeFieldNames( "m_jobs" );
		return rsb.toString();
	}
}
