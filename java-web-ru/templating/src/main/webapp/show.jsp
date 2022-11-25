<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                    crossorigin="anonymous">
    </head>
    <body>
        <tr>
            <td>Id: ${user.get("id")}</td></br>
            <td>Full name: ${user.get("firstName").concat(" ").concat(user.get("lastName"))}</td></br>
            <td>Email: ${user.get("email")}</td></br>
            <td><a href='/users/delete?id=${user.get("id")}'>Delete user</a></td></br>
        </tr>
    </body>
</html>
<!-- END -->
