package com.sdcet.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sdcet.library.dao.CodeDao;
import com.sdcet.library.domain.Code;
import com.sdcet.library.utils.Constans;
import com.sdcet.library.utils.DateChange;
import com.sdcet.library.utils.RandomRange;

public class CodeDaoJDBC implements CodeDao {
	
	private DataSource dataSource;

	public CodeDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public void destroy() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String nowdate = sdf.format(date);
		
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "delete from Books where mdate > ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1,nowdate);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("销毁过期借阅码操作失败：" + e.getMessage());
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
	public Code add(int bookid, int userid) {
		Code code = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ndate = sdf.format(date);
		String mdate = DateChange.subDay2(ndate);
		
		String id = RandomRange.randomHexString(Constans.RANDOM_SIZE);
		
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Code (id,bookid,userid,ndate,mdate) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, bookid);
			ps.setInt(3, userid);
			ps.setString(4, ndate);
			ps.setString(5, mdate);
			
			code = new Code(id, bookid, userid, ndate, mdate);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加借阅码操作失败：" + e.getMessage());
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
		return code;
	}

	@Override
	public boolean hasMatchCode(String id) {
		boolean isSuccess = false;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select count(*) from Code where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
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
			throw new RuntimeException("根据用户名和密码查询用户失败：" + e.getMessage());
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

}
