package com.wrapper;


public class DmStatisticsDeviceEntity {

	private String productTime;
	private String dateType;
	private String dataNum;
	
	public DmStatisticsDeviceEntity(){}
	
	public DmStatisticsDeviceEntity(String productTime, String dateType, String dataNum){
		this.productTime = productTime;
		this.dateType = dateType;
		this.dataNum = dataNum;
	}
	
	public String getProductTime() {
		return productTime;
	}
	public void setProductTime(String productTime) {
		this.productTime = productTime;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getDataNum() {
		return dataNum;
	}
	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}

	
	
}
