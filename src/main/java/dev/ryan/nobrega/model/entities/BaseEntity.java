package dev.ryan.nobrega.model.entities;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
@MappedSuperclass
public  abstract class BaseEntity  {
    private Boolean ativo;

}
