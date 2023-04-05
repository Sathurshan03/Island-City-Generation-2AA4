package ca.mcmaster.cas.se2aa4.a3.island.Elements;

import java.awt.Color;

public enum SegmentElement {
    REGULAR(new Color(250, 250, 250, 0)),
    River(new Color(15,94,196, 254)), 
    ROAD(new Color(0,0,0,255));

    private Color segmentColor;
    private SegmentElement(Color color){
        this.segmentColor = color;
    }

    public Color getColor(){
        return segmentColor;
    }
}
