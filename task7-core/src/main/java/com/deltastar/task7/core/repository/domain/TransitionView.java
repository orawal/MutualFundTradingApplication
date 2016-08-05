package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.CCConstants;
import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NamedQueries({
        @NamedQuery(name = "findTransitionViewByCustomerId", query = "SELECT tv FROM TransitionView tv where tv.customerId = :p_customerId order by tv.createdAt desc"),
        @NamedQuery(name = "findTransitionViewByStatus", query = "SELECT tv FROM TransitionView tv where tv.status = :p_status order by tv.status asc, tv.createdAt asc"),
        @NamedQuery(name = "findAllTransitionView", query = "SELECT tv FROM TransitionView tv order by tv.createdAt desc")
})
public class TransitionView {
    public static final String BUY = "Buy";
    public static final String SELL = "Sell";
    public static final String DEPOSIT = "Deposit";
    public static final String WITH_DRAW = "WithDraw";
    public static final String TO_BE_EXECUTED = "To be executed";
    private int id;
    private int customerId;
    private long fundId;
    private long positionId;
    private Timestamp executeDate;
    private long shares;
    private Byte type;
    private long amount;
    private Byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fundName;
    private String displayName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "fundId", nullable = true)
    public long getFundId() {
        return fundId;
    }

    public void setFundId(long fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "positionId", nullable = true)
    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "executeDate", nullable = true)
    public Timestamp getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Timestamp executeDate) {
        this.executeDate = executeDate;
    }

    @Basic
    @Column(name = "shares", nullable = false)
    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    @Transient
    public String getPriceForDisplay() {
        return Util.formatCash(shares);
    }

    @Transient
    public String getAmountForDisplay() {
        return Util.cashFormatForDisplay(amount);
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "fundName", nullable = true, length = 256)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "displayName", nullable = true, length = 64)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    @Transient
    public String getContent() {
        return !Util.isEmpty(fundName) ? ((type == CCConstants.TRAN_TYPE_BUY_FUND ? BUY : SELL) + " " + fundName)
                : (type == CCConstants.TRAN_TYPE_DEPOSIT_CHECK ? DEPOSIT : WITH_DRAW);
    }

    @Transient
    public String getTime() {
        String result = Util.formatTime(getExecuteDate());
        if (Util.isEmpty(result)) {
            result = TO_BE_EXECUTED;
        }
        return result;
    }

}
