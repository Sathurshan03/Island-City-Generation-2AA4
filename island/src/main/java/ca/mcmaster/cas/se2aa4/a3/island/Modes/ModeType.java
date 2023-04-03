package ca.mcmaster.cas.se2aa4.a3.island.Modes;

public enum ModeType {
    SANDBOX, REGULAR, HEATMAP, URBAN;

    public String toString(){
        switch(this){
        case SANDBOX :
            return "sandbox";
        case REGULAR :
            return "regular";
        case HEATMAP:
            return "heatmap";
        case URBAN:
            return "urban";
        }
        return null;
    }
}
