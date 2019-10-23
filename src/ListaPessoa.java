package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import persistencia.Gravacao;
import persistencia.Persistencia;
import persistencia.PersistenciaCSV;
import util.DataUtil;

public class ListaPessoa {

	private List<Pessoa> listaPessoa;

	public ListaPessoa() {
		this.listaPessoa = new ArrayList<Pessoa>();
		PersistenciaCSV csv = new PersistenciaCSV();
//	PersistenciaXML xml = new PersistenciaXML();
//	PersistenciaHTML html = new PersistenciaHTML();
//	PersistenciaJSON json = new PersistenciaJSON();
		Persistencia pers = new Persistencia((Gravacao) csv);

		
	}

	public void gravar(String arquivoNome) throws IOException {
		new PersistenciaCSV().gravar(this.listaPessoa, arquivoNome);
	}
	
	public boolean inserirPessoa(Pessoa pessoa, String arquivoNome) throws IOException {
		if (!pessoa.equals(null)) {
			pessoa.setCodigo(returnCodigoListaPessoa());
			this.listaPessoa.add(pessoa);
			new PersistenciaCSV().gravar(this.listaPessoa, arquivoNome);
			return true;
		}
		return false;
	}

	public void inserirPessoaLista(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public boolean removerPessoaLista(String nome, String arquivoNome) throws IOException {
		int index = retornaIndexDaPessoaNaLista(nome);
		if (index != -1)
			this.listaPessoa.remove(index);
		else 
			return false;
		new PersistenciaCSV().gravar(listaPessoa, arquivoNome);
		return true;
	}

	public List<Pessoa> retornaListaPessoa() {
		return listaPessoa;
	}

	public Integer retornaTamanhoDaListaPessoa() {
		return this.listaPessoa.size();
	}
	
	public Pessoa retornaPessoa(int index) {
		for (Pessoa pessoa : this.listaPessoa) {
			if (pessoa.getCodigo() == index)
				return listaPessoa.get(index);
		}
		return null;
	}

	public String retornaListaCompleta() {
		StringBuilder builder = new StringBuilder();
		ListIterator<Pessoa> pessoa = this.listaPessoa.listIterator();
		while (pessoa.hasNext()) {
			Object element = pessoa.next();
			builder.append(element).append("\n");
		}
		return builder.toString();
	}
	
	public String retornaListaCompletaDeDominio() {
		StringBuilder builder = new StringBuilder("\n");
		List<String> texto = new ArrayList<String>();
		boolean aux = true;
		for (Pessoa pessoa : this.listaPessoa) 
			texto.add(pessoa.getEmail().split("@")[1]);
		for (int i = 0; i < texto.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (texto.get(i) == texto.get(j) && i != j) {
					aux = false;
					break;
				}
			}
			if (aux) {
				builder.append(texto.get(i)).append("\n");	
				aux = true;
			}
		}
		
		return builder.toString();
	}
	
	public String retornaListaCompletaAniversarioDoMes(int mes) {
		StringBuilder builder = new StringBuilder("\n");
		for (Pessoa pessoa : this.listaPessoa) 
			if (DataUtil.MesEmInteiro(pessoa.getDataNascimento()) == (mes ))
				builder.append(pessoa).append("\n");
		
		return (!builder.toString().isEmpty()) ? builder.toString() : "Não há aniversariantes neste mês.";
	}

	private int returnCodigoListaPessoa() {
		return listaPessoa.get(listaPessoa.size() - 1).getCodigo() + 1;
	}

	public Integer retornaIndexDaPessoaNaLista(String nome) {
		for (int i = 0; i < this.listaPessoa.size(); i++) 
			if (this.listaPessoa.get(i).getNome().equals(nome))
				return i;
		return -1;
	}
	
	public boolean retornaCodigoExisteNaListaPessoa(int index) {
		for (int i = 0; i < this.listaPessoa.size(); i++) 
			if (this.listaPessoa.get(i).getCodigo() == index)
				return true;
		return false;
	}

	public String retornaStringListDominios(String dominio, String erroReturn) {
		dominio = dominio.replace("@", "");
		StringBuilder builder = new StringBuilder();		
		for (Pessoa pessoa : this.listaPessoa)
			if (pessoa.getEmail().split("@")[1].equals(dominio))
				builder.append(pessoa).append("\n");
		return (!builder.toString().isEmpty()) ? builder.toString() : erroReturn;
	}
	
	public String buscaPorNome(String nome, String erroReturn) {
		for (Pessoa pessoa : this.listaPessoa)
			if (pessoa.getNome().equals(nome))
				return pessoa.toString();
		return erroReturn;
	}
	
	public boolean alterarPessoa(int opcao, int index, String texto) {
		for (int i = 0; i < this.listaPessoa.size(); i++) {
			if (this.listaPessoa.get(i).getCodigo() == index)
				switch (opcao) {
				case 1:
					this.listaPessoa.get(i).setNome(texto);
					return true;
					
				case 2:
					this.listaPessoa.get(i).setEmail(texto);
					return true;
					
				case 3:
					this.listaPessoa.get(i).setDataNascimento(texto);
					return true;
					
				case 4:
					this.listaPessoa.get(i).setTelefone(texto);
					return true;

				default:
					return false;
				}
		}
		return false;
	}
}
