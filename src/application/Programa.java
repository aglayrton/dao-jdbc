package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Programa {
	
	
	public static void main(String[] args) {
		/*Department obj = new Department(1, "Books");  
		System.out.println(obj);
		Seller seller = new Seller(1, "Juli�o", "juliao@gmail.com", new Date(), 3000.0, obj);
		System.out.println(seller);*/
		
		//dessa forma o programa n�o conhece a implementa��o, conhece comente a interface
		SellerDao sellerDao =  DaoFactory.createSellerDao();
		System.out.println("=== Teste n�mero 1: findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println("=== Teste n�mero 2: findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list =  sellerDao.findAll(department);
		for(Seller sl : list) {
			System.out.println(sl);
		}
	}
}
