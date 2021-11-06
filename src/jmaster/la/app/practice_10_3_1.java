package jmaster.la.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class practice_10_3_1 {

	public static void main(String[] args) {

		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";

		String sql = "SELECt * FROM emp ORDER BY age ASC";

		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					 PreparedStatement pstmt = conn.prepareStatement(sql);
					 ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					System.out.print(rs.getInt("code") + "：");
					System.out.print(rs.getString("name") + "：");
					System.out.print(rs.getInt("age") + "：");
					System.out.println(rs.getString("tel"));
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
