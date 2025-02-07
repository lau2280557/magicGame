import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public abstract class GameData {
    protected final List<Knight> activeKnights;
    protected final List<Fortune> fortunes; 
    protected final List<Knight> knights;
    private static final int MAX_ACTIVE = 4;
    protected final List<MOB> monsters;
    private static final Random random = new Random();
    
    public GameData(){
        activeKnights = new ArrayList<>();
        fortunes = new ArrayList<>();
        knights = new ArrayList<>();
        monsters = new ArrayList<>();
    }
    protected Knight findKnight(String nameOrId, List<Knight> list){ 
        for (Knight knight : list){
            if (knight.getName().toLowerCase().contains(nameOrId.toLowerCase())){
                return knight;
            }
            if (knight.getId().toString().equals(nameOrId)){
                return knight;
            }
        }
        return null;
    }
    
    public Knight getActive(String nameOrId){
        return findKnight(nameOrId, activeKnights);
    }
    
    public List<Knight> getActiveKnights(){
        return activeKnights; 
    }
    
    public Knight getKnight(String nameOrId){
        return findKnight(nameOrId, knights);
    }
    
    public List<Knight> getKnights(){
        return knights;
    }
    
    public Fortune getRandomFortune(){
        int r = random.nextInt(fortunes.size());
        return fortunes.get(r);
    }
    
    public List<MOB> getRandomMonsters(){
        ArrayList<MOB> newmob = new ArrayList<>();
        int maxMonsters = monsters.size();
        if (maxMonsters > activeKnights.size()){
            maxMonsters = activeKnights.size();
        }
        for (int i = 0; i < maxMonsters; i++){
            int m = random.nextInt(monsters.size());
                newmob.add(monsters.get(m));
            }
        return newmob;
        }
    
    
    public List<MOB> getRandomMonsters(int number){
        ArrayList<MOB> scarymob = new ArrayList<>(); 
        for (int i = 0; i < number; i++) {
            int scary = random.nextInt(monsters.size());
            scarymob.add(monsters.get(scary));
        }
        return scarymob;
    }
    
    public void removeActive(Knight kt){
        if (!activeKnights.isEmpty()) {
            activeKnights.remove(kt);
            kt.resetDamage();
        }
    }
    
    public boolean setActive(Knight kt){
        if (activeKnights.size() < MAX_ACTIVE){
            activeKnights.add(kt);
            return true;
        } else {
            return false;
        }
    }

    public abstract void save(String filename); 
   
}
