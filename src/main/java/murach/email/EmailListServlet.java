package murach.email;

import java.io.IOException;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import murach.business.User;
import murach.data.UserDB;

// Annotation này tự động ánh xạ URL /emailList tới Servlet này mà không cần cấu hình web.xml
//@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        // Lấy hành động (action) từ form
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // Mặc định
        }

        // Xử lý logic dựa trên action
        if (action.equals("join")) {
            url = "/index.html";
        }
        else if (action.equals("add")) {
            // Lấy tham số người dùng nhập từ request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // Tạo đối tượng User và "lưu" vào DB
            User user = new User(firstName, lastName, email);
            //UserDB.insert(user);

            // Gắn đối tượng User vào request để truyền sang file JSP
            request.setAttribute("user", user);

            url = "/thanks.jsp";
        }

        // Chuyển hướng (forward) request và response tới URL tương ứng
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}