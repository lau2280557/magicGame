import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in;

    public ConsoleView(){
        this.in = new Scanner(System.in);
    }

    public void splashScreen() {
        System.out.println("Welcome to the game");
    }

    public void endGame() {
        System.out.println("You have completed the game!");
    }

    public String displayMainMenu() { 
        System.out.print("What would you like to do? ");
        return in.nextLine();
    }
    public void listKnights(List<Knight> knights) {
        if (!knights.isEmpty()){
            for(Knight knight: knights){
                String f = String.format("%d: %s ", knight.getId(), knight.getName());
                System.out.print(f);
            }
        } else {
            System.out.println("No knights to list");
        }
    }


    public void printHelp() {
        System.out.println("Unsure what to do, here are some options:");
        System.out.println("    ls or list all  - listing the knights");    
        System.out.println("    list active  - list the active knights knights only");
        System.out.println("    show name or id - show the knight details card");
        System.out.println("    set active name or id - set knight as active (note: only 4 knights can be active)"); 
        System.out.println("    remove active name or id - remove a knight from active status (heals knight)");
        System.out.println("    explore or adventure or quest - find random monsters to fight");
        System.out.println("    save filename - save the game to the file name (default: saveData.csv)");
        System.out.println("    exit or goodbye - to leave the game");
        System.out.println();
        System.out.println("Game rules: You can have four active knights. As long as they are active, they won't heal,"); 
        System.out.println("but they can gain XP by going on adventures.");
        System.out.println("When you make a knight inactive, they will heal. How many monsters can you defeat"); 
        System.out.println("before, you have to heal?");  
    }
    

    public void knightNotFound() {
        System.out.println("Knight not found!");
    }

    public void showKnight(Knight knight){
        System.out.println(knight);
        System.out.println();
    }

    public void setActiveFailed() {
        System.out.println("Unable to set active knight. Only four can be active at a time.");
        System.out.println();
    }

    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        System.out.println("Our heroes come across the following monsters. Prepare for battle!");
        System.out.printf("%-27s Foes%n", "Knights");

        int count;
        if(monsters.size() > activeKnights.size()){
            count = monsters.size();
        } else {
            count = activeKnights.size();
        }

        for (int i = 0; i < count; i++) {
            String knight = (i < activeKnights.size()) ? activeKnights.get(i).getName() : "";
            String monster = (i < monsters.size()) ? monsters.get(i).getName() : "";

            System.out.printf("%-27s %-27s%n", knight, monster);
    }
    }
    
    
    public void printBattleText(MOB dead){
        System.out.println(String.format("%s was defeated!", dead.getName()));
    }
    
    public void printFortunes(List<Knight> activeKnights){
        System.out.println("For this quest, our knights drew the following fortunes!");
        for(Knight knight: activeKnights) {
            String kngt = knight.getName();
            Fortune fortune = knight.getActiveFortune();
            String sft = fortune.toString();
            System.out.println(kngt + "drew");
            System.out.println(sft);
        }
    }
    
    public boolean checkContinue() {
        System.out.println("Would you like to continue on your quest (y/n)?");
        String input =in.nextLine().toLowerCase();
        if(input.equals("y") || input.equals("yes")) {
            return true;
        }
        else{
            return false;
        }
    }

    public void printDefeated() {
        System.out.println("All active knights have been defeated!");
        System.out.println();
    }
     
}
