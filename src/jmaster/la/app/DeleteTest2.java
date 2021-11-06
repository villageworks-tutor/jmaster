package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest2 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報の設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String password = "himitu";
			// データベース接続オブジェクトの取得
			conn = DriverManager.getConnection(url, user, password);
			// 実行するSQLの設定
			String sql = "DELETE FROM emp WHERE code = 4";
			// SQL実行オブジェクトの取得
			pstmt = conn.prepareStatement(sql);
			// SQLの実行
			int row = pstmt.executeUpdate();
			System.out.println(row + "件、レコードを削除しました。");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// リソースの解放
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
