import java.util.List;
import java.util.Random;

public class CombatEngine {
    private final GameData data;
    private final DiceSet dice;
    private final Random rnd;
    private final GameView view;

    public CombatEngine(GameData data, GameView view){
        this.data = data;
        this.view = view;
        this.dice = new DiceSet();
        this.rnd = new Random(); 
    }

    public void initialize(){
        List<Knight> activeKnights = data.getActiveKnights();
        if (activeKnights == null || activeKnights.isEmpty()) {
            System.out.println("No active knights found");
            return;
        }
        for (Knight activeKnight : activeKnights) {
            Fortune fortune = data.getRandomFortune();
            activeKnight.setActiveFortune(fortune);
        }
        view.printFortunes(activeKnights);
    }

    public void clear() {
        for(Knight knight: data.getKnights()){
            knight.setActiveFortune(null);
        }
    }
    
    public void runCombat(){}
        //optional;private int doBattle(List<MOB> attackers, List<MOB> defenders){}
}
