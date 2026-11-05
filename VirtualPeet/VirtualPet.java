import java.util.Random;

public class VirtualPet {

    String name;
    String type;   // "perro", "gato" o "dummy"
    int energy;
    int hygiene;
    int weight;
    int mood;
    boolean dead = false;  // para avisar de la muerte solo una vez

    // Constructor para perro o gato: atributos aleatorios entre 40 y 60
    public VirtualPet(String name, String type) {
        Random rnd = new Random();
        this.name = name;
        this.type = type;
        this.energy = rnd.nextInt(21) + 40;
        this.hygiene = rnd.nextInt(21) + 40;
        this.weight = rnd.nextInt(21) + 40;
        this.mood = rnd.nextInt(21) + 40;
    }

    // Constructor para el dummy: todo a 100
    public VirtualPet() {
        this.name = "Dummy";
        this.type = "dummy";
        this.energy = 100;
        this.hygiene = 100;
        this.weight = 100;
        this.mood = 100;
    }

    // 1.- Dormir
    public void sleep() {
        System.out.println(name + " duerme... Zzz");
        if (type.equals("perro")) {
            energy = energy + 25;
        } else {
            energy = energy + 20;
        }
        mood = mood + 10;
    }

    // 2.- Jugar
    public void play() {
        System.out.println(name + " juega!");
        energy = energy - 20;
        mood = mood + 20;
        weight = weight - 10;
    }

    // 3.- Comer
    public void eat() {
        System.out.println(name + " come. Nam nam!");
        energy = energy + 10;
        hygiene = hygiene - 15;
        weight = weight + 10;
    }

    // 4.- Limpiar
    public void clean() {
        System.out.println(name + " se esta duchando.");
        if (type.equals("perro")) {
            energy = energy - 10;
            hygiene = hygiene + 25;
            mood = mood - 15;
        } else {
            energy = energy - 5;
            hygiene = hygiene + 25;
            mood = mood + 5;
        }
    }

    // 6.- Bailar
    public void bailar() {
        System.out.println(this.name + " está bailando un mambo! 💃🕺");
        this.energy -= 10;
    }

    // 5.- Atacar al dummy varias veces
    public void attack(VirtualPet dummy, int times) {
        for (int i = 1; i <= times; i++) {
            energy = energy - 3;
            hygiene = hygiene - 3;
            mood = mood - 3;
            dummy.energy = dummy.energy - 3;
            dummy.hygiene = dummy.hygiene - 3;
            dummy.mood = dummy.mood - 3;
            System.out.println("Ataque numero " + i + "  (Dummy: energia " + dummy.energy
                    + ", higiene " + dummy.hygiene + ", animo " + dummy.mood + ")");

            if (isDead()) {
                return;
            }
            if (dummy.energy <= 0 || dummy.hygiene <= 0 || dummy.mood <= 0) {
                System.out.println("El dummy se ha roto. Se repone a 100.");
                dummy.energy = 100;
                dummy.hygiene = 100;
                dummy.mood = 100;
                return;
            }
        }
    }

    // Devuelve true si algun atributo llega a 0 o pasa de 100
    public boolean isDead() {
        if (energy <= 0 || energy > 100 || hygiene <= 0 || hygiene > 100
                || weight <= 0 || weight > 100 || mood <= 0 || mood > 100) {
            if (!dead) {
                draw("x.x");
                System.out.println("Un atributo ha llegado a 0 o ha pasado de 100.");
                System.out.println(name + " ha muerto. GAME OVER.");
                dead = true;
            }
            return true;
        }
        return false;
    }

    // Dibuja la cara de la mascota
    private void draw(String face) {
        System.out.println("");
        if (type.equals("perro")) {
            System.out.println("   U___U");
        } else {
            System.out.println("   /\\_/\\");
        }
        System.out.println("  ( " + face + " )");
        System.out.println("   > ^ <");
        System.out.println("");
    }

    // Barra de # (un # por cada 10 puntos)
    private String bar(int value) {
        String b = "";
        for (int i = 0; i < value / 10; i++) {
            b = b + "#";
        }
        return b;
    }

    // Muestra la cara y los atributos
    public void showStatus() {
        String face = "o.o";
        if (energy <= 25) {
            face = "-.-";
        } else if (mood <= 30) {
            face = "T.T";
        } else if (hygiene <= 25) {
            face = "x.o";
        } else if (mood >= 70) {
            face = "^.^";
        }
        draw(face);
        System.out.println(name + " (" + type + ")");
        System.out.println("Energia: " + energy + "  " + bar(energy));
        System.out.println("Higiene: " + hygiene + "  " + bar(hygiene));
        System.out.println("Peso:    " + weight + "  " + bar(weight));
        System.out.println("Animo:   " + mood + "  " + bar(mood));
    }
}