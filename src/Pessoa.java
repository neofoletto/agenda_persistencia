package src;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.DataUtil;
import util.Utils;

public class Pessoa {

	private int codigo;
	private String nome;
	private String email;
	private Date dataNascimento;
	private String telefone;

	public Pessoa() {
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public boolean setNome(String nome) {
		if (nome.length() > 2) {
			this.nome = nome;
			return true;
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if (new Utils().isValidEmailAddressRegex(email)) {
			this.email = email;
			return true;
		}
		return false;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public boolean setDataNascimento(String dataNascimento) {
		if (DataUtil.validaData(dataNascimento)) {
			this.dataNascimento = new DataUtil().retornaDateFormatada(dataNascimento);
			return true;
		}
		return false;
	}
	
	public void setDataNascimentoDate(Date date) {
		this.dataNascimento = date;
	}

	public String getTelefone() {
		return telefone;
	}

	public boolean setTelefone(String telefone) {
		if (telefone.length() >= 9 && telefone.length() <= 11) {
			this.telefone = telefone;
			return true;
		}
		return false;
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa: [");
		builder.append("codigo=");
		builder.append(codigo);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", dataNascimento=");
		builder.append(dataNascimento);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}
	
	
}