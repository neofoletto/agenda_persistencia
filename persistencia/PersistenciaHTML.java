package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import src.Pessoa;
import util.DataUtil;

public class PersistenciaHTML {

	private String setNome(String nome) {
		if (nome.length() != 0) {
			return nome + ".html";
		}
		return "none.html";
	}
	
    public void gerar(List<Pessoa> lista, String opcao, String nameFile) throws IOException {
        FileWriter arq = new FileWriter(setNome(nameFile));
        PrintWriter gravarArq = new PrintWriter(arq);
//
        gravarArq.printf("%s\n",cabecalho());
        switch (opcao){
            case "gerarHtml": case "gerarhtml":
                gravarArq.printf("%s\n", gerarHtml(lista));
                break;
            case "gerarAniversarianteMes": case "geraraniversariantemes":
                gravarArq.printf("%s\n", gerarAniversarianteMes(lista));
                break;
            default:

                break;
        }
        gravarArq.printf("%s\n",rodape());
        arq.close();
    }

    public StringBuilder gerarHtml(List<Pessoa> lista) throws IOException {
        StringBuilder input = new StringBuilder();
        for (Pessoa pessoa : lista) {
            input.append("<tr><td>");
            input.append("Codigo: ");
            input.append(pessoa.getCodigo());
            input.append("<br />Nome: ");
            input.append(pessoa.getNome());
            input.append("<br />Data Nascimento: ");
            input.append(DataUtil.DataHoraForStringPadrao(pessoa.getDataNascimento()));
            input.append("<br />E-Mail: ");
            input.append(pessoa.getEmail());
            input.append("<br />Telefone: ");
            input.append(pessoa.getTelefone());
            input.append("</td></tr><br />\n");
        }
        return input;
    }

    public StringBuilder gerarAniversarianteMes(List<Pessoa> lista) throws IOException {
        StringBuilder input = new StringBuilder();
        for (Pessoa pessoa : lista) {
            input.append("<tr><td>");
            input.append("Codigo: ");
            input.append(pessoa.getCodigo());
            input.append("<br />Nome: ");
            input.append(pessoa.getNome());
            input.append("</td></tr><br />\n");
        }
        return input;
    }

    public String cabecalho() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head><title>Listas</title>\n");
        html.append("<meta charset=\"UTF-8\">\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("<table border=\"1\">\n");
        html.append("<tr><td>Contatos</td></tr>\n");
        return html.toString();
    }

    public String rodape() {
        StringBuilder html = new StringBuilder();
        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>\n");
        return html.toString();
    }

}
