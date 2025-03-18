package com.rightprice.auth.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.rightprice.auth.util.AppLoger;

public class FPDealRoleAndContractor {

	
private int dealVersionId;

private int dealAutoTowerId;

private int isMasterRole;
	
	
	
	private ArrayList<FpDealRole> dealRoles;
	
	private List<DealContraactorRole> dealContractorRole;

	private List<PersistedFpDealRoles> persistedDealRoles;
	
	public int getDealVersionId() {
		return dealVersionId;
	}
	

	public int getDealAutoTowerId() {
		return dealAutoTowerId;
	}


	/*public List<FpDealContractorRole> getFpdealContractorRole() {
		return fpdealContractorRole;
	}


	public void setFpdealContractorRole(List<FpDealContractorRole> fpdealContractorRole) {
		this.fpdealContractorRole = fpdealContractorRole;
	}*/


	public int getIsMasterRole() {
		return isMasterRole;
	}


	public void setIsMasterRole(int isMasterRole) {
		this.isMasterRole = isMasterRole;
	}


	public ArrayList<FpDealRole> getDealRoles() {
		return dealRoles;
	}


	public void setDealRoles(ArrayList<FpDealRole> dealRoles) {
		this.dealRoles = dealRoles;
	}


	public void setDealVersionId(int dealVersionId) {
		this.dealVersionId = dealVersionId;
	}
	
	public void setDealAutoTowerId(int dealAutoTowerId) {
		this.dealAutoTowerId = dealAutoTowerId;
	}

	/*public ArrayList<FpDealRole> getDealRoles() {
		return fpdealRoles;
	}

	public void setDealRoles(ArrayList<FpDealRole> fpdealRoles) {
		this.fpdealRoles = fpdealRoles;
	}

	public List<FpDealContraactorRole> getDealContractorRole() {
		return dealContractorRole;
	}

	public void setDealContractorRole(List<DealContraactorRole> dealContractorRole) {
		this.dealContractorRole = dealContractorRole;
	}*/
	
	
	public List<DealContraactorRole> getDealContractorRole() {
		return dealContractorRole;
	}


	public void setDealContractorRole(List<DealContraactorRole> dealContractorRole) {
		this.dealContractorRole = dealContractorRole;
	}


	public List<PersistedFpDealRoles> getPersistedDealRoles() {
		return persistedDealRoles;
	}

	public void setPersistedDealRoles(List<PersistedFpDealRoles> persistedDealRoles) {
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
