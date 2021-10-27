package nl.hanze.hive;

public class StratFactory {
    public Strategy getStrat(Hive.Tile strat){
        Strategy strategy = null;
        switch(strat){
            case BEETLE:
                strategy = new BeetleStrategy();
                break;
            case GRASSHOPPER:
                strategy = new GrasshopperStrategy();
                break;
            case QUEEN_BEE:
                strategy = new BeequeenStrategy();
                break;
            case SOLDIER_ANT:
                strategy = new SoldierantStrategy();
                break;
            case SPIDER:
                strategy = new SpiderStrategy();
                break;
            
        }
        return strategy;
    }
}
