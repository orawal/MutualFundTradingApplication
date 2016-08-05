package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tonythompson on 1/14/16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findTransitionByCustomerId", query = "SELECT t FROM Transition t where t.customerId = :p_customerId"),
        @NamedQuery(name = "findTransitionByStatus", query = "SELECT t FROM Transition t where t.status = :p_status order by t.status asc, t.createdAt asc"),
        @NamedQuery(name = "findAllTransition", query = "SELECT t FROM Transition t order by t.updatedAt desc")
})
public class Transition {
    private int id;
    private int customerId;
    private int fundId;
    private int positionId;
    private Timestamp executeDate;
    private long shares;
    private byte type;
    private long amount;
    private byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @GeneratedValue
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

    @Transient
    public String getPriceForDisplay() {
        return Util.cashFormatForDisplay(shares);
    }


    @Transient
    public String getAmountForDisplay() {
        return Util.cashFormatForDisplay(amount);
    }

    @Basic
    @Column(name = "fundId", nullable = true)
    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "positionId", nullable = true)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
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

    @Basic
    @Column(name = "type", nullable = false)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
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
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
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
}
