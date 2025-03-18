package com.rightprice.auth.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.rightprice.auth.util.AppLoger;

public class FPDCRoleAndContractor {
	
	private int dealVersionId;
	
	
	
	private ArrayList<DealRoles> dealRoles;
	
	private List<DealContraactorRole> dealContractorRole;

	private List<PersistedDealRoles> persistedDealRoles;
	
	public int getDealVersionId() {
		return dealVersionId;
	}


	public void setDealVersionId(int dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public ArrayList<DealRoles> getDealRoles() {
		return dealRoles;
	}

	public void setDealRoles(ArrayList<DealRoles> dealRoles) {
		this.dealRoles = dealRoles;
	}

	public List<DealContraactorRole> getDealContractorRole() {
		return dealContractorRole;
	}

	public void setDealContractorRole(List<DealContraactorRole> dealContractorRole) {
		this.dealContractorRole = dealContractorRole;
	}
	
	
	public List<PersistedDealRoles> getPersistedDealRoles() {
		return persistedDealRoles;
	}

	public void setPersistedDealRoles(List<PersistedDealRoles> persistedDealRoles) {
		this.persistedDealRoles = persistedDealRoles;
	}

	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      AppLoger.APPLOGGER.info(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}

	
}
