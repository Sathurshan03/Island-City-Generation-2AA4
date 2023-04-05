package ca.mcmaster.cas.se2aa4.a3.island.Elements;

import java.awt.Color;

public enum VertexElement {
    WATER(new Color(255,255,255,0)),
    RIVER(new Color(15,94,196, 254)),
    LAND(new Color(255, 255, 255, 0)),
    CITY(new Color(212, 199, 88, 254)), 
    ROAD(new Color(0, 0, 0, 254));

    public final Color vertexColor;
    private VertexElement(Color color){
        this.vertexColor = color;
    }

    public Color getColor(){
        return vertexColor;
    }

}
