package edu.hm.hs.application.api.object.resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import edu.hm.hs.application.api.object.AbstractRessourceObject;

/**
 * Resource für den Job.
 * 
 * @author Stefan Wörner
 */
@JsonPropertyOrder( value = { "id", "date", "profile", "job" }, alphabetic = true )
@JsonSerialize( include = Inclusion.NON_NULL )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class Application extends AbstractRessourceObject
{

	private static final long serialVersionUID = -1614196997206535676L;

	private Long m_id;

	private Date m_date;

	private Job m_job;

	private Profile m_profile;

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	@JsonProperty( "id" )
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
	@JsonProperty( "id" )
	public void setId( Long id )
	{
		m_id = id;
	}

	/**
	 * Liefert das Attribut date.
	 * 
	 * @return date
	 */
	@JsonProperty( "date" )
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
	@JsonProperty( "date" )
	public void setDate( Date date )
	{
		m_date = date;
	}

	/**
	 * Liefert das Attribut job.
	 * 
	 * @return job
	 */
	@JsonProperty( "job" )
	public Job getJob()
	{
		return m_job;
	}

	/**
	 * Setzt das Attribut job.
	 * 
	 * @param job
	 *            zu setzender Wert für das Attribut job
	 */
	@JsonProperty( "job" )
	public void setJob( Job job )
	{
		m_job = job;
	}

	/**
	 * Liefert das Attribut profile.
	 * 
	 * @return profile
	 */
	@JsonProperty( "profile" )
	public Profile getProfile()
	{
		return m_profile;
	}

	/**
	 * Setzt das Attribut profile.
	 * 
	 * @param profile
	 *            zu setzender Wert für das Attribut profile
	 */
	@JsonProperty( "profile" )
	public void setProfile( Profile profile )
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
		return new String[] {};
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
				Application.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, Application.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( Application.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
