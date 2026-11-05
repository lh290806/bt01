package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import murach.business.User;

public class UserDB {
    public static int insert(User user) {
        // 1. URL kết nối (Xóa phần ?user=... ở đuôi đi)
        String dbUrl = "jdbc:postgresql://aws-0-ap-southeast-2.pooler.supabase.com:5432/postgres";

        // 2. Tên đăng nhập (Lấy chính xác đoạn mã Supabase cấp cho bạn)
        String dbUser = "postgres.isktlhjoxvaetezlxkpx";

        // 3. Mật khẩu thật của bạn (Bỏ dấu ngoặc vuông đi)
        String dbPass = "kCZ52PLFcZb7GtKD";

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