package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

/*A classe vai expor um m�todo que retorna um tipo da interface, mais internamente ela instancia
 * uma implementa��o, assim n�o vamos expor a implementa��o
 * */

public class DaoFactory {
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
