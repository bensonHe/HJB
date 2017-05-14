package com.hjb.spider.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HourseInfo {
	private String hourseId;
	private String title;
	// 现在价格
	private BigDecimal totalPrice;
	private BigDecimal unitPrice;
	private String position;
	private String xiaoqu;
	private String huxing;
	private String size;
	private String turned;
	private String remark;
	private String lift;
	private String image;
	private String href;
	private Date createTime;
	private Date updateTime;

	// 第一次价格
	private BigDecimal firstTotalPrice;

	// 上一次价格
	private BigDecimal lastTotalPrice;

	private BigDecimal changePrice;

	public BigDecimal getFirstTotalPrice() {
		return firstTotalPrice;
	}

	public void setFirstTotalPrice(BigDecimal firstTotalPrice) {
		this.firstTotalPrice = firstTotalPrice;
	}

	public BigDecimal getLastTotalPrice() {
		return lastTotalPrice;
	}

	public void setLastTotalPrice(BigDecimal lastTotalPrice) {
		this.lastTotalPrice = lastTotalPrice;
	}

	public BigDecimal getChangePrice() {
		return changePrice;
	}

	public void setChangePrice(BigDecimal changePrice) {
		this.changePrice = changePrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getHourseId() {
		return hourseId;
	}

	public void setHourseId(String hourseId) {
		this.hourseId = hourseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHuxing() {
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTurned() {
		return turned;
	}

	public void setTurned(String turned) {
		this.turned = turned;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLift() {
		return lift;
	}

	public void setLift(String lift) {
		this.lift = lift;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
