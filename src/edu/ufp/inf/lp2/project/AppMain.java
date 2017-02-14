/*
 *
 * Projeto (projeto_lp2_1516) - Linguagens de Programaçao 2
 * 
 * edu.ufp.inf.lp2.project
 * AppMain.java
 *
 * 2015/2016 (c) Universidade Fernando Pessoa
 *
 */
package edu.ufp.inf.lp2.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagocardoso
 */
public class AppMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");
        lines.add("c");
        lines.add("d");
        lines.add("e");
        lines.add("f");
        
        BufferedReader br = null;
        Network n = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network_2s.txt")));
            int V = Integer.parseInt(br.readLine());
            //System.out.println("V = " + V);
            n = new Network(V);
            int E = Integer.parseInt(br.readLine());
            //System.out.println("E = " + E);
            
            String line = br.readLine();
            while(line!= null) {
                
                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                
                double c1 = Double.parseDouble(st.nextToken());
                double c2 = Double.parseDouble(st.nextToken());
                double c3 = Double.parseDouble(st.nextToken());
                
                n.addEdge(new Connection(v,w,c1,c2,c3));
                
                //System.out.println(w + " " + v + " " + c1 + " " + c2 + " " + c3);
                line = br.readLine();
            }
            
            System.out.println(n.toString());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        n.loadPassengers(".//data//passengers.txt");
        n.loadStations(".//data/stations.txt",0);
        n.loadLines(lines);
        
        
        /** Search Methods **/
        n.getPassengersBetweenAge(20, 60);
        n.getPassengersWithName("Tiago");
        
        
        // 54 - Hospital do São João
        // 23 - Povoa do Varzim
        // 67 - D.João II
        // 17 - Trindade
        
        /** Nearst stations **/
        //System.out.println(n.findNearestStation(28106)); // Ponte da Pedra - São João
        System.out.println(n.findNearestStationFromCoordinate(41.174075, -8.649431)); // Via Rápida - Viso
        //System.out.println(n.findNearestStationFromCoordinate(41.515526, -8.779559)); // Biba Ofir - Povoa
        
        /** Lines in Station **/
        /**ArrayList<Line> p_lines = n.getLinesInStation(17);
        System.out.println("Lines in Station - " + n.getStationsST().get(17).getName());
        p_lines.stream().forEach((l) -> {
            System.out.println("\tLine " + l.getName());
        });
        System.out.println(n.getLinesInStation(17));*/
        
        
        n.getStationsInZone("C3");
        
        /** New Connection **/
        n.addEdge(new Connection(59,16,1.16,0.16,0.15)); // marques - lapa
        
        /** Shortest Path's **/
        //System.out.println(n.shortestPath(n, 59, 16));
        //n.shortestPath(n, from, to);
        //n.shortestPath(n, 67, 23);
        //System.out.println(n.calculatePrice(n, 23, 67, Network.TIME));
       
        //n.bellmanFord(n,54);
        //n.bellmanFord2Points(n,59,16);
        
        /** Check is Network is connected **/
        Network.isConnected(n);
      
    }
}
