package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManipCSV {

	public ManipCSV() {
	}

	private String setNome(String nome) {
		if (nome.length() != 0) {
			return nome + ".csv";
		}
		return "none.csv";
	}

	public void gravar(List<Pessoa> lista, String arquivoNome) throws IOException {
		FileWriter arq = new FileWriter(setNome(arquivoNome));
		PrintWriter gravarArq = new PrintWriter(arq);

		for (Pessoa pessoa : lista) {
			gravarArq.printf("%d;%s;%s;%s;%s\n"
					, pessoa.getCodigo()
					, pessoa.getNome()
					, pessoa.getDataNascimento().getTime()
					, pessoa.getEmail()
			    , pessoa.getTelefone());
		}
		arq.close();
	}

	public List<Pessoa> ler(String arquivoNome) {
		setNome(arquivoNome);
		List<Pessoa> lista = null;
		try {
			Pessoa pessoa;
			FileReader arq = new FileReader(setNome(arquivoNome));
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			lista = new ArrayList<Pessoa>();
			while (linha != null) {
				String[] leitura = linha.split(";");
				pessoa = new Pessoa();
				pessoa.setCodigo(Integer.parseInt(leitura[0]));
				pessoa.setNome(leitura[1]);
				pessoa.setDataNascimentoDate(new Date(Long.parseLong(leitura[2])));
				pessoa.setEmail(leitura[3]);
				pessoa.setTelefone(leitura[4]);
				lista.add(pessoa);
				linha = lerArq.readLine();
			}
			arq.close();
		} catch (Exception e) {
			System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}			
		return lista;
	}
}
