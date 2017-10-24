package service;

import dao.DaoException;
import entity.Address;
import org.junit.*;

import java.util.List;


public class AddressServiceDaoTest {




    @Before
    public void beforeClass()  {

    }

    @After
    public void afterClass() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");
        AddressServiceDao addressServiceDao = new AddressServiceDao();
        addressServiceDao.remove(address);
        addressServiceDao.close();
    }


    @Test
    public void add() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        addressServiceDao.add(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressServiceDao.remove(address);

        addressServiceDao.close();
    }

    @Test
    public void getAll() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        addressServiceDao.add(address);
        List<Address> list = addressServiceDao.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        addressServiceDao.remove(address);
        addressServiceDao.close();
    }

    @Test
    public void getById() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        addressServiceDao.add(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressServiceDao.remove(address);
        addressServiceDao.close();
    }

    @Test
    public void update() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        addressServiceDao.add(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getId(), address.getId());
        address.setCity("New City");
        address.setCountry("New Country");
        address.setStreet("new Street");
        address.setPostCode("new PostCode");
        addressServiceDao.update(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressServiceDao.remove(address);
        addressServiceDao.close();
    }

    @Test (expected = DaoException.class)
    public void remove() throws DaoException {
        Address address = new Address(999L, "Test", "Test", "Test", "Test");

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        addressServiceDao.add(address);
        Assert.assertNotNull(addressServiceDao.getById(address.getId()).getId());
        addressServiceDao.remove(address);
        Assert.assertNull(addressServiceDao.getById(address.getId()).getId());
        addressServiceDao.close();
    }

}