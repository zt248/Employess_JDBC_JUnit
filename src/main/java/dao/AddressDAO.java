package dao;

import bl.MyException;
import entity.Address;

import java.util.List;

public interface AddressDAO {

    //create
    void add(Address address) throws MyException;

    //read
    List<Address> getAll() throws MyException;

    Address getById(Long id) throws MyException;

    //update
    void update(Address address) throws MyException;

    //delete

    void remove(Address address) throws MyException;

}
