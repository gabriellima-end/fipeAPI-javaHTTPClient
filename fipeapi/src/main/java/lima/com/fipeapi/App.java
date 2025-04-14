package lima.com.fipeapi;

import com.google.gson.*;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args ) throws Exception{
    	
    	//INSTANCIANDO OS IMPORTS
    	Scanner scanner = new Scanner(System.in);
    	Gson gson = new Gson();
    	
    	//CHAMANDO API DA TABELA FIPE
    	String marcasJson = ConnectionAPI.get("https://parallelum.com.br/fipe/api/v1/carros/marcas");   	
    	//TRANSFORMANDO O JSON UM OBJETO ARRAY
    	JsonArray marcas = JsonParser.parseString(marcasJson).getAsJsonArray();
    	
    	//PASSANDO EM CADA ELEMENTO "M" QUE ESTÁ DENTRO DA LISTA "MARCAS"
    	for(JsonElement m : marcas ) {
    		
    		//CONVERTENDO O ITEM "m" EM UM OBJETO JSON COMPLETO
    		JsonObject marca = m.getAsJsonObject();
    		//ACESSANDO OS DADOS POR NOME
    		System.out.println(marca.get("codigo") + " - " + marca.get("nome"));
    		
    	}
        
    	System.out.println("Digite o código da marca: ");
    	String codigoMarca = scanner.nextLine();
    	
        
    }
}
