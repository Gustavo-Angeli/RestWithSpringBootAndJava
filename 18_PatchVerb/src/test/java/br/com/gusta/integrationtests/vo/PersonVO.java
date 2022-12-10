package br.com.gusta.integrationtests.vo;


import org.springframework.hateoas.*;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlRootElement
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Boolean enabled;

	public PersonVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PersonVO personVO = (PersonVO) o;
		return Objects.equals(id, personVO.id) && Objects.equals(firstName, personVO.firstName) && Objects.equals(lastName, personVO.lastName) && Objects.equals(address, personVO.address) && Objects.equals(gender, personVO.gender) && Objects.equals(enabled, personVO.enabled);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, firstName, lastName, address, gender, enabled);
	}
}
