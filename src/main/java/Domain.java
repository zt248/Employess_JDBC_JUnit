import bl.MyException;
import entity.Address;
import service.AddressService;

import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) throws MyException {
//        Utill utill = new Utill();
//        utill.getConnection();

        AddressService addressService = new AddressService();

        Address address = new Address();
        address.setId(2L);
        address.setCountry("DC 2");
        address.setCity("Gotham City 2");
        address.setStreet("Arkham street 2");
        address.setPostCode("555556");


        try {
//            addressService.add(address);
//            addressService.update(new Address(2L,"new Addres","new Cuty","new Street","new PostCode"));
          //  addressService.remove(address);
           // System.out.println(addressService.getById(1L));
            List<Address> addressList = addressService.getAll();
            for (Address a : addressList) {
                System.out.println(a);
            }
            addressService.close();

        } catch (Exception e) {
            throw new MyException();
        }


    }
}
