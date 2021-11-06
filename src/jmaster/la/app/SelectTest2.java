package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest2 {

	public static void main(String[] args) {
		// 年齢取得
		Scanner scanner = new Scanner(System.in);
		System.out.print("年齢を入力してください：");
		int age = scanner.nextInt();
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
			String sql = "SELECT * FROM emp WHERE age >= ?";
			// SQL実行オブジェクトを取得
			PreparedStatement pstmt= conn.prepareStatement(sql);
			// プレースホルダを設定
			pstmt.setInt(1, age);
			// SQLを実行
			ResultSet rs = pstmt.executeQuery();
			// 結果の取得と表示
			while (rs.next()) {
				System.out.print(rs.getInt("code") + "：");
				System.out.print(rs.getString("name") + "：");
				System.out.print(rs.getInt("age") + "：");
				System.out.println(rs.getString("tel"));
			}
			// データベース接続庵連オブジェクトの解放
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
