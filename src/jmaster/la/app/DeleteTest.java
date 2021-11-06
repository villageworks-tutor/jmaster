package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {

	public static void main(String[] args) {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報の設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String password = "himitu";
			// データベース接続オブジェクトの取得
			Connection conn = DriverManager.getConnection(url, user, password);
			// 実行するSQLの設定
			String sql = "DELETE FROM emp WHERE code = 4";
			// SQL実行オブジェクトの取得
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQLの実行
			int row = pstmt.executeUpdate();
			System.out.println(row + "件、レコードを削除しました。");
			// データベース接続関連オブジェクトの解放
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
