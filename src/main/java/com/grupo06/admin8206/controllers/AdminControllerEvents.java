package com.grupo06.admin8206.controllers;

import com.grupo06.admin8206.domains.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

public class AdminControllerEvents {
    @Autowired
    RestTemplate restTemplate;

    //NAVEGACION
    //pagina principal de la pagina
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    //redireccion a formulario añadir evento
    @GetMapping("/showFormEvent")
    public String showFormEvent(Model model) {
        // create model attribute to bind form data
        Event event = new Event();
        model.addAttribute("evento", event);
        return "formNewEvent";
    }

    //CONTROLADORES CRUD

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
        model.addAttribute("usuario", newEvent);
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
}
