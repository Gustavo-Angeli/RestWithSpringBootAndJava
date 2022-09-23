package br.com.gusta.integrationtests.vo;

import com.fasterxml.jackson.annotation.*;
import com.github.dozermapper.core.*;
import org.springframework.hateoas.*;

import java.io.*;
import java.util.*;

public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PersonVO personVO = (PersonVO) o;
		return id.equals(personVO.id) && firstName.equals(personVO.firstName) && lastName.equals(personVO.lastName) && address.equals(personVO.address) && gender.equals(personVO.gender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, firstName, lastName, address, gender);
	}
}
