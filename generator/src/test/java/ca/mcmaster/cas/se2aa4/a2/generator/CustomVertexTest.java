package ca.mcmaster.cas.se2aa4.a2.generator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class CustomVertexTest {
    
    @Test
    public void vertexIsNotNull() {
        CustomVertex vertex = new CustomVertex(5, 2, 2);
        assertNotNull(vertex.getVertex());
    }

    @Test
    public void testCentroidColour(){
        RegularMesh mesh = new RegularMesh(40, 40, 2, 20);
        for(CustomVertex centroid: mesh.centroids){
            assertEquals((new Color(254,0,0,254)).getRGB(), centroid.getColour().getRGB());
        }
    }
    
}
