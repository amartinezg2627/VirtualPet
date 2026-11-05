public class Main {

    public static void main(String[] args) {
        // Creamos la mascota
        VirtualPet miMascota = new VirtualPet("Toby", "perro");

        // Estado antes de bailar
        System.out.println("--- Antes de bailar ---");
        miMascota.showStatus();

        // Invocamos el nuevo método
        miMascota.bailar();

        // Estado después de bailar (la energía debe bajar 10)
        System.out.println("--- Despues de bailar ---");
        miMascota.showStatus();
    }
}