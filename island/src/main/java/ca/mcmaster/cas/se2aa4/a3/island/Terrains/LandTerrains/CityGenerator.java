package ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.GraphGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import graphadt.GraphComponents.Node;

public class CityGenerator implements Generator {
    private int numCities;
    private List<TileVertex> cityVerticies; 
    private List<TileVertex> landVerticies; 
    
    public CityGenerator (int numCities, List<TileVertex> landVerticies){
        this.numCities = numCities; 
        cityVerticies = new ArrayList<>();
        this.landVerticies = landVerticies; 
    }

    public void generate(){
        //Randomly choose Land TileVerticies to be cities
        cityVerticies = new ArrayList<>();

        int counter = 0;
        int pos;
        TileVertex city;
        while (counter < numCities && !landVerticies.isEmpty()){
            pos = IslandCommandLineReader.randomGenerator.getNextInteger(0,landVerticies.size());
            city = landVerticies.remove(pos);
            cityVerticies.add(city);
            counter ++;
        }

        //Set the city property to the tilevertex 
        for (TileVertex cityVertex: cityVerticies){
            cityVertex.setVertexCity();
        }

        System.out.println(cityVerticies.size() +  " cities were created.");
    }

    public Node getCityNode(GraphGenerator graphGenerator, TileVertex vertex){
        return graphGenerator.returnNode(vertex);
    }

    public List<TileVertex> getCityVerticies(){
        return cityVerticies;
    }

}
