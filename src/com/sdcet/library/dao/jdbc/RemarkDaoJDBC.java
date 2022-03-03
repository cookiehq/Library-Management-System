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

import com.sdcet.library.dao.RemarkDao;
import com.sdcet.library.domain.Remark;
import com.sdcet.library.domain.Users;

public class RemarkDaoJDBC implements RemarkDao {

	private DataSource dataSource;

	public RemarkDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("��������Դʧ�ܣ�" + e.getMessage());
		}
	}

	@Override
	public List<Remark> findAll() {
		List<Remark> remarkList = new ArrayList<Remark>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Remark order by date desc";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// ��������
				int id = rs.getInt(1);
				String content = rs.getString(2);
				String author = rs.getString(3);
				String date = rs.getString(4);

				Remark remark = new Remark(id, content, author, date);
				remarkList.add(remark);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡȫ�����۲���ʧ�ܣ�" + e.getMessage());
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

		return remarkList;
	}

	@Override
	public void add(Remark remark) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Remark (content,author,date) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, remark.getContent());
			ps.setString(2, remark.getAuthor());
			ps.setString(3, remark.getDate());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("������۲���ʧ�ܣ�" + e.getMessage());
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
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "delete from Remark where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("���ݱ��ɾ�����۲���ʧ�ܣ�" + e.getMessage());
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
	public List<Remark> findAll2() {
		List<Remark> remarkList = new ArrayList<Remark>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select id,left(content,5),author,date from Remark order by date desc";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// ��������
				int id = rs.getInt(1);
				String content = rs.getString(2)+"...";
				String author = rs.getString(3);
				String date = rs.getString(4);

				Remark remark = new Remark(id, content, author, date);
				remarkList.add(remark);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡȫ�����۲���ʧ�ܣ�" + e.getMessage());
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

		return remarkList;
	}

}
