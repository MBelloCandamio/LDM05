/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pract_1_trimestre;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Martin Bello
 * Juego de Piedra papel tijera
 */
public class PiedraPapelTijera {

/**
 * Aqui se definen los puntos y las rondas como integer, los nombre de las jugadas
 * como strings y un boolean que va a servir para regular la salida del juego
 * durante su ejecución.
 */
int puntosJugador;
int puntosMaquina;
int puntosMax =3;
String pi = "Piedra";
String pa = "Papel";
String ti = "Tigera";
String la = "Lagarto";
String sp = "Spock";
private boolean activo = true;

    /**
     * Este es el main que va a ejecutar el programa del juego
     * @param args 
     */
    public static void main(String[] args) {
        
        //Creo una instancia del juego y un objeto scan
        PiedraPapelTijera juego = new PiedraPapelTijera();
        Scanner scan = new Scanner(System.in);
        
    //Mientras que el juego esté activo se repetirá el bucle. El juego pasará a
    //activo=false cuando se presione 2 (salir) en el menú principal.
    while (juego.activo) {
        //Gestionamos el posible error de introducir letras en lugar
        //de números con un try/catch --> InputMismatchException
        try{
            //Mostramos el menú principal.
            juego.menuPrincipal();
            System.out.println("Seleciona una option: ");
            //Pedimos un int de respuesta (Jugar o Salir)
            int opcion = scan.nextInt();
       
                switch (opcion) {
                //Opcion de jugar --> Ponemos puntuaciones a 0 e iniciamos el juego
                case 1:
                    juego.puntosMaquina = 0;
                    juego.puntosJugador = 0;
                    juego.gamePlay();
                    break;
                //Opción salir --> activo = false, se sale del juego
                case 2:
                    System.out.println("Hasta la vista!!");
                    juego.activo = false;
                    break;
                //Si la opcion no es 1 ni 2    
                default:
                    System.out.println("Opcion no válida. Elige una de las opciones disponibles.");
                    break;
                }   
            //Gestionamos el error al introducir letras    
            }catch(InputMismatchException e){
            scan.nextLine();
            System.err.println("Debe escoger un número");
        }
        
    }
        
    }
    
    /**
     * Este método crea el menú principal de juego
     */   
    public void menuPrincipal(){
        System.out.println("""
                           Menu:
                           
                           1. Jugar al mejor de cinco
                           2. Salir
                           """);
    }

   /**
    * Con este método presentamos al juegador las opciones y permitimos que éste
    * elija un numero asociado a esa opción. Si la opcion no se encuentra entre las
    * disponibles, el bucle se repite. 
    * Si la opcion esta entre las disponibles, el método devuelve el int asociado a esa eleccion. 
    * Para evitar que el juego acabe si el jugador inserta una string en lugar de un int, 
    * se añade un catch con el InputMismatchException pidiendo el valor adecuado
    * @return  devuelve el int asociado a la jugada del jugador
    */
    
    public int turnoJugador(){
        Scanner scan = new Scanner(System.in);
        int choice;
        while (true){
            try{
                System.out.println("Elige una opción: ");
                System.out.println("""
                           1. Piedra
                           2. Papel
                           3. Tijera
                           4. Lagarto
                           5. Spock""");  
                choice = scan.nextInt();
                if(choice >= 1 && choice <=5){
                    break;
                }else{
                    System.out.println("Elige una opción de la lista");
                }
            }catch(InputMismatchException e){
                scan.nextLine();
                System.err.println("Debe escoger un número");
            }   
        }    
                return choice;
        
    }
    /**
     * Este método produce un número random entre el 1 y el 5
     * @return int asociado a la jugada random de la máquina
     */
    public int turnoMaquina(){
        Random random = new Random();
        return random.nextInt(5) +1;
    }
    /**
     *Con este método pretendo mostrar la opción asociada al numero escogido 
     * @param jugada int de la jugada del jugador o la máquina
     * @param jugador string a mostrar (Jugador o Máquina)
     */
    public void mostrarSeleccion(int jugada, String jugador) {
        String seleccion = "";
        switch (jugada) {
            case 1:
                seleccion = "Piedra";
                break;
            case 2:
                seleccion = "Papel";
                break;
            case 3:
                seleccion = "Tijera";
                break;
            case 4:
                seleccion = "Lagarto";
                break;
            case 5:
                seleccion = "Spock";
                break;
        }
        System.out.println(jugador + " elige " + seleccion);
    }
    
    /**
     *En este método se definen las reglas del juego; cuando se gana, se pierde 
     *y se empata. Ademas también aumenta los puntos del vencedor. 
     * @param jugador opcion seleccionada por el juador
     * @param maquina opcion seleccionada por  la máquina
     * @return 
     */
    public int revisarJugada(int jugador, int maquina){
        if(jugador == maquina){
            return 0;
        }
        else if (
                (jugador == 1 && (maquina == 3 || maquina == 4)) ||
                (jugador == 2 && (maquina == 1 || maquina == 5)) ||
                (jugador == 3 && (maquina == 2 || maquina == 4)) ||
                (jugador == 4 && (maquina == 5 || maquina == 2)) ||
                (jugador == 5 && (maquina == 1 || maquina == 3))){
            puntosJugador ++;
            return 1;
        }
        else{
            puntosMaquina ++;
            return 2;
        }
    }
    
    /**
     * En este método se define, utilizando los métodos anteriores, el desarrollo
     * del juego.
     */
    public void gamePlay(){
        
    //Creamos un objeto scanner
    Scanner scan = new Scanner(System.in);
        //Mientras que ninguno de los jugadores alcance una puntuación igual
        //al numero de rondas se repite el bucle
        while(puntosJugador < puntosMax && puntosMaquina < puntosMax ){
            
            //primero se ejecutan los turnos
            int player = turnoJugador();
            int computer = turnoMaquina();
            //se muestran la selecciones
            mostrarSeleccion(player, "Jugador");
            mostrarSeleccion(computer, "La máquina");
            //se calcula el resultado
            int resultado = revisarJugada(player, computer);
            
            //se muestran los distintos mensajes dependiendo del resultado
            switch (resultado) {
                case 0:
                    System.out.println("""
                                            Empate!! Juega otra vez
                                                       """);
                    break;
                case 1:
                    System.out.println("""
                                        El jugador gana esta ronda!!
                                                       """);
                    break;
                default:
                System.out.println("""
                                        Has perdido la ronda
                                                   """);
                    break;
            }
           
            System.out.println("""
                               Resultado:
                                   Jugador: """ + puntosJugador + "\n"
                    + "    Máquina: " + puntosMaquina + "\n");
            
            //Cuando alguno de los jugadores llega a una puntuación igual al número
            //de rondas se sale del bucle   
        }
        //Al salir del bucle se comprueba quien ha ganado
        if (puntosJugador > puntosMaquina){
            System.out.println("Has ganado!!, Enhorabuena!!");
        }
        else{
            System.out.println("Has perdido. Prueba otra vez.");
        }
     
    
    }
    
}
