package com.wrapper;


public class DmStatisticsDeviceEntity {

	private String productTime;
	private Integer dateType;
	private String dataNum;
	
	public DmStatisticsDeviceEntity(){}
	
	public DmStatisticsDeviceEntity(String productTime, Integer dateType, String dataNum){
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
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	public String getDataNum() {
		return dataNum;
	}
	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}

	
	
}
