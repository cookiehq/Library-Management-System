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
			throw new RuntimeException("��������Դʧ�ܣ�" + e.getMessage());
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
				// ��������
				int count = rs.getInt(1);
				if (count == 1) {
					isSuccess = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�����û����������ѯ����Աʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�رս����ʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
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
				// ��������
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
			throw new RuntimeException("�����û�����ѯ�û�ʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�رս����ʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�"
								+ e.getMessage());
					}
				}

			}
		}
		
		return admin;
	}

}
