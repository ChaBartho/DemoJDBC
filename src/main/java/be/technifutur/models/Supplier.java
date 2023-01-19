package be.technifutur.models;

import lombok.Data;

@Data
public class Supplier {

    private long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private String homepage;


}
