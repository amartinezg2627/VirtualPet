import java.util.Random;
import java.util.Scanner;

public class Main {

    // Atributos de la mascota
    static String nombre;
    static String tipo;
    static int energia;
    static int higiene;
    static int peso;
    static int animo;

    // Atributos del dummy
    static int dEnergia = 100;
    static int dHigiene = 100;
    static int dAnimo = 100;

    // Dibuja la mascota con la cara que se le pasa
    static void dibujar(String cara) {
        System.out.println("");
        if (tipo.equals("perro")) {
            System.out.println("   U___U");
        } else {
            System.out.println("   /\\_/\\");
        }
        System.out.println("  ( " + cara + " )");
        System.out.println("   > ^ <");
        System.out.println("");
    }

    // Barra de # (un # por cada 10 puntos)
    static String barra(int valor) {
        String b = "";
        for (int i = 0; i < valor / 10; i++) {
            b = b + "#";
        }
        return b;
    }

    // Elige la cara segun como esta la mascota
    static void mostrarEstado() {
        String cara = "o.o";
        if (energia <= 25) {
            cara = "-.-";   // cansada
        } else if (animo <= 30) {
            cara = "T.T";   // triste
        } else if (higiene <= 25) {
            cara = "x.o";   // sucia
        } else if (animo >= 70) {
            cara = "^.^";   // feliz
        }
        dibujar(cara);
        System.out.println(nombre + " (" + tipo + ")");
        System.out.println("Energia: " + energia + "  " + barra(energia));
        System.out.println("Higiene: " + higiene + "  " + barra(higiene));
        System.out.println("Peso:    " + peso + "  " + barra(peso));
        System.out.println("Animo:   " + animo + "  " + barra(animo));
    }

    // Devuelve true si la mascota ha muerto
    static boolean haMuerto() {
        if (energia <= 0 || energia > 100 || higiene <= 0 || higiene > 100
                || peso <= 0 || peso > 100 || animo <= 0 || animo > 100) {
            dibujar("x.x");
            System.out.println("Un atributo ha llegado a 0 o ha pasado de 100.");
            System.out.println(nombre + " ha muerto. GAME OVER.");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        // Valores aleatorios entre 40 y 60
        energia = rnd.nextInt(21) + 40;
        higiene = rnd.nextInt(21) + 40;
        peso = rnd.nextInt(21) + 40;
        animo = rnd.nextInt(21) + 40;

        // Elegir tipo
        tipo = "";
        while (!tipo.equals("perro") && !tipo.equals("gato")) {
            System.out.println("Quieres un perro o un gato? (escribe perro o gato)");
            tipo = sc.nextLine().trim().toLowerCase();
        }

        // Elegir nombre
        System.out.println("Como se llama tu mascota?");
        nombre = sc.nextLine().trim();

        mostrarEstado();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("");
            System.out.println("1 - Dormir");
            System.out.println("2 - Jugar");
            System.out.println("3 - Comer");
            System.out.println("4 - Limpiar");
            System.out.println("5 - Atacar");
            System.out.println("0 - Salir");
            System.out.println("Elige una opcion:");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un numero.");
                continue;
            }

            if (opcion == 1) {
                System.out.println(nombre + " duerme... Zzz");
                if (tipo.equals("perro")) {
                    energia = energia + 25;
                } else {
                    energia = energia + 20;
                }
                animo = animo + 10;
            } else if (opcion == 2) {
                System.out.println(nombre + " juega!");
                energia = energia - 20;
                animo = animo + 20;
                peso = peso - 10;
            } else if (opcion == 3) {
                System.out.println(nombre + " come. Nam nam!");
                energia = energia + 10;
                higiene = higiene - 15;
                peso = peso + 10;
            } else if (opcion == 4) {
                System.out.println(nombre + " se esta duchando.");
                if (tipo.equals("perro")) {
                    energia = energia - 10;
                    higiene = higiene + 25;
                    animo = animo - 15;
                } else {
                    energia = energia - 5;
                    higiene = higiene + 25;
                    animo = animo + 5;
                }
            } else if (opcion == 5) {
                System.out.println("Cuantas veces quieres atacar?");
                int veces = 0;
                try {
                    veces = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Escribe un numero.");
                }
                for (int i = 1; i <= veces; i++) {
                    energia = energia - 3;
                    higiene = higiene - 3;
                    animo = animo - 3;
                    dEnergia = dEnergia - 3;
                    dHigiene = dHigiene - 3;
                    dAnimo = dAnimo - 3;
                    System.out.println("Ataque numero " + i + "  (Dummy: energia " + dEnergia
                            + ", higiene " + dHigiene + ", animo " + dAnimo + ")");
                    if (haMuerto()) {
                        opcion = 0;
                        break;
                    }
                    if (dEnergia <= 0 || dHigiene <= 0 || dAnimo <= 0) {
                        System.out.println("El dummy se ha roto. Se repone a 100.");
                        dEnergia = 100;
                        dHigiene = 100;
                        dAnimo = 100;
                        break;
                    }
                }
            } else if (opcion == 0) {
                System.out.println("Fin del programa.");
                break;
            } else {
                System.out.println("Opcion no valida.");
                continue;
            }

            if (opcion == 0) {
                break; // la mascota ha muerto en el ataque
            }

            mostrarEstado();

            if (haMuerto()) {
                break;
            }
        }
        sc.close();
    }
}