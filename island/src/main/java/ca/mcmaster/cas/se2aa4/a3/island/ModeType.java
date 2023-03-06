package ca.mcmaster.cas.se2aa4.a3.island;

public enum ModeType {
    SANDBOX;

    public String toString(){
        switch(this){
        case SANDBOX :
            return "sandbox";
        }
        return null;
    }
}
