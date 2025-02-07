public class Knight extends MOB {
    private Fortune activeFortune;
    protected final int id;
    protected int xp;

public Knight(int id, String name, int hp, int armor, int hitmodifier, DiceType damageDie, int xp){
    super(name, hp, armor, hitmodifier, damageDie);
    this.id = id; 
    this.xp = xp;
    
}
public void addXP(int xp){
    this.xp += xp;
}

public Fortune getActiveFortune(){
    return activeFortune;
} 

public int getArmor() { 
    if(activeFortune!= null){
        return getActiveFortune().getArmor() + super.getArmor();
}
    return super.getArmor();
}

public DiceType getDamageDie() { 
    if (activeFortune != null) {
        return activeFortune.getDamageDie();
    }   
        return super.getDamageDie();
}

public int getHitModifier() {
    if (activeFortune != null) {
        return super.getHitModifier() + activeFortune.getHitModifier();
    }
    return super.getHitModifier();
}

public Integer getId() {
    return id;
}

public int getMaxHP() { 
    if (activeFortune != null) {
        return super.getMaxHP() + activeFortune.getMaxHP();
    }
    return super.getMaxHP();
}

public int getXP() {
    return xp;
}

public void setActiveFortune(Fortune activeFortune) { 
    this.activeFortune = activeFortune;
}

public String toCSV() { 
    return String.format("%s,%d,%d,%d,%s,%d", getName(), getMaxHP(), getArmor(), getHitModifier(), getDamageDie(), getXP());
}


public String toString() {
    return "+============================+\n" +
           String.format("|%-27s |%n", getName()) +
           String.format("|id:  %-23d|%n", getId()) +
           "|                            |\n" +
           String.format("|Health: %-6d  XP: %-7d |%n", getMaxHP(), getXP()) +
           String.format("| Power: %-6s  Armor: %-4d |%n", getDamageDie().toString(), getArmor()) +
           "|                            |\n" +
           "+============================+";
          
}




    public static void main(String[] args) {
        Knight knight = new Knight(43, "Damsel", 40, 16, 7, DiceType.D8, 1);
        System.out.println(knight);
    }
    
}
