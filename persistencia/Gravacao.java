package persistencia;

import java.util.List;

import src.Pessoa;

public interface Gravacao {

	public boolean gravar(List<Pessoa> lista);
	public List<Pessoa> ler();
}
