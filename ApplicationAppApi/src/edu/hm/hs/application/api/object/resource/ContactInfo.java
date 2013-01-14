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
import edu.hm.hs.application.api.object.enumeration.ContactType;

/**
 * Resource für die Kontakt-Infos.
 * 
 * @author Stefan Wörner
 */
@JsonPropertyOrder( value = { "id", "type", "value" }, alphabetic = true )
@JsonSerialize( include = Inclusion.NON_NULL )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class ContactInfo extends AbstractRessourceObject
{

	private static final long serialVersionUID = 1692104957800493733L;

	private Long m_id;

	private ContactType m_type;

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
	 * Liefert das Attribut type.
	 * 
	 * @return type
	 */
	@JsonProperty( "type" )
	public ContactType getType()
	{
		return m_type;
	}

	/**
	 * Setzt das Attribut type.
	 * 
	 * @param type
	 *            zu setzender Wert für das Attribut type
	 */
	@JsonProperty( "type" )
	public void setType( ContactType type )
	{
		m_type = type;
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
				ContactInfo.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, ContactInfo.class, getExclusionList() );
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
		rsb.setUpToClass( ContactInfo.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
