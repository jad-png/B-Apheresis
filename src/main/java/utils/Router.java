package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Router {
    public static void goTo(HttpServletResponse res, HttpServletRequest req, String path) throws ServletException, IOException {
        String fullPath = "/WEB-INF/" + path + ".jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(fullPath);
        dispatcher.forward(req, res);
    }

    public static void redirect(HttpServletResponse res, HttpServletRequest req, String path) throws ServletException, IOException {
        res.sendRedirect(path);
    }
}
