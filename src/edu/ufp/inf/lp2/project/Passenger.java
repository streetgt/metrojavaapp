/*
 *
 * Projeto (projeto_lp2_1516) - Linguagens de Programaçao 2
 * 
 * edu.ufp.inf.lp2.project.gui
 * AppMainJFrame.java
 *
 * 2015/2016 (c) Universidade Fernando Pessoa
 *
 */
package edu.ufp.inf.lp2.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Passenger {

    private int id;

    private String name;

    private Date birthdate;

    private double balance;

    private Coordinate position;

    public Passenger() {
    }

    public Passenger(int id, String name, Date birthdate, double balance, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.balance = balance;
        this.position = coordinate;
    }

    public Passenger(int id, String name, Date birthdate, double balance, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.balance = balance;
        this.position = new Coordinate(lat, lon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public void setAge(String newbirth) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = (Date) df.parse(newbirth);
        } catch (ParseException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(date != null ) {
            this.birthdate = date;
        }
    }
    
    public double addBalance(double amount) {
        double current = this.getBalance();
        this.setBalance(current + amount);
        return current;
    }

    public double subtractBalance(double amount) {
        double current = this.getBalance();
        this.setBalance(current - amount);
        return current;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fbirthdate = sdf.format(this.birthdate);
        return "Passenger{" + "id=" + id + ", name=" + name + ", birthdate=" + fbirthdate + ", balance=" + balance + ", position=" + position + '}';
    }
}
