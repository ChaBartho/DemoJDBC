package be.technifutur.dao.impl;
import be.technifutur.ConnectionFactory;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.models.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public void insert(Supplier entity) {
    }

    @Override
    public List<Supplier> getAll() {
        String sql = """
                SELECT *
                FROM suppliers
                """;

        try (
            Connection co = ConnectionFactory.createConnection();
            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery( sql )
        ) {

            List<Supplier> suppliers = new ArrayList<>();

            while( rs.next() )
                suppliers.add( Converter.convert(rs, Supplier.class) );
            return suppliers;

        }
        catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public Optional<Supplier> getOne(Long id) {

        String sql = """
                SELECT *
                FROM suppliers
                WHERE supplier_id =
                """ + id;
        try(
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql )
                ){

            if( rs.next() )
                return Optional.of( Converter.convert(rs, Supplier.class) );
            return Optional.empty();

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }
    }




    @Override
    public boolean update(Long id, Supplier entity) {

        String sql =  """
                UPDATE suppliers
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
                    ,homepage = ?
                WHERE supplie_id = ?
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
            stmt.setString( 11, entity.getHomepage() );
            stmt.setLong( 12, id );

            return stmt.executeUpdate() == 1;
        }
        catch ( SQLException ex ){
            throw new RuntimeException("error in data access", ex);
        }
    }




    @Override
    public void delete(Long id) {

        String sql = """
                DELETE FROM suppliers
                WHERE supplier_id = ?
                """+ id;

        String sqlNull= """
                UPDATE products
                SET supplier_id = null
                WHERE supplier_id = ?
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement( sql );
                PreparedStatement stmtnull = connection.prepareStatement( sqlNull )
        ){
            stmtnull.setLong(1, id);
            stmtnull.executeUpdate();

            stmt.setLong(1, id);

            if( stmt.executeUpdate() != 1 )
                throw new RuntimeException("supplier not found");
        }
        catch ( SQLException ex ){
            throw new RuntimeException("error in data access", ex);
        }
    }




/*
    private Supplier convertSupp(ResultSet rs) throws SQLException{
        Supplier supp = new Supplier();

        supp.setId( rs.getLong("supplier_id") );
        supp.setCompany( rs.getString("company_name"));
        supp.setContactName( rs.getString("contact_name"));
        supp.setContactTitle( rs.getString("contact_title"));
        supp.setAddress( rs.getString("address"));
        supp.setCity( rs.getString("city"));
        supp.setRegion( rs.getString("region"));
        supp.setPostalCode( rs.getString("postal_code"));
        supp.setCountry( rs.getString("country"));
        supp.setPhone( rs.getString("phone"));
        supp.setFax( rs.getString("fax"));
        supp.setHomepage( rs.getString("homepage"));

        return supp;
    }
*/





}
