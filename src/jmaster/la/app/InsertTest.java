package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertTest {

	public static void main(String[] args) {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報文字列を設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String password = "himitu";
			// データベースへの接続
			Connection conn = DriverManager.getConnection(url, user, password);
			// 実行するSQLの設定
			String sql = "INSERT INTO emp (code, name, age, tel) VALUES (8, '近藤', 29, '09-9999-99999')";
			// SQL実行オブジェクトを取得
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQLの実行
			int row = pstmt.executeUpdate();
			System.out.println(row + "件、データベースに追加しました。");
			// リソースの解放
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
