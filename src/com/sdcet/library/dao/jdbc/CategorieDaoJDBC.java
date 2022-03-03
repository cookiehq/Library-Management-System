package com.sdcet.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sdcet.library.dao.CategorieDao;
import com.sdcet.library.domain.Categories;
import com.sdcet.library.utils.Constans;
import com.sdcet.library.utils.PageBean;


public class CategorieDaoJDBC implements CategorieDao {
	private DataSource dataSource;

	public CategorieDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public List<Categories> findAll() {
		List<Categories> categoriesList = new ArrayList<Categories>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Categories";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);

				Categories categorie = new Categories(id, name, description);

				categoriesList.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取所有图书分类操作失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭ps失败：" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}

		return categoriesList;
	}

	@Override
	public PageBean<Categories> findAllByPage(int page) {
		PageBean<Categories> pageBean = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			connection = dataSource.getConnection();
			int recordCount = -1;
			String sql = "select count(*) from Categories";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 处理结果集
				recordCount = rs.getInt(1);
			}
			
			List<Categories> categoriesList = new ArrayList<Categories>();
			sql = "select top "+Constans.PAGE_SIZE+" * from Categories where id not in "
					+ " ( select top "+ Constans.PAGE_SIZE * (page - 1) +" id from Categories )";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);

				Categories categorie = new Categories(id, name, description);
				categoriesList.add(categorie);
			}
			pageBean = new PageBean<Categories>(page, 12, recordCount, categoriesList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取所有分类(分页)操作失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭ps失败：" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}

		return pageBean;
	}

	@Override
	public void upate(Categories categories) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "update Categories set name=?,description=? where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, categories.getName());
			ps.setString(2, categories.getDescription());
			ps.setInt(3, categories.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改图书分类操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}

		}

	}

	@Override
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			ps = connection.prepareStatement("delete from Categories where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = connection.prepareStatement("update Books set categorieId = 0 where categorieId = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("删除图书分类操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}

		}

	}

	@Override
	public void add(Categories categories) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Categories (id,name,description) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, categories.getId());
			ps.setString(2, categories.getName());
			ps.setString(3, categories.getDescription());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加图书操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}

		}

	}

	@Override
	public Categories findById(int cid) {
		Categories categorie = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Categories where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);

				categorie = new Categories(id, name, description);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据id查找图书操作失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭ps失败：" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}

		return categorie;
	}

	@Override
	public List<Categories> findByName(String fname) {
		List<Categories> categoriesList = new ArrayList<Categories>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Categories where name like ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + fname + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);

				Categories categorie = new Categories(id, name, description);

				categoriesList.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据名称获取图书分类操作失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭ps失败：" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}

		return categoriesList;
	}

}
