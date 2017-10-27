import dao.DaoException;
import entity.Address;
import dao.impl.AddressImplDao;

import java.util.List;

public class Domain {
    public static void main(String[] args) throws DaoException {
//        Utill utill = new Utill();
//        utill.getConnection();

        AddressImplDao addressImplDao = new AddressImplDao();

        Address address = new Address();
        address.setId(2L);
        address.setCountry("DC 2");
        address.setCity("Gotham City 2");
        address.setStreet("Arkham street 2");
        address.setPostCode("555556");


        try {
//            addressImplDao.add(address);
//            addressImplDao.update(new Address(2L,"new Addres","new Cuty","new Street","new PostCode"));
          //  addressImplDao.remove(address);
           // System.out.println(addressImplDao.getById(1L));


            List<Address> addressList = addressImplDao.getAll();
            for (Address a : addressList) {
                System.out.println(a);
            }
            addressImplDao.close();

        } catch (Exception e) {
            throw new DaoException();
        }


    }
}
