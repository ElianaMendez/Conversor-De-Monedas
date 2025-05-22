import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMonedas {

    public ParMonedas buscaPar(int numeroParMonedas){
        String monedaUno = "USD";
        String monedaDos = "COP";
        String monedaTres = "AUD";
        String monedaCuatro = "ARS";
        String monedaCinco = "EUR";
        String monedaSeis = "BRL";

        String primerPar = monedaUno + "/" + monedaDos;
        String segundoPar = monedaTres + "/" + monedaCuatro;
        String tercerPar = monedaCinco + "/" + monedaSeis;
        String cuartoPar = monedaDos + "/" + monedaTres;
        String quintoPar = monedaCuatro + "/" + monedaCinco;
        String sextoPar = monedaUno + "/" + monedaSeis;
        String parElegido = null;

        while(numeroParMonedas != 0){
            if(numeroParMonedas == 1){
                parElegido = primerPar;
            } else if (numeroParMonedas == 2){
                parElegido = segundoPar;
            } else if (numeroParMonedas == 3){
                parElegido = tercerPar;
            } else if (numeroParMonedas == 4){
                parElegido = cuartoPar;
            } else if (numeroParMonedas == 5){
                parElegido = quintoPar;
            } else if(numeroParMonedas == 6){
                parElegido = sextoPar;
            } else if (numeroParMonedas == 7){
                System.out.println("Saliendo del Programa...gracias por utilizar nuestra aplicación");
            } else {
                System.out.println("La opcion ingresada no es válida.");
            }
            break;
        }

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/7a653b2f29bad516a08f83ec/" +
                "pair/"+parElegido);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(),ParMonedas.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontré esa paridad de monedas");
        }
    }
}
