/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrm.apirestful;
import spark.Spark.*;
import static spark.Spark.*;
import static com.nrm.apirestful.JsonUtil.*;
import spark.Spark;

/**
 *
 * @author Nicolás
 */
public class LigaController {

    public LigaController(final Liga liga) {

        //Enable	CORS
        Spark.options("/*", (request, response)
                -> {
            String accessControlRequestHeaders = request.headers("Access-ControlRequest-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-ControlRequest-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }
            return "OK";
        });
        Spark.before((request, response)
                -> {
            response.header("Access-Control-Allow-Origin", "*");
        });

        get("/users", (req, res) -> liga.getEquipos(), json());
        after((req, res) -> {
            res.type("application/json");
        });
        get("/users/:id", (req, res) -> {
            String id = req.params(":id");
            Equipo user = liga.getEquipo(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, json());
        delete("/users/:id", (req, res) -> {
            String id = req.params(":id");
            Equipo user = liga.deleteEquipo(id);

            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, json());

        post("/users", (req, res) -> liga.createEquipo(
                req.queryParams("id"),
                req.queryParams("nombre"),
                req.queryParams("fundacion"),
                req.queryParams("titulos")
        ), json());

        put("/users/:id", (req, res) -> liga.setEquipo(
                req.queryParams("id"),
                req.queryParams("nombre"),
                req.queryParams("fundacion"),
                req.queryParams("titulos")
        ), json());

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }

}
