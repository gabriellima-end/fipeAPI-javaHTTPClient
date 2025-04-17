package lima.com.fipeapi;

import com.google.gson.*;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args ) {
    	
    	//INSTANCIANDO OS IMPORTS
    	Scanner scanner = new Scanner(System.in);
    	Gson gson = new Gson();
    	
    	//CHAMANDO API DA TABELA FIPE
    	
    	
		try {
			String marcasJson;
			
			//PASSO 1, OBTENDO A MARCA DO CARRO----------------
			marcasJson = ConnectionAPI.get("https://parallelum.com.br/fipe/api/v1/carros/marcas");
			//TRANSFORMANDO O JSON UM OBJETO ARRAY
	    	JsonArray marcas = JsonParser.parseString(marcasJson).getAsJsonArray();
	    	
	    	//PASSANDO EM CADA ELEMENTO "M" QUE ESTÁ DENTRO DA LISTA "MARCAS"
	    	for(JsonElement m : marcas ) {
	    		
	    		//CONVERTENDO O ITEM "m" EM UM OBJETO JSON COMPLETO
	    		JsonObject marca = m.getAsJsonObject();
	    		//ACESSANDO OS DADOS POR NOME
	    		System.out.println(marca.get("codigo") + " - " + marca.get("nome"));
	    		
	    	}
	    	
	    	//CAPTURANDO O VALOR QUE O USUÁRIO DIGITAR
	    	System.out.println("Digite o código da marca: ");
	    	String codigoMarca = scanner.nextLine();
	    	
	    	//PASSO 2, OBTENDO O MODELO DO CARRO ----------------------
	    	
	    	String modelosJson;
	    	
	    	modelosJson = ConnectionAPI.get("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos");
	    	
	    	//POR SE TRATAR DE UM OBJETO E NÃO ARRAY TEMOS QUE PARSEAR O OBJETO PRINCIPAL E DEPOIS PEGAR O ARRAY MODELOS
	    	JsonObject modelosObj = JsonParser.parseString(modelosJson).getAsJsonObject();
	    	
	    	//PEGANDO APENAS O ARRAY "MODELOS"
	    	JsonArray modelos = modelosObj.getAsJsonArray("modelos");
	    	
	    	for(JsonElement mod : modelos) {
	    		
	    		JsonObject modelo = mod.getAsJsonObject();
	    		System.out.println(modelo.get("codigo") + " - " + modelo.get("nome"));
	    		
	    	}
	    	
	    	System.out.println("Digite o código do modelo: ");
	    	String codigoModelo = scanner.nextLine();
	    	
	    	//PASSO 3, OBTENDO OS ANOS DO MODELO E INFORMAÇÕES DO CARRO ------------------------
	    	
	    	String anosJson;
	    	
	    	anosJson = ConnectionAPI.get("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");             
	    	
	    	JsonArray anos = JsonParser.parseString(anosJson).getAsJsonArray();
	    	
	    	for(JsonElement anosElement : anos) {
	    		
	    		JsonObject ano = anosElement.getAsJsonObject();
	    		System.out.println(ano.get("codigo") + " - " + ano.get("nome"));
	    		
	    	}
	    	
	    	System.out.println("Digite o codigo do ano do modelo: ");
	    	String codigoAnoModelo = scanner.nextLine();
	    	
	    	String anoModeloJson = ConnectionAPI.get("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAnoModelo);
	    	
	    	JsonObject anosModeloObj = JsonParser.parseString(anoModeloJson).getAsJsonObject();
	    	
	    	//POR SE TRATAR DE UM OBJETO SIMPLES, NÃO PRECISA DE FOR, APENAS DA LÓGICA DE CAPTURA.
	    	System.out.println("Dados do veículo:");
	    	System.out.println("Valor: " + anosModeloObj.get("Valor").getAsString());
	    	System.out.println("Marca: " + anosModeloObj.get("Marca").getAsString());
	    	System.out.println("Modelo: " + anosModeloObj.get("Modelo").getAsString());
	    	System.out.println("Ano do Modelo: " + anosModeloObj.get("AnoModelo").getAsString());
	    	System.out.println("Combustivel: " + anosModeloObj.get("Combustivel").getAsString());
	    	
	    	
	    	
//    		{
//    		  "TipoVeiculo": 1,
//    		  "Valor": "R$ 45.238,00",
//    		  "Marca": "Toyota",
//    		  "Modelo": "Supra",
//    		  "AnoModelo": 2007,
//    		  "Combustivel": "Gasolina",
//    		  "CodigoFipe": "002023-0",
//    		  "MesReferencia": "abril de 2025",
//    		  "SiglaCombustivel": "G"
//    		}
	    	
	    	
		} catch (Exception e) {
			
			System.out.print("erro na requisição ");
			e.printStackTrace();
			System.out.print("fim do erro na requisição");
			
		}finally {
		    scanner.close(); // <- fecha o scanner aqui
		}
    	
    	
        
    }
}
