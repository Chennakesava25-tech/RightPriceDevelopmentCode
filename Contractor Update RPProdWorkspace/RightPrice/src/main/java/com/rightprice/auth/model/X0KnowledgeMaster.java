package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class X0KnowledgeMaster 
{
@Id
@Column(name="Knowledge_Id")
private int knowledgeId;

@Column(name="Knowledge_Name")
private String knowledgeName;

public int getKnowledgeId() {
	return knowledgeId;
}

public void setKnowledgeId(int knowledgeId) {
	this.knowledgeId = knowledgeId;
}

public String getKnowledgeName() {
	return knowledgeName;
}

public void setKnowledgeName(String knowledgeName) {
	this.knowledgeName = knowledgeName;
}

}
