package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Programa {
	
	
	public static void main(String[] args) {
		
		SellerDao sellerDao =  DaoFactory.createSellerDao();
		
		System.out.println("=== Teste número 1: findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
		System.out.println("=== Teste número 2: findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list =  sellerDao.findAll(department);
		for(Seller sl : list) {
			System.out.println(sl);
		}
		List<Seller> listSeller =  sellerDao.findAll();
		for(Seller sl : listSeller) {
			System.out.println(sl);
		}
		
		System.out.println("=== Teste número 4: seller insert ===");
		Seller seller2 = new Seller(null, "Aglayrton", "aglayrton@gmail.com", new Date(), 4000.0, department);
		//sellerDao.insert(seller2);
		System.out.println("Inserted! New id='"+seller2.getId());
		
		System.out.println("=== Teste número 5: seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Maria");
		sellerDao.update(seller);
		System.out.println("=== Update complete ===");
	}
}
