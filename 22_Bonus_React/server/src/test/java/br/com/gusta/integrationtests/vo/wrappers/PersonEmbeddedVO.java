package br.com.gusta.integrationtests.vo.wrappers;

import br.com.gusta.integrationtests.vo.*;
import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

public class PersonEmbeddedVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("personVOList")
    private List<PersonVO> persons;

    public List<PersonVO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonVO> persons) {
        this.persons = persons;
    }
}
