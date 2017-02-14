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

public class Station {
    
    private int id;

    private String name;

    private String zone;

    private Coordinate coords;
    
    public Station() {
    }

    public Station(int id, String name, String zone, Coordinate coords) {
        this.id = id;
        this.name = name;
        this.zone = zone;
        this.coords = coords;
    }
    
    public Station(int id, String name, String zone, double lat, double longi) {
        this.id = id;
        this.name = name;
        this.zone = zone;       
        this.coords = new Coordinate(lat,longi);
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Coordinate getCoords() {
        return coords;
    }

    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }
     
    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + ", zone=" + zone + ", coords=" + coords + '}';
    }  
}
