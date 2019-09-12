import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oHeroDao;
import models.Hero;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        // setup a production database and frontend DAO Sql2oTaskDao
        String connectionString = "jdbc:h2:~/hero_squads.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);

        //get: show all heroes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            ArrayList<Hero> heroes = Hero.getAll();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a new hero form.
        post("/heroes/new", (request, response) -> { //URL to add a new hero on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("hero");
            Hero newHero = new Hero(content);
            heroDao.add(newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes.
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero.
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
//            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find hero
            Hero foundHero = heroDao.findById(idOfHeroToFind);
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual hero page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero.
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
//            Hero editHero = Hero.findById(idOfHeroToEdit);
            Hero editHero = heroDao.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero.
        post("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
//            Hero editHero = Hero.findById(idOfHeroToEdit);
//            editHero.update(newName);
            heroDao.update(idOfHeroToEdit, newName);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: delete an individual hero.
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            Hero deleteHero = Hero.findById(idOfHeroToDelete); //use it to find post
//            deleteHero.deleteHero();
            heroDao.deleteById(idOfHeroToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
