package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.GraphGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.CityGraphs.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.CityGenerator;
import graphadt.GraphComponents.Edge;
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
        CityGenerator cityGenerator = new CityGenerator(numCities, landVerticies);
        cityGenerator.generate();
        List<TileVertex> cityVerticies = cityGenerator.getCityVerticies();

        GraphGenerator graphGenerator = new GraphGenerator(verticesInfoList, segmentInfoList);
        graphGenerator.generate();

        ShortestPathFinder shortestPathFinder = new ShortestPathFinder(graphGenerator.getGraph());
        Queue<Edge> path = null;
        try{
            path = shortestPathFinder.findPath(cityGenerator.getCityNode(graphGenerator, cityVerticies.get(0)), cityGenerator.getCityNode(graphGenerator, cityVerticies.get(4)));
        }
        catch(Exception e){

        }

        while (!path.isEmpty()){
            Edge g = path.poll();
            graphGenerator.islandEdgeSetRoad(g.getID());
        }
        
    }
}
