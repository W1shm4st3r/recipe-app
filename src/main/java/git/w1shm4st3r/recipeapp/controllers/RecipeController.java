package git.w1shm4st3r.recipeapp.controllers;

import git.w1shm4st3r.recipeapp.commands.RecipeCommand;
import git.w1shm4st3r.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String showById(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }
}
