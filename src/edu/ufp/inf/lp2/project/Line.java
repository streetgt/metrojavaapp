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

import edu.princeton.cs.introcs.Out;
import java.awt.Color;
import java.util.ArrayList;

public class Line {

    private final int SIZE = 8;
    
    private int id;
    
    private String name;
    
    private Color color;

    private ArrayList<Station> stations = new ArrayList<>();
    
    public Line() {
    }

    public Line(int id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Line(int id, String name, Color color, ArrayList<Station> stations) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }
    
    
    public void addStation(Station s) {
        for (Station station : this.stations) {
            if(station.equals(s)) {
                return;
            }
        }
        this.stations.add(s);
    }
    
    public Station removeStation(Station s) {
        for (Station station : this.stations) {
            if(station.equals(s)) {
                this.stations.remove(station);
                return station;
            }
        }
        return null;
    }
     
    public Station removeStation(int id) {
        for (Station station : this.stations) {
            if(station.getId() == id) {
                this.stations.remove(station);
                return station;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Line{" + "SIZE=" + SIZE + ", id=" + id + ", name=" + name + ", color=" + color + ", stations=" + stations + '}';
    }

    public void saveLine(String path) {
        Out o = new Out(path);
        String color_out = Integer.toString(this.color.getRGB());
        o.println(this.id + ";" + this.name + ";" + color_out + ";" + this.stations.size());
        for (Station s : this.stations) {
            o.println(s.toString());
        }
    }
}
