package edu.hm.hs.application.internal.object.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für den Job.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Job" )
@Table( name = "jobs" )
public class EntityJob extends AbstractEntityObject
{

	private static final long serialVersionUID = 7160017059160999590L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "name", length = 255, nullable = false )
	private String m_name;

	// Bidirektionale n:1 Beziehung (Besitzende Seite)
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	@JoinColumn( name = "company_id" )
	private EntityCompany m_company;

	// Bidirektionale 1:n Beziehung (Inverse Seite)
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "m_job" )
	private List<EntityApplication> m_applications;

	/**
	 * Standardkonstruktor.
	 */
	public EntityJob()
	{
		m_applications = new ArrayList<EntityApplication>();
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
	 * Liefert das Attribut company.
	 * 
	 * @return company
	 */
	public EntityCompany getCompany()
	{
		return m_company;
	}

	/**
	 * Setzt das Attribut company.
	 * 
	 * @param company
	 *            zu setzender Wert für das Attribut company
	 */
	public void setCompany( EntityCompany company )
	{
		m_company = company;
	}

	/**
	 * Liefert das Attribut applications.
	 * 
	 * @return applications
	 */
	public List<EntityApplication> getApplications()
	{
		return m_applications;
	}

	/**
	 * Setzt das Attribut applications.
	 * 
	 * @param applications
	 *            zu setzender Wert für das Attribut applications
	 */
	public void setApplications( List<EntityApplication> applications )
	{
		m_applications = applications;
	}

	/**
	 * Fügt eine Bewerbung hinzu.
	 * 
	 * @param application
	 *            Hinzuzufügende Bewerbung
	 */
	public void addApplication( EntityApplication application )
	{
		application.setJob( this );
		m_applications.add( application );
	}

	/**
	 * Löscht die Bewerbung.
	 * 
	 * @param application
	 *            Zu löschende Bewerbung
	 */
	public void removeApplication( EntityApplication application )
	{
		m_applications.remove( application );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return new String[] { "m_id", "m_applications" };
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
				EntityJob.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityJob.class, getExclusionList() );
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
		rsb.setUpToClass( EntityJob.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		rsb.setExcludeFieldNames( "m_applications" );
		return rsb.toString();
	}
}
