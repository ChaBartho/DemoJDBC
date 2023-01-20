package be.technifutur.dao.impl;
import be.technifutur.ConnectionFactory;
import be.technifutur.dao.CustomerDAO;
import be.technifutur.models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public void insert(Customer entity) {
    }


    @Override
    public List<Customer> getAll() {

        String sql = """
                SELECT *
                FROM customers
                """;
        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
        ) {
            List<Customer> customers = new ArrayList<>();
            return customers;

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }

    }

    @Override
    public Optional<Customer> getOne(String id) {

        String sql = """
                SELECT *
                FROM customers
                WHERE customer_id =
                """ + id;
        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
        ) {

            if( rs.next() )
                return Optional.of( Converter.convert(rs, Customer.class)  );
            return Optional.empty();

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }
    }



    @Override
    public boolean update(String id, Customer entity) {
        String sql =  """
                UPDATE customers
                SET
                    company_name = ?
                    ,contact_name = ?
                    ,contact_title = ?
                    ,address = ?
                    ,city = ?
                    ,region = ?
                    ,postal_code = ?
                    ,country = ?
                    ,phone = ?
                    ,fax = ?
                WHERE costumer_id = ?
                """;
        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement( sql );
        ){

            stmt.setString( 1, entity.getCompany() );
            stmt.setString( 2, entity.getContactName() );
            stmt.setString( 3, entity.getContactTitle() );
            stmt.setString( 4, entity.getAddress() );
            stmt.setString( 5, entity.getCity() );
            stmt.setString( 6, entity.getRegion() );
            stmt.setString( 7, entity.getPostalCode() );
            stmt.setString( 8, entity.getCountry() );
            stmt.setString( 9, entity.getPhone() );
            stmt.setString( 10, entity.getFax() );
            stmt.setString( 11, id );

            return stmt.executeUpdate() == 1;
        }
        catch ( SQLException ex ){
            throw new RuntimeException("error in data access", ex);
        }
    }



    @Override
    public void delete(String id) {

        String sql = """
                DELETE FROM customers
                WHERE customer_id = ?
                """+ id;

        String sqlNull= """
                UPDATE customers
                SET customer_id = null
                WHERE customer_id = ?
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement( sql );
                PreparedStatement stmtnull = connection.prepareStatement( sqlNull )
        ){
            stmtnull.setString(1, id);
            stmtnull.executeUpdate();

            stmt.setString(1, id);

            if( stmt.executeUpdate() != 1 )
                throw new RuntimeException("customers not found");
        }
        catch ( SQLException ex ){
            throw new RuntimeException("error in data access", ex);
        }
    }


/*    private Customer convertCust(ResultSet rs) throws SQLException{
        Customer cust = new Customer();

        cust.setId( rs.getLong("id") );
        cust.setCompany( rs.getString("company"));
        cust.setContactName( rs.getString("contactName"));
        cust.setContactTitle( rs.getString("contactTitle"));
        cust.setAddress( rs.getString("address"));
        cust.setCity( rs.getString("city"));
        cust.setRegion( rs.getString("region"));
        cust.setPostalCode( rs.getString("postalCode"));
        cust.setCountry( rs.getString("country"));
        cust.setPhone( rs.getString("phone"));
        cust.setFax( rs.getString("fax"));

        return cust;
    }*/
}
