public class GameController {
    private final GameData data;
    private final CombatEngine engine;
    private final GameView view;

    public GameController(GameData data, GameView view, CombatEngine engine){
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    public void start(){
        view.splashScreen();
        String command;
        do {
        command = view.displayMainMenu().toLowerCase().trim();
        }   
        while (processCommand(command));
    
    view.endGame();      
    }
    protected boolean processCommand(String command){ 
        command = command.toLowerCase();
        if (command.contains("exit") || command.contains("bye")){
            return false; 
        }
        
        if (command.equals("ls") || command.equals("list all")){
            view.listKnights(data.getKnights());
        }
        else if (command.equals("list active")){
            view.listKnights(data.getActiveKnights());
        }
        else if (command.startsWith("show")) {
            String showkt = command.substring(command.indexOf("show")+ 4).trim();
            processShowKnight(showkt);
        }
        else if (command.startsWith("set active")){
            String activekt = command.substring(command.indexOf("active")+ 6).trim();
            processSetActive(activekt);
        }
        else if (command.startsWith("remove")){
            String removekt = command.substring(command.indexOf("remove") + 6).trim();
            processRemoveActive(removekt);
        }

        else if (command.contains("explore") || command.contains("adventure") || command.contains("quest")) {
            engine.initialize();
            engine.runCombat();
            engine.clear();

        } else {
            view.printHelp();
        }
        return true;
    }
    

    private void processRemoveActive(String remove){ 
        Knight removekt = data.getActive(remove);
        if (removekt != null) {
            data.removeActive(removekt);
 }      else {
            view.knightNotFound(); 
 }
    }

    private void processSetActive(String active){
        Knight setskt = data.getKnight(active);
        if (setskt == null) {
            view.knightNotFound();
            return;
        }
        if (!data.setActive(setskt)){
                view.setActiveFailed();
        }
    }

    private void processShowKnight(String nameOrId){ 
        Knight showkt = data.getKnight(nameOrId); 
        if(showkt != null) {
            view.showKnight(showkt);
    } else {
        view.knightNotFound();
    }
    }
    
}
