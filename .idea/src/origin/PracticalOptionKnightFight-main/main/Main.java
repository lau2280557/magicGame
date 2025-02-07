public class Main {
    private static String gamedata = "gamedata.csv";
    private static String saveData = "knights.csv";

    public static void main(String[] args){
        processArgs(args);
        GameData data = new CSVGameData(gamedata, saveData);
        GameView view  = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }

    private static void processArgs(String[] args){
        for(String arg : args) {
            if (arg.startsWith("--data=")) {
                gamedata= arg.substring(7);
            } else {
                saveData = arg; 
            }
        }
    }
}
