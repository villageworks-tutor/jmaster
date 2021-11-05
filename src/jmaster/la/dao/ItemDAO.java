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
			// 実行するSQLを設定
			String sql = "SELECT code, name, price FROM item";
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// SQLの実行結果を結果セットに代入
			rs = pstmt.executeQuery();
			// 結果セットから商品リストにインスタンスを移行
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
				throw new DAOException("リソースの開放に失敗しました。");
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
