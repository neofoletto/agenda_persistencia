package src;

import java.util.List;

public interface Gravacao {

	public boolean gravar(List<Pessoa> lista);
	public List<Pessoa> ler();
}
