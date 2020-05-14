package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT seller.*,department.Name as DepName\r\n" + 
				"FROM seller INNER JOIN department\r\n" + 
				"ON seller.DepartmentId = department.Id\r\n" + 
				"WHERE seller.Id = ?";
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			//Vamos setar para criar os objetos para depois compara-los
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				/*dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));*/
				
				Seller obj = instantiateDepartment(rs, dep);
				return obj;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
		}
	}

	private Seller instantiateDepartment(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep =  new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll(Department department) {
		String sql = "SELECT seller.*,department.Name as DepName\r\n" + 
				"FROM seller INNER JOIN department\r\n" + 
				"ON seller.DepartmentId = department.Id\r\n" + 
				"WHERE DepartmentId = ? ORDER BY Name";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());
			ResultSet rs = st.executeQuery();
			List<Seller> list = new ArrayList<>(); 
			//uma coleção que te a chave do tipo integer, e valor do tipo departamento
			//uma estrutura map vazia
			Map <Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				//vamos testar se o departamento já existe, vamos passa o id
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sl = instantiateDepartment(rs, dep);
				list.add(sl);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	
}
