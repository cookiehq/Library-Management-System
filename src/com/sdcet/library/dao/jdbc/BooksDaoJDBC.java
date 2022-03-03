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

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Categories;
import com.sdcet.library.utils.Constans;
import com.sdcet.library.utils.PageBean;

public class BooksDaoJDBC implements BooksDao {
	private DataSource dataSource;

	public BooksDaoJDBC() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public List<Books> findByName(String bname) {
		List<Books> booksList = new ArrayList<Books>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Books, Categories where Books.name like ? and Books.categorieId = Categories.id";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + bname + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);
				int cid = rs.getInt(9);
				String cname = rs.getString(10);
				String description = rs.getString(11);
				
				Categories categories = new Categories(cid, cname, description);

				Books books = new Books(id, name, author, publisher, price, stock, borrows,catalogId);
				books.setCategorie(categories);

				booksList.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据书名查找图书操作失败：" + e.getMessage());
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

		return booksList;
	}

	@Override
	public Books findById(int bid) {
		Books books = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Books where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);

				books = new Books(id, name, author, publisher, price, stock, borrows,catalogId);

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

		return books;
	}

	@Override
	public List<Books> findByCategorieId(int categorieId) {
		List<Books> booksList = new ArrayList<Books>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Books where categorieId = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, categorieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);

				Books books = new Books(id, name, author, publisher, price, stock, borrows);
				booksList.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取指定分类下的所有图书：" + e.getMessage());
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

		return booksList;
	}

	@Override
	public List<Books> findAll() {
		List<Books> booksList = new ArrayList<Books>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Books";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);

				Books books = new Books(id, name, author, publisher, price, stock, borrows);

				booksList.add(books);
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

		return booksList;
	}

	@Override
	public void update(Books books) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "update Books set name=?,author=?,publisher=?,price=?,stock=?"
					+ ",borrows=? ,categorieId = ? where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, books.getName());
			ps.setString(2, books.getAuthor());
			ps.setString(3, books.getPublisher());
			ps.setInt(4, books.getPrice());
			ps.setInt(5, books.getStock());
			ps.setInt(6, books.getBorrows());
			ps.setInt(7, books.getCategorieId());
			ps.setInt(8, books.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改图书操作失败：" + e.getMessage());
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

			String sql = "delete from Books where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除图书操作失败：" + e.getMessage());
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
	public void add(Books books) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into Books (name,author,publisher,price,stock,borrows,categorieId) values(?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, books.getName());
			ps.setString(2, books.getAuthor());
			ps.setString(3, books.getPublisher());
			ps.setInt(4, books.getPrice());
			ps.setInt(5, books.getStock());
			ps.setInt(6, books.getBorrows());
			ps.setInt(7, books.getCategorieId());
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
	public List<Books> findByBorrows() {
		List<Books> booksList = new ArrayList<Books>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select top 12 * from Books order by borrows desc";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);

				Books books = new Books(id, name, author, publisher, price, stock, borrows);

				booksList.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("借阅次数查找图书操作失败：" + e.getMessage());
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

		return booksList;
	}

	@Override
	public PageBean<Books> findAllByPage(int page) {
		PageBean<Books> pageBean = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			int recordCount = -1;
			String sql = "select count(*) from Books";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 处理结果集
				recordCount = rs.getInt(1);
			}

			List<Books> booksList = new ArrayList<Books>();
			sql = "select top "+Constans.PAGE_SIZE+" * from Books , Categories where Books.categorieId = Categories.id "
					+ " and Books.id not in  ( select top "+ Constans.PAGE_SIZE * (page - 1) +" Books.id from Books , Categories "
							+ " where Books.categorieId = Categories.id)";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				int borrows = rs.getInt(7);
				int catalogId = rs.getInt(8);
				int cid = rs.getInt(9);
				String cname = rs.getString(10);
				String description = rs.getString(11);
				
				Categories categories = new Categories(cid, cname, description);

				Books books = new Books(id, name, author, publisher, price, stock, borrows);
				books.setCategorie(categories);
				booksList.add(books);
			}
			pageBean = new PageBean<Books>(page, 12, recordCount, booksList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取指定分类下的所有图书(分页)：" + e.getMessage());
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

}
