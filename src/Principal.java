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

        while (true) {
            String opcionesDeConversion = """
                    Ingresa la opción de conversión de monedas que quieres elegir:

                    1. Dólar Americano --> Peso Colombiano (USD/COP)
                    2. Dólar Australiano --> Peso Argentino (AUD/ARS)
                    3. Euro --> Real Brasileño (EUR/BRL)
                    4. Peso Colombiano --> Dólar Australiano (COP/AUD)
                    5. Peso Argentino --> Euro (ARS/EUR)
                    6. Dólar Americano --> Real Brasileño (USD/BRL)
                    7. Salir
                    """;
            System.out.println(opcionesDeConversion);

            try {
                int parUsuario = lectura.nextInt();
                if (parUsuario == 7) {
                    System.out.println("Finalizando la aplicación. ¡Gracias por usar el conversor!");
                    break;
                }

                ParMonedas par = consulta.buscaPar(parUsuario);
                if (par == null) {
                    System.out.println("No se pudo obtener la tasa de conversión. Intenta nuevamente.");
                    continue;
                }

                System.out.println("Ingresa el monto que quieres convertir:");
                double valorAConvertir = lectura.nextDouble();
                double valorConvertido = valorAConvertir * par.conversion_rate();

                switch (parUsuario) {
                    case 1 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaUno + "] " +
                            "corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaDos + "]");
                    case 2 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaTres + "] " +
                            "corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaCuatro + "]");
                    case 3 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaCinco + "]" +
                            " corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaSeis + "]");
                    case 4 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaDos + "] " +
                            "corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaTres + "]");
                    case 5 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaCuatro + "] " +
                            "corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaCinco + "]");
                    case 6 -> System.out.println("El valor de " + valorAConvertir + " [" + monedaUno + "] " +
                            "corresponde al valor de ---> " + String.format("%.2f",valorConvertido) + " [" + monedaSeis + "]");
                    default -> System.out.println("Opción inválida.");
                }

            } catch (Exception e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                lectura.nextLine(); // Limpiar buffer de entrada
            }
        }

        lectura.close();
    }
}
