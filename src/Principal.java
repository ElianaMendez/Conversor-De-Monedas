import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String monedaUno = "USD";
        String monedaDos = "COP";
        String monedaTres = "AUD";
        String monedaCuatro = "ARS";
        String monedaCinco = "EUR";
        String monedaSeis = "BRL";
        Scanner lectura = new Scanner(System.in);

        ConsultarMonedas consulta = new ConsultarMonedas();
        System.out.println("*********************************************");
        System.out.println("Bienvenido al mejor Conversor De Moneda!\n");
        String opcionesDeConversion = """
                Ingresa la opción de conversión de monedas que quieres elegir:
                
                1. Dolar Americano --> Peso Colombiano (USD/COP)
                2. Dolar Australiano --> Peso Argentino (AUD/ARS)
                3. Euro --> Real Brasileño (EUR/BRL)
                4. Pesos Colombiano --> Dolar Australiano (COP/AUD)
                5. Pesos Argentino --> Euro (ARS/EUR)
                6. Dolar Americano --> Real Brasileño (USD/BRL)
                7. Salir
                \n
                """;
        System.out.println(opcionesDeConversion);

        try{
            var parUsuario = lectura.nextInt();
            ParMonedas par = consulta.buscaPar(parUsuario);
            if(par == null){
                return;
            }
            System.out.println("Ingresa el monto que quieres convertir");
            var valorAConvertir = lectura.nextDouble();
            var valorConvertido = valorAConvertir * par.conversion_rate();

            if(parUsuario == 1){
                System.out.println("El valor de "+valorAConvertir+" ["+monedaUno+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaDos+"]");
            } else if (parUsuario == 2){
                System.out.println("El valor de "+valorAConvertir+" ["+monedaTres+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaCuatro+"]");
            } else if (parUsuario == 3){
                System.out.println("El valor de "+valorAConvertir+" ["+monedaCinco+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaSeis+"]");
            } else if (parUsuario == 4){
                System.out.println("El valor de "+valorAConvertir+" "+monedaDos+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaTres+"]");
            } else if (parUsuario == 5){
                System.out.println("El valor de "+valorAConvertir+" ["+monedaCuatro+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaCinco+"]");
            } else if (parUsuario == 6){
                System.out.println("El valor de "+valorAConvertir+" ["+monedaUno+"]"+" corresponde " +
                        "al valor de ---> "+valorConvertido+" ["+monedaSeis+"]");
            }else{
                System.out.println("Finalizando la aplicación");
            }

        }catch (NumberFormatException e) {
            System.out.println("Número no enconstrado "+e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());

        }
    }
}
