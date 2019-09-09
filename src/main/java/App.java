import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newhero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all heroes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero.

        //get: process a form to update a hero.

        //post: process a new hero form.
        post("/heroes/new", (request, response) -> { //URL to add a new hero on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("hero");
            Hero newHero = new Hero(content);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero.

        //get: delete all heroes.

    }

}
