package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> users = objectMapper.readValue(Files.readString(
                Paths.get("src/main/resources/users.json")),
                new TypeReference<ArrayList<Map<String, String>>>() { });
        return users.stream().map(user ->
                new User(user.get("firstName"), user.get("lastName"), user.get("id"), user.get("email"))).toList();
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<User> users = getUsers();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("""
                <html>
                    <head>
                    </head>
                    <body>
                        <tr>
                """);

        users.forEach(user -> stringBuilder.append(
                String.format("""
                            <td>%s</td>
                            <td>
                                <a href=/users/%s>%s</a>
                            </td>""", user.getId(), user.getId(), user.getFirstName() + " " + user.getLastName())
        ));

        stringBuilder.append("""
                        </tr>
                    </body>
                </html>""");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(stringBuilder.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        User user = getUsers().stream().filter(value -> value.getId().equals(id)).findAny().orElse(null);
        PrintWriter pw = response.getWriter();
        if (user != null) {
            pw.println(String.format("""
                    <html>
                        <head>
                        </head>
                        <body>
                            <tr>
                                <td>%s</td>
                                <td>%s</td>
                                <td>%s</td>
                                <td>%s</td>
                            </tr>
                        </body>
                    </html>""", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
        } else {
            response.sendError(404, "Not found");
        }
        // END
    }
}
