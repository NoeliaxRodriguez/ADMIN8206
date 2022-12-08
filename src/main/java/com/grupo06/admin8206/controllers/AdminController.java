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
public class AdminController {

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

    //redireccion a formulario añadir evento
    @GetMapping("/showFormEvent")
    public String showFormEvent(Model model) {
        // create model attribute to bind form data
        Event event = new Event();
        model.addAttribute("evento", event);
        return "formNewEvent";
    }
    //redireccion a formulario update event
    @GetMapping("/showFormForUpdateEvent/{id}")
    public String showFormForUpdateEvent(@PathVariable (value = "id") Integer id, Model model) {
        //llamamos a funcion get event by id
        Event ev = restTemplate.getForObject("http://localhost:10603/restful/events/{id}", Event.class, id);
        model.addAttribute("evento", ev);
        return "updateFormEvent";
    }

    //redireccion a formulario añadir ticket
    @GetMapping("/showFormTicket")
    public String showFormTicket(Model model) {
        // create model attribute to bind form data
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        //debemos pasar una lista con todos los eventos disponibles
        Event[] listaEv = restTemplate.getForObject("http://localhost:10603/restful/events", Event[].class);
        model.addAttribute("eventList", listaEv);
        //tambien le pasamos la lista con los usuarios disponibles
        User[] listaUs = restTemplate.getForObject("http://localhost:10602/restful/users", User[].class);
        model.addAttribute("userList", listaUs);
        return "formNewTicket";
    }
    //redireccion a formulario update event
    @GetMapping("/showFormForUpdateTicket/{ref}")
    public String showFormForUpdateTicket(@PathVariable (value = "ref") Integer ref, Model model) {
        //llamamos a funcion get ticket by nref
        Ticket tic = restTemplate.getForObject("http://localhost:10604/restful/tickets/{ref}", Ticket.class,ref);
        model.addAttribute("ticket", tic);
        return "updateFormTicket";
    }

    //CONTROLADORES CRUD

    //-----------------------------------------------------
    //  USUARIOS
    //-----------------------------------------------------

    //GET USERS
    @RequestMapping(value = "/get-all-users", method = RequestMethod.GET)
    public String returnTodosUsuarios(Model model) {
        User[] listaUs = restTemplate.getForObject("http://localhost:10602/restful/users", User[].class);
        model.addAttribute("userList", listaUs);
        return "viewTodosUsuarios";
    }
    //GET USER BY USERNAME
    @RequestMapping (value = "/get-user/{name}", method = RequestMethod.GET)
    public String returnUsuario(Model model, @PathVariable String name) {
        User us = restTemplate.getForObject("http://localhost:10602/restful/users/{name}", User.class, name);
        model.addAttribute("usuario", us);
        return "viewTodosUsuarios";
    }

    //POST USER
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


    //-----------------------------------------------------
    //  EVENTOS
    //-----------------------------------------------------

    //GET EVENTS
    @RequestMapping(value = "/get-all-events", method = RequestMethod.GET)
    public String returnTodosEventos(Model model) {
        Event[] listaEv = restTemplate.getForObject("http://localhost:10603/restful/events", Event[].class);
        model.addAttribute("eventList", listaEv);
        return "viewTodosEventos";
    }

    //GET EVENTS BY ID
    @RequestMapping (value = "/get-event/{id}", method = RequestMethod.GET)
    public String returnEvento(Model model, @PathVariable Integer id) {
        Event ev = restTemplate.getForObject("http://localhost:10603/restful/events/{id}", Event.class, id);
        model.addAttribute("evento", ev);
        return "viewTodosEventos";
    }

    //POST EVENTO
    @RequestMapping (value = "/post-event", method = RequestMethod.POST)
    public String saveEvento(Model model, @ModelAttribute Event ev) {
        Event newEvent = restTemplate.postForObject("http://localhost:10603/restful/events", ev, Event.class);
        model.addAttribute("evento", newEvent);
        return returnTodosEventos(model);
    }

    //DELETE EVENTO
    @RequestMapping (value = "/delete-event/{id}", method = RequestMethod.POST)
    public String DeleteEvento(Model model, @PathVariable Integer id) {
        restTemplate.delete("http://localhost:10603/restful/events/"+id);
        return returnTodosEventos(model);
    }

    //UPDATE EVENTO
    @RequestMapping (value = "/update-event/{id}", method = RequestMethod.POST)
    public String updateEvento(Model model, @ModelAttribute Event ev, @PathVariable Integer id){
        restTemplate.put("http://localhost:10603/restful/events/{id}", ev, id);
        return returnTodosEventos(model);
    }

    //-----------------------------------------------------
    //  TICKETS
    //-----------------------------------------------------
    //GET TICKETS
    @RequestMapping(value = "/get-all-tickets", method = RequestMethod.GET)
    public String returnTodosTickets(Model model) {
        Ticket[] listaTic = restTemplate.getForObject("http://localhost:10604/restful/tickets", Ticket[].class);
        model.addAttribute("ticketList", listaTic);
        return "viewTodosTickets";
    }

    //GET TICKET BY ID
    @RequestMapping (value = "/get-ticket/{ref}", method = RequestMethod.GET)
    public String returnTicket(Model model, @PathVariable Integer ref) {
        Ticket tic = restTemplate.getForObject("http://localhost:10604/restful/tickets/{id}", Ticket.class, ref);
        model.addAttribute("ticket", tic);
        return "viewTodosTickets";
    }

    //POST TICKET
    @RequestMapping (value = "/post-ticket", method = RequestMethod.POST)
    public String saveTicket(Model model, @ModelAttribute Ticket tic) {
        Ticket newTicket = restTemplate.postForObject("http://localhost:10604/restful/tickets", tic, Ticket.class);
        model.addAttribute("ticket", newTicket);
        return returnTodosTickets(model);
    }

    //DELETE TICKET
    @RequestMapping (value = "/delete-ticket/{ref}", method = RequestMethod.POST)
    public String Deleteticket(Model model, @PathVariable Integer ref) {
        restTemplate.delete("http://localhost:10604/restful/tickets/"+ref);
        return returnTodosTickets(model);
    }

    //UPDATE TICKET
    @RequestMapping (value = "/update-ticket/{ref}", method = RequestMethod.POST)
    public String updateTicket(Model model, @ModelAttribute Ticket tic, @PathVariable Integer ref){
        restTemplate.put("http://localhost:10604/restful/tickets/{ref}", tic, ref);
        return returnTodosTickets(model);
    }
}
