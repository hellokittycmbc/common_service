package com.fx.bonus.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class IBCommissionAccountDetail extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer ibId;

    private BigDecimal amount;

    private String paymentDate;

    private Integer fromTransactionId;

    private String comment;

    private int isPaid = ((0));

    private BigDecimal volume;

    private BigDecimal ibcommission;

    private Integer source = ((0));

    private int status;

    private Integer mt4account;

    private String settledate;
    
    private Integer isInnerSales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIbId() {
        return ibId;
    }

    public void setIbId(Integer ibId) {
        this.ibId = ibId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate == null ? null : paymentDate.trim();
    }

    public Integer getFromTransactionId() {
        return fromTransactionId;
    }

    public void setFromTransactionId(Integer fromTransactionId) {
        this.fromTransactionId = fromTransactionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getIbcommission() {
        return ibcommission;
    }

    public void setIbcommission(BigDecimal ibcommission) {
        this.ibcommission = ibcommission;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public String getSettledate() {
        return settledate;
    }

    public void setSettledate(String settledate) {
        this.settledate = settledate == null ? null : settledate.trim();
    }

	public Integer getIsInnerSales() {
		return isInnerSales;
	}

	public void setIsInnerSales(Integer isInnerSales) {
		this.isInnerSales = isInnerSales;
	}
    
}