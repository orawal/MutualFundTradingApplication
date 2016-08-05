package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by tonythompson on 1/14/16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findCustomerByUserName", query = "SELECT c FROM Customer c where c.userName = :p_userName"),
        @NamedQuery(name = "findCustomerByKeyWords", query = "SELECT c FROM Customer c where c.userName like :p_userName or c.firstName like :p_firstName or c.lastName like :p_lastName"),
        @NamedQuery(name = "findAllCustomer", query = "SELECT c FROM Customer c")
})
public class Customer {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String city;
    private String state;
    private String zipcode;
    private int salt;
    private String password;
    private String addressLine2;
    private long cash;
    private long cashToBeDeposited;
    private long cashToBeChecked;
    private Byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Customer(String userName, String password, String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String zipcode) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Customer(String userName, String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String zipcode) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }


    public Customer() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "firstName", nullable = false, length = 32)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = false, length = 32)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "addressLine1", nullable = false, length = 256)
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 256)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 32)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "zipcode", nullable = false, length = 32)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "salt", nullable = true)
    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "addressLine2", nullable = true, length = 256)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "cash", nullable = false)
    public long getCash() {
        return cash;
    }

    @Transient
    public String getCashForDisplay() {
        return Util.cashFormatForDisplay(cash);
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "cashToBeDeposited", nullable = false)
    public long getCashToBeDeposited() {
        return cashToBeDeposited;
    }

    @Transient
    public String getCashToBeDepositedForDisplay() {
        return Util.cashFormatForDisplay(cashToBeDeposited);
    }

    public void setCashToBeDeposited(long cashToBeDeposited) {
        this.cashToBeDeposited = cashToBeDeposited;
    }

    @Basic
    @Column(name = "cashToBeChecked", nullable = false)
    public long getCashToBeChecked() {
        return cashToBeChecked;
    }

    @Transient
    public String getCashToBeCheckedForDisplay() {
        return Util.cashFormatForDisplay(cashToBeChecked);
    }

    public void setCashToBeChecked(long cashToBeChecked) {
        this.cashToBeChecked = cashToBeChecked;
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


    public boolean checkPassword(String clearPassword) {
        return hash(clearPassword).equals(this.password);
    }

    public void hashPassword() {
        this.salt = newSalt();
        this.password = hash(password);
    }


    private String hash(String clearPassword) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
        }

        String saltString = String.valueOf(salt);

        md.update(saltString.getBytes());
        md.update(clearPassword.getBytes());
        byte[] digestBytes = md.digest();

        // Format the digest as a String
        StringBuffer digestSB = new StringBuffer();
        for (int i = 0; i < digestBytes.length; i++) {
            int lowNibble = digestBytes[i] & 0x0f;
            int highNibble = (digestBytes[i] >> 4) & 0x0f;
            digestSB.append(Integer.toHexString(highNibble));
            digestSB.append(Integer.toHexString(lowNibble));
        }
        String digestStr = digestSB.toString();

        return digestStr;
    }

    private int newSalt() {
        Random random = new Random();
        return random.nextInt(8192) + 1;  // salt cannot be zero, except for uninitialized password
    }
}