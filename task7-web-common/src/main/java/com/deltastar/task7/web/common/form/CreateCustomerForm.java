package com.deltastar.task7.web.common.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Create Customer Form
 * <p>
 * Delta Star Team
 */
public class CreateCustomerForm {
    @NotEmpty(message = "{create.customer.userName.required}")
    private String userName;

    @Size(max = 6, message = "{create.customer.error.password.size}")
    private String password;

    @NotEmpty(message = "{create.customer.firstName.required}")
    private String firstName;

    @NotEmpty(message = "{create.customer.lastName.required}")
    private String lastName;

    @NotEmpty(message = "{create.customer.addressLine1.required}")
    private String addressLine1;

    @NotEmpty(message = "{create.customer.city.required}")
    private String city;

    @NotEmpty(message = "{create.customer.state.required}")
    private String state;

    @NotEmpty(message = "{create.customer.zipcode.required}")
    private String zipcode;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }
}
