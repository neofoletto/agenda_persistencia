package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import main.Pessoa;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersistenciaJSON implements Gravacao{

	private GsonBuilder builder;
	@SuppressWarnings("unused")
	private Gson gson;

	private String setNome(String nome) {
		if (nome.length() != 0) {
			return nome + ".json";
		}
		return "none.json";
	}

	public boolean gravar(List<Pessoa> lista, String fileName) {
		@SuppressWarnings("unused")
		Pessoa pes = new Pessoa();
		builder = new GsonBuilder();
		Gson gson = builder.create();
		try {
			FileWriter writer = new FileWriter(setNome(fileName));
			writer.write(gson.toJson(lista));
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Pessoa> ler(String fileName) {
		builder = new GsonBuilder();
		gson = builder.create();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(setNome(fileName)));
			Type listType = new TypeToken<ArrayList<Pessoa>>() {
			}.getType();
			List<Pessoa> lista = new ArrayList<Pessoa>();
			lista = new Gson().fromJson(bufferedReader, listType);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
