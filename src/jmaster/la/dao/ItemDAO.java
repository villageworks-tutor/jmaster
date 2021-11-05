package jmaster.la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jmaster.la.bean.ItemBean;

public class ItemDAO {

	/**
	 * クラスフィールド
	 */
	private Connection conn;

	/**
	 * コストラクタ
	 * @throws DAOException
	 */
	public ItemDAO() throws DAOException {
		this.getConnection();
	}

	/**
	 * データベースに接続する。
	 * @throws DAOException DAO例外
	 */
	private void getConnection() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報の設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String password = "himitu";
			// データベースへの接続
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}

	/**
	 * 全件検索を実行する。
	 * @return List<ItemBean> ItemBeanクラスのインスタンスのリスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll() throws DAOException {
		// データベース接続関連オブジェクトの初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// データベース接続オブジェクトが開放されている場合は取得し直す
			if (this.conn == null) this.getConnection();
			// 実行するSQLを設定
			String sql = "SELECT code, name, price FROM item ORDER BY code";
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// SQLの実行結果を結果セットに代入
			rs = pstmt.executeQuery();
			// 結果セットから商品リストに入れ替え
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				list.add(bean);
			}
			// 商品リストを返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	/**
	 * 指定した金額以下の価格の商品を取得する。
	 * @param price 価格検索の上限金額
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByPrice(int price) throws DAOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// データベース接続オブジェクトが開放されている場合は取得し直す
			if (this.conn == null) this.getConnection();
			// 実行するSQLを設定
			String sql = "SELECT code, name, price FROM item WHERE price <= ? ORDER BY code";
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// プレースホルダを設定
			pstmt.setInt(1, price);
			// SQLの実行結果を結果セットに代入
			rs= pstmt.executeQuery();
			// 結果セットを商品リストに入れ替え
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	/**
	 * 商品を価格で並べ替える。
	 * @param isAscending 昇順の場合はtrue、降順の場合はfalse
	 * @return List<ItemBean> 商品リスト：値段が同額の商品の場合は商品番号（主キー）の降順となる。
	 * @throws DAOException DAO例外
	 */
	public List<ItemBean> sortByPrice(boolean isAscending) throws DAOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT code, name, price FROM item ORDER BY price ";
		if (isAscending) {
			sql += "ASC";
		} else {
			sql += "DESC";
		}

		try {
			// データベース接続オブジェクトが開放されている場合は取得し直す
			if (this.conn == null) this.getConnection();
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
	/**
	 * 商品を追加する。
	 * @param item 追加する商品のインスタンス
	 * @return 追加に成功した場合は1、それ以外はDAO例外として返却されない。
	 * @throws DAOException
	 */
	public int add(ItemBean item) throws DAOException {
		PreparedStatement pstmt = null;
		String sql = "INSERt INTO item (name, price) VALUES (?, ?)";
		try {
			// データベース接続オブジェクトが開放されている場合は取得し直す
			if (this.conn == null) this.getConnection();
			pstmt = this.conn.prepareStatement(sql);
			// プレースホルダを設定
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			// SQLを実行
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの追加に失敗しました。");
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	public int delete(int code) throws DAOException {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM item WHERE code = ?";
		try {
			if (this.conn == null) this.getConnection();
			pstmt = this.conn.prepareStatement(sql);
			// プレースホルダを設定
			pstmt.setInt(1, code);
			// SQLを実行
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました。");
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}

	}

	/**
	 * データベース接続オブジェクトを解放する。
	 * @throws SQLException SQL例外
	 */
	private void close() throws SQLException {
		if (this.conn != null) {
			this.conn.close();
			this.conn = null;
		}
	}
}
