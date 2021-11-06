package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// データベース接続情報の設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pasword = "himitu";
			// データベース接続オブジェクトを取得：データベースに接続
			Connection conn = DriverManager.getConnection(url, user, pasword);
			// 実行するSQLの設定
			String sql = "SELECT * FROM emp";
			// SQL実行オブジェクトを取得
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQLの実行
			ResultSet rs = pstmt.executeQuery();
			// 結果の取得と表示
			while (rs.next()) {
				System.out.print(rs.getInt("code") + "：");
				System.out.print(rs.getString("name") + "：");
				System.out.print(rs.getInt("age") + "：");
				System.out.println(rs.getString("tel"));
			}
			// リソースの解放
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
