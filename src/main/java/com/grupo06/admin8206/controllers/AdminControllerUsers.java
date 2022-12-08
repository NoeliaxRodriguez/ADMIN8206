package com.grupo06.admin8206.controllers;

import com.grupo06.admin8206.domains.Event;
import com.grupo06.admin8206.domains.Ticket;
import com.grupo06.admin8206.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class AdminControllerUsers {

    @Autowired
    RestTemplate restTemplate;

    //NAVEGACION
    //pagina principal de la pagina
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    //redireccion a formulario añadir user
    @GetMapping("/showFormUser")
    public String showFormUser(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("usuario", user);
        return "formNewUser";
    }

    //redireccion a formulario update user
    @GetMapping("/showFormForUpdate/{userName}")
    public String showFormForUpdate(@PathVariable (value = "userName") String userName, Model model) {
        //llamamos a funcion get by username
        User us = restTemplate.getForObject("http://localhost:10602/restful/users/{userName}", User.class, userName);
        model.addAttribute("usuario", us);
        return "updateFormUser";
    }
    //CONTROLADORES CRUD
    @RequestMapping(value = "/get-all-users", method = RequestMethod.GET)
    public String returnTodosUsuarios(Model model) {
        User[] listaUs = restTemplate.getForObject("http://localhost:10602/restful/users", User[].class);
        model.addAttribute("userList", listaUs);
        return "viewTodosUsuarios";
    }

    @RequestMapping (value = "/get-user/{name}", method = RequestMethod.GET)
    public String returnUsuario(Model model, @PathVariable String name) {
        User us = restTemplate.getForObject("http://localhost:10602/restful/users/{name}", User.class, name);
        model.addAttribute("usuario", us);
        return "viewTodosUsuarios";
    }

    @RequestMapping (value = "/post-user", method = RequestMethod.POST)
    public String saveUser(Model model, @ModelAttribute User us) {
        User newUser = restTemplate.postForObject("http://localhost:10602/restful/users", us, User.class);
        model.addAttribute("usuario", newUser);
        return returnTodosUsuarios(model);
    }

    //DELETE USERS
    @RequestMapping (value = "/delete-user/{userName}", method = RequestMethod.POST)
    public String DeleteUsuario(Model model, @PathVariable String userName) {
        restTemplate.delete("http://localhost:10602/restful/users/"+userName);
        return returnTodosUsuarios(model);
    }

    //UPDATE USERS
    @RequestMapping (value = "/update-user/{userName}", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute User us, @PathVariable String userName){
        restTemplate.put("http://localhost:10602/restful/users/{userName}", us, userName);
        return returnTodosUsuarios(model);
    }

    //GET TICKETS
    @RequestMapping(value = "/get-all-tickets", method = RequestMethod.GET)
    public String returnTodosTickets(Model model) {
        Ticket[] listaTic = restTemplate.getForObject("http://localhost:10604/restful/tickets", Ticket[].class);
        model.addAttribute("userList", listaTic);
        System.out.println(listaTic[0].getNumReferencia());
        return "viewTodosUsuarios";
    }
    
}
