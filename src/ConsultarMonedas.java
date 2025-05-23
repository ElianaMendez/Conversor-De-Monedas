import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConsultarMonedas {

    private String getApiKey() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            return props.getProperty("API_KEY");
        } catch (IOException e) {
            System.out.println("Error al cargar la API KEY: " + e.getMessage());
            return null;
        }
    }

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
        } else {
            System.out.println("Opción inválida.");
            return null;
        }

        String apiKey = getApiKey();
        if (apiKey == null) return null;

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + parElegido);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(),ParMonedas.class);
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
            return null;
        }
    }
}
