package ru.aldamm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aldamm.DAO.PersonDAO;
import ru.aldamm.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("people")
public class PeopleControllers {
    private final PersonDAO personDAO;

    public PeopleControllers(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getIndex(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "people/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getId(id));
        return "people/show";
    }

    @GetMapping("new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.insert(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getId(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}