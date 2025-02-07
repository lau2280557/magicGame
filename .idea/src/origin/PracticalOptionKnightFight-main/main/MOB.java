public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private final String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDice){ //constructor
        this.name = name;
        this.maxHP = hp;
        this.hitModifier = hitModifier;
        this.damageDie = damageDice; 
        this.armor = armor;
        damage = 0; 
    
    }
    
    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }
    
    public int getMaxHP() { //health
        return maxHP;
    }

    
    public DiceType getDamageDie() { //power
        return damageDie;
    }

    
    public int getHitModifier() {
        return hitModifier;
    }

    public String getName() {
        return name; 
    }

    public int getHP() {
      return maxHP -= damage; 
    }

    public MOB copy(){
        return new MOB(name, maxHP, armor, hitModifier, damageDie); 
    }

    public void addDamage(int damage){
        this.damage += damage;
    
    }

    public void resetDamage() {
        this.damage = 0;
    }

    @Override
    public String toString(){
        return "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        "|                            |\n" +
        String.format("|         Health: %-10d |%n", getHP())  +
        String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
        "|                            |\n" +
        "+============================+";
    }
    

    public static void main(String[] args) {
        MOB mob = new MOB("damsel", 45, 4, 3, DiceType.D8);
        System.out.print(mob);
    }
}
