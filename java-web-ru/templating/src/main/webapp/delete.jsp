<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <title>Delete</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                    crossorigin="anonymous">
    </head>
    <body>
        <tr>
            <td>Are you sure you want to delete this user?</td></br>
            <td>Id: ${user.get("id")}</td></br>
            <td>Full name: ${user.get("firstName").concat(" ").concat(user.get("lastName"))}</td></br>
            <td>
                <form action='/users/delete?id=${user.get("id")}' method="post">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
    </body>
</html>
<!-- END -->
