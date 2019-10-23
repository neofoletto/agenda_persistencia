package persistencia;

import java.util.List;

import src.Pessoa;

public interface Gravacao {

	public boolean gravar(List<Pessoa> lista, String fileName);
	public List<Pessoa> ler(String fileName);
}
