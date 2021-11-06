package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class practice_10_3_2 {

	public static void main(String[] args) {

		// コマンドラインからの入力
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員番号を入力してください：");
		int code = scanner.nextInt();
		System.out.print("名前を入力してください：");
		String name = scanner.nextLine();
		System.out.print("年齢を入力してください：");
		int age = scanner.nextInt();
		System.out.print("電話番号を入力してください：");
		String tel = scanner.nextLine();

		// データベース接続情報の設定
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";

		// 実行するSQL
		String sql = "INSERT INTO emp VALUES (?, ?, ?, ?)";

		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					 PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// プレースホルダの設定
				pstmt.setInt(1, code);
				pstmt.setString(2, name);
				pstmt.setInt(3, age);
				pstmt.setString(4, tel);
				// SQLの実行
				int row = pstmt.executeUpdate();
				// 実行完了メッセージの表示
				System.out.println(row + "件、データベースに追加しました。");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
