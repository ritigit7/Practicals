package AD_Practicals;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        // Display shopping cart
        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>Shopping Cart</title></head><body>");
        response.getWriter().println("<h1>Shopping Cart</h1>");
        if (cart != null) {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                response.getWriter().println("<p>" + entry.getKey() + ": " + entry.getValue() + "</p>");
            }
        } else {
            response.getWriter().println("<p>Cart is empty</p>");
        }
        response.getWriter().println("<a href='" + request.getContextPath() + "/index.html'>Back to Home</a>");
        response.getWriter().println("</body></html>");
    }
}
