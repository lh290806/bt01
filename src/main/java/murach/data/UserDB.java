package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import murach.business.User;

public class UserDB {
    public static int insert(User user) {
        // Chuyển đổi thành đường dẫn chuẩn JDBC của Java
        // Giữ nguyên chuỗi kết nối và user
        String dbUrl = "jdbc:postgresql://db.isktlhjoxvaetezlxkpx.supabase.co:5432/postgres";
        String dbUser = "postgres";

        // Thay vì: String dbPass = "MatKhauCuaBan123";
        // Hãy đổi thành:
        String dbPass = System.getenv("DB_PASSWORD");

        String query = "INSERT INTO Users (Email, FirstName, LastName) VALUES (?, ?, ?)";
        int result = 0;

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());

                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}