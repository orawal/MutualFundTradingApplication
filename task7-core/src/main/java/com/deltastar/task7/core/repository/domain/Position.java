package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tonythompson on 1/14/16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findPositionByCustomerId", query = "SELECT p FROM Position p where p.customerId = :p_customerId"),
        @NamedQuery(name = "findPositionByCustomerIdAndFundId", query = "SELECT p FROM Position p where p.customerId = :p_customerId and p.fundId = :p_fundId and p.status = :p_status")
})
public class Position {
    private int id;
    private int customerId;
    private int fundId;
    private long shares;
    private byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Position(int fundId, int customerId, byte status) {
        this.fundId = fundId;
        this.customerId = customerId;
        this.status = status;
    }

    public Position() {
    }

    @Id
    @GeneratedValue
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
    @Column(name = "fundId", nullable = false)
    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
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
        return Util.cashFormatForDisplay(shares);
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
