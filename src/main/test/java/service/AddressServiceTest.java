package service;

import bl.MyException;
import entity.Address;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;


public class AddressServiceTest {

    Address address = null;
    AddressService addressService = null;

    @Before
    public void beforeClass() throws MyException {
        address = new Address(999L, "Test", "Test", "Test", "Test");
        addressService = new AddressService();
    }

    @After
    public void afterClass() throws MyException {
        try {
            addressService.close();
        } catch (Exception e) {
            throw new MyException();
        }
    }


    @Test
    public void add() throws MyException {
        addressService.add(address);
        Assert.assertEquals(addressService.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressService.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressService.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressService.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressService.getById(address.getId()).getPostCode(), address.getPostCode());
        addressService.remove(address);
    }

    @Test
    public void getAll() throws MyException {
        addressService.add(address);
        List<Address> list = addressService.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        addressService.remove(address);
    }

    @Test
    public void getById() throws MyException {
        addressService.add(address);
        Assert.assertEquals(addressService.getById(address.getId()).getId(), address.getId());
        Assert.assertEquals(addressService.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressService.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressService.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressService.getById(address.getId()).getPostCode(), address.getPostCode());
        addressService.remove(address);
    }

    @Test
    public void update() throws MyException {
        addressService.add(address);
        Assert.assertEquals(addressService.getById(address.getId()).getId(), address.getId());
        address.setCity("New City");
        address.setCountry("New Country");
        address.setStreet("new Street");
        address.setPostCode("new PostCode");
        addressService.update(address);
        Assert.assertEquals(addressService.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressService.getById(address.getId()).getCountry(), address.getCountry());
        Assert.assertEquals(addressService.getById(address.getId()).getCity(), address.getCity());
        Assert.assertEquals(addressService.getById(address.getId()).getStreet(), address.getStreet());
        Assert.assertEquals(addressService.getById(address.getId()).getPostCode(), address.getPostCode());
        addressService.remove(address);
    }

    @Test (expected = MyException.class)
    public void remove() throws MyException {
        addressService.add(address);
        Assert.assertNotNull(addressService.getById(address.getId()).getId());
        addressService.remove(address);
        Assert.assertNull(addressService.getById(address.getId()).getId());
    }

}