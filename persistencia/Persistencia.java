package persistencia;

import java.util.List;

import src.Pessoa;

public class Persistencia {

	private Gravacao g;
	
	public Persistencia(Gravacao g) {
		this.g = g;
	}
	
	public boolean gravar(List<Pessoa> lista, String fileName) {
		return g.gravar(lista, fileName);
	}
	
	public List<Pessoa> ler(String fileName) {
		return g.ler(fileName);
	}
}
