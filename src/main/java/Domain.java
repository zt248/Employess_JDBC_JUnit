import dao.DaoException;
import entity.Address;
import service.AddressServiceDao;

import java.util.List;

public class Domain {
    public static void main(String[] args) throws DaoException {
//        Utill utill = new Utill();
//        utill.getConnection();

        AddressServiceDao addressServiceDao = new AddressServiceDao();

        Address address = new Address();
        address.setId(2L);
        address.setCountry("DC 2");
        address.setCity("Gotham City 2");
        address.setStreet("Arkham street 2");
        address.setPostCode("555556");


        try {
//            addressServiceDao.add(address);
//            addressServiceDao.update(new Address(2L,"new Addres","new Cuty","new Street","new PostCode"));
          //  addressServiceDao.remove(address);
           // System.out.println(addressServiceDao.getById(1L));


            List<Address> addressList = addressServiceDao.getAll();
            for (Address a : addressList) {
                System.out.println(a);
            }
            addressServiceDao.close();

        } catch (Exception e) {
            throw new DaoException();
        }


    }
}
