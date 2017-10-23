package service;

import bl.MyException;
import bl.Utill;
import dao.AddressDAO;
import entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressService extends Utill implements AddressDAO {


    private PreparedStatement psAdd = null;
    private PreparedStatement psGetById = null;
    private PreparedStatement psUpdate = null;
    private PreparedStatement psRemove = null;

    public AddressService() throws MyException {
    }


    public PreparedStatement preparedStatementAdd(String sql) {

        try {
            if (psAdd == null) {
                psAdd = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return psAdd;
    }


    private PreparedStatement preparedStatementGetById(String sql) {
        try {
            if (psGetById == null) {
                psGetById = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return psGetById;
    }

    private PreparedStatement preparedStatementUpdate(String sql) {
        try {
            if (psUpdate == null) {
                psUpdate = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return psUpdate;
    }

    private PreparedStatement preparedStatementRemove(String sql) {
        try {
            if (psRemove == null) {
                psRemove = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return psRemove;
    }


    Connection connection = getConnection();

    public void add(Address address) throws MyException {
        String sql = "INSERT INTO ADDRESS (ID, COUNTRY, CITY, STREET, POST_CODE) VALUES (?,?,?,?,?);";
        try {
            preparedStatementAdd(sql);
            psAdd.setLong(1, address.getId());
            psAdd.setString(2, address.getCountry());
            psAdd.setString(3, address.getCity());
            psAdd.setString(4, address.getStreet());
            psAdd.setString(5, address.getPostCode());

            psAdd.executeUpdate();
        } catch (Exception e) {
            throw new MyException();
        }
    }

    public List<Address> getAll() throws MyException {
        List<Address> addressList = new ArrayList<Address>();

        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostCode(resultSet.getString("POST_CODE"));

                addressList.add(address);
            }
        } catch (Exception e) {
            throw new MyException();
        }

        return addressList;
    }

    public Address getById(Long id) throws MyException {
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID = ?";

        Address address = new Address();
        preparedStatementGetById(sql);
        try {
            psGetById.setLong(1, id);
            ResultSet resultSet = psGetById.executeQuery();

            resultSet.next();
            address.setId(resultSet.getLong("ID"));
            address.setCountry(resultSet.getString("COUNTRY"));
            address.setCity(resultSet.getString("CITY"));
            address.setStreet(resultSet.getString("STREET"));
            address.setPostCode(resultSet.getString("POST_CODE"));


        } catch (Exception e) {
            throw new MyException();
        }
        return address;
    }

    public void update(Address address) throws MyException {

        String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";

        preparedStatementUpdate(sql);

        try {
            psUpdate.setString(1, address.getCountry());
            psUpdate.setString(2, address.getCity());
            psUpdate.setString(3, address.getStreet());
            psUpdate.setString(4, address.getPostCode());
            psUpdate.setLong(5, address.getId());

            psUpdate.executeUpdate();

        } catch (Exception e) {
            throw new MyException();
        }

    }

    public void remove(Address address) throws MyException {

        String sql = "DELETE FROM ADDRESS WHERE ID=?";

        preparedStatementRemove(sql);

        try {
            psRemove.setLong(1, address.getId());

            psRemove.executeUpdate();
        } catch (Exception e) {
            throw new MyException();

        }

    }


    public void close() throws MyException {
        try {


        if (psAdd != null) {
            psAdd.close();
        } else if (psGetById != null) {
            psGetById.close();
        } else if (psUpdate != null) {
            psUpdate.close();
        } else if (psRemove != null) {
            psRemove.close();
        } else if (connection != null) {
            connection.close();
        }
        }catch (Exception e){
            throw new MyException();
        }
        System.out.println("Connection OFF");
    }
}
