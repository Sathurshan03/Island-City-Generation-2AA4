package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.ConvertCityNodes;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.GraphGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.CentralNodeFinder;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.CitySetter;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.CityGenerator;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;

public class Urban extends Regular{
    private int numCities;
    private List<TileVertex> landVerticies; 
    public Urban (String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxRivers, SoilTypes soil, int numAquifers, int numCities) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, maxLakes, maxRivers, soil, numAquifers);
        super.generate();

        this.numCities = numCities;

        //Get all the landtiles
        landVerticies = new ArrayList<>();
        for (TileVertex vertex: verticesInfoList){
            if (vertex.isVertexLand()){
                landVerticies.add(vertex);
            }
        }
    }

    public void generate(){
        //Create an urban island map

        //Create a graph from the mesh
        GraphGenerator graphGenerator = new GraphGenerator(verticesInfoList, segmentInfoList);
        graphGenerator.generate();

        //Generate cities
        CityGenerator cityGenerator = new CityGenerator(numCities, landVerticies);
        cityGenerator.generate();

        ConvertCityNodes convertCityNodes = new ConvertCityNodes(cityGenerator.getCityVerticies());

        //Find the central node
        CentralNodeFinder centralNodefinder = new CentralNodeFinder(graphGenerator.getGraph(), convertCityNodes.getCityNodes());
        CitySetter citySetter = new CitySetter(graphGenerator.getIslandNodeMap(), graphGenerator.getIslandEdgesMap());
        citySetter.setCentralCity(centralNodefinder.getCentralNode());

        //Set the roads of the paths
        citySetter.setRoads(centralNodefinder.getMinimalPath(), convertCityNodes.getCityNodes());    
    }
}
