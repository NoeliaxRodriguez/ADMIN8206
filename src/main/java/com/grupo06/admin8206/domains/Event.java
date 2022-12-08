package com.grupo06.admin8206.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;


    public Event(String categoria, String ciudad, Date fecha, String nombre, String sala, String imagen){
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.nombre = nombre;
        this.sala = sala;
        this.imagen = imagen;
    }

    private int id_evento;

    private String categoria;

    private String ciudad;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date fecha;

    private String nombre;

    private String sala;

    private String imagen;


    //bi-directional many-to-one association to Ticket
    /*@OneToMany
    private List<Ticket> tickets;
*/

    public Event() {
    }

    public int getId_evento() {
        return this.id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCiudad() {return this.ciudad; }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(fecha);
        }catch (Exception e){
            System.out.println("no hay fecha");
        }
        this.fecha = date1;
    }

    public String getNombre() { return this.nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSala() {return this.sala; }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

  /*  public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Ticket addTicket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setEventoBean(this);

        return ticket;
    }

    public Ticket removeTicket(Ticket ticket) {
        getTickets().remove(ticket);
        ticket.setEventoBean(null);

        return ticket;
    }
    public boolean modifyEvent(String nombre, String categoria, Date date1, String ciudad, String sala, String imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = date1;
        this.ciudad = ciudad;
        this.sala = sala;
        this.imagen = imagen;
        return true;
    }
   */

}

