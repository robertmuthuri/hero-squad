import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
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
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //get: show all heroes
        //get: show all heroes in all squads and show all squads
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            ArrayList<Hero> heroes = Hero.getAll();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual squad and heroes it contains
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfSquadToFind = Integer.parseInt(request.params("id")); //new
            Squad foundSquad =  squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes", allHeroesBySquad);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new squad
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll(); //refresh list of links for navbar
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new squad
        post("/squads", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Squad newSquad = new Squad(name);
            squadDao.add(newSquad);
            model.put("squads", squadDao.getAll());
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all squads and all heroes
        // /squads/delete

        //get: delete all heroes.
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get a specific squad (and the heroes it contains)
         get("/squads/:id", (req, res) -> {
             Map<String, Object> model = new HashMap<>();
             int idOfSquadToFind = Integer.parseInt(req.params("id")); //new
             Squad foundSquad = squadDao.findById(idOfSquadToFind);
             model.put("squad", foundSquad);
             List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
             model.put("heroes", allHeroesBySquad);
             model.put("squads", squadDao.getAll()); //refresh list of links for navbar
             return new ModelAndView(model, "squad-detail.hbs");
         }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        // squads/:id/edit

        //get: delete a squad and the heroes it contains
        // /squads/:id/delete

        //get: delete an individual hero.
//        get("/heroes/:id/delete", (req, res) -> {
        get("/squads/:squad_id/heroes/:hero_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("hero_id")); //pull id - must match route segment
//            Hero deleteHero = Hero.findById(idOfHeroToDelete); //use it to find post
//            deleteHero.deleteHero();
            heroDao.deleteById(idOfHeroToDelete);
            return new ModelAndView(model, "success.hbs");
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
//            Hero newHero = new Hero(content);
            Hero newHero = new Hero(content, 1); //ignore the hardcoded squadId for now
            heroDao.add(newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual hero.
        //get: show an individual hero that is nested within a squad.
//        get("/heroes/:id", (req, res) -> {
        get("/squads/:squad_id/heroes/:hero_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params("hero_id")); //pull id - must match route segment
//            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find hero
            Hero foundHero = heroDao.findById(idOfHeroToFind); // use the id to find the hero
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
        post("/heroes/:id/update", (req, res) -> {  //URL to update hero on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
//            Hero editHero = Hero.findById(idOfHeroToEdit);
//            editHero.update(newName);
//            heroDao.update(idOfHeroToEdit, newName);
            heroDao.update(idOfHeroToEdit, newName, 1); //ignore the hardcoded squadId for now
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
