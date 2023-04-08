package ca.mcmaster.cas.se2aa4.a3.island.CityCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.IslandNode;
import graphadt.GraphComponents.Node;

public class ConvertCityNodes {
    private Set<Node> cityNodes;

    public ConvertCityNodes(List<IslandNode> cityIslandNode){
        cityNodes = new HashSet<>();

        //convert Island Node to Nodes
        for (IslandNode islandNode: cityIslandNode){
            cityNodes.add(islandNode.getNode());
        }
    }

    public Set<Node> getCityNodes(){
        return cityNodes; 
    }
}
