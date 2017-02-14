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

import edu.princeton.cs.algs4.DirectedEdge;

public class Connection extends DirectedEdge {
    
    private double timeWeight;

    private double moneyWeight;

    public Connection(int l1, int l2, double distance, double time, double money) {
        super(l1, l2, distance);
        this.timeWeight = time;
        this.moneyWeight = money;
    }
    
    public Connection(Station s1, Station s2, double distance, double timecost, double money) {
        super(s1.getId(), s2.getId(), distance);
        this.moneyWeight = money;
        this.timeWeight = timecost;
    }
    
    public double getDistance() {
        return super.weight();
    }
    
    public double getTimeWeight() {
        return timeWeight;
    }

    public void setTimeWeight(double timeWeight) {
        this.timeWeight = timeWeight;
    }

    public double getMoneyWeight() {
        return moneyWeight;
    }

    public void setMoneyWeight(double moneyWeight) {
        this.moneyWeight = moneyWeight;
    }
    
    @Override
    public String toString() {
        return super.from() + "->" + super.to() + 
                " - {Distance: " + super.weight() + " , Time: " + this.timeWeight + " , Price: " + this.moneyWeight +"}";
    }

    @Override
    public double weight() {
        switch(Network.selectedCost){
            case Network.MONEY: return this.moneyWeight;
            case Network.TIME: return this.timeWeight;
            default: return super.weight();
        }
    }
}
