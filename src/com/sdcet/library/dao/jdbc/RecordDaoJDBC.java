package com.sdcet.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sdcet.library.dao.RecordDao;
import com.sdcet.library.domain.Record;
import com.sdcet.library.utils.DateChange;

public class RecordDaoJDBC implements RecordDao {

	private DataSource dataSource;

	public RecordDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public List<Record> findAll() {
		List<Record> recordList = new ArrayList<Record>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Record";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int bookid = rs.getInt(1);
				int userid = rs.getInt(2);
				String ndate = rs.getString(3);
				String mdate = rs.getString(4);
				String state = rs.getString(5);

				Record record = new Record(bookid, userid, ndate, mdate, state);
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取所有图书操作失败：" + e.getMessage());
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

		return recordList;
	}

	@Override
	public void add(int bookid, int userid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ndate = sdf.format(date);
		String mdate = DateChange.subMonth(ndate);
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Record (bookid,userid,ndate,mdate,state) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookid);
			ps.setInt(2, userid);
			ps.setString(3, ndate);
			ps.setString(4, mdate);
			ps.setString(5, "未归还");

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
	public void returnbook(int bookid,int userid) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "update Record set state = '已归还' where bookid = ? and userid = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookid);
			ps.setInt(2, userid);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("还书更新借阅记录操作失败：" + e.getMessage());
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
	public List<Record> soonToExpire() {
		List<Record> recordList = new ArrayList<Record>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String nowdate = sdf.format(date);
		String subdate = DateChange.subDay(nowdate);

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Record where mdate <= ? and mdate >= ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1,subdate);
			ps.setString(2,nowdate);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int bookid = rs.getInt(1);
				int userid = rs.getInt(2);
				String ndate = rs.getString(3);
				String mdate = rs.getString(4);
				String state = rs.getString(5);

				Record record = new Record(bookid, userid, ndate, mdate, state);
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取即将过期的借阅记录操作失败：" + e.getMessage());
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

		return recordList;
	}

	@Override
	public List<Record> overdue() {
		List<Record> recordList = new ArrayList<Record>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String nowdate = sdf.format(date);

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Record where mdate < ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1,nowdate);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int bookid = rs.getInt(1);
				int userid = rs.getInt(2);
				String ndate = rs.getString(3);
				String mdate = rs.getString(4);
				String state = rs.getString(5);

				Record record = new Record(bookid, userid, ndate, mdate, state);
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取过期的借阅记录操作失败：" + e.getMessage());
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

		return recordList;
	}

	@Override
	public List<Record> unexpired() {
		List<Record> recordList = new ArrayList<Record>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String nowdate = sdf.format(date);

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Record where mdate > ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1,nowdate);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int bookid = rs.getInt(1);
				int userid = rs.getInt(2);
				String ndate = rs.getString(3);
				String mdate = rs.getString(4);
				String state = rs.getString(5);

				Record record = new Record(bookid, userid, ndate, mdate, state);
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取未过期的借阅记录操作失败：" + e.getMessage());
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

		return recordList;
	}

	@Override
	public List<Record> findByUserId(int id) {
		List<Record> recordList = new ArrayList<Record>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Record where userid = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int bookid = rs.getInt(1);
				int userid = rs.getInt(2);
				String ndate = rs.getString(3);
				String mdate = rs.getString(4);
				String state = rs.getString(5);

				Record record = new Record(bookid, userid, ndate, mdate, state);
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取某个读者的借阅记录操作失败：" + e.getMessage());
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

		return recordList;
	}

	@Override
	public void addBorrow(int bookid) {
		
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "update Books set borrows = (select borrows from Books where id = ?)+1 where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookid);
			ps.setInt(2, bookid);

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

}
