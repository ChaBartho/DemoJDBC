package be.technifutur.dao.impl;
import be.technifutur.ConnectionFactory;
import be.technifutur.dao.ProductDAO;
import be.technifutur.models.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public void insert(Product entity) {
    }

    @Override
    public List<Product> getAll() {

        String sql = """
                SELECT *
                FROM products
                """;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
        ) {

            List<Product> products = new ArrayList<>();

            while( rs.next() )
                products.add( Converter.convert(rs, Product.class) );
           return products;

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }
    }




    @Override
    public Optional<Product> getOne(Long id) {

        String sql = """
                SELECT *
                FROM products
                WHERE product_id =
                """ + id;
        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
                ) {

            if( rs.next() )
                return Optional.of( Converter.convert(rs, Product.class) );
            return Optional.empty();

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }
    }



    @Override
    public boolean update(Long id, Product entity) {
        return false;
    }




    @Override
    public void delete(Long id) {

    }



/*
    private Product convert(ResultSet rs) throws SQLException{
        Product prod = new Product();

        prod.setId( rs.getLong("product_id") );
        prod.setName( rs.getString("product_name") );
        prod.setStock( rs.getInt("units_in_stock") );
        prod.setUnitPrice( rs.getDouble("unit_price") );
        prod.setQttPerUnit( rs.getString("quantity_per_unit") );
        prod.setDiscontinued( rs.getBoolean("discontinued") );

        return prod;
    }
*/






}
