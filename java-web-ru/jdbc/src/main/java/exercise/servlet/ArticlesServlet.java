package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import exercise.Article;
import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }


    private int getPageNumber(HttpServletRequest request) {
        String pageNumber = request.getParameter("page");
        if (pageNumber == null) {
            return 1;
        }
        return Integer.parseInt(pageNumber);
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Article> articles = new ArrayList<>();
        int pageNumber = getPageNumber(request);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, title FROM articles ORDER BY id LIMIT 10 OFFSET ?");

            preparedStatement.setInt(1, (pageNumber - 1) * 10);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getLong("id"),
                        resultSet.getString("title")));
            }
        }
        catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("articles", articles);
        request.setAttribute("pageNumber", pageNumber);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Article article;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, title, body FROM articles WHERE id = ?");

            preparedStatement.setInt(1, Integer.parseInt(Objects.requireNonNull(getId(request))));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            article = new Article(resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("body"));
        }
        catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
