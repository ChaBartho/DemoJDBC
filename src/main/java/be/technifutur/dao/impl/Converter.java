package be.technifutur.dao.impl;

import be.technifutur.models.Customer;
import be.technifutur.models.Product;
import be.technifutur.models.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {

    static <T> T convert(ResultSet rs, Class<T> resultClass) throws SQLException {
        if( resultClass.equals( Product.class ) )
            return (T)convertProduct(rs);
        else if (resultClass.equals( Supplier.class ))
            return (T)convertSupplier(rs);
        else if (resultClass.equals( Customer.class ))
            return (T)convertCustomer(rs);

        throw new IllegalArgumentException("resultClass invalid");
    }



    //Méthode de conversion pour Product
    private static Product convertProduct(ResultSet rs) throws SQLException {
        Product prod = new Product();

        prod.setId( rs.getLong( "product_id" ) );
        prod.setName( rs.getString( "product_name" ) );
        prod.setStock( rs.getInt( "units_in_stock" ) );
        prod.setUnitPrice( rs.getDouble( "unit_price" ) );
        prod.setQttPerUnit( rs.getString( "quantity_per_unit" ) );
        prod.setDiscontinued( rs.getBoolean( "discontinued" ) );

        if( rs.getObject("supplier_id") != null )
            prod.setSupplier( convertSupplier( rs ) );

        return prod;
    }



    //Méthode de conversion pour Supplier
    private static Supplier convertSupplier(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();

        supplier.setId( rs.getLong("supplier_id") );
        supplier.setCity( rs.getString("city") );
        supplier.setAddress( rs.getString("address") );
        supplier.setPostalCode( rs.getString("postal_code") );
        supplier.setCity( rs.getString("city") );
        supplier.setCountry( rs.getString("country") );
        supplier.setRegion( rs.getString("region") );
        supplier.setCompany( rs.getString("company_name") );
        supplier.setContactTitle( rs.getString("contact_title") );
        supplier.setContactName( rs.getString("contact_name") );
        supplier.setPhone( rs.getString("phone") );
        supplier.setFax( rs.getString("fax") );
        supplier.setHomepage( rs.getString("homepage") );

        return supplier;
    }



    //Méthode de conversion pour Customer
    private static Customer convertCustomer(ResultSet rs) throws SQLException{
        Customer cust = new Customer();

        cust.setId( rs.getString("customer_id") );
        cust.setCompany( rs.getString("company"));
        cust.setContactName( rs.getString("contact_name"));
        cust.setContactTitle( rs.getString("contact_title"));
        cust.setAddress( rs.getString("address"));
        cust.setCity( rs.getString("city"));
        cust.setRegion( rs.getString("region"));
        cust.setPostalCode( rs.getString("postal_code"));
        cust.setCountry( rs.getString("country"));
        cust.setPhone( rs.getString("phone"));
        cust.setFax( rs.getString("fax"));

        return cust;
    }






}
