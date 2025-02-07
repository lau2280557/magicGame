import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 

public class CSVGameData extends GameData {
     

    public CSVGameData(String gamedata, String saveData){
        loadGameData(gamedata);
        loadSaveData(saveData);
    }

    public void loadSaveData(String saveData){
        int counter = 0;
        Scanner file = readFile(saveData);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(","); 
            Knight kt = new Knight(
                    ++counter,
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()),
                    line.nextInt());
            knights.add(kt);
            line.close();
        }
    }

    private Scanner readFile(String fileName){
        try {
            FileInputStream file = new FileInputStream(fileName);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void loadGameData(String gamedata) {
        Scanner file = readFile(gamedata);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(",");
            String type = line.next().trim();
            if (type.equals("FORTUNE")) { //fixed index out of bounds??
                String description = line.next().trim();
                int v1 = line.nextInt();
                int v2 = line.nextInt();
                int v3 = line.nextInt();
                String dice = line.next().trim();
                DiceType diceType;
                if (dice.equals("-")) { //then had to handle this case? 
                    diceType = null;
                } else {
                    diceType = DiceType.valueOf(dice);
                }

                Fortune f = new Fortune(description, v1, v2, v3, diceType);
                fortunes.add(f);
            }
            if (type.equals("MOB")) {
                MOB m = new MOB(
                        line.next().trim(),
                        line.nextInt(),
                        line.nextInt(),
                        line.nextInt(),
                        DiceType.valueOf(line.next()));
                monsters.add(m);
            } line.close();
        } 
        
        
    }

    public void save(String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Knight knight: knights) {
                writer.write(knight.toCSV() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
