package edu.hm.hs.application.internal.object.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import edu.hm.hs.application.api.object.ErrorConstants;
import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für die Adresse.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Address" )
@Table( name = "addresses" )
public class EntityAddress extends AbstractEntityObject
{

	private static final long serialVersionUID = -9159495875237573215L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "street", length = 255, nullable = false )
	@NotEmpty( message = ErrorConstants.STREET_EMPTY_ERROR_MSG )
	private String m_street;

	@Column( name = "house_number", length = 10, nullable = false )
	@NotEmpty( message = ErrorConstants.HOUSENUMBER_EMPTY_ERROR_MSG )
	private String m_houseNumber;

	@Column( name = "zip_code", length = 5, nullable = false )
	@NotNull( message = ErrorConstants.ZIPCODE_EMPTY_ERROR_MSG )
	@Digits( integer = 5, fraction = 0, message = ErrorConstants.ZIPCODE_LENGHT_ERROR_MSG )
	private Integer m_zipCode;

	@Column( name = "city", length = 255, nullable = false )
	@NotEmpty( message = ErrorConstants.CITY_EMPTY_ERROR_MSG )
	private String m_city;

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
	 * Liefert das Attribut street.
	 * 
	 * @return street
	 */
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
	public void setStreet( String street )
	{
		m_street = street;
	}

	/**
	 * Liefert das Attribut houseNumber.
	 * 
	 * @return houseNumber
	 */
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
	public void setHouseNumber( String houseNumber )
	{
		m_houseNumber = houseNumber;
	}

	/**
	 * Liefert das Attribut zipCode.
	 * 
	 * @return zipCode
	 */
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
	public void setZipCode( Integer zipCode )
	{
		m_zipCode = zipCode;
	}

	/**
	 * Liefert das Attribut city.
	 * 
	 * @return city
	 */
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
				EntityAddress.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityAddress.class, getExclusionList() );
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
		rsb.setUpToClass( EntityAddress.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
