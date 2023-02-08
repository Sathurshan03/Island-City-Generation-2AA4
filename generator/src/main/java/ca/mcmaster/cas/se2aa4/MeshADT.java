package ca.mcmaster.cas.se2aa4;

import java.util.ArrayList;
import java.util.List;

public class MeshADT {

    List<Object> mesh;

    public MeshADT(){
        mesh = new ArrayList<>();
    }

    public int addPolygon(Object polygon){
        mesh.add(polygon);
        return mesh.indexOf(polygon);
    }
    
}
