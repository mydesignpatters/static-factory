package app.principal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.gerador.FabricaGerador;
import app.gerador.GeradorArquivo;

public class AppStaticFactory {
    public static void main(String[] args) {
        System.out.println("Testando o padrão Static Factory");

        GeradorArquivo ga = FabricaGerador.criarGeradorXML(FabricaGerador.ZIP);

        final Map<String, Object> propriedades = new HashMap<String, Object>();
        propriedades.put("porta", "8080");
        propriedades.put("banco", "mysql");
        propriedades.put("servidor", "tomcat");

        String nome = "meuArquivoDePropriedadeCompactado.xml";

        System.out.println("Gerando arquivo de propriedades XML compactado...");

        try {
            ga.gerarArquivo(nome, propriedades);
            System.out.println("Arquivo " + nome + " gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo " + nome + " - " + e.getMessage());
        }
        
        GeradorArquivo ga2 = FabricaGerador.criarGeradorXML(FabricaGerador.CRYPTO);

        String nome2 = "meuArquivoDePropriedadeCriptografado.xml";

        System.out.println("Gerando arquivo de propriedades XML criptografado...");

        try {
            ga2.gerarArquivo(nome2, propriedades);
            System.out.println("Arquivo " + nome2 + " gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo " + nome + " - " + e.getMessage());
        }
        
    }
}