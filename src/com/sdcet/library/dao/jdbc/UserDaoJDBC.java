package com.sdcet.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;

import com.sdcet.library.dao.UserDao;
import com.sdcet.library.domain.Users;
import com.sdcet.library.utils.Constans;

public class UserDaoJDBC implements UserDao {
	private DataSource dataSource;

	public UserDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("��������Դʧ�ܣ�" + e.getMessage());
		}
	}

	@Override
	public void add(Users user) {
		Connection connection = null;
		PreparedStatement ps = null;
		String passwd = user.getPassword();
		passwd = DigestUtils.md5Hex(passwd + Constans.SALT);

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Users (loginName,password,name,gender,phone) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getLoginName());
			ps.setString(2, passwd);
			ps.setString(3, user.getName());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getPhone());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�û�ע�����ʧ�ܣ�" + e.getMessage());
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

	@Override
	public void update(Users user) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		String passwd = user.getPassword();
		passwd = DigestUtils.md5Hex(passwd + Constans.SALT);

		try {
			connection = dataSource.getConnection();

			String sql = "update Users set loginName=?,password=?,name=?,gender=?,phone=? where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getLoginName());
			ps.setString(2, passwd);
			ps.setString(3, user.getName());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getPhone());
			ps.setInt(6, user.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�û���Ϣ�޸Ĳ���ʧ�ܣ�" + e.getMessage());
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

	@Override
	public List<Users> findAll() {
		List<Users> userList = new ArrayList<Users>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Users";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// ��������
				int id = rs.getInt(1);
				String loginName = rs.getString(2);
				String password = rs.getString(3);
				String name = rs.getString(4);
				String gender = rs.getString(5);
				String phone = rs.getString(6);

				Users user = new Users(id, loginName, password, name, gender, phone);
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ͼ�����ʧ�ܣ�" + e.getMessage());
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

		return userList;
	}

	@Override
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "delete from Users where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ɾ���û�����ʧ�ܣ�" + e.getMessage());
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

	@Override
	public boolean hasMatchUser(String name, String password) {
		boolean isSuccess = false;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select count(*) from Users where loginName = ? and password = ?";
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
			throw new RuntimeException("�����û����������ѯ�û�ʧ�ܣ�" + e.getMessage());
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
	public Users findByLoginName(String fname) {
		Users user = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Users where loginName = ?";
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
				
				user = new Users(id, loginName, password, name, gender, phone);
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
		
		return user;
	}

}
