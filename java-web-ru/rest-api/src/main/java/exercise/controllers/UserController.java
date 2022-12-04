package exercise.controllers;

import io.ebeaninternal.server.util.Str;
import io.javalin.core.validation.BodyValidator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser().findList();
        ctx.json(users);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser().id.eq(Long.parseLong(id)).findOne();
        if (user != null) {
            ctx.json(user);
        }
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User newUser = ctx.bodyValidator(User.class)
                .check(user -> user.getFirstName() != null && !user.getFirstName().isEmpty(),
                        "First name can't be empty")
                .check(user -> user.getLastName() != null && !user.getLastName().isEmpty(),
                        "Last name can't be empty")
                .check(user -> user.getEmail() != null && !user.getEmail().isEmpty() && EmailValidator
                                .getInstance()
                                .isValid(user.getEmail()),
                        "Wrong email format")
                .check(user -> user.getPassword() != null && user.getPassword().length() >= 4 && StringUtils
                                .isNumeric(user.getPassword()),
                        "Password should be numeric with more than 4 digits").get();
        newUser.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser().id.eq(Long.parseLong(id)).findOne();
        if (user != null) {
            user.delete();
        }
        // END
    };
}
