package com.sdcet.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sdcet.library.dao.AdminDao;
import com.sdcet.library.domain.Admins;

public class AdminDaoJDBC implements AdminDao {

	private DataSource dataSource;

	public AdminDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public boolean hasMatchUser(String name, String password) {
		boolean isSuccess = false;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select count(*) from Admins where loginName = ? and password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 处理结果集
				int count = rs.getInt(1);
				if (count == 1) {
					isSuccess = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据用户名和密码查询管理员失败：" + e.getMessage());
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

		return isSuccess;
	}

	@Override
	public Admins findByLoginName(String fname) {
		Admins admin = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Admins where loginName = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, fname);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String loginName = rs.getString(2);
				String password = rs.getString(3);
				String name = rs.getString(4);
				String gender = rs.getString(5);
				String phone = rs.getString(6);
				
				admin = new Admins(id, loginName, password, name, gender, phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据用户名查询用户失败：" + e.getMessage());
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
						throw new RuntimeException("关闭数据库连接失败："
								+ e.getMessage());
					}
				}

			}
		}
		
		return admin;
	}

}
