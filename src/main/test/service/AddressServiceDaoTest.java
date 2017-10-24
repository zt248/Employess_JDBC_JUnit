package service;

import dao.DaoException;
import entity.Address;
import org.junit.*;

import java.util.List;


public class AddressServiceDaoTest {

    Address address;
    AddressServiceDao addressServiceDao;

    @Before
    public void beforeClass() throws DaoException {
        address = new Address(999L, "Test", "Test", "Test", "Test");
        addressServiceDao = new AddressServiceDao();
    }

    @After
    public void afterClass() throws DaoException {
        try {
            addressServiceDao.remove(address);
            addressServiceDao.close();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @Test
    public void add() throws DaoException {
        addressServiceDao.add(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressServiceDao.remove(address);
    }

    @Test
    public void getAll() throws DaoException {
        addressServiceDao.add(address);
        List<Address> list = addressServiceDao.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        addressServiceDao.remove(address);
    }

    @Test
    public void getById() throws DaoException {
        addressServiceDao.add(address);
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressServiceDao.getById(address.getId()).getPostCode(), address.getPostCode());
        addressServiceDao.remove(address);
    }

    @Test
    public void update() throws DaoException {
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
    }

    @Test (expected = DaoException.class)
    public void remove() throws DaoException {
        addressServiceDao.add(address);
        Assert.assertNotNull(addressServiceDao.getById(address.getId()).getId());
        addressServiceDao.remove(address);
        Assert.assertNull(addressServiceDao.getById(address.getId()).getId());
    }

}