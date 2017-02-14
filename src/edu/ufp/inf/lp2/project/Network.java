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

import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.BinaryIn;
import edu.princeton.cs.introcs.BinaryOut;
import static edu.ufp.inf.lp2.project.UsefullFunctions.HaversineInM;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagocardoso
 */
public class Network extends EdgeWeightedDigraph implements DrawInterface {

    private ArrayList<Line> lines = new ArrayList<>();
    
    private RedBlackBST<Integer, Station> stationsST = new RedBlackBST<>();
    
    private RedBlackBST<Integer, Passenger> passengersST = new RedBlackBST<>();
    
    protected static String selectedCost = Network.DISTANCE;
    
    public final static String DISTANCE = "DISTANCE";
    public final static String MONEY = "MONEY";
    public final static String TIME = "TIME";
    
    public static int countStationID;
    
    public Network(int e) {
        super(e);
    }

    /**
     * Get for Stations RedBlack 
     * 
     * @return 
     */
    public RedBlackBST<Integer, Station> getStationsST() {
        return stationsST;
    }
    
    /**
     * Set Stations RedBlack
     * 
     * @param stationsST 
     */
    public void setStationsST(RedBlackBST<Integer, Station> stationsST) {
        this.stationsST = stationsST;
    }
    
    /**
     * Load Stations from a file
     * 
     * @param path
     * @param type 
     */
    public void loadStations(String path, int type) {
        if(type == 0) {
            In in = new In(path);
            while (!in.isEmpty()) {
                String[] split = in.readLine().split(";");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                String zone = split[2];
                double lat = Double.parseDouble(split[3]);
                double longi = Double.parseDouble(split[4]);

                Station aux = new Station(id, name, zone, lat,longi);
                this.stationsST.put(id, aux);
                Network.countStationID = id;
            }
        }
        else {
            BinaryIn bin = new BinaryIn(path);
            while (!bin.isEmpty()) {
                String[] split = bin.readString().split(";");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                String zone = split[2];
                double lat = Double.parseDouble(split[3]);
                double longi = Double.parseDouble(split[4]);

                Station aux = new Station(id, name, zone, lat,longi);
                this.stationsST.put(id, aux);
                Network.countStationID = id;
            }
        
        }
        Network.countStationID++;
    }
    
    /**
     * Print Stations on Console
     */
    public void printStations() {
        for (Integer auxInteger : stationsST.keys()) {
            Station paux = stationsST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }
    
    /**
     * Save Stations to file
     * @param path
     * @param type 
     */
    public void saveStations(String path, int type) {
        if(type == 0 ) {
            Out o = new Out(path);

            for (Integer id : stationsST.keys()) {
                Station aux = stationsST.get(id);
                o.println(
                        aux.getId() + ";" + 
                        aux.getName() + ";" + 
                        aux.getZone() + ";" + 
                        aux.getCoords().getLatitude() + ";" +
                        aux.getCoords().getLongitude());
            }
        } else {
            BinaryOut bo = new BinaryOut(path);
            for (Integer id : stationsST.keys()) {
                Station aux = stationsST.get(id);
                bo.write(
                        aux.getId() + ";" + 
                        aux.getName() + ";" + 
                        aux.getZone() + ";" + 
                        aux.getCoords().getLatitude() + ";" +
                        aux.getCoords().getLongitude()+ "\n");
            }
            
        }
        System.out.println("saveStations(): saved!");
    }
    
    // Passenger
    /**
     * Get Passengers RedBlack
     * 
     * @return 
     */
    public RedBlackBST<Integer, Passenger> getPassengersST() {
        return passengersST;
    }
    
    /**
     * Set Passengers RedBlack
     * @param passengersST 
     */
    public void setPassengersST(RedBlackBST<Integer, Passenger> passengersST) {    
        this.passengersST = passengersST;
    }

    /**
     * Add a passagender to RedBlack
     * @param p 
     */
    public void addPassenger(Passenger p) {
        for (Integer id : passengersST.keys()) {
            Passenger paux = (Passenger) passengersST.get(id);
            if(paux.getId() == p.getId())  {
                System.out.println("addPassenger(): already exists!");
                return; 
            }
        }
        passengersST.put(p.getId(), p);
    }

    /**
     * Search a Passenger in RedBlack using ID
     * 
     * @param id
     * @return 
     */
    public Passenger searchPassenger(int id) {
        for (Integer auxInteger : passengersST.keys()) {
            Passenger paux = passengersST.get(auxInteger);
            if(paux.getId() == id)  {
                return paux;
            }
        }
        return null;
    }

    /**
     * Search a Passenger in RedBlack using Name
     * 
     * @param name
     * @return 
     */
    public Passenger searchPassenger(String name) {
        for (Integer auxInteger : passengersST.keys()) {
            Passenger paux = passengersST.get(auxInteger);
            if(paux.getName().compareTo(name) == 0)  {
                return paux;
            }
        }
        return null;
    }
    
    /**
     * Print Passangers in Console
     */
    public void printPassengers() {
        for (Integer auxInteger : passengersST.keys()) {
            Passenger paux = passengersST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }
    
    /**
     * Load Passengers from a file
     * 
     * @param path 
     */
    public void loadPassengers(String path) {
        In in = new In(path);
        while (!in.isEmpty()) {
            String[] split = in.readLine().split(";");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            String date_string = split[2];
            double lat = Double.parseDouble(split[3]);
            double longi = Double.parseDouble(split[4]);
            Double balance = Double.parseDouble(split[5]);

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date date = null;
            try {
                date = (Date) df.parse(date_string);
            } catch (ParseException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }

            Passenger auxp = new Passenger(id, name, date, balance,lat,longi);
            passengersST.put(id,auxp);
            //System.out.println(auxp.toString());
        }
        System.out.println("savePassengers(): loaded finish!");
    }

    /**
     * Save Passengers to a file
     * 
     * @param path 
     */
    public void savePassengers(String path) {
        Out o = new Out(path);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date;

        for (Integer id : passengersST.keys()) {
            Passenger paux = passengersST.get(id);
            date = sdf.format(paux.getBirthdate());
            o.println(
                    paux.getId() + ";" + 
                    paux.getName() + ";" + 
                    date + ";" + 
                    paux.getPosition().getLatitude() + ";" +
                    paux.getPosition().getLongitude() + ";" +        
                    paux.getBalance());
        }
        
        System.out.println("savePassengers(): saved!");
    }
    
    /**
     * Get passengers between a defined age
     * 
     * @param ai
     * @param af 
     */
    public void getPassengersBetweenAge(int ai, int af) {
        System.out.println("Passengers with Age between " + ai + " and " + af);
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
    
        for (Integer id: this.passengersST.keys()) {
            Passenger p = this.passengersST.get(id);
            c2.setTime(p.getBirthdate());
            int age = c.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
            if(age > ai && age < af) {
                System.out.println("\t"+p.getName());
            }
        }
    }
    
    /**
     * Get passengers with a defined name
     * 
     * @param ai
     * @param af 
     */
    public void getPassengersWithName(String name) {
        System.out.println("Passengers with Name: " + name);
        
        for (Integer id: this.passengersST.keys()) {
            Passenger p = this.passengersST.get(id);
            
            if(p.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("\t ID - " + p.getId() + "; Name - " + p.getName());
            }
        }
    }
    
    /**
     * Get ArrayList lines
     * 
     * @return 
     */
    public ArrayList<Line> getLines() {
        return lines;
    }

    /**
     * Set ArrayList lines
     * 
     * @param lines 
     */
    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
    
    
    /**
     * Load all lines
     * 
     * @param lines 
     */
    public void loadLines(ArrayList<String> lines) {
        for (String file_name : lines) {
            In in = new In(".//data//line_" + file_name + ".txt");
            String[] split = in.readLine().split(";");
            final int id = Integer.parseInt(split[0]);
            final String name = split[1];
            final Color color = new Color(Integer.parseInt(split[2]));
            final int total = Integer.parseInt(split[3]);
            Line l = new Line(id, name, color);
            for (int i = 0; i < total; i++) {
                Station s = this.stationsST.get(in.readInt());
                l.addStation(s);
            }
            System.out.println(l.toString());
            this.lines.add(l);
        }
    }
    
    /**
     * Get Discount from a passenger based on Age
     * 
     * @param user
     * @param price
     * @return double
     */
    public static double calculateDiscount(Passenger user, double price) {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(user.getBirthdate());
        
        int age = c.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        
        if (age >= 0 && age <= 5) { // free for kids
            return 0.0;
        } else if (age >= 55) { // 10% discount
            return price - (0.1 * price);
        }
        return price;
    }
   
    /**
     * Find the nearst station from a Passenger
     * 
     * @param id_person
     * @return 
     */
    public Station findNearestStation(int id_person) {
        
        Passenger p = this.passengersST.get(id_person);
        Station r = null;
        double distance = 99999;
        double aux;
        for (Integer id : this.stationsST.keys()) {
            Station s = this.stationsST.get(id);
            aux = HaversineInM(
                    p.getPosition().getLatitude(),p.getPosition().getLongitude(),
                    s.getCoords().getLatitude(),s.getCoords().getLongitude());
            if(aux < distance) {
                distance = aux;
                r = s;
            }
        }
        return r;
    }
    
    /**
    * Get the nearst station from a destination point
    * 
    * @param lat
    * @param longi
    * @return 
    */
    public Station findNearestStationFromCoordinate(double lat, double longi) {
        
        Station r = null;
        double distance = Double.MAX_VALUE;
        double aux;
        for (Integer id : this.stationsST.keys()) {
            Station s = this.stationsST.get(id);
            aux = HaversineInM(
                    lat,longi,
                    s.getCoords().getLatitude(),s.getCoords().getLongitude());
            if(aux < distance) {
                distance = aux;
                r = s;
            }
        }
        return r;
    }

    /**
     * Get Lines in a Station
     * 
     * @param ids
     */
    public ArrayList<Line> getLinesInStation(int id) {
        ArrayList<Line> lines = new ArrayList<>();
        Station s = this.stationsST.get(id);
        for (Line line : this.lines) {
            for (Station aux : line.getStations()) {
                if(s.equals(aux)) {
                    lines.add(line);
                    //System.out.println("\tLine " + line.getName());
                }
            }
        }
        return lines;
    }
    
    /**
     * Get stations in a zone
     * 
     * @param zone
     */
    public void getStationsInZone(String zone) {
        
        System.out.println("Stations in zone " + zone);
        for (Integer id : this.stationsST.keys()) {
            Station s = this.stationsST.get(id);
            if(s.getZone().compareTo(zone) == 0) {
                System.out.println("\tStation > " + s.getName());
            }
        }
    }
    
    /**
     * Calculate cost between 2 stations, money, distance or time.
     * 
     * @param g
     * @param from
     * @param to
     * @param type
     * @return 
     */
    public String calculatePrice(EdgeWeightedDigraph g, int from, int to, String type) {
        switch(type) {
            case Network.TIME: Network.selectedCost = Network.TIME; break;
            case Network.MONEY: Network.selectedCost = Network.MONEY; break;
            default: Network.selectedCost = Network.DISTANCE;
        }
        
        StringBuilder sb = new StringBuilder();
        DijkstraSP sp = new DijkstraSP(g, from);
        
        Station station_to = this.stationsST.get(from);
        Station station_from = this.stationsST.get(to);
        
        if (sp.hasPathTo(to)) {
            sb.append("Total ");
            switch(type) {
                case Network.TIME: sb.append("time ").append(String.format("%.2f", sp.distTo(to))).append(" minutes"); break;
                case Network.MONEY: sb.append("money ").append(String.format("%.2f", sp.distTo(to))).append("€"); break;
                default: {
                    if(sp.distTo(to) > 1000.0) {
                        sb.append("distance ").append(String.format("%.2f", sp.distTo(to)/1000)).append("km");
                    } else {
                        sb.append("distance ").append(String.format("%.2f", sp.distTo(to))).append("m");
                    }
                }
            }
            sb.append("\n");
        }
        else {
            sb.append(station_from.getName()).append(" to ").append(station_to.getName()).append("\t has no path");
        }
        
        return sb.toString();
    }
    
    /**
     * Calculates the shortest path between 2 stations
     * 
     * @param g
     * @param from
     * @param to 
     */
    public String shortestPath(EdgeWeightedDigraph g, int from, int to) {
        StringBuilder sb = new StringBuilder();
        
        DijkstraSP sp = new DijkstraSP(g, from);
        
        Station station_from = this.stationsST.get(from);
        Station station_to = this.stationsST.get(to);
        
        // print shortest path
        if (sp.hasPathTo(to)) {
            
            sb.append(station_from.getName()).append(" to ").append(station_to.getName()).append(":\n\n");
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    Station s1 = this.stationsST.get(c.from());
                    Station s2 = this.stationsST.get(c.to());
                    sb.append(s1.getName()).append(" > ").append(s2.getName()).append("\n");
                }
            }
            sb.append("\nTotal distance - ").append(String.format("%.2f",sp.distTo(to))).append("km ");
        }
        else {
            sb.append(station_from.getName()).append(" to ").append(station_to.getName()).append("\t has no path");
        }
        
        return sb.toString();
    }
    
    /**
     * Get the higher & lower latitude / longitude
     * 
     * @return 
     */
    public double[] getCoords() {
        double[] r = new double[4];
        double latM = 0.0;
        double latm = 0.0;
        double longM = 0.0;
        double longm = 0.0;
        int i = 0;
        for (Line line : this.lines) {
            i = 0;
            double auxLAT;
            double auxLON;
            for (Station s : line.getStations()) {
                auxLAT = s.getCoords().getLatitude();
                auxLON = s.getCoords().getLongitude();
                if(i == 0) {
                    latm = auxLAT;
                    longM = auxLON;
                }
                
                //Latitude
                if(auxLAT > latM) latM = auxLAT;
                if(auxLAT < latm) latm = auxLAT;
                     
                //Longitude
                if(auxLON > longM) longM = auxLON;
                if(auxLON < longm) longm = auxLON;
                
            }
        }
        r[0] = latM;
        r[1] = latm;
        r[2] = longM;
        r[3] = longm;
        //System.out.println("LatM " + latM + " Latm " + latm + " | LongM " + longM + " Longm " + longm);
        return r;
    }
    
    /**
     * Check if Network is connected
     * 
     * @param g 
     */
    public static void isConnected(EdgeWeightedDigraph g) {
        int s = 0;
        int flag = 0;

        DijkstraSP sp = new DijkstraSP(g, s);

        for (int t = 0; t < g.V(); t++) {
            if (!sp.hasPathTo(t)) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Network is connected!");
        } else {
            System.out.println("Network is not connected!");
        }
    }
    
    /**
     * Save network EdgeWeightDigraph to a file
     * 
     * @param EdgeWeightedDigraph g
     * @param String path
     * @param boolean binary 
     */
    public void saveNetwork(EdgeWeightedDigraph g, String path, boolean binary) {
        if(binary) {
            File f = null;
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                f = new File("data/binary","network.bin");
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);

                oos.writeObject(g);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally{
                try {
                    fos.close();
                    oos.close();

                } catch (IOException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else {
            Out o = new Out(path);
            o.println(Network.countStationID);
            o.println(g.E());
            for (DirectedEdge e: g.edges()) {
                Connection c = (Connection) e;
                o.println(c.from() + " " + c.to() + " " + c.getDistance() + " " + c.getTimeWeight() + " " + c.getMoneyWeight());
            }
        }
    }
    /**
     * Method for draw in DrawPanel 
     * 
     * @param w
     * @param h
     * @param g 
     */
    @Override
    public void draw(int w, int h, Graphics g) {
        double[] coord = this.getCoords();
        
        // LatM 41.3780395 Latm 41.1751836 | LongM -8.5419897 Longm -8.7581311
        
        // (0,0) = (latM,lonm) = (coord[0],coord[3])
        // (w,h) = (latm,lonM) = (coord[1],coord[2])
        for (Line line : this.lines) {
            int size = line.getStations().size();
            for(int i = 0; i < size; i++) {
                Station s = line.getStations().get(i);

                double x_r = s.getCoords().getLongitude();
                double y_r = s.getCoords().getLatitude();
                
                int x_m = UsefullFunctions.map(x_r, coord[3], coord[2], 0, w-100);
                int y_m = UsefullFunctions.map(y_r, coord[0], coord[1], 30, h-100);
                
                g.setColor(line.getColor());
                g.fillOval(x_m, y_m, 6, 6);
                
            }
        }
        
        if(passengersST.size()!= 0) {
            for (Integer auxInteger : passengersST.keys()) {
                Passenger p = passengersST.get(auxInteger);
                int x_m = UsefullFunctions.map(p.getPosition().getLongitude(), coord[3], coord[2], 0, w-100);
                int y_m = UsefullFunctions.map(p.getPosition().getLatitude(), coord[0], coord[1], 30, h-100);

                g.setColor(Color.BLACK);
                g.fillOval(x_m, y_m, 4, 4);
            }
        }
    }
}
