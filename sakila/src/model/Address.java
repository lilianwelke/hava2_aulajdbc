
package model;

import java.sql.Timestamp;

/**
 *
 * @author lw005973
 */
public class Address {
    private int address_id;
    private String address;
    private String address2;
    private String district;
    private City city;
    private String postal_code;
    private String phone;
    private Timestamp last_update;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int adress_id) {
        this.address_id = adress_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
    
}
