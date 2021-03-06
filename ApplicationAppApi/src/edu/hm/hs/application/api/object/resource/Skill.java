package edu.hm.hs.application.api.object.resource;

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
 * Resource für die Skills.
 * 
 * @author Stefan Wörner
 */
@JsonPropertyOrder( value = { "id", "value" }, alphabetic = true )
@JsonSerialize( include = Inclusion.NON_NULL )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class Skill extends AbstractRessourceObject
{

	private static final long serialVersionUID = -724765179877364621L;

	private Long m_id;

	private String m_value;

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
	 * Liefert das Attribut value.
	 * 
	 * @return value
	 */
	@JsonProperty( "value" )
	public String getValue()
	{
		return m_value;
	}

	/**
	 * Setzt das Attribut value.
	 * 
	 * @param value
	 *            zu setzender Wert für das Attribut value
	 */
	@JsonProperty( "value" )
	public void setValue( String value )
	{
		m_value = value;
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
				Skill.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, Skill.class, getExclusionList() );
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
		rsb.setUpToClass( Skill.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
