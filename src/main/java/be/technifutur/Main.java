package be.technifutur;

import be.technifutur.dao.CustomerDAO;
import be.technifutur.dao.ProductDAO;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.dao.impl.CustomerDAOImpl;
import be.technifutur.dao.impl.ProductDAOImpl;
import be.technifutur.dao.impl.SupplierDAOImpl;
import be.technifutur.models.Customer;
import be.technifutur.models.Product;
import be.technifutur.models.Supplier;

import java.util.List;

public class Main {
    public static void main(String[] args){


        ProductDAO proDao = new ProductDAOImpl();
        List<Product> products = proDao.getAll();
        products.forEach(System.out::println);

        System.out.println("---------------------------------------------");

        SupplierDAO suppDao = new SupplierDAOImpl();
        List<Supplier> suppliers = suppDao.getAll();
        suppliers.forEach(System.out::println);

        System.out.println("---------------------------------------------");




    }
}