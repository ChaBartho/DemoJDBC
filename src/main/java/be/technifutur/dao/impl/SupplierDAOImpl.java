package be.technifutur.dao.impl;
import be.technifutur.ConnectionFactory;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.models.Supplier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet rs = stmt.executeQuery( sql );
        ) {

            List<Supplier> suppliers = new ArrayList<>();

            while( rs.next() )
                suppliers.add( convertSupp(rs) );
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
                ResultSet rs = stmt.executeQuery( sql );
                ){

            if( rs.next() )
                return Optional.of( convertSupp(rs) );
            return Optional.empty();

        }
        catch (SQLException ex){
            throw new RuntimeException("error in data access", ex);
        }
    }

    @Override
    public void update(Long id, Supplier entity) {
    }

    @Override
    public void delete(Long id) {
    }

    private Supplier convertSupp(ResultSet rs) throws SQLException{
        Supplier supp = new Supplier();

        supp.setId( rs.getLong("supplier_id") );
        supp.setCompanyName( rs.getString("company_name"));
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





}
