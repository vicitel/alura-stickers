import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // conexão HTTP + buscar lista de filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json"; // filmes mais populares
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json" // melhores series
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json" // series mais populares
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();        

        // selecionar dados de interesse (titulo, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        for (int i = 0; i <listaDeFilmes.size(); i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1m\u001b[38;2;184;184;184m\u001b[48;2;255;169;203mTítulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1m\u001b[38;2;184;184;184m\u001b[48;2;255;169;203mVeja o Poster:\u001b[m " + filme.get("image"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numEstrelas = (int) classificacao;
            for (int n = 1; n <= numEstrelas; n++) {
                System.out.print("⭐");
            }
            
            System.out.println("\n");
                      

            
        }


    }
}
