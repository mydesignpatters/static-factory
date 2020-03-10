package app.gerador;

import app.gerador.componente.Compactador;
import app.gerador.componente.Criptografador;
import app.gerador.componente.PosProcessador;
import app.gerador.componente.PosProcessadorComposto;

public abstract class FabricaGerador {
    public static final String ZIP = "ZIP";
    public static final String CRYPTO = "CRYPTO";

    /**
     * Cria arquivo XML de acordo com os processadores passados
     * @param processadores processadores passados
     * @return arquivo XML processado com os processadores
     */
    public static GeradorArquivo criarGeradorXML(String... processadores) {
        GeradorArquivo g = new GeradorXML();
        g.setProcessador(criarProcessador(processadores));
        return g;
    }

    /**
     * Cria um arquivo de propriedades com os processadores passados
     * @param processadores processadores passados
     * @return arquivo de propriedades processado com os processadores
     */
    public static GeradorArquivo criarGeradorPropriedades(String... processadores) {
        GeradorArquivo g = new GeradorPropriedades();
        g.setProcessador(criarProcessador(processadores));
        return g;
    }

    /**
     * Define quais são os processamentos que serão feitos de acordo com os parâmetros passados
     * @param processadores parâmetros passados
     * @return realiza o processamento do arquivo de acordo com os parâmetros passados
     */
    private static PosProcessador criarProcessador(String... processadores) {
        if (processadores.length > 1) {
            PosProcessadorComposto pp = new PosProcessadorComposto();
            for (String proc : processadores) {
                pp.add(criarProcessador(proc));
            }
            return pp;

        } else if (processadores[0].equals(ZIP)) {
            return new Compactador();
        } else if (processadores[0].equals(CRYPTO)) {
            return new Criptografador(1);
        }
        return null;
    }
}
