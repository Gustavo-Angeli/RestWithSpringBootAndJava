package br.com.gusta.integrationtests.vo.wrappers;

import com.fasterxml.jackson.annotation.*;

import java.io.*;
import java.util.*;

public class WrapperPersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private PersonEmbeddedVO embedded;

    public WrapperPersonVO() {
    }

    public PersonEmbeddedVO getEmbedded() {
        return embedded;
    }

    public void setEmbedded(PersonEmbeddedVO embedded) {
        this.embedded = embedded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperPersonVO that = (WrapperPersonVO) o;
        return Objects.equals(embedded, that.embedded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(embedded);
    }
}
