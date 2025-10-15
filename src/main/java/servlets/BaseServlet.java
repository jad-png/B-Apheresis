package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class BaseServlet  extends HttpServlet {

    protected String getAction(HttpServletRequest req) {
        String action = req.getParameter("action");
        return (action == null || action.isEmpty()) ? "list" : action;
    }

    protected boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header);
    }
}
