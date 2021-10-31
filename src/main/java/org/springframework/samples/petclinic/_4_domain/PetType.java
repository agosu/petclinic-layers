package org.springframework.samples.petclinic._4_domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

}
