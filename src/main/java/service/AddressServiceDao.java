package service;

import dao.DaoException;
import dao.Utill;
import dao.AddressDAO;
import entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressServiceDao extends Utill implements AddressDAO {

    private Connection connection = getConnection();

    private PreparedStatement psAdd = null;
    private PreparedStatement psGetById = null;
    private PreparedStatement psUpdate = null;
    private PreparedStatement psRemove = null;

    public AddressServiceDao() throws DaoException {
    }



    private PreparedStatement preparedStatementAdd(String sql) throws DaoException {

        try {
            if (psAdd == null) {
                psAdd = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return psAdd;
    }


    private PreparedStatement preparedStatementGetById(String sql) throws DaoException {
        try {
            if (psGetById == null) {
                psGetById = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return psGetById;
    }

    private PreparedStatement preparedStatementUpdate(String sql) throws DaoException {
        try {
            if (psUpdate == null) {
                psUpdate = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return psUpdate;
    }

    private PreparedStatement preparedStatementRemove(String sql) throws DaoException {
        try {
            if (psRemove == null) {
                psRemove = connection.prepareStatement(sql);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return psRemove;
    }


    public void add(Address address) throws DaoException {
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
            throw new DaoException(e);
        }
    }

    public List<Address> getAll() throws DaoException {
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
            resultSet.close();
        } catch (Exception e) {
            throw new DaoException(e);
        }

        return addressList;
    }

    public Address getById(Long id) throws DaoException {
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
            resultSet.close();
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return address;
    }

    public void update(Address address) throws DaoException {

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
            throw new DaoException(e);
        }

    }

    public void remove(Address address) throws DaoException {

        String sql = "DELETE FROM ADDRESS WHERE ID=?";

        preparedStatementRemove(sql);

        try {
            psRemove.setLong(1, address.getId());

            psRemove.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }


    public void close() throws DaoException {
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
        } catch (Exception e) {
            throw new DaoException(e);
        }
        System.out.println("Connection OFF");
    }
}
