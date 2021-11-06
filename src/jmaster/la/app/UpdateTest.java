package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {

	public static void main(String[] args) {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報の設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String password = "himitu";
			// データベース接続オブジェクトを取得
			Connection conn = DriverManager.getConnection(url, user, password);
			// 実行するSQLの設定
			String sql = "UPDATE emp SET age = 27 WHERE code = 1";
			// SQL実行オブジェクトを取得
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQLを実行
			int row = pstmt.executeUpdate();
			System.out.println(row + "件、レコードを更新しました。");
			// データベース接続関連オブジェクトの解放
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
