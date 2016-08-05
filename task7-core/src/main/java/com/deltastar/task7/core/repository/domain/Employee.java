package com.deltastar.task7.core.repository.domain;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

@Entity
@NamedQueries({
        @NamedQuery(name = "findEmployeeByUserName", query = "SELECT e FROM Employee e where e.userName = :p_userName"),
        @NamedQuery(name = "findAllEmployee", query = "SELECT e FROM Employee e"),
})
public class Employee {


    private int id;
    private String userName;
    private String password;
    private int salt;
    private String firstName;
    private String lastName;
    private byte type;
    private byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public Employee(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
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
    @Column(name = "userName", nullable = false, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "salt", nullable = true)
    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
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
    @Column(name = "type", nullable = true)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
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
        byte[] digestbytes = md.digest();

        // Format the digest as a String
        StringBuffer digestSB = new StringBuffer();
        for (int i = 0; i < digestbytes.length; i++) {
            int lowNibble = digestbytes[i] & 0x0f;
            int highNibble = (digestbytes[i] >> 4) & 0x0f;
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
