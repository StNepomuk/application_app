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
 * Resource für die Adresse.
 * 
 * @author Stefan Wörner
 */
@JsonPropertyOrder( value = { "id", "street", "houseNumber", "zipCode", "city" }, alphabetic = true )
@JsonSerialize( include = Inclusion.ALWAYS )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class Address extends AbstractRessourceObject
{

	private static final long serialVersionUID = -1300543223047201201L;

	private Long m_id;

	private String m_street;

	private String m_houseNumber;

	private Integer m_zipCode;

	private String m_city;

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
	 * Liefert das Attribut street.
	 * 
	 * @return street
	 */
	@JsonProperty( "street" )
	public String getStreet()
	{
		return m_street;
	}

	/**
	 * Setzt das Attribut street.
	 * 
	 * @param street
	 *            zu setzender Wert für das Attribut street
	 */
	@JsonProperty( "street" )
	public void setStreet( String street )
	{
		m_street = street;
	}

	/**
	 * Liefert das Attribut houseNumber.
	 * 
	 * @return houseNumber
	 */
	@JsonProperty( "houseNumber" )
	public String getHouseNumber()
	{
		return m_houseNumber;
	}

	/**
	 * Setzt das Attribut houseNumber.
	 * 
	 * @param houseNumber
	 *            zu setzender Wert für das Attribut houseNumber
	 */
	@JsonProperty( "houseNumber" )
	public void setHouseNumber( String houseNumber )
	{
		m_houseNumber = houseNumber;
	}

	/**
	 * Liefert das Attribut zipCode.
	 * 
	 * @return zipCode
	 */
	@JsonProperty( "zipCode" )
	public Integer getZipCode()
	{
		return m_zipCode;
	}

	/**
	 * Setzt das Attribut zipCode.
	 * 
	 * @param zipCode
	 *            zu setzender Wert für das Attribut zipCode
	 */
	@JsonProperty( "zipCode" )
	public void setZipCode( Integer zipCode )
	{
		m_zipCode = zipCode;
	}

	/**
	 * Liefert das Attribut city.
	 * 
	 * @return city
	 */
	@JsonProperty( "city" )
	public String getCity()
	{
		return m_city;
	}

	/**
	 * Setzt das Attribut city.
	 * 
	 * @param city
	 *            zu setzender Wert für das Attribut city
	 */
	@JsonProperty( "city" )
	public void setCity( String city )
	{
		m_city = city;
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
				Address.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, Address.class, getExclusionList() );
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
		rsb.setUpToClass( Address.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
