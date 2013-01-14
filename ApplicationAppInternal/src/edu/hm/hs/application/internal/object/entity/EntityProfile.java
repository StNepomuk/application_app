package edu.hm.hs.application.internal.object.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import edu.hm.hs.application.api.object.ErrorConstants;
import edu.hm.hs.application.api.object.enumeration.Gender;
import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für das Benutzer-Profil.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "Profile" )
@Table( name = "profiles" )
public class EntityProfile extends AbstractEntityObject
{

	private static final long serialVersionUID = -8399234568090444586L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "firstname", length = 255, nullable = false )
	@NotEmpty( message = ErrorConstants.FIRSTNAME_EMPTY_ERROR_MSG )
	private String m_firstname;

	@Column( name = "lastname", length = 255, nullable = false )
	@NotEmpty( message = ErrorConstants.LASTNAME_EMPTY_ERROR_MSG )
	private String m_lastname;

	@Column( name = "email_address", length = 255, nullable = false )
	@NotEmpty( message = ErrorConstants.EMAIL_ADDRESS_EMPTY_ERROR_MSG )
	@Email( message = ErrorConstants.ILLEGAL_EMAIL_ADDRESS_ERROR_MSG )
	private String m_eMailAddress;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "date_of_birth", nullable = true )
	private Date m_dateOfBirth;

	@Enumerated( EnumType.STRING )
	@Column( name = "gender", nullable = true )
	private Gender m_gender;

	// Bidirektionale 1:1 Beziehung (Besitzende Seite)
	@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = false )
	@JoinColumn( name = "user_id" )
	private EntityUser m_user;

	// Unidirektionale 1:1 Beziehung
	@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
	@JoinColumn( name = "address_id" )
	private EntityAddress m_address;

	// Unidirektionale n:m Beziehung
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( name = "profile_skills",
				joinColumns = @JoinColumn( name = "profile_id" ),
				inverseJoinColumns = @JoinColumn( name = "skill_id" ) )
	private List<EntitySkill> m_skills;

	// Unidirektionale 1:n Beziehung
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY )
	@JoinTable( name = "profile_contact_infos",
				joinColumns = @JoinColumn( name = "profile_id" ),
				inverseJoinColumns = @JoinColumn( name = "contact_info_id" ) )
	private List<EntityContactInfo> m_contactInfos;

	// Bidirektionale 1:n Beziehung (Inverse Seite)
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "m_profile" )
	private List<EntityApplication> m_applications;

	/**
	 * Standardkonstruktor.
	 */
	public EntityProfile()
	{
		m_applications = new ArrayList<EntityApplication>();
		m_contactInfos = new ArrayList<EntityContactInfo>();
		m_skills = new ArrayList<EntitySkill>();
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
	 * Liefert das Attribut firstname.
	 * 
	 * @return firstname
	 */
	public String getFirstname()
	{
		return m_firstname;
	}

	/**
	 * Setzt das Attribut firstname.
	 * 
	 * @param firstname
	 *            zu setzender Wert für das Attribut firstname
	 */
	public void setFirstname( String firstname )
	{
		m_firstname = firstname;
	}

	/**
	 * Liefert das Attribut lastname.
	 * 
	 * @return lastname
	 */
	public String getLastname()
	{
		return m_lastname;
	}

	/**
	 * Setzt das Attribut lastname.
	 * 
	 * @param lastname
	 *            zu setzender Wert für das Attribut lastname
	 */
	public void setLastname( String lastname )
	{
		m_lastname = lastname;
	}

	/**
	 * Liefert das Attribut eMailAddress.
	 * 
	 * @return eMailAddress
	 */
	public String geteMailAddress()
	{
		return m_eMailAddress;
	}

	/**
	 * Setzt das Attribut eMailAddress.
	 * 
	 * @param eMailAddress
	 *            zu setzender Wert für das Attribut eMailAddress
	 */
	public void seteMailAddress( String eMailAddress )
	{
		m_eMailAddress = eMailAddress;
	}

	/**
	 * Liefert das Attribut dateOfBirth.
	 * 
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth()
	{
		return m_dateOfBirth;
	}

	/**
	 * Setzt das Attribut dateOfBirth.
	 * 
	 * @param dateOfBirth
	 *            zu setzender Wert für das Attribut dateOfBirth
	 */
	public void setDateOfBirth( Date dateOfBirth )
	{
		m_dateOfBirth = dateOfBirth;
	}

	/**
	 * Liefert das Attribut gender.
	 * 
	 * @return gender
	 */
	public Gender getGender()
	{
		return m_gender;
	}

	/**
	 * Setzt das Attribut gender.
	 * 
	 * @param gender
	 *            zu setzender Wert für das Attribut gender
	 */
	public void setGender( Gender gender )
	{
		m_gender = gender;
	}

	/**
	 * Liefert das Attribut user.
	 * 
	 * @return user
	 */
	public EntityUser getUser()
	{
		return m_user;
	}

	/**
	 * Setzt das Attribut user.
	 * 
	 * @param user
	 *            zu setzender Wert für das Attribut user
	 */
	public void setUser( EntityUser user )
	{
		m_user = user;
	}

	/**
	 * Liefert das Attribut address.
	 * 
	 * @return address
	 */
	public EntityAddress getAddress()
	{
		return m_address;
	}

	/**
	 * Setzt das Attribut address.
	 * 
	 * @param address
	 *            zu setzender Wert für das Attribut address
	 */
	public void setAddress( EntityAddress address )
	{
		m_address = address;
	}

	/**
	 * Liefert das Attribut skills.
	 * 
	 * @return skills
	 */
	public List<EntitySkill> getSkills()
	{
		return m_skills;
	}

	/**
	 * Setzt das Attribut skills.
	 * 
	 * @param skills
	 *            zu setzender Wert für das Attribut skills
	 */
	public void setSkills( List<EntitySkill> skills )
	{
		m_skills = skills;
	}

	/**
	 * Fügt einen Skill hinzu.
	 * 
	 * @param skill
	 *            Hinzuzufügender Skill
	 */
	public void addSkill( EntitySkill skill )
	{
		if (!m_skills.contains( skill ))
		{
			m_skills.add( skill );
		}
	}

	/**
	 * Liefert den passenden Skill.
	 * 
	 * @param skill
	 *            Gesuchter Skill
	 * @return Skill
	 */
	public EntitySkill getSkill( EntitySkill skill )
	{
		Iterator<EntitySkill> iterator = m_skills.iterator();

		while (iterator.hasNext())
		{
			EntitySkill contSkill = iterator.next();
			if (contSkill.equals( skill ))
			{
				return contSkill;
			}
		}

		return null;
	}

	/**
	 * Liefert den passenden Skill.
	 * 
	 * @param skillId
	 *            Gesuchter Skill
	 * @return Skill
	 */
	public EntitySkill getSkill( Long skillId )
	{
		Iterator<EntitySkill> iterator = m_skills.iterator();

		while (iterator.hasNext())
		{
			EntitySkill contSkill = iterator.next();
			try
			{
				if (contSkill.getId().equals( skillId ))
				{
					return contSkill;
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
	 * Löscht den Skill.
	 * 
	 * @param skill
	 *            Zu löschender Skill
	 */
	public void removeSkill( EntitySkill skill )
	{
		m_skills.remove( skill );
	}

	/**
	 * Löscht alle Skills.
	 */
	public void removeAllSkills()
	{
		m_skills.removeAll( m_skills );
	}

	/**
	 * Liefert das Attribut contactInfos.
	 * 
	 * @return contactInfos
	 */
	public List<EntityContactInfo> getContactInfos()
	{
		return m_contactInfos;
	}

	/**
	 * Setzt das Attribut contactInfos.
	 * 
	 * @param contactInfos
	 *            zu setzender Wert für das Attribut contactInfos
	 */
	public void setContactInfos( List<EntityContactInfo> contactInfos )
	{
		m_contactInfos = contactInfos;
	}

	/**
	 * Fügt eine Kontakt-Info hinzu.
	 * 
	 * @param contactInfo
	 *            Hinzuzufügende Kontakt-Info
	 */
	public void addContactInfo( EntityContactInfo contactInfo )
	{
		if (!m_contactInfos.contains( contactInfo ))
		{
			m_contactInfos.add( contactInfo );
		}
	}

	/**
	 * Liefert den passenden ContactInfo.
	 * 
	 * @param contactInfo
	 *            Gesuchter ContactInfo
	 * @return ContactInfo
	 */
	public EntityContactInfo getContactInfo( EntityContactInfo contactInfo )
	{
		Iterator<EntityContactInfo> iterator = m_contactInfos.iterator();

		while (iterator.hasNext())
		{
			EntityContactInfo contContactInfo = iterator.next();
			if (contContactInfo.equals( contactInfo ))
			{
				return contContactInfo;
			}
		}

		return null;
	}

	/**
	 * Liefert den passenden ContactInfo.
	 * 
	 * @param contactInfoId
	 *            Gesuchter ContactInfo
	 * @return ContactInfo
	 */
	public EntityContactInfo getContactInfo( Long contactInfoId )
	{
		Iterator<EntityContactInfo> iterator = m_contactInfos.iterator();

		while (iterator.hasNext())
		{
			EntityContactInfo contContactInfo = iterator.next();
			try
			{
				if (contContactInfo.getId().equals( contactInfoId ))
				{
					return contContactInfo;
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
	 * Löscht die Kontakt-Info.
	 * 
	 * @param contactInfo
	 *            Zu löschende Kontakt-Info
	 */
	public void removeContactInfo( EntityContactInfo contactInfo )
	{
		m_contactInfos.remove( contactInfo );
	}

	/**
	 * Löscht alle Kontakt-Infos.
	 */
	public void removeAllContactInfos()
	{
		m_contactInfos.removeAll( m_contactInfos );
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
		application.setProfile( this );
		m_applications.add( application );
	}

	/**
	 * Liefert die passenden Bewerbung.
	 * 
	 * @param applicationId
	 *            Gesuchte Bewerbung
	 * @return Bewerbung
	 */
	public EntityApplication getApplication( Long applicationId )
	{
		Iterator<EntityApplication> iterator = m_applications.iterator();

		while (iterator.hasNext())
		{
			EntityApplication contApplication = iterator.next();
			try
			{
				if (contApplication.getId().equals( applicationId ))
				{
					return contApplication;
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
	 * Liefert die passenden Bewerbung.
	 * 
	 * @param jobId
	 *            Gesuchter Job
	 * @return Bewerbung
	 */
	public EntityApplication getApplicationForJob( Long jobId )
	{
		Iterator<EntityApplication> iterator = m_applications.iterator();

		while (iterator.hasNext())
		{
			EntityApplication contApplication = iterator.next();
			try
			{
				if (contApplication.getJob().getId().equals( jobId ))
				{
					return contApplication;
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
	 * Löscht alle Bewerbungen.
	 */
	public void removeAllApplications()
	{
		m_applications.removeAll( m_applications );
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
				EntityProfile.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityProfile.class, getExclusionList() );
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
		rsb.setUpToClass( EntityProfile.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		rsb.setExcludeFieldNames( "m_applications" );
		return rsb.toString();
	}
}
