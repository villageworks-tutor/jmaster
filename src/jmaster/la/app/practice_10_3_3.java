package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class practice_10_3_3 {

	public static void main(String[] args) {
		// コンソールからの入力の受付
		Scanner scanner = new Scanner(System.in);
		System.out.print("名前の一部を入力してください：");
		String name = scanner.nextLine();

		// データベース接続情報の設定
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";

		// 実行するSQL
		String sql = "SELECT * FROM emp WHERE name LIKE ?";

		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					 PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// プレースホルダの設定：あいまい検索用のワイルドカードはこの局面で追加
				pstmt.setString(1, "%" + name + "%");
				// SQLの実行
				try (ResultSet rs = pstmt.executeQuery();) {
					while (rs.next()) {
						System.out.print(rs.getInt("code") + "：");
						System.out.print(rs.getString("name") + "：");
						System.out.print(rs.getInt("age") + "：");
						System.out.println(rs.getString("tel"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
