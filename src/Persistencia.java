package src;

import java.util.List;

public class Persistencia {

	private Gravacao g;
	
	public Persistencia(Gravacao g) {
		this.g = g;
	}
	
	public boolean gravar(List<Pessoa> lista) {
		return g.gravar(lista);
	}
	
	public List<Pessoa> ler() {
		return g.ler();
	}
}
