package model.dao;

import model.dao.impl.SellerDaoJDBC;

/*A classe vai expor um método que retorna um tipo da interface, mais internamente ela instancia
 * uma implementação, assim não vamos expor a implementação
 * */

public class DaoFactory {
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
