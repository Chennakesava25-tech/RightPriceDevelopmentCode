package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MST_INS_Syntel_Entity ")
public class SyntelEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SYNTEL_ENTITY_ID")
	private int syntel_entity_id;
	
	@Column(name = "SYNTEL_ENTITY")
	private String syntel_entity;
	
	@Column(name = "SYNTEL_ENTITY_NAME")
	private String syntel_entity_name;

	public int getSyntel_entity_id() {
		return syntel_entity_id;
	}

	public void setSyntel_entity_id(int syntel_entity_id) {
		this.syntel_entity_id = syntel_entity_id;
	}

	public String getSyntel_entity() {
		return syntel_entity;
	}

	public void setSyntel_entity(String syntel_entity) {
		this.syntel_entity = syntel_entity;
	}

	public String getSyntel_entity_name() {
		return syntel_entity_name;
	}

	public void setSyntel_entity_name(String syntel_entity_name) {
		this.syntel_entity_name = syntel_entity_name;
	}
	
	
	
}
