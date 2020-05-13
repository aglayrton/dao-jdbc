package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Programa {
	
	
	public static void main(String[] args) {
		/*Department obj = new Department(1, "Books");  
		System.out.println(obj);
		Seller seller = new Seller(1, "Julião", "juliao@gmail.com", new Date(), 3000.0, obj);
		System.out.println(seller);*/
		
		//dessa forma o programa não conhece a implementação, conhece comente a interface
		SellerDao sellerDao =  DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}
}
