package com.rightprice.auth.util;

public class FileUploadErrors {

	public static final String NULL_ENTRY = " value can't be empty in row ";
	public static final String NOT_FOUND = " Role not present for ";
	
	
	public static String nullEntry(String nullParameter,int rowCount){
		return nullParameter + NULL_ENTRY   +rowCount +"    \n";
		}
	public static String nullEntryName(String nullParameter){
		return nullParameter + NULL_ENTRY   +"    \n";
		}
		
	public static String duplicateEntry(String duplicateParameter){
		return " \n Duplicate entry for Value " + duplicateParameter +"    \n" ;
	}

	public static String lengthCheck(String lengthParameter, int rowCount) {
		return lengthParameter + "\n should not exceed three characters in row  " +rowCount +"   \n";
	}
	
	public static String zeroEntry(String string, int rowCount){
		
		return "\n  Value of " + string + "should not be empty in row  " +rowCount+ "    \n";
	}
	
    public static String colaEntry(String string, int rowCount){
		
		return "\n Compare to Category Id value 1    " + string + " field should NOT be 0 or 1 in row  " +rowCount+ "    \n";
	}
	
	public static String zeroEntryOff(String string, int rowCount){
		return  "\n  Value of " + string +"should be 0 or 1 in row  " + rowCount+"    \n";
		
	}
	
	public static String colEntryNumberMismatch(){
		return " Total number of column in row should be 7";
	}
	
	public static String notFound(String nullParameter,int rowCount){
		return NOT_FOUND + nullParameter + "in row "+rowCount +"    \n";
	}
	
}
