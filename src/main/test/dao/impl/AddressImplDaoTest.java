package dao.impl;

import dao.DaoException;
import entity.Address;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;


public class AddressImplDaoTest {

//    @After
//    public void afterClass() throws DaoException {
//        Address address = new Address(999L, "Test", "Test", "Test", "Test");
//        AddressImplDao addressImplDao = new AddressImplDao();
//        addressImplDao.remove(address);
//        addressImplDao.close();
//    }


    @Test
    public void add() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressImplDao addressImplDao = new AddressImplDao();

        addressImplDao.add(address);
        Assert.assertEquals(addressImplDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressImplDao.remove(address);

        addressImplDao.close();
    }

    @Test
    public void getAll() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressImplDao addressImplDao = new AddressImplDao();

        addressImplDao.add(address);
        List<Address> list = addressImplDao.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        addressImplDao.remove(address);
        addressImplDao.close();
    }

    @Test
    public void getById() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressImplDao addressImplDao = new AddressImplDao();

        addressImplDao.add(address);
        Assert.assertEquals(addressImplDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressImplDao.remove(address);
        addressImplDao.close();
    }

    @Test
    public void update() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressImplDao addressImplDao = new AddressImplDao();

        addressImplDao.add(address);
        Assert.assertEquals(addressImplDao.getById(address.getId()).getId(), address.getId());
        address.setCity("New City");
        address.setCountry("New Country");
        address.setStreet("new Street");
        address.setPostCode("new PostCode");
        addressImplDao.update(address);
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressImplDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressImplDao.remove(address);
        addressImplDao.close();
    }

    @Test(expected = DaoException.class)
    public void remove() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");
        AddressImplDao addressImplDao = new AddressImplDao();

        try {
            addressImplDao.add(address);
            Assert.assertNotNull(addressImplDao.getById(address.getId()).getId());

            addressImplDao.remove(address);
            addressImplDao.close();
            Assert.assertNull(addressImplDao.getById(address.getId()).getId());
//            Assert.fail();
//            fail();
        } catch (DaoException d) {
            System.out.println("Нет такой записи!");
        }
         finally {
        addressImplDao.close();
        }

    }

}