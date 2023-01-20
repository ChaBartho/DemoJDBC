package be.technifutur.models;

import lombok.Data;

@Data
public class Product {

    private long id;
    private String name;
    private String qttPerUnit;
    private double unitPrice;
    private int stock;
    private boolean discontinued;
    private Supplier supplier;


}
