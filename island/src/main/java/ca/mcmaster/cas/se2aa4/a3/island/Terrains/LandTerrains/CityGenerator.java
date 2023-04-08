package ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;

public class CityGenerator implements Generator {
    private int numCities;
    private List<IslandNode> cityIslandNode; 
    private List<TileVertex> landVerticies; 
    
    public CityGenerator (int numCities, List<TileVertex> landVerticies){
        this.numCities = numCities; 
        cityIslandNode = new ArrayList<>();
        this.landVerticies = landVerticies; 
    }

    public void generate(){
        //Randomly choose Land TileVerticies to be cities
        cityIslandNode = new ArrayList<>();

        int counter = 0;
        int pos;
        TileVertex city;
        IslandNode cityNode;
        while (counter < numCities && !landVerticies.isEmpty()){
            pos = IslandCommandLineReader.randomGenerator.getNextInteger(0,landVerticies.size());
            city = landVerticies.remove(pos);
            cityNode = city.getIslandNode();
            cityIslandNode.add(cityNode);
            counter ++;
        }

        //Set the city property to the tilevertex 
        for (IslandNode node: cityIslandNode){
            node.setCity();
        }

        System.out.println(cityIslandNode.size() +  " cities were created.");
    }

    public List<IslandNode> getCityVerticies(){
        return cityIslandNode;
    }

}
