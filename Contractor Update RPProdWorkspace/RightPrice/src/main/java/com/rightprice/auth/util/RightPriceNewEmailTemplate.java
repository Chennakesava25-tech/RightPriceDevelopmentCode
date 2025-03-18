package com.rightprice.auth.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.RateCardTest;
import com.rightprice.auth.model.VerticalWiseBUPOCDetails;
import com.rightprice.auth.repository.RightPriceRepository;

public class RightPriceNewEmailTemplate {
	@Autowired
	private RightPriceRepository rpr;
	public void triggerMail(int templateID, List<RateCardDetails> rateCardDetails,String deliveryEmailId,
    String deliverySpName, String salesSpname,String consolidate,String consolidateGM,String rightPriceGM,Map<String, List<String>>rcInformation,String currencyName,String Difference,String deliverySpEmailId,String RejectedComments,String UserComments,String deliveryData[],String  Leaddetails[],String Status,RateCardDetails rateCardApprovalDetails,RateCardTest rcDetail, BigDecimal tcvValue, String createdByname, String createdbyEmail, String updatedByName, String updatedByEmail,List<VerticalWiseBUPOCDetails> verticalPOCMailList, Map<Integer, List<String>> proxyEmail, String pMinclRiskVd)

	{
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
		@SuppressWarnings("unchecked")
		String tempId;
		//String currencyName = null;
		String desc;
		String[] to = null;
		String[] cc = null;
		String subject;
		String mailContent;
		String CEOName = Leaddetails[0];
		String CEOEmail=Leaddetails[1];
		String CDOName=Leaddetails[2];
		String CDOEmail=Leaddetails[3];
		String level1 = Leaddetails[4];
		String level1mail=Leaddetails[5];
		String level2=Leaddetails[6];
		String level2Email=Leaddetails[7];
		String deliveryHeadEmail=deliveryData[1];
		String BuHeadName=deliveryData[2];
		String deliveryHeadName = deliveryData[0];
		String BuHeadEmail=deliveryData[3];
		String JVCEO=Leaddetails[8];
		String JVCEOEmail=Leaddetails[9];
		List<String> ApproverList = new ArrayList<String>() ;
    	List<String> ApproverName = new ArrayList<String>() ;
    	List<String> ApproverStatus = new ArrayList<String>() ;
		String updatedStaus=rateCardApprovalDetails.getStatusIndicator();
		String isRenewal=null, isAutomate=null,oldRenewalId=null,onsiteHrsDay=null,offsiteHrsDay=null;
		Integer oldRenewal=0;
		Double onsite = 0.00;
		 Double offsite = 0.00;
		 Double onsiteHrs=0.00;
		
		Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
		getAllData = rcInformation;
		List<String> blendedvalues = getAllData.get("1");
		List<String> gmvalues = getAllData.get("2");
		List<String> utilMix = getAllData.get("3");
		List<String> locationList = getAllData.get("4");
		/*List<String> countryName = getAllData.get("4");
		List<String> cityName = getAllData.get("5");*/
		 int sizeofTo=verticalPOCMailList.size();
		 
		 Map<Integer, List<String>> proxyApproverEmail = new HashMap<Integer, List<String>>();
		 proxyApproverEmail = proxyEmail;
			List<String> DelievryProxy = proxyApproverEmail.get(1);
			List<String> BUhProxy = proxyApproverEmail.get(2);

			//List<String> qualityProxy = proxyApproverEmail.get(3);

	//List<String> RiskManagersProxy = proxyApproverEmail.get(3);

			List<String> CDOProxy = proxyApproverEmail.get(4);
			List<String> CEOProxy = proxyApproverEmail.get(5);
			List<String> level1Proxy = proxyApproverEmail.get(6);
			List<String> level2Proxy = proxyApproverEmail.get(7);

		int counts=0;
		if(!rateCardApprovalDetails.getUserType().equals("GFT")){
		if (rateCardApprovalDetails.getCurrentApprovalStatus() != null) {
			if (rateCardApprovalDetails.getCurrentApprovalStatus() != 4) {
				if (rcDetail.getRainbowApprovalFlag() != null) {
					if (rcDetail.getRainbowApprovalFlag()== 1) {
					
					if (rcDetail.getApprovalFlagLevel() != null) {

						if (rcDetail.getApprovalFlagLevel() == 5) {
							for (Object object : rateCardDetails) {

								int count = 0;
								RateCardDetails matrixData = (RateCardDetails) object;
								if ((matrixData.getIsManualRc().equals("A"))
										|| (matrixData.getIsManualRc().equals("H"))) {
									if (matrixData.getLevel1ApproverRoleId() != null) {
										if (matrixData.getLevel1ApproverRoleId() == 4) {
											count++;
											ApproverList.add("Delivery Head");
											ApproverName.add(deliveryHeadName);
											counts = count;
										}

										if (matrixData.getLevel1ApproverRoleId() == 11) {
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}
									} else if (matrixData.getLevel1ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;
									}
									if (matrixData.getLevel2ApproverRoleId() != null) {
										if (matrixData.getLevel2ApproverRoleId() == 6) {
											count++;
											ApproverList.add("GFT");
											ApproverName.add("GFT");
											counts = count;
										}

										if (matrixData.getLevel2ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									} else if (matrixData.getLevel2ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;
									}

									if (matrixData.getLevel3ApproverRoleId() != null) {
										if (matrixData.getLevel3ApproverRoleId() == 7) {
											count++;
											ApproverList.add("CDO");
											ApproverName.add(CDOName);
											counts = count;
										}

										if (matrixData.getLevel3ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									} else if (matrixData.getLevel3ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;
									}

									if (matrixData.getLevel4ApproverRoleId() != null) {
										if (matrixData.getLevel4ApproverRoleId() == 8) {
											count++;
											ApproverList.add("BU HEAD");
											ApproverName.add(BuHeadName);
											counts = count;
										}

										if (matrixData.getLevel4ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									} else if (matrixData.getLevel4ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;
									}

									if (matrixData.getLevel5ApproverRoleId() != null) {
										if (matrixData.getLevel5ApproverRoleId() == 10) {
											count++;
											ApproverList.add("CEO");
											ApproverName.add(CEOName);
											counts = count;
										}

										if (matrixData.getLevel5ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									} else if (matrixData.getLevel5ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;
									}
									if (matrixData.getLevel6ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;

									}
									if (matrixData.getLevel6ApproverRoleId() != null) {
										if (matrixData.getLevel6ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									}
									if (matrixData.getLevel7ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;

									}
									if (matrixData.getLevel7ApproverRoleId() != null) {
										if (matrixData.getLevel7ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									}

									if (matrixData.getLevel8ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										counts = count;
										break;

									}
									if (matrixData.getLevel8ApproverRoleId() != null) {
										if (matrixData.getLevel8ApproverRoleId() == 11) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
									}

								}
								if (matrixData.getIsManualRc().equals("M")) {
									if (matrixData.getIsItKpo() == 0 || matrixData.getIsItKpo() == 1) {
										if (matrixData.getLevel1ApproverRoleId() != null) {
											if (matrixData.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;
											}

											if (matrixData.getLevel1ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel2ApproverRoleId() != null) {
											if (matrixData.getLevel2ApproverRoleId() == 7) {
												count++;
												ApproverList.add("CDO");
												ApproverName.add(CDOName);
												counts = count;
											}

											if (matrixData.getLevel2ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel3ApproverRoleId() != null) {
											if (matrixData.getLevel3ApproverRoleId() == 8) {
												count++;
												ApproverList.add("BU HEAD");
												ApproverName.add(BuHeadName);
												counts = count;
											}

											if (matrixData.getLevel3ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel4ApproverRoleId() != null) {
											if (matrixData.getLevel4ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;
											}

											if (matrixData.getLevel4ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
										if (matrixData.getLevel5ApproverRoleId() != null) {
											if (matrixData.getLevel5ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}
										if (matrixData.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}

										if (matrixData.getLevel6ApproverRoleId() != null) {
											if (matrixData.getLevel6ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}
										if (matrixData.getLevel6ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}

										if (matrixData.getLevel7ApproverRoleId() != null) {
											if (matrixData.getLevel7ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}
										if (matrixData.getLevel7ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}

									}

									if (matrixData.getIsItKpo() == 2) {
										if (matrixData.getLevel1ApproverRoleId() != null) {
											if (matrixData.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;

											}
											if (matrixData.getLevel1ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

										}

										else if (matrixData.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel2ApproverRoleId() != null) {
											if (matrixData.getLevel2ApproverRoleId() == 6) {
												count++;
												ApproverList.add("GFT");
												ApproverName.add("GFT");
												counts = count;

											}

											if (matrixData.getLevel2ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

										}

										else if (matrixData.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
										if (matrixData.getLevel3ApproverRoleId() != null) {
											if (matrixData.getLevel3ApproverRoleId() == 13) {
												count++;
												ApproverList.add("BU Head");
												ApproverName.add(JVCEO);
												counts = count;

											}

											if (matrixData.getLevel3ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

										}

										else if (matrixData.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
										if (matrixData.getLevel4ApproverRoleId() != null) {
											if (matrixData.getLevel4ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;

											}

											if (matrixData.getLevel4ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

										}

										else if (matrixData.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										count = counts;

									}

								}
								if (matrixData.getCurrentApprovalStatus() == 4
										|| matrixData.getCurrentApprovalStatus() == 1) {
									for (int i = 0; i < count; i++) {
										ApproverStatus.add("Pending");

									}
								}
								if (matrixData.getCurrentApprovalStatus() == 7) {
									for (int i = 0; i < count; i++) {
										ApproverStatus.add("Pending");

									}
								}

							}

						}
						if (rcDetail.getApprovalFlagLevel() == 6) {
							for (Object object : rateCardDetails) {
								int count = 0;
								RateCardDetails matrixDatalevel2 = (RateCardDetails) object;
								if ((matrixDatalevel2.getIsManualRc().equals("A"))
										|| (matrixDatalevel2.getIsManualRc().equals("H"))) {
									if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
											count++;
											ApproverList.add("Delivery Head");
											ApproverName.add(deliveryHeadName);
											counts = count;
										}

										if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}
									if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel2ApproverRoleId() == 6) {
											count++;
											ApproverList.add("GFT");
											ApproverName.add("GFT");
											counts = count;
										}

										if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}

									if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel3ApproverRoleId() == 7) {
											count++;
											ApproverList.add("CDO");
											ApproverName.add(CDOName);
											counts = count;
										}

										if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}

									if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel4ApproverRoleId() == 8) {
											count++;
											ApproverList.add("BU HEAD");
											ApproverName.add(BuHeadName);
											counts = count;
										}

										if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}

									if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel5ApproverRoleId() == 10) {
											count++;
											ApproverList.add("CEO");
											ApproverName.add(CEOName);
											counts = count;
										}

										if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									} else if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}
									if (matrixDatalevel2.getLevel6ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel6ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel7ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									}
									if (matrixDatalevel2.getLevel6ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;
									}
									if (matrixDatalevel2.getLevel7ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel7ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel8ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									}

									if (matrixDatalevel2.getLevel7ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;

									}
									if (matrixDatalevel2.getLevel8ApproverRoleId() != null) {
										if (matrixDatalevel2.getLevel8ApproverRoleId() == 11
												|| matrixDatalevel2.getLevel9ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
									}

									if (matrixDatalevel2.getLevel8ApproverRoleId() == null) {
										count++;
										ApproverList.add("Level 1");
										ApproverName.add(level1);
										count++;
										ApproverList.add("Level 2");
										ApproverName.add(level2);
										counts = count;
										break;

									}

								}
								if (matrixDatalevel2.getIsManualRc().equals("M")) {
									if (matrixDatalevel2.getIsItKpo() == 0 || matrixDatalevel2.getIsItKpo() == 1) {

										if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel2ApproverRoleId() == 7) {
												count++;
												ApproverList.add("CDO");
												ApproverName.add(CDOName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel3ApproverRoleId() == 8) {
												count++;
												ApproverList.add("BU HEAD");
												ApproverName.add(BuHeadName);
											}

											if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel4ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
										if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}
										if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;

										}
										if (matrixDatalevel2.getLevel6ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel6ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel7ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}

										else if (matrixDatalevel2.getLevel6ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel7ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel7ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel8ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}

										else if (matrixDatalevel2.getLevel7ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

									}

									if (matrixDatalevel2.getIsItKpo() == 2) {

										if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel2ApproverRoleId() == 6) {
												count++;
												ApproverList.add("GFT");
												ApproverName.add("GFT");
												counts = count;
											}

											if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel3ApproverRoleId() == 13) {
												count++;
												ApproverList.add("BUH");
												ApproverName.add(JVCEO);
												counts = count;
											}

											if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel4ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
										if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}
										if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level_2");
											ApproverName.add(level2);
											counts = count;
											break;

										}

									}

								}

							}

						}
						
					}
				}
					else  if (rcDetail.getRainbowApprovalFlag()==2){
						for(Object object : rateCardDetails)
							
						{
							RateCardDetails matrixData = (RateCardDetails)object;
				        	int count=0;
				        if(matrixData.getIsManualRc().equals("A")	||matrixData.getIsManualRc().equals("H")){
						
						 if(matrixData.getLevel1ApproverRoleId()!=null){
								if (matrixData.getLevel1ApproverRoleId()==4){
									count++;
									ApproverList.add("Delivery Head");
									ApproverName.add(deliveryHeadName);
									counts = count;
								}
							}
							if(matrixData.getLevel2ApproverRoleId()!=null){
								if (matrixData.getLevel2ApproverRoleId()==6){
									count++;
									ApproverList.add("GFT");
									ApproverName.add("GFT");
									counts = count;
								}
							}
							if(matrixData.getLevel3ApproverRoleId()!=null){
								if (matrixData.getLevel3ApproverRoleId()==7){
									count++;
									ApproverList.add("CDO");
									ApproverName.add(CDOName);
									counts = count;
								}
							}
							
							if(matrixData.getLevel4ApproverRoleId()!=null){
								if (matrixData.getLevel4ApproverRoleId()==8){
									count++;
									ApproverList.add("BU HEAD");
									ApproverName.add(BuHeadName);
									counts = count;
								}
							}
							if(matrixData.getLevel5ApproverRoleId()!=null){
								if (matrixData.getLevel5ApproverRoleId()==10){
									count++;
									ApproverList.add("CEO");
									ApproverName.add(CEOName);
									counts = count;
									break;
								}
							}

						}
				        
			        	if(matrixData.getIsManualRc().equals("M")){
			        		if(matrixData.getIsItKpo()==2){
			        	if(matrixData.getLevel1ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("Delivery Head");
			        		ApproverName.add(deliveryHeadName);
			        		counts = count;
			        	}
			        	if(matrixData.getLevel2ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("GFT");
			        		ApproverName.add("GFT");
			        		counts = count;
			        	}
			        	if(matrixData.getLevel3ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("BUH");
			        		ApproverName.add(JVCEO);
			        		counts = count;
			        	}
			        	counts=count;
			        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
			    			for(int i=0;i<count;i++){
			    			 ApproverStatus.add("Pending");
			    			
			              }
			            }
			        	if(matrixData.getCurrentApprovalStatus()==7){
			        			for(int i=0;i<count;i++){
			        			 ApproverStatus.add("Pending");
			        			
			                  }
			                }
			        	}
			        		
			        		if(matrixData.getIsItKpo()==0||matrixData.getIsItKpo()==1){
					        	if(matrixData.getLevel1ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("Delivery Head");
					        		ApproverName.add(deliveryHeadName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel2ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("CDO");
					        		ApproverName.add(CDOName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel3ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("BU HEAD");
					        		ApproverName.add(BuHeadName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel4ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("CEO");
					        		ApproverName.add(CEOName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel5ApproverRoleId()!=null){
					        		count++;
					        	}
					        	if(matrixData.getLevel6ApproverRoleId()!=null){
					        		count++;
					        	}
					        	if(matrixData.getLevel7ApproverRoleId()!=null){
					        		count++;
					        	}
					        	counts=count;
					        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
					    			for(int i=0;i<counts;i++){
					    			 ApproverStatus.add("Pending");
					    			
					              }
					            }
					        	if(matrixData.getCurrentApprovalStatus()==7){
					        			for(int i=0;i<counts;i++){
					        			 ApproverStatus.add("Pending");
					        			
					                  }
					                }
					        	}	
					}
			    
			    

					    }
						
					}
						
			}
				else  if (rcDetail.getRainbowApprovalFlag() == null) {

						
					for(Object object : rateCardDetails)
						
					{
						RateCardDetails matrixData = (RateCardDetails)object;
			        	int count=0;
			        if(matrixData.getIsManualRc().equals("A")	||matrixData.getIsManualRc().equals("H")){
					
					 if(matrixData.getLevel1ApproverRoleId()!=null){
							if (matrixData.getLevel1ApproverRoleId()==4){
								count++;
								ApproverList.add("Delivery Head");
								ApproverName.add(deliveryHeadName);
								counts = count;
							}
						}
						if(matrixData.getLevel2ApproverRoleId()!=null){
							if (matrixData.getLevel2ApproverRoleId()==6){
								count++;
								ApproverList.add("GFT");
								ApproverName.add("GFT");
								counts = count;
							}
						}
						if(matrixData.getLevel3ApproverRoleId()!=null){
							if (matrixData.getLevel3ApproverRoleId()==7){
								count++;
								ApproverList.add("CDO");
								ApproverName.add(CDOName);
								counts = count;
							}
						}
						
						if(matrixData.getLevel4ApproverRoleId()!=null){
							if (matrixData.getLevel4ApproverRoleId()==8){
								count++;
								ApproverList.add("BU HEAD");
								ApproverName.add(BuHeadName);
								counts = count;
							}
						}
						if(matrixData.getLevel5ApproverRoleId()!=null){
							if (matrixData.getLevel5ApproverRoleId()==10){
								count++;
								ApproverList.add("CEO");
								ApproverName.add(CEOName);
								counts = count;
								break;
							}
						}

					}
			        
		        	if(matrixData.getIsManualRc().equals("M")){
		        		if(matrixData.getIsItKpo()==2){
		        	if(matrixData.getLevel1ApproverRoleId()!=null){
		        		count++;
		        		ApproverList.add("Delivery Head");
		        		ApproverName.add(deliveryHeadName);
		        		counts = count;
		        	}
		        	if(matrixData.getLevel2ApproverRoleId()!=null){
		        		count++;
		        		ApproverList.add("GFT");
		        		ApproverName.add("GFT");
		        		counts = count;
		        	}
		        	if(matrixData.getLevel3ApproverRoleId()!=null){
		        		count++;
		        		ApproverList.add("BUH");
		        		ApproverName.add(JVCEO);
		        		counts = count;
		        	}
		        	counts=count;
		        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
		    			for(int i=0;i<count;i++){
		    			 ApproverStatus.add("Pending");
		    			
		              }
		            }
		        	if(matrixData.getCurrentApprovalStatus()==7){
		        			for(int i=0;i<count;i++){
		        			 ApproverStatus.add("Pending");
		        			
		                  }
		                }
		        	}
		        		
		        		if(matrixData.getIsItKpo()==0||matrixData.getIsItKpo()==1){
				        	if(matrixData.getLevel1ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("Delivery Head");
				        		ApproverName.add(deliveryHeadName);
				        		counts = count;
				        	}
				        	if(matrixData.getLevel2ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("CDO");
				        		ApproverName.add(CDOName);
				        		counts = count;
				        	}
				        	if(matrixData.getLevel3ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("BU HEAD");
				        		ApproverName.add(BuHeadName);
				        		counts = count;
				        	}
				        	if(matrixData.getLevel4ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("CEO");
				        		ApproverName.add(CEOName);
				        		counts = count;
				        	}
				        	if(matrixData.getLevel5ApproverRoleId()!=null){
				        		count++;
				        	}
				        	if(matrixData.getLevel6ApproverRoleId()!=null){
				        		count++;
				        	}
				        	if(matrixData.getLevel7ApproverRoleId()!=null){
				        		count++;
				        	}
				        	counts=count;
				        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
				    			for(int i=0;i<counts;i++){
				    			 ApproverStatus.add("Pending");
				    			
				              }
				            }
				        	if(matrixData.getCurrentApprovalStatus()==7){
				        			for(int i=0;i<counts;i++){
				        			 ApproverStatus.add("Pending");
				        			
				                  }
				                }
				        	}	
				}
		    
		    

				    }
						
				}
				
					
				}
				
			}
		}
		
		if (rateCardApprovalDetails.getUserType().equals("GFT")){
			if (rateCardApprovalDetails.getCurrentApprovalStatus() != null) {
				if (rateCardApprovalDetails.getCurrentApprovalStatus() != 4) {
					
					if (rateCardApprovalDetails.getRainbowApprovalFlag() != null) {
						if (rateCardApprovalDetails.getRainbowApprovalFlag()== 1) {
						
						if (rateCardApprovalDetails.getApprovalFlagLevel() != null) {

							if (rateCardApprovalDetails.getApprovalFlagLevel() == 5) {
								for (Object object : rateCardDetails) {

									int count = 0;
									RateCardDetails matrixData = (RateCardDetails) object;
									if ((matrixData.getIsManualRc().equals("A"))
											|| (matrixData.getIsManualRc().equals("H"))) {
										if (matrixData.getLevel1ApproverRoleId() != null) {
											if (matrixData.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;
											}

											if (matrixData.getLevel1ApproverRoleId() == 11) {
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;

											}
										} else if (matrixData.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
										if (matrixData.getLevel2ApproverRoleId() != null) {
											if (matrixData.getLevel2ApproverRoleId() == 6) {
												count++;
												ApproverList.add("GFT");
												ApproverName.add("GFT");
												counts = count;
											}

											if (matrixData.getLevel2ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel3ApproverRoleId() != null) {
											if (matrixData.getLevel3ApproverRoleId() == 7) {
												count++;
												ApproverList.add("CDO");
												ApproverName.add(CDOName);
												counts = count;
											}

											if (matrixData.getLevel3ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel4ApproverRoleId() != null) {
											if (matrixData.getLevel4ApproverRoleId() == 8) {
												count++;
												ApproverList.add("BU HEAD");
												ApproverName.add(BuHeadName);
												counts = count;
											}

											if (matrixData.getLevel4ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}

										if (matrixData.getLevel5ApproverRoleId() != null) {
											if (matrixData.getLevel5ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;
											}

											if (matrixData.getLevel5ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										} else if (matrixData.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;
										}
										if (matrixData.getLevel6ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}
										if (matrixData.getLevel6ApproverRoleId() != null) {
											if (matrixData.getLevel6ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}
										if (matrixData.getLevel7ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}
										if (matrixData.getLevel7ApproverRoleId() != null) {
											if (matrixData.getLevel7ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}

										if (matrixData.getLevel8ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											counts = count;
											break;

										}
										if (matrixData.getLevel8ApproverRoleId() != null) {
											if (matrixData.getLevel8ApproverRoleId() == 11) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
										}

									}
									if (matrixData.getIsManualRc().equals("M")) {
										if (matrixData.getIsItKpo() == 0 || matrixData.getIsItKpo() == 1) {
											if (matrixData.getLevel1ApproverRoleId() != null) {
												if (matrixData.getLevel1ApproverRoleId() == 4) {
													count++;
													ApproverList.add("Delivery Head");
													ApproverName.add(deliveryHeadName);
													counts = count;
												}

												if (matrixData.getLevel1ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											} else if (matrixData.getLevel1ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

											if (matrixData.getLevel2ApproverRoleId() != null) {
												if (matrixData.getLevel2ApproverRoleId() == 7) {
													count++;
													ApproverList.add("CDO");
													ApproverName.add(CDOName);
													counts = count;
												}

												if (matrixData.getLevel2ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											} else if (matrixData.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

											if (matrixData.getLevel3ApproverRoleId() != null) {
												if (matrixData.getLevel3ApproverRoleId() == 8) {
													count++;
													ApproverList.add("BU HEAD");
													ApproverName.add(BuHeadName);
													counts = count;
												}

												if (matrixData.getLevel3ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											} else if (matrixData.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

											if (matrixData.getLevel4ApproverRoleId() != null) {
												if (matrixData.getLevel4ApproverRoleId() == 10) {
													count++;
													ApproverList.add("CEO");
													ApproverName.add(CEOName);
													counts = count;
												}

												if (matrixData.getLevel4ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											} else if (matrixData.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
											if (matrixData.getLevel5ApproverRoleId() != null) {
												if (matrixData.getLevel5ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											}
											if (matrixData.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;

											}

											if (matrixData.getLevel6ApproverRoleId() != null) {
												if (matrixData.getLevel6ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											}
											if (matrixData.getLevel6ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;

											}

											if (matrixData.getLevel7ApproverRoleId() != null) {
												if (matrixData.getLevel7ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}
											}
											if (matrixData.getLevel7ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;

											}

										}

										if (matrixData.getIsItKpo() == 2) {
											if (matrixData.getLevel1ApproverRoleId() != null) {
												if (matrixData.getLevel1ApproverRoleId() == 4) {
													count++;
													ApproverList.add("Delivery Head");
													ApproverName.add(deliveryHeadName);
													counts = count;

												}
												if (matrixData.getLevel1ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}

											}

											else if (matrixData.getLevel1ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

											if (matrixData.getLevel2ApproverRoleId() != null) {
												if (matrixData.getLevel2ApproverRoleId() == 6) {
													count++;
													ApproverList.add("GFT");
													ApproverName.add("GFT");
													counts = count;

												}

												if (matrixData.getLevel2ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}

											}

											else if (matrixData.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
											if (matrixData.getLevel3ApproverRoleId() != null) {
												if (matrixData.getLevel3ApproverRoleId() == 13) {
													count++;
													ApproverList.add("BUH");
													ApproverName.add(JVCEO);

												}

												if (matrixData.getLevel3ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}

											}

											else if (matrixData.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}
											if (matrixData.getLevel4ApproverRoleId() != null) {
												if (matrixData.getLevel4ApproverRoleId() == 10) {
													count++;
													ApproverList.add("CEO");
													ApproverName.add(CEOName);
													counts = count;

												}

												if (matrixData.getLevel4ApproverRoleId() == 11) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													counts = count;
													break;
												}

											}

											else if (matrixData.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												counts = count;
												break;
											}

											count = counts;

										}

									}
									if (matrixData.getCurrentApprovalStatus() == 4
											|| matrixData.getCurrentApprovalStatus() == 1) {
										for (int i = 0; i < count; i++) {
											ApproverStatus.add("Pending");

										}
									}
									if (matrixData.getCurrentApprovalStatus() == 7) {
										for (int i = 0; i < count; i++) {
											ApproverStatus.add("Pending");

										}
									}

								}

							}
							if (rateCardApprovalDetails.getApprovalFlagLevel() == 6) {
								for (Object object : rateCardDetails) {
									int count = 0;
									RateCardDetails matrixDatalevel2 = (RateCardDetails) object;
									if ((matrixDatalevel2.getIsManualRc().equals("A"))
											|| (matrixDatalevel2.getIsManualRc().equals("H"))) {
										if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
												count++;
												ApproverList.add("Delivery Head");
												ApproverName.add(deliveryHeadName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
										if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel2ApproverRoleId() == 6) {
												count++;
												ApproverList.add("GFT");
												ApproverName.add("GFT");
												counts = count;
											}

											if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel3ApproverRoleId() == 7) {
												count++;
												ApproverList.add("CDO");
												ApproverName.add(CDOName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel4ApproverRoleId() == 8) {
												count++;
												ApproverList.add("BU HEAD");
												ApproverName.add(BuHeadName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}

										if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel5ApproverRoleId() == 10) {
												count++;
												ApproverList.add("CEO");
												ApproverName.add(CEOName);
												counts = count;
											}

											if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										} else if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
										if (matrixDatalevel2.getLevel6ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel6ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel7ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}
										if (matrixDatalevel2.getLevel6ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;
										}
										if (matrixDatalevel2.getLevel7ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel7ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel8ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}

										if (matrixDatalevel2.getLevel7ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;

										}
										if (matrixDatalevel2.getLevel8ApproverRoleId() != null) {
											if (matrixDatalevel2.getLevel8ApproverRoleId() == 11
													|| matrixDatalevel2.getLevel9ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level 2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
										}

										if (matrixDatalevel2.getLevel8ApproverRoleId() == null) {
											count++;
											ApproverList.add("Level 1");
											ApproverName.add(level1);
											count++;
											ApproverList.add("Level 2");
											ApproverName.add(level2);
											counts = count;
											break;

										}

									}
									if (matrixDatalevel2.getIsManualRc().equals("M")) {
										if (matrixDatalevel2.getIsItKpo() == 0 || matrixDatalevel2.getIsItKpo() == 1) {

											if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
													count++;
													ApproverList.add("Delivery Head");
													ApproverName.add(deliveryHeadName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel2ApproverRoleId() == 7) {
													count++;
													ApproverList.add("CDO");
													ApproverName.add(CDOName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel3ApproverRoleId() == 8) {
													count++;
													ApproverList.add("BU HEAD");
													ApproverName.add(BuHeadName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel4ApproverRoleId() == 10) {
													count++;
													ApproverList.add("CEO");
													ApproverName.add(CEOName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
											if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											}
											if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;

											}
											if (matrixDatalevel2.getLevel6ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel6ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel7ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											}

											else if (matrixDatalevel2.getLevel6ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel7ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel7ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel8ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											}

											else if (matrixDatalevel2.getLevel7ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

										}

										if (matrixDatalevel2.getIsItKpo() == 2) {

											if (matrixDatalevel2.getLevel1ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel1ApproverRoleId() == 4) {
													count++;
													ApproverList.add("Delivery Head");
													ApproverName.add(deliveryHeadName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel1ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel2ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel1ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel2ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel2ApproverRoleId() == 6) {
													count++;
													ApproverList.add("GFT");
													ApproverName.add("GFT");
													counts = count;
												}

												if (matrixDatalevel2.getLevel2ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel3ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel2ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel3ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel3ApproverRoleId() == 13) {
													count++;
													ApproverList.add("BUH");
													ApproverName.add(JVCEO);
													counts = count;
												}

												if (matrixDatalevel2.getLevel3ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel4ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel3ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}

											if (matrixDatalevel2.getLevel4ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel4ApproverRoleId() == 10) {
													count++;
													ApproverList.add("CEO");
													ApproverName.add(CEOName);
													counts = count;
												}

												if (matrixDatalevel2.getLevel4ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel5ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											} else if (matrixDatalevel2.getLevel4ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;
											}
											if (matrixDatalevel2.getLevel5ApproverRoleId() != null) {
												if (matrixDatalevel2.getLevel5ApproverRoleId() == 11
														|| matrixDatalevel2.getLevel6ApproverRoleId() == null) {
													count++;
													ApproverList.add("Level 1");
													ApproverName.add(level1);
													count++;
													ApproverList.add("Level_2");
													ApproverName.add(level2);
													counts = count;
													break;
												}
											}
											if (matrixDatalevel2.getLevel5ApproverRoleId() == null) {
												count++;
												ApproverList.add("Level 1");
												ApproverName.add(level1);
												count++;
												ApproverList.add("Level_2");
												ApproverName.add(level2);
												counts = count;
												break;

											}

										}

									}

								}

							}
							
						}
					}
						else  if (rateCardApprovalDetails.getRainbowApprovalFlag()==2){
							for(Object object : rateCardDetails)
								
							{
								RateCardDetails matrixData = (RateCardDetails)object;
					        	int count=0;
					        if(matrixData.getIsManualRc().equals("A")	||matrixData.getIsManualRc().equals("H")){
							
							 if(matrixData.getLevel1ApproverRoleId()!=null){
									if (matrixData.getLevel1ApproverRoleId()==4){
										count++;
										ApproverList.add("Delivery Head");
										ApproverName.add(deliveryHeadName);
										counts = count;
									}
								}
								if(matrixData.getLevel2ApproverRoleId()!=null){
									if (matrixData.getLevel2ApproverRoleId()==6){
										count++;
										ApproverList.add("GFT");
										ApproverName.add("GFT");
										counts = count;
									}
								}
								if(matrixData.getLevel3ApproverRoleId()!=null){
									if (matrixData.getLevel3ApproverRoleId()==7){
										count++;
										ApproverList.add("CDO");
										ApproverName.add(CDOName);
										counts = count;
									}
								}
								
								if(matrixData.getLevel4ApproverRoleId()!=null){
									if (matrixData.getLevel4ApproverRoleId()==8){
										count++;
										ApproverList.add("BU HEAD");
										ApproverName.add(BuHeadName);
										counts = count;
									}
								}
								if(matrixData.getLevel5ApproverRoleId()!=null){
									if (matrixData.getLevel5ApproverRoleId()==10){
										count++;
										ApproverList.add("CEO");
										ApproverName.add(CEOName);
										counts = count;
										break;
									}
								}

							}
					        
				        	if(matrixData.getIsManualRc().equals("M")){
				        		if(matrixData.getIsItKpo()==2){
				        	if(matrixData.getLevel1ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("Delivery Head");
				        		ApproverName.add(deliveryHeadName);
				        		counts = count;
				        	}
				        	if(matrixData.getLevel2ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("GFT");
				        		ApproverName.add("GFT");
				        		counts = count;
				        	}
				        	if(matrixData.getLevel3ApproverRoleId()!=null){
				        		count++;
				        		ApproverList.add("BUH");
				        		ApproverName.add(JVCEO);
				        		counts = count;
				        	}
				        	counts=count;
				        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
				    			for(int i=0;i<count;i++){
				    			 ApproverStatus.add("Pending");
				    			 counts = count;
				    			
				              }
				            }
				        	if(matrixData.getCurrentApprovalStatus()==7){
				        			for(int i=0;i<count;i++){
				        			 ApproverStatus.add("Pending");
				        			
				                  }
				                }
				        	}
				        		
				        		if(matrixData.getIsItKpo()==0||matrixData.getIsItKpo()==1){
						        	if(matrixData.getLevel1ApproverRoleId()!=null){
						        		count++;
						        		ApproverList.add("Delivery Head");
						        		ApproverName.add(deliveryHeadName);
						        		counts = count;
						        	}
						        	if(matrixData.getLevel2ApproverRoleId()!=null){
						        		count++;
						        		ApproverList.add("CDO");
						        		ApproverName.add(CDOName);
						        		counts = count;
						        	}
						        	if(matrixData.getLevel3ApproverRoleId()!=null){
						        		count++;
						        		ApproverList.add("BU HEAD");
						        		ApproverName.add(BuHeadName);
						        		counts = count;
						        	}
						        	if(matrixData.getLevel4ApproverRoleId()!=null){
						        		count++;
						        		ApproverList.add("CEO");
						        		ApproverName.add(CEOName);
						        		counts = count;
						        	}
						        	if(matrixData.getLevel5ApproverRoleId()!=null){
						        		count++;
						        	}
						        	if(matrixData.getLevel6ApproverRoleId()!=null){
						        		count++;
						        	}
						        	if(matrixData.getLevel7ApproverRoleId()!=null){
						        		count++;
						        	}
						        	counts=count;
						        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
						    			for(int i=0;i<counts;i++){
						    			 ApproverStatus.add("Pending");
						    			
						              }
						            }
						        	if(matrixData.getCurrentApprovalStatus()==7){
						        			for(int i=0;i<counts;i++){
						        			 ApproverStatus.add("Pending");
						        			
						                  }
						                }
						        	}	
						}
				    
				    

						    }
							
						}
							
				}
					else  if (rateCardApprovalDetails.getRainbowApprovalFlag() == null) {

							
						for(Object object : rateCardDetails)
							
						{
							RateCardDetails matrixData = (RateCardDetails)object;
				        	int count=0;
				        if(matrixData.getIsManualRc().equals("A")	||matrixData.getIsManualRc().equals("H")){
						
						 if(matrixData.getLevel1ApproverRoleId()!=null){
								if (matrixData.getLevel1ApproverRoleId()==4){
									count++;
									ApproverList.add("Delivery Head");
									ApproverName.add(deliveryHeadName);
									counts = count;
								}
							}
							if(matrixData.getLevel2ApproverRoleId()!=null){
								if (matrixData.getLevel2ApproverRoleId()==6){
									count++;
									ApproverList.add("GFT");
									ApproverName.add("GFT");
									counts = count;
								}
							}
							if(matrixData.getLevel3ApproverRoleId()!=null){
								if (matrixData.getLevel3ApproverRoleId()==7){
									count++;
									ApproverList.add("CDO");
									ApproverName.add(CDOName);
									counts = count;
								}
							}
							
							if(matrixData.getLevel4ApproverRoleId()!=null){
								if (matrixData.getLevel4ApproverRoleId()==8){
									count++;
									ApproverList.add("BU HEAD");
									ApproverName.add(BuHeadName);
									counts = count;
								}
							}
							if(matrixData.getLevel5ApproverRoleId()!=null){
								if (matrixData.getLevel5ApproverRoleId()==10){
									count++;
									ApproverList.add("CEO");
									ApproverName.add(CEOName);
									counts = count;
									break;
								}
							}

						}
				        
			        	if(matrixData.getIsManualRc().equals("M")){
			        		if(matrixData.getIsItKpo()==2){
			        	if(matrixData.getLevel1ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("Delivery Head");
			        		ApproverName.add(deliveryHeadName);
			        		counts = count;
			        	}
			        	if(matrixData.getLevel2ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("GFT");
			        		ApproverName.add("GFT");
			        		counts = count;
			        	}
			        	if(matrixData.getLevel3ApproverRoleId()!=null){
			        		count++;
			        		ApproverList.add("BUH");
			        		ApproverName.add(JVCEO);
			        		counts = count;
			        	}
			        	counts=count;
			        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
			    			for(int i=0;i<count;i++){
			    			 ApproverStatus.add("Pending");
			    			 
			    			
			              }
			            }
			        	if(matrixData.getCurrentApprovalStatus()==7){
			        			for(int i=0;i<count;i++){
			        			 ApproverStatus.add("Pending");
			        			
			                  }
			                }
			        	}
			        		
			        		if(matrixData.getIsItKpo()==0||matrixData.getIsItKpo()==1){
					        	if(matrixData.getLevel1ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("Delivery Head");
					        		ApproverName.add(deliveryHeadName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel2ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("CDO");
					        		ApproverName.add(CDOName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel3ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("BU HEAD");
					        		ApproverName.add(BuHeadName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel4ApproverRoleId()!=null){
					        		count++;
					        		ApproverList.add("CEO");
					        		ApproverName.add(CEOName);
					        		counts = count;
					        	}
					        	if(matrixData.getLevel5ApproverRoleId()!=null){
					        		count++;
					        	}
					        	if(matrixData.getLevel6ApproverRoleId()!=null){
					        		count++;
					        	}
					        	if(matrixData.getLevel7ApproverRoleId()!=null){
					        		count++;
					        	}
					        	counts=count;
					        	if(matrixData.getCurrentApprovalStatus()==4||matrixData.getCurrentApprovalStatus()==1){
					    			for(int i=0;i<counts;i++){
					    			 ApproverStatus.add("Pending");
					    			
					              }
					            }
					        	if(matrixData.getCurrentApprovalStatus()==7){
					        			for(int i=0;i<counts;i++){
					        			 ApproverStatus.add("Pending");
					        			
					                  }
					                }
					        	}	
					}
			    
			    

					    }
							
					}
					
						
					
					
					
				}
		}
		}
		
		
		
		
		
		
		
		
		
		
		
		if (rateCardApprovalDetails.getCurrentApprovalStatus() != null) {
			if (rateCardApprovalDetails.getCurrentApprovalStatus() != 4) {
				if(rateCardApprovalDetails.getCurrentApprovalStatus()==2||rateCardApprovalDetails.getCurrentApprovalStatus()==3){
				
					if(rateCardApprovalDetails.getCurrentApproverRoleId()!=null){
				for (Object object : rateCardDetails) {

					int count = 0;
					RateCardDetails matrixData = (RateCardDetails) object;
    		
    		if(matrixData.getLevel1ApproverRoleId()!=null){
            	if(1==matrixData.getCurrentApprovalLevel()){
        			ApproverStatus.add(0, "Approved");
        			//ApproverStatus.add(1, "Approved");
        			for(int i=1;i<counts;i++){
        				ApproverStatus.add("  Pending  ");
        			}
        			
        		}
             }	
    		
    	if(matrixData.getLevel2ApproverRoleId()!=null){
    		
    	if(2==matrixData.getCurrentApprovalLevel()){
			ApproverStatus.add(0, "Approved");
			ApproverStatus.add(1, "Approved");
			
			for(int i=2;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
     }	
    	
    
    	if(3==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
			for(int i=3;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
    	if(4==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
			for(int i=4;i<counts;i++){
				ApproverStatus.add("Pending");
			}
			
		}
    	if(5==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
    		ApproverStatus.add(4, "Approved");
    		for(int i=5;i<counts;i++){
				ApproverStatus.add("Pending");
			}
    		
		}
    	
    	if(6==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
    		ApproverStatus.add(4, "Approved");
    		ApproverStatus.add(5, "Approved");
			for(int i=6;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
    	if(7==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
    		ApproverStatus.add(4, "Approved");
    		ApproverStatus.add(5, "Approved");
    		ApproverStatus.add(6, "Approved");
			for(int i=7;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
       	if(8==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
    		ApproverStatus.add(4, "Approved");
    		ApproverStatus.add(5, "Approved");
    		ApproverStatus.add(6, "Approved");
    		ApproverStatus.add(7, "Approved");
			for(int i=8;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
       	if(9==matrixData.getCurrentApprovalLevel()){
    		ApproverStatus.add(0, "Approved");
    		ApproverStatus.add(1, "Approved");
    		ApproverStatus.add(2, "Approved");
    		ApproverStatus.add(3, "Approved");
    		ApproverStatus.add(4, "Approved");
    		ApproverStatus.add(5, "Approved");
    		ApproverStatus.add(6, "Approved");
    		ApproverStatus.add(7, "Approved");
    		ApproverStatus.add(8, "Approved");
			for(int i=9;i<counts;i++){
				ApproverStatus.add("  Pending  ");
			}
			
		}
      
		}
				}
					
					else if(rateCardApprovalDetails.getCurrentApproverRoleId()==null){
						if(rateCardApprovalDetails.getCurrentApprovalStatus()==2){
							for(int i=0;i<counts;i++){
								ApproverStatus.add("  Pending  ");
							}
						}
						else if(rateCardApprovalDetails.getCurrentApprovalStatus()==3){
							for(int i=0;i<counts;i++){
								ApproverStatus.add("  Approved  ");
							}
						}
					}
					
			}
		}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//if(rateCardApprovalDetails.getCurrentApprovalStatus() != 4){}
		
	
		
		

			

		for (Object object : rateCardDetails) {
			RateCardDetails data = (RateCardDetails)object;
			int currencyId=data.getConsolidatedRcCurrencyId();
			Integer isRenew=data.getIsRenewal();
			
			if(!(isRenew==null)){
				 isRenewal = new Integer(isRenew).toString();
			
			if(isRenewal.equals("0")){
				isRenewal="NO";
			}
			else if (isRenewal.equals("1")){
				isRenewal="Yes";
			}
			}
			else {
				isRenewal="-";
			}
			isAutomate=data.getIsManualRc();
			if(isAutomate.equals("A")){
				isAutomate="Automated";
				
			}
			else if (isAutomate.equals("M")){
				isAutomate="Manual";
			}
			else if (isAutomate.equals("H")){
				isAutomate="Hybrid";
			}
			else 
				isAutomate="N/A";
			oldRenewal=data.getRenewalRCId();
			if(!(oldRenewal==null)){
				oldRenewalId = new Integer(oldRenewal).toString();
			}
			else {
				oldRenewalId="NA";
			}
			
			 onsite = data.getExpectedOnsiteResourcePercentage();
			 offsite = data.getExpectedOffshoreResourcePercentage();
			 onsiteHrs=data.getOnsiteHoursPerDay();
			 DecimalFormat df = new DecimalFormat("#0.00");
			 if(onsiteHrs != null) {
				 onsiteHrsDay=(df.format(onsiteHrs));
			 } else {
				 onsiteHrs = 0.00;
				onsiteHrsDay=(df.format(onsiteHrs));
			 }
			 Double oFFsiteHrs=data.getOffshoreHoursPerDay();
			 DecimalFormat decF = new DecimalFormat("#0.00");
			 if(oFFsiteHrs != null) {
				offsiteHrsDay =(decF.format(oFFsiteHrs));
			 } else {
				 oFFsiteHrs = 0.00; 
				 offsiteHrsDay =(decF.format(oFFsiteHrs));
			 }
		}
		
 		EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
		//String approvalLink ="https://10.128.10.231/RightPrice/login";
		switch (templateID) {
		case 3: {
			AppLoger.APPLOGGER.info("Executing case 3");
			AppLoger.APPLOGGER.info("Mail Generated for Deactivation Of Rate Card");
			tempId = "Temp3";
			desc = "Case 3: Rate Card is Deactivated";
            if(deliverySpEmailId!=null){
            	deliverySpEmailId=deliverySpEmailId;
            }else
            {
            	deliverySpEmailId=deliveryEmailId;
            }
			to = new String[1];
			to[0] = deliverySpEmailId.toString();
			cc = new String[2];
			cc[0] = deliveryEmailId;
			/*cc[1]="Syndev@TATOSMAIL.COM";
			cc[2] = "Syndev@TATOSMAIL.COM";*/
			cc[1]="gft_costing@eviden.com";
			//cc[2] = "vishal.arora@eviden.com";
			for(RateCardDetails rateData : rateCardDetails) {
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				 String comments=rateData.getRccomments();
				 String[] lastComment=comments.split("\n");
			     String finalComment=lastComment[lastComment.length-(lastComment.length)].trim();
			     String[] finalComments=finalComment.split(":");
			     String deactivatecmnt=finalComments[finalComments.length-1].trim();
				subject = "Rate card has been deactivated - "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"- "+rateData.getRcId();
				
				mailContent = "<p>Dear "+deliverySpName+" and GFT Team,</p><br>"
						+ "<p>&emsp;&emsp;The rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> has been deactivated .</p>"
						+ "<p>&emsp;&emsp;Rate Card was applicable for the period "+startDate+" to "+endDate+".</p>"
						+ "<p>&emsp;&emsp;Reason for Deactivation: - "+deactivatecmnt+".</p>"
						+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
						+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
						+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
						+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
						+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
						+"<tr><td><b>Region wise utilization</b></td>"
						+ "<td>"
						+ "<table class='tblNoBorder'><tbody>";
						for(int i=0;i<utilMix.size();i++)
						{
							mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
						+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
						+ "<td><table class='tblNoBorder'><tbody>";
						for(int j=0;j<blendedvalues.size();j++)
						{
							mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
						+ "<table class='tblNoBorder'><tbody>";
						for(int k=0;k<blendedvalues.size();k++)
						{
							mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
						+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
						+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
						+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
						//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
						+"</tbody></table>"
						+"<br>"
						+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
						+ "<p>&emsp;&emsp;"+rateData.getRccomments()+"</p>"
						+ "<br><br>Regards,<br>GFT Team"
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 4: {
			AppLoger.APPLOGGER.info("Executing case 4");
			AppLoger.APPLOGGER.info("Mail Generated for Extension Of Rate Card");
			tempId = "Temp4";
			//String content = comment.substring(comment.indexOf(' ') + 1);
			desc = "Case 4: Rate Card is Extended";
			to = new String[1];
			to[0] = deliverySpEmailId.toString();
			cc = new String[2];
			cc[0] = deliveryEmailId;
			/*cc[1] = "Syndev@TATOSMAIL.COM";
			cc[2] = "Syndev@TATOSMAIL.COM";*/
			cc[1] = "gft_costing@eviden.com";
			//cc[2] = "vishal.arora@eviden.com";
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				 String comments=rateData.getRccomments();
				 String[] lastComment=comments.split("\n");
			     String finalComment=lastComment[lastComment.length-(lastComment.length)].trim();
			     String[] finalComments=finalComment.split(":");
			     String extendcmnt=finalComments[finalComments.length-1].trim();
				 //lastComment[lastComment.length-1]
				subject = "Rate Card has been extended until "+expectedEndDate+" "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+deliverySpName+" and GFT Team,</p><br>"
					+ "<p>&emsp;&emsp;The rate card for <strong>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+ "</strong> has been extended until "+ expectedEndDate+".</p>"
					+ "<p>&emsp;&emsp;End Date of the rate card earlier was "+endDate +" .</p>"
					+ "<p>&emsp;&emsp;Reason for Extension: - "+extendcmnt+".</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+rateData.getRccomments()+"</p>"
					+ "<br><br>Regards,<br>GFT Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 5: {
			AppLoger.APPLOGGER.info("Executing case 5");
			AppLoger.APPLOGGER.info("Mail Generated When Delivery Team Submits");
			tempId = "Temp5";
			desc = "Case 5: Rate Card is Submitted";
			
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	to = new String[2];
						to[0] =  deliveryHeadEmail.toString();
						to[1] = deliverySpEmailId.toString();
		            }else{
		            	to = new String[1];
						to[0] =  deliveryHeadEmail.toString();
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
						
					}
		  	}
		  	else
	        {
		  		to = new String[1];
				to[0] =  deliveryHeadEmail.toString();
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
	        }
			
		  	
		  	cc = new String[1];
			cc[0] = createdbyEmail.toString();
		  	if(DelievryProxy.get(0)!=null){
		  		int ccsize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccsize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
			}
			else{
				cc = new String[1];
				cc[0] = createdbyEmail.toString();
		     }

			for(RateCardDetails rateData : rateCardDetails) {
					 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
					 String startDate = dateFormat(rateData.getRcStartDate());
					 String endDate=dateFormat(rateData.getRcEndDate());
					 String utilMixdata = null;
					 String country=null;
					 String city=null;
					//// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
					 
				   subject = "Delivery has submitted a rate card for your approval "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			        mailContent = "<p>Dear "+deliveryHeadName+",</p><br>"
					+ "<p>&emsp;&emsp;"+createdByname+" has requested approval of a rate card for <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" </strong>to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" "  +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<table class> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards, <br>"+createdByname+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
		}
		
		
/*		case 6: {
			AppLoger.APPLOGGER.info("Executing case 6");
			AppLoger.APPLOGGER.info("When Delivery Head Approves");
			tempId = "Temp6";
			desc = "Case 6: Rate Card is Approved";
			to = new String[2];
			to[0] ="gft_costing@eviden.com";
			to[1]="vishal.arora@eviden.com";
			//to[3]=gfteam
			cc = new String[3];
			cc[0] =  deliveryEmailId;
			cc[1] =  deliveryHeadEmail;
			cc[2] = deliverySpEmailId;
			
			for(RateCardDetails rateData : rateCardDetails) {
					 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
					 String startDate = dateFormat(rateData.getRcStartDate());
					 String endDate=dateFormat(rateData.getRcEndDate());
					 DecimalFormat diff = new DecimalFormat("#.00");
					// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				   subject = "Delivery Head has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				   mailContent = "<p>Dear GFT Team,</p><br>"
					+ "<p>&emsp;&emsp;"+deliveryHeadName+" has approved a rate card for <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" </strong>to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
					+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table class> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+deliveryHeadName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}*/
		
		
		case 6: {
			AppLoger.APPLOGGER.info("Executing case 6");
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+createdbyEmail);
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			tempId = "Temp6";
			
			String username="";
		  	String userEmail="";
		  	String desg="";
		  	//to = new String[1];
			//cc = new String[2];
			String username1="";
		  	String userEmail1="";
		  	String desg1="";
		  	int itkpo=0;
		  	for(Object object : rateCardDetails)
			{
			RateCardDetails matrixData = (RateCardDetails)object;
			itkpo=matrixData.getIsItKpo();
				}
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            }else{
						deliverySpEmailId=createdbyEmail;
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
					}
		  	}
		  	else
            {
            	deliverySpEmailId=createdbyEmail;
            }
			switch (Status) {
	  		case "Delivery Head" : 
	  			username=deliveryHeadName;
	  			userEmail=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username="GFT Costing Team";
	  			//userEmail="Syndev@TATOSMAIL.COM";
	  			userEmail="gft_costing@eviden.com";
	  			desg="GFT TEAM";
	  			break;
	  		case "CDO" : 
	  			username=CDOName;
	  			userEmail=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" :
	  			if(itkpo==0||itkpo==1){
	  				username=BuHeadName;
		  			userEmail=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username=JVCEO;
		  			userEmail=JVCEOEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  		case "CEO" : 
	  			username=CEOName;
	  			userEmail=CEOEmail;
	  			desg="CEO";
	  			break;
	  		case "Level 1" : 
	  			username=level1;
	  			userEmail=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username=level2;
	  			userEmail=level2Email;
	  			desg=level2;
	  			break;
	  			
	  		default : 
	  			break;
			}
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
		  			userEmail1=JVCEOEmail;
		  			desg1="BUH";
		  			break;
	  			}
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1="CEO";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  			
	  		default : 
	  			break;
			}
			desc = "send for approval";
			//to[0] = userEmail1;
			//cc[0] = userEmail;
			//cc[1] = createbyEmail;
			if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
				if(!(deliverySpEmailId.equals(""))){
					deliverySpEmailId=deliverySpEmailId;
				}else{
					deliverySpEmailId=createdbyEmail;
					AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
				}
				
				
			}else{
				deliverySpEmailId=createdbyEmail;
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			}
			
			if(Status.equals("Delivery Head")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
					if(DelievryProxy.get(0)!=null){
						cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
						int j=0;
						for(int i=3;i<cc.length;i++)
						{
							cc[i]=DelievryProxy.get(j);
							j++;
						}
						
					}
				else{
					cc = new String[3];
					cc[0] = userEmail;
					cc[1] = createdbyEmail;
					cc[2] = deliverySpEmailId;}
				
				
			}
			if(Status.equals("GFT")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				//cc[2] = "vishal.arora@eviden.com";
				cc[2] = deliverySpEmailId;
				
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=3;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
			}
			if(Status.equals("CDO")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=3;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[3];
					cc[0] = userEmail;
					cc[1] = createdbyEmail;
					cc[2] = deliverySpEmailId;
					}
				
			}
			if(Status.equals("BUH")||Status.equals("BU_Head")||Status.equals("BU Head")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
					if(BUhProxy.get(0)!=null){
						cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
						int j=0;
						for(int i=3;i<cc.length;i++)
						{
							cc[i]=BUhProxy.get(j);
							j++;
						}
						
					}
					
				else{
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;}
			}
			if(Status.equals("CEO")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=3;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[3];
					cc[0] = userEmail;
					cc[1] = createdbyEmail;
					cc[2] = deliverySpEmailId;}
				
				
			}
			if(Status.equals("Level 1")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
				
				if(level1Proxy.get(0)!=null){
					int sizeProxy=level1Proxy.size();
					cc = Arrays.copyOf(cc, cc.length + sizeProxy);
					int j=0;
					for(int i=3;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[3];
					cc[0] = userEmail;
					cc[1] = createdbyEmail;
					cc[2] = deliverySpEmailId;}
				
			}
			if(Status.equals("Level 2")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyEmail;
				cc[2] = deliverySpEmailId;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=3;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[3];
					cc[0] = userEmail;
					cc[1] = createdbyEmail;
					cc[2] = deliverySpEmailId;}
				
			}
			
			if(updatedStaus.equals("Delivery Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("CDO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("BUH")||updatedStaus.equals("BU_Head")||updatedStaus.equals("BU Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(BUhProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=BUhProxy.get(j);
						j++;
					}
					
				}
			}
			if(updatedStaus.equals("CEO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 1")){
				to = new String[1];
				to[0] = userEmail1;
				int j=0;
				if(level1Proxy.get(0)!=null){
					int ccSize=cc.length;
					cc = Arrays.copyOf(cc, cc.length + level1Proxy.size());
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 2")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				
			}
			
			
			if(updatedStaus.equals("GFT")){
				to = new String[1];
				to[0] = userEmail1;
				//to[1] = "vishal.arora@eviden.com";
				int ccSize=cc.length;
				int j=0;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				for(int i=ccSize;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
			}
			
			for(RateCardDetails rateData : rateCardDetails) {
				 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				 String startDate = dateFormat(rateData.getRcStartDate());
				 String endDate=dateFormat(rateData.getRcEndDate());
				 DecimalFormat diff = new DecimalFormat("#.00");
				// // // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			   subject = desg +" has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			   mailContent = "<p>Dear "+desg1+",</p><br>"
				+ "<p>&emsp;&emsp;"+desg+" has approved a rate card for <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" </strong>to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
				+"<br>"
				
				
				+"<table class> <tbody>"
				+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
				+"<tr>";
				for(int k=0;k<ApproverList.size();k++)
				{
					mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
				}
				mailContent+=	"</tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+username+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		
		
		case 7: {
			AppLoger.APPLOGGER.info("Executing case 7");
			AppLoger.APPLOGGER.info("When GFT Team Approves");
			tempId = "Temp7";
			desc = "Case 7: Rate Card is Approved";
			to = new String[1];
			to[0]=  CDOEmail.toString();
			//to[3]=  gftteams
			cc = new String[3];
			//cc[0] ="Syndev@TATOSMAIL.COM";
			cc[0] ="gft_costing@eviden.com";
			cc[1] =  deliveryEmailId;
			cc[2] = deliverySpEmailId;
			//cc[3]="Syndev@TATOSMAIL.COM";
			//cc[3]="vishal.arora@eviden.com";
			cc = Arrays.copyOf(cc, cc.length + sizeofTo);
			int m=0;
			for(int i=3;i<cc.length;i++){
				cc[i]=verticalPOCMailList.get(m).getEmployeeemail();
				m++;
		      }
			for(RateCardDetails rateData : rateCardDetails) {
					 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
					 String startDate = dateFormat(rateData.getRcStartDate());
					 String endDate=dateFormat(rateData.getRcEndDate());
					 String utilMixdata = null;
					 String country=null;
					 String city=null;
					 String userComment=null;
					// // // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
					 
				    subject = "GFT has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			        mailContent = "<p>Dear "+CDOName+",</p><br>"
					+ "<p>&emsp;&emsp;GFT Team has requested approval of a rate card for <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" </strong>to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" "  +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>GFT Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 8: {
			AppLoger.APPLOGGER.info("Executing case 8");
			AppLoger.APPLOGGER.info("When CDO Approves");
			tempId = "Temp8";
			desc = "Case 8: Rate Card is Approved";
			to = new String[1];
			to[0]=BuHeadEmail.toString();
			cc = new String[3];
			cc[0] = CDOEmail;
			cc[1] =  deliveryEmailId;
			cc[2] = deliverySpEmailId;
			for(RateCardDetails rateData : rateCardDetails) {
					 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
					 String startDate = dateFormat(rateData.getRcStartDate());
					 String endDate=dateFormat(rateData.getRcEndDate());
					// // // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
					 
				    subject = "CDO has approved a rate card "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			        mailContent = "<p>Dear "+BuHeadName+",</p><br>"
					+ "<p>&emsp;&emsp;"+CDOName+" has approved a rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" </strong>to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" "  +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<table class> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+CDOName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 9: {
			AppLoger.APPLOGGER.info("Executing case 9");
			AppLoger.APPLOGGER.info("When BU Head Approves");
			tempId = "Temp9";
			desc = "Case 9: Rate Card is Approved";
			to = new String[1];
			to[0] =CEOEmail.toString();
			cc = new String[3];
			cc[0]= BuHeadEmail.toString();
			cc[1] =  deliveryEmailId;
			cc[2] = deliverySpEmailId;
			for(RateCardDetails rateData : rateCardDetails) {
					 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
					 String startDate = dateFormat(rateData.getRcStartDate());
					 String endDate=dateFormat(rateData.getRcEndDate());
					 String utilMixdata = null;
					 String country=null;
					 String city=null;
					// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
					 
				    subject = "BUH has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			        mailContent = "<p>Dear "+CEOName+",</p><br>"
					+ "<p>&emsp;&emsp;"+BuHeadName+" has approved a rate card for  <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<table class> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+BuHeadName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 10: {
			AppLoger.APPLOGGER.info("Executing case 10");
			AppLoger.APPLOGGER.info("When CEO Approves");
			tempId = "Temp10";
			desc = "Case 10: Rate Card is Approved";
			to = new String[1];
			to[0]= 	CEOEmail.toString();
			cc = new String[1];
			cc[0] = deliveryEmailId.toString();
			for(RateCardDetails rateData : rateCardDetails) {
			 String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			 String startDate = dateFormat(rateData.getRcStartDate());
			 String endDate=dateFormat(rateData.getRcEndDate());
			 String utilMixdata = null;
			 String country=null;
			 String city=null;
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
				   subject = "CEO has approved a rate card "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
		            mailContent = "<p>Dear "+deliverySpName+"</p><br>"
					+ "<p>&emsp;&emsp;"+CEOName+" has approved a rate card for  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" and the approval process is now completed.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " "  +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+CEOName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
		}
		break;
		
		case 11: {
			AppLoger.APPLOGGER.info("Executing case 11");
			AppLoger.APPLOGGER.info("Mail Generated When Delivery Team Submits");
			tempId = "Temp5";
			desc = "Case 11: Rate Card is Submitted";
			to = new String[1];
			/*to[0]="Syndev@TATOSMAIL.COM";
			to[1]="Syndev@TATOSMAIL.COM";*/
			to[0]="gft_costing@eviden.com";
			//to[1]="vishal.arora@eviden.com";
			//to[1] =  gft team;
			
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	cc = new String[2];
		        		cc[0] = deliverySpEmailId;
		        		cc[1] = createdbyEmail.toString();
		        		cc = Arrays.copyOf(cc, cc.length + sizeofTo);
						int j=0;
						for(int i=2;i<cc.length;i++){
							cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
							j++;
					      }

		            }else{
		            	cc = new String[1];
		        		cc[0] = createdbyEmail.toString();
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
						cc = Arrays.copyOf(cc, cc.length + sizeofTo);
						int j=0;
						for(int i=1;i<cc.length;i++){
							cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
							j++;
					      }
						
					}
		  	}
		  	else
	        {
		  		cc = new String[1];
	    		cc[0] = createdbyEmail.toString();
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
	        }
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				 // // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				subject = "Delivery has submitted a rate card for your approval "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			        mailContent = "<p>Dear GFT Team</p><br>"
					+ "<p>&emsp;&emsp;"+createdByname+" has requested a rate card for <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					
					/*+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+"<br>"*/
					//+ "<p>&emsp;&emsp;"+userComment+"</p>"
					+ "<br>Regards, <br>"+deliverySpName+"."
					+ "<br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		
		case 12: {
			AppLoger.APPLOGGER.info("Executing case 12");
			AppLoger.APPLOGGER.info("Mail Generatedwhen GFt sends for approval");
			tempId = "Temp12";
			desc = "Case 12: Rate Card is approved";
			to = new String[1];
			to[0] =  deliveryHeadEmail.toString();
			
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	cc = new String[3];
		    			//cc[0] = "Syndev@TATOSMAIL.COM";
		    			cc[0] = "gft_costing@eviden.com";
		    			cc[1] =  createdbyEmail;
		    			cc[2] = deliverySpEmailId;
		    			//cc[3]="Syndev@TATOSMAIL.COM";
		    			//cc[3]="vishal.arora@eviden.com";
		    			cc = Arrays.copyOf(cc, cc.length + sizeofTo);
						int j=0;
						for(int i=3;i<cc.length;i++){
							cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
							j++;
					      }
						if(DelievryProxy.get(0)!=null){
							int ccszie=cc.length;
							cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
							int m=0;
							if(DelievryProxy.get(0)!=null){
								for(int i=ccszie;i<cc.length;i++)
								{
									cc[i]=DelievryProxy.get(m);
									m++;
								}
								
							}
						}
		            }else{
		            	cc = new String[2];
		    			//cc[0] = "Syndev@TATOSMAIL.COM";
		    			cc[0] = "gft_costing@eviden.com";
		    			cc[1] =  createdbyEmail;
		    			//cc[2]="Syndev@TATOSMAIL.COM";
		    			//cc[2]="vishal.arora@eviden.com";
		    			cc = Arrays.copyOf(cc, cc.length + sizeofTo);
						int j=0;
						for(int i=2;i<cc.length;i++){
							cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
							j++;
					      }
						if(DelievryProxy.get(0)!=null){
							int ccszie=cc.length;
							cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
							int m=0;
							if(DelievryProxy.get(0)!=null){
								for(int i=ccszie;i<cc.length;i++)
								{
									cc[i]=DelievryProxy.get(m);
									m++;
								}
								
							}
						}
						
					}
		  	}
		  	else
	        {
		  	cc = new String[2];
			//cc[0] = "Syndev@TATOSMAIL.COM";
     		cc[0] = "gft_costing@eviden.com";
			cc[1] =  createdbyEmail;
			//cc[2]="Syndev@TATOSMAIL.COM";
			//cc[2]="vishal.arora@eviden.com";
			cc = Arrays.copyOf(cc, cc.length + sizeofTo);
			int j=0;
			for(int i=2;i<cc.length;i++){
				cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
				j++;
		      }
			if(DelievryProxy.get(0)!=null){
				int ccszie=cc.length;
				cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
				int m=0;
				if(DelievryProxy.get(0)!=null){
					for(int i=ccszie;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(m);
						m++;
					}
					
				}
			}
	        }
			
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				
				subject = "GFT has submitted a rate card for your approval "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+deliveryHeadName+",</p><br>"
					+ "<p>&emsp;&emsp;GFT Team has submitted a rate card for <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval</strong>.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards, <br>GFT Team."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 27: {
			AppLoger.APPLOGGER.info("Executing case 12");
			AppLoger.APPLOGGER.info("Mail Generated GFT team Approves");
			tempId = "Temp12";
			desc = "Case 12: Rate Card is approved";
			to = new String[2];
			to[0] = deliveryEmailId.toString();
			cc = new String[1];
			cc[0] =  deliveryHeadEmail.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				String updatedBy=rateData.getUpdatedBy();
				String submittedby="";
				String head="";
				
				switch (updatedBy) {
		  		case "GFT" : 
		  			 submittedby="GFT";
		  			break;
		  		case "rmanuja" : 
		  			head="Delivery Head";
		  			submittedby= "Rajesh Manuja";
		  			break;
		  		case "RSingamp1" : 
		  			head="CDO";
		  			submittedby= "Ramakumar Singampalli";
		  			break;
		  		case "Djogal" :
		  			head="BUH";
		  			submittedby= "Devesh Jogal";
		  		case "RKhanna3" : 
		  			head="CEO";
		  			submittedby= "Rakesh Khanna";
		  			break;
		  		default : 
		  			break;
				}
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				subject = head+" has submitted a rate card for your approval "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+deliveryHeadName+",</p><br>"
					+ "<p>&emsp;&emsp;"+submittedby+" has approved a rate card for <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval</strong>.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards, <br>"+deliverySpName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
		}
		
		case 13: {
			AppLoger.APPLOGGER.info("Executing case 13");
			AppLoger.APPLOGGER.info("When Delivery Head Approves");
			tempId = "Temp13";
			desc = "Case 13: Rate Card is Approved";
			to = new String[1];
			to[0]=  CDOEmail.toString();
			cc = new String[3];
			cc[0] =  deliveryEmailId;
			cc[1] = deliverySpEmailId;
			cc[2]=deliveryHeadEmail;

			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				subject = "Delivery Head  has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+CDOName+"</p><br>"
					+ "<p>&emsp;&emsp;Delivery Head has approved a rate card for <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period  <strong> "+startDate+"  </strong> to  <strong> "+endDate+" </strong> .</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+deliveryHeadName
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 14: {
			AppLoger.APPLOGGER.info("Executing case 14");
			AppLoger.APPLOGGER.info("When CDO Approves");
			tempId = "Temp14";
			desc = "Case 14: Rate Card is Approved";
			to = new String[1];
			to[0]=BuHeadEmail.toString();
			cc = new String[3];
			cc[0] =  deliveryEmailId;
			cc[1] = deliverySpEmailId;
			cc[2] =CDOEmail.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				int CurrencyId=rateData.getConsolidatedRcCurrencyId();
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				   subject = "CDO has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			        mailContent = "<p>Dear "+BuHeadName+"</p><br>"
					+ "<p>&emsp;&emsp;"+CDOName+" has approved a rate card for <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+" to "+endDate+" </strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" "  +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+CDOName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 15: {
			AppLoger.APPLOGGER.info("Executing case 15");
			AppLoger.APPLOGGER.info("When BU Head Approves");
			tempId = "Temp15";
			desc = "Case 15: Rate Card is Approved";
			to = new String[1];
			to[0] =CEOEmail.toString();
			cc = new String[3];
			cc[0] =  deliveryEmailId;
			cc[1] = deliverySpEmailId;
			cc[2]=BuHeadEmail.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				subject = "BUH has approved a rate card  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+CEOName+",</p><br>"
					+ "<p>&emsp;&emsp;"+BuHeadName+"has approved a rate card for  <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"</strong> and has requested your approval.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+BuHeadName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 16: {
			AppLoger.APPLOGGER.info("Executing case 16");
			AppLoger.APPLOGGER.info("When CEO Approves");
			tempId = "Temp16";
			desc = "Case 16: Rate Card is Approved";
			to = new String[1];
			to[0] = deliveryEmailId.toString();
			cc = new String[2];
			cc[0] = deliverySpEmailId;
			cc[1]= 	CEOEmail.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				 
				subject = "CEO has approved a rate card "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+deliverySpName+"</p><br>"
					+ "<p>&emsp;&emsp;CEO has approved a rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> and the approval process is now completed. </p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to review the rate card.</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
					+"<br>"
					
					
					+"<table> <tbody>"
					+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
					+"<tr>";
					for(int k=0;k<ApproverList.size();k++)
					{
						mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
					}
					mailContent+=	"</tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+CEOName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
		}
		break;
		
		case 17: {
			AppLoger.APPLOGGER.info("Executing case 17");
			AppLoger.APPLOGGER.info("When Delivery Head Recycle");
			tempId = "Temp17";
			desc = "Case 17: Rate Card is Recycled";
			
			to = new String[1];
			to[0] = createdbyEmail.toString();
			
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	cc = new String[2];
		        		cc[0] = deliverySpEmailId;
		        		cc[1] = deliveryHeadEmail.toString();
		            }else{
		            	cc = new String[1];
		        		cc[0] = deliveryHeadEmail.toString();
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
						
					}
		  	}
		  	else
	        {
		  		cc = new String[1];
	    		cc[0] = deliveryHeadEmail.toString();
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
	        }
			
			
			
			
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
				
				subject = "Rate card has been recycled by Delivery Head for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+createdByname+"</p><br>"
					+ "<p>&emsp;&emsp;"+deliveryHeadName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
					+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
					+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+deliveryHeadName+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
		}
		break;
	
		
	case 18: {
		AppLoger.APPLOGGER.info("Executing case 18");
		AppLoger.APPLOGGER.info("When GFT Recycles");
		tempId = "Temp18";
		desc = "Case 18: Rate Card is Recycled";
		to = new String[1];
		to[0] = createdbyEmail;
		
	  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
	  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
		  	 if(!deliverySpEmailId.equals("")){
	            	deliverySpEmailId=deliverySpEmailId;
	            	cc = new String[3];
//	        		cc[0] = "Syndev@TATOSMAIL.COM";
	        		cc[0] = "gft_costing@eviden.com";
	        		cc[1] = deliverySpEmailId.toString();
	        		cc[2] = deliveryHeadEmail;
//	        		cc[3]="Syndev@TATOSMAIL.COM";
	        		//cc[3]="vishal.arora@eviden.com";
	            }else{
	            	cc = new String[2];
//	        		cc[0] = "Syndev@TATOSMAIL.COM";
	        		cc[0] = "gft_costing@eviden.com";
	        		cc[1] = deliveryHeadEmail;
//	        		cc[2]="Syndev@TATOSMAIL.COM";
	        		//cc[2]="vishal.arora@eviden.com";
					AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
					
				}
	  	}
	  	else
        {
	  		cc = new String[2];
//    		cc[0] = "Syndev@TATOSMAIL.COM";
    		cc[0] = "gft_costing@eviden.com";
    		cc[1] = deliveryHeadEmail;
//    		cc[2]="Syndev@TATOSMAIL.COM";
    		//cc[2]="vishal.arora@eviden.com";
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
        }
		
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by GFT for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
		    mailContent = "<p>Dear "+createdByname+"</p><br>"
				+ "<p>&emsp;&emsp; GFT Team has recycled the rate card for  <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br> GFT Team."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;
	case 19: {
		AppLoger.APPLOGGER.info("Executing case 19");
		AppLoger.APPLOGGER.info("When  CDO Recycles");
		tempId = "Temp19";
		desc = "Case 18: Rate Card is Recycled";
		to = new String[1];
		to[0] = createdbyEmail;
		
	  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
	  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
		  	 if(!deliverySpEmailId.equals("")){
		  		 
	            	deliverySpEmailId=deliverySpEmailId;
	            	cc = new String[4];
	            	cc[0] = deliverySpEmailId.toString();
	        		cc[1] = CDOEmail.toString();
//	        		cc[2] = "Syndev@TATOSMAIL.COM";
	        		cc[2] = "gft_costing@eviden.com";
	        		cc[3] = deliveryHeadEmail;
//	        		cc[4]="Syndev@TATOSMAIL.COM";
	        		//cc[4]="vishal.arora@eviden.com";
	            }else{
	            	cc = new String[3];
	        		cc[0] = CDOEmail.toString();
//	        		cc[1] = "Syndev@TATOSMAIL.COM";
	        		cc[1] = "gft_costing@eviden.com";
	        		cc[2] = deliveryHeadEmail;
//	        		cc[3]="Syndev@TATOSMAIL.COM";
	        		//cc[3]="vishal.arora@eviden.com";
					AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
					
				}
	  	}
	  	else
        {
	  		cc = new String[3];
    		cc[0] = CDOEmail.toString();
//    		cc[1] = "Syndev@TATOSMAIL.COM";
    		cc[1] = "gft_costing@eviden.com";
    		cc[2] = deliveryHeadEmail;
//    		cc[3]="Syndev@TATOSMAIL.COM";
    		//cc[3]="vishal.arora@eviden.com";
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
        }
		
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by CDO for  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+createdByname+"</p><br>"
				+ "<p>&emsp;&emsp;"+CDOName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+CDOName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

				emailDetails.setTo(to);
				emailDetails.setCc(cc);
				emailDetails.setSubject(subject);
				emailDetails.setMailContent(mailContent);
			}
	}
	break;


	case 20: {
		AppLoger.APPLOGGER.info("Executing case 19");
		AppLoger.APPLOGGER.info("When BUH Recycles");
		tempId = "Temp20";
		desc = "Case 20: Rate Card is Recycled";
		to = new String[1];
		to[0] = createdbyEmail;
		
	  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
	  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
		  	 if(!deliverySpEmailId.equals("")){
	            	deliverySpEmailId=deliverySpEmailId;
	            	
	        		cc = new String[5];
	        		cc[0] = deliverySpEmailId.toString();
	        		cc[1] = BuHeadEmail.toString();
	        		cc[2] = CDOEmail.toString();
//	        		cc[3] = "Syndev@TATOSMAIL.COM";
	        		cc[3] = "gft_costing@eviden.com";
	        		cc[4] = deliveryHeadEmail;
//	        		cc[5]="Syndev@TATOSMAIL.COM";
	        		//cc[5]="vishal.arora@eviden.com";
	            }else{
	            	cc = new String[4];
	        		cc[0] = BuHeadEmail.toString();
	        		cc[1] = CDOEmail.toString();
//	        		cc[2] = "Syndev@TATOSMAIL.COM";
	        		cc[2] = "gft_costing@eviden.com";
	        		cc[3] = deliveryHeadEmail;
//	        		cc[4]="Syndev@TATOSMAIL.COM";
	        		//cc[4]="vishal.arora@eviden.com";
	        		
					AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
					
				}
	  	}
	  	else
        {
	  		cc = new String[4];
    		cc[0] = BuHeadEmail.toString();
    		cc[1] = CDOEmail.toString();
//    		cc[2] = "Syndev@TATOSMAIL.COM";
    		cc[2] = "gft_costing@eviden.com";
    		cc[3] = deliveryHeadEmail;
//    		cc[4]="Syndev@TATOSMAIL.COM";
    		//cc[4]="vishal.arora@eviden.com";
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
        }
		
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by BUH for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+createdByname+"</p><br>"
				+ "<p>&emsp;&emsp;"+BuHeadName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+BuHeadName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;

	case 21 : {
		AppLoger.APPLOGGER.info("Executing case 20");
		AppLoger.APPLOGGER.info("When CEO Recycles");
		tempId = "Temp20";
		desc = "Case 20: Rate Card is Recycled";
		to = new String[1];
		to[0] = createdbyEmail;
	  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
	  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
		  	 if(!deliverySpEmailId.equals("")){
	            	deliverySpEmailId=deliverySpEmailId;
	            	cc = new String[6];
	        		cc[0] = deliverySpEmailId.toString();
	        		cc[1] = BuHeadEmail.toString();
	        		cc[2] = CDOEmail.toString();
//	        		cc[3] = "Syndev@TATOSMAIL.COM";
	        		cc[3] = "gft_costing@eviden.com";
	        		cc[4] = deliveryHeadEmail;
	        		cc[5] = CEOEmail;
//	        		cc[6]="Syndev@TATOSMAIL.COM";
	        		//cc[6]="vishal.arora@eviden.com";
	            }else{
	            	cc = new String[5];
	        		cc[0] = BuHeadEmail.toString();
	        		cc[1] = CDOEmail.toString();
//	        		cc[2] = "Syndev@TATOSMAIL.COM";
	        		cc[2] = "gft_costing@eviden.com";
	        		cc[3] = deliveryHeadEmail;
	        		cc[4] = CEOEmail;
//	        		cc[5]="Syndev@TATOSMAIL.COM";
	        		//cc[5]="vishal.arora@eviden.com";
	        		
					AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
					
				}
	  	}
	  	else
        {
	  		cc = new String[5];
    		cc[0] = BuHeadEmail.toString();
    		cc[1] = CDOEmail.toString();
//    		cc[2] = "Syndev@TATOSMAIL.COM";
    		cc[2] = "gft_costing@eviden.com";
    		cc[3] = deliveryHeadEmail;
    		cc[4] = CEOEmail;
//    		cc[5]="Syndev@TATOSMAIL.COM";
    		//cc[5]="vishal.arora@eviden.com";
			AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
        }
		
		
		
		
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by CEO for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+createdByname+"</p><br>"
				+ "<p>&emsp;&emsp;"+CEOName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+CEOName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;

	
	case 22: {
		AppLoger.APPLOGGER.info("Executing case 29");
		AppLoger.APPLOGGER.info("When Deal rejected");
		String username="";
	  	String userEmail="";
	  	String desg="";
	  	//to = new String[1];
		//cc = new String[1];
		String username1="";
	  	String userEmail1="";
	  	String desg1="";
		switch (Status) {
  		case "Delivery Head" : 
  			username=deliveryHeadName;
  			userEmail=deliveryHeadEmail;
  			desg="Delivery Head";
  			break;
  		case "GFT" : 
  			username="GFT Costing Team";
//  			userEmail="Syndev@TATOSMAIL.COM";
  			userEmail="gft_costing@eviden.com";
  			desg="GFT Costing TEAM";
  			break;
  		case "CDO" : 
  			username=CDOName;
  			userEmail=CDOEmail;	
  			desg="CDO";
  			break;
  		case "BU Head" : 
  		case "BU_Head" :
  		case "BUH" : 
  			username1=BuHeadName;
  			userEmail =BuHeadEmail;
  			desg="BU Head";
  			break;
  		
  		case "CEO" : 
  			username=CEOName;
  			userEmail=CEOEmail;
  			desg="CEO";
  			break;
  			
  		case "Level 1" : 
  			username=level1;
  			userEmail=level1mail;
  			desg=level1;
  			break;
  			
  		case "Level 2" : 
  			username=level2;
  			userEmail=level2Email;
  			desg=level2;
  			break;
  		default : 
  			break;
		}
/*			switch (updatedStaus) {
  		case "Delivery Head" : 
  			username1=deliveryHeadName;
  			userEmail1=deliveryHeadEmail;
  			desg1="Delivery Head";
  			break;
  		case "GFT" : 
  			username1="GFT Costing";
  			userEmail1="gft_costing@eviden.com";
  			desg1="GFT Costing TEAM";
  			break;
  		case "CDO" : 
  			username1=CDOName;
  			userEmail1=CDOEmail;	
  			desg1="CDO";
  			break;
  		case "BU Head" : 
  		case "BU_Head" :
  		case "BUH" : 
  			username1=BuHeadName;
  			userEmail1=BuHeadEmail;
  			desg1="BU Head";
  			break;
  		case "RiskManagers" : 
  			username1=RiskManagersPerson;
  			userEmail1=RiskManagersPersonemail;
  			desg1="RiskManagers Team";
  			break;
  		case "CEO" : 
  			username1=CEOName;
  			userEmail1=CEOEmail;
  			desg1="CEO";
  			
  		default : 
  			break;
		}*/
		desc = "Case 22: rate Card Rejected";
		if(Status.equals("GFT")){
			to = new String[1];
			cc = new String[1];
			to[0] = createdbyEmail;
//			cc[0] = "Syndev@TATOSMAIL.COM";
			//cc[0] = "vishal.arora@eviden.com";
			cc[0] = userEmail;
			
		}else
		{
			to = new String[1];
			cc = new String[1];
			to[0] = createdbyEmail;
			cc[0] = userEmail;
		}
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			subject = "Rate card has been recycled by"+desg+"for"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
			mailContent = "<p>Dear "+createdByname+"</p><br>"
					+ "<p>&emsp;&emsp;"+username+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
					
					+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				
					+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				
					+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
					+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
					+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
					//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+"<br>"
					+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
					+ "<p>&emsp;&emsp;"+UserComments+"</p>"
					+ "<br><br>Regards,<br>"+username+"."
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
		}
		break;
		}
	
	
	
	
/*	
	case 22: {
		AppLoger.APPLOGGER.info("Executing case 22");
		AppLoger.APPLOGGER.info("When Delivery Head Recycle");
		tempId = "Temp22";
		desc = "Case 22: Rate Card is Recycled";
		to = new String[1];
		to[0] = deliverySpEmailId;
		cc = new String[2];
		cc[0] = deliveryEmailId;
		cc[1]=deliveryHeadEmail;
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate card has been recycled by Delivery Head for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+deliverySpName+"</p><br>"
				+ "<p>&emsp;&emsp;"+deliveryHeadName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
			
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
			
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
				+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+deliveryHeadName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
				break;
	}*/

	case 23: {
		AppLoger.APPLOGGER.info("Executing case 23");
		AppLoger.APPLOGGER.info("When GFT Recycles");
		tempId = "Temp23";
		desc = "Case 23: Rate Card is Recycled";
		to = new String[1];
		to[0]=deliverySpEmailId;
		cc = new String[3];
		cc[0] =  deliveryEmailId;
//		cc[1]="Syndev@TATOSMAIL.COM";
		cc[1]="gft_costing@eviden.com";
		cc[2]=deliveryHeadEmail;
//		cc[3]="Syndev@TATOSMAIL.COM";
		//cc[3]="vishal.arora@eviden.com";
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by GFT for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
		    mailContent = "<p>Dear "+deliverySpName+"</p><br>"
				+ "<p>&emsp;&emsp; GFT Team has recycled the rate card for  <strong>"+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+"<br>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br> GFT Team."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;
	case 24: {
		AppLoger.APPLOGGER.info("Executing case 24");
		AppLoger.APPLOGGER.info("When  CDO Recycles");
		tempId = "Temp24";
		desc = "Case 24: Rate Card is Recycled";
		to = new String[1];
		to[0] = deliverySpEmailId;
		cc = new String[4];
		cc[0] = CDOEmail.toString();
		cc[1] =  deliveryEmailId;
//		cc[2]="Syndev@TATOSMAIL.COM";
		cc[2]="gft_costing@eviden.com";
		cc[3]=deliveryHeadEmail;
//		cc[4]="Syndev@TATOSMAIL.COM";
		//cc[4]="vishal.arora@eviden.com";
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by CDO for  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+deliverySpName+"</p>"
				+ "<p>&emsp;&emsp;"+CDOName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+CDOName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;
	case 25: {
		AppLoger.APPLOGGER.info("Executing case 19");
		AppLoger.APPLOGGER.info("When BUH Recycles");
		tempId = "Temp20";
		desc = "Case 20: Rate Card is Recycled";
		to = new String[1];
		to[0] = deliverySpEmailId;
		cc = new String[5];
		cc[0] = CDOEmail.toString();
		cc[1] =  deliveryEmailId;
//		cc[2]="Syndev@TATOSMAIL.COM";
		cc[2]="gft_costing@eviden.com";
		cc[3]=deliveryHeadEmail;
		cc[4] =  BuHeadEmail;
//		cc[5]="Syndev@TATOSMAIL.COM";
		//cc[5]="vishal.arora@eviden.com";
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by BUH for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+deliverySpName+"</p>"
				+ "<p>&emsp;&emsp;"+BuHeadName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
						//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+BuHeadName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;

	case 26 : {
		AppLoger.APPLOGGER.info("Executing case 20");
		AppLoger.APPLOGGER.info("When CEO Recycles");
		tempId = "Temp20";
		desc = "Case 20: Rate Card is Recycled";
		to = new String[1];
		to[0] = deliverySpEmailId;
		cc = new String[6];
		cc[0] = CDOEmail.toString();
		cc[1] =  deliveryEmailId;
//		cc[2]="Syndev@TATOSMAIL.COM";
		cc[2]="gft_costing@eviden.com";
		cc[3]=deliveryHeadEmail;
		cc[4] =  BuHeadEmail;
		cc[5] = CEOEmail;
//		cc[6]="Syndev@TATOSMAIL.COM";
		//cc[6]="vishal.arora@eviden.com";
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			subject = "Rate Card has been recycled by CEO for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
			
		mailContent = "<p>Dear "+deliverySpName+"</p>"
				+ "<p>&emsp;&emsp;"+CEOName+" has recycled the rate card for  <strong> "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong>.</p>"
				
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+" " +tcvValue+"</p>"
				+ "<p>&emsp;&emsp;Request you to revisit the rate card.</p>"
				+ "<p>&emsp;&emsp;Reason for recycling the rate card: "+RejectedComments+"</p>"
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+CEOName+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;
	
	case 28 : {
		AppLoger.APPLOGGER.info("Executing case 20");
		AppLoger.APPLOGGER.info("When CEO Approves");
		tempId = "Temp28";
		int itkpo=0;
		
		for(Object object : rateCardDetails)
		{
		RateCardDetails matrixData = (RateCardDetails)object;
		itkpo=matrixData.getIsItKpo();
			}
		desc = "Case 28: Rate Card is Approved";
		String username="";
	  	String userEmail="";
	  	String desg="";
		switch (Status) {
  		case "Delivery Head" : 
  			username=deliveryHeadName;
  			userEmail=deliveryHeadEmail;
  			desg="Delivery Head";
  			break;
  		case "GFT" : 
  			username="GFT team";
//  			userEmail="Syndev@TATOSMAIL.COM";
  			userEmail="gft_costing@eviden.com";
  			desg="GFT";
  			break;
  		case "CDO" : 
  			username=CDOName;
  			userEmail=CDOEmail;	
  			desg="CDO";
  			break;
  		/*case "BUH" : 
  			username=BuHeadName;
  			userEmail=BuHeadEmail;
  			
  			break;*/
  		case "BU Head" : 
  		case "BU_Head" :
  		case "BUH" : 
  			if(itkpo==0||itkpo==1){
  				username=BuHeadName;
	  			userEmail=BuHeadEmail;
	  			desg="BU Head";
	  			break;
  			}
  			else if(itkpo==2){
  				username=JVCEO;
	  			userEmail=JVCEOEmail;
	  			desg="BU Head";
	  			break;
  			}
  		case "CEO" : 
  			username=CEOName;
  			userEmail=CEOEmail;
  			desg="CEO";
  			break;
  		case "Level 1" : 
  			username=level1;
  			userEmail=level1mail;
  			desg=level1;
  			break;
  		case"Level 2":
  			username=level2;
  			userEmail=level2Email;
  			desg=level2;
  			break;
  			
  		default : 
  			break;
		}
		to = new String[1];
		to[0] = createdbyEmail.toString();
	
		/*to[3] = CDOEmail.toString();
		to[4] = BuHeadEmail.toString();
		to[5]= 	CEOEmail.toString();*/
		
		if(Status.equals("GFT")){
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	cc = new String[2];
		        		cc[0] = deliverySpEmailId;
		        		cc[1] = userEmail.toString();
		        		//cc[2] = "vishal.arora@eviden.com";
		        		cc = Arrays.copyOf(cc, cc.length + sizeofTo);
		    			int j=0;
		    			for(int i=2;i<cc.length;i++){
		    				cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
		    				j++;
		    		      }
		            }else{
		            	cc = new String[1];
		        		cc[0] = userEmail.toString();
		        		//cc[1] = "vishal.arora@eviden.com";
		        		cc = Arrays.copyOf(cc, cc.length + sizeofTo);
		    			int j=0;
		    			for(int i=1;i<cc.length;i++){
		    				cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
		    				j++;
		    		      }
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
						
					}
		  	}
		  	else
	        {
		  		cc = new String[1];
	    		cc[0] = userEmail.toString();
	    		//cc[1] = "vishal.arora@eviden.com";
	    		cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
	        }
		}
		else{
		  	if(deliverySpEmailId!=null|| !(deliverySpEmailId.isEmpty())){
		  		AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
			  	 if(!deliverySpEmailId.equals("")){
		            	deliverySpEmailId=deliverySpEmailId;
		            	cc = new String[2];
		        		cc[0] = deliverySpEmailId;
		        		cc[1] = userEmail.toString();
		            }else{
		            	cc = new String[1];
		        		cc[0] = userEmail.toString();
						AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
						
					}
		  	}
		  	else
	        {
		  		cc = new String[1];
	    		cc[0] = userEmail.toString();
				AppLoger.APPLOGGER.info("Mail Generated when send for approval"+deliverySpEmailId);
	        }
		}
		for(RateCardDetails rateData : rateCardDetails) {
			String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
			String startDate = dateFormat(rateData.getRcStartDate());
			String endDate=dateFormat(rateData.getRcEndDate());
			// // BigDecimal tcvValue = BigDecimal.valueOf(rateData.getTvc());
			 
			 subject = desg+" has approved a rate card "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
	            mailContent = "<p>Dear "+createdByname+"</p>"
				+ "<p>&emsp;&emsp;"+username+" has approved a rate card for  "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" and the approval process is now completed.</p>"
				
				+ "<p>&emsp;&emsp;Rate Card is applicable for the period <strong>"+startDate+"</strong> to <strong>"+endDate+"</strong>.</p>"
			
				+ "<p>&emsp;&emsp;Estimated annual revenue from this account is "+currencyName+ " "  +tcvValue+"</p>"
				
				+ "<p>&emsp;&emsp;<u><strong> Rate Card Summary: -</strong> </u></p>"
				+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
				+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
				+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rateData.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
				+"<tr><td><b>Rate Card ID</b></td><td> "+ rateData.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
				+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated/Manual/Hybrid</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
				+"<tr><td><b>Region wise utilization</b></td>"
				+ "<td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int i=0;i<utilMix.size();i++)
				{
					mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
				+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
				+ "<td><table class='tblNoBorder'><tbody>";
				for(int j=0;j<blendedvalues.size();j++)
				{
					mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
				+ "<table class='tblNoBorder'><tbody>";
				for(int k=0;k<blendedvalues.size();k++)
				{
					mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
				}
				mailContent+=	"</tbody></table></td></tr>"
				+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
				+"<tr><td><b>VR %</b></td><td>"+rateData.getVolumeDiscount()+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
				+"<tr><td><b>Consolidated PM Post VR % and Incl. risk</b><td>"+pMinclRiskVd+ "</td></tr>"
				//+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<u><strong> Approval Matrix: -</strong> </u></p>"
				+"<table> <tbody>"
				+"<tr><td><b> Approver Designation </b></td><td><b> Name </b></td><td><b> Status </b></td></tr>"
				+"<tr>";
				for(int k=0;k<ApproverList.size();k++)
				{
					mailContent+= "<tr><td>"+ ApproverList.get(k)+ "</td>" + " <td>"+ ApproverName .get(k)+ "</td>"+" <td>"+ ApproverStatus  .get(k)+"</tr>"; 
				}
				mailContent+=	"</tr>"
				+"</tbody></table>"
				+"<br>"
				+ "<p>&emsp;&emsp;<strong><u><i> Comments </i></u></strong>.</p>"
				+"<br>"
				+ "<p>&emsp;&emsp;"+UserComments+"</p>"
				+ "<br><br>Regards,<br>"+username+"."
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";


		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);
			}
	}
	break;
	
		
		}
		try {

			AppLoger.APPLOGGER.info(" triggerMail started");
			MailTrigger.sendMail(emailDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppLoger.APPLOGGER.info(" triggerMail : EXIT :");
	}

	// Formatting Date Format
	private static String dateFormat(String serviceFromDate) {
		String date = serviceFromDate;
		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date21 = null;
		try {
			date21 = simpleDataFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		simpleDataFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDataFormat.format(date21);

	}

}
