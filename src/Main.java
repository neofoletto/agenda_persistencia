package src;

import java.io.IOException;
import java.util.Scanner;
import util.Utils;

/**
 * 
 * @author neo
 *
 *         Realizar CRUD
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Scanner imput = new Scanner(System.in);
		ListaPessoa listaPessoa = new ListaPessoa();

		String fileName = "pop"; // nome do arquivo

//		System.out.print("Insira nome do arquivo: ");
//		fileName = imput.nextLine();
		listaPessoa.inserirPessoaLista(new ManipCSV().ler(fileName));

		while (true) {
			System.out.println("\n** Menu **");
			System.out.println("1- Incluir");
			System.out.println("2- Alterar");
			System.out.println("3- Excluir");
			System.out.println("4- Consulta por Nome");
			System.out.println("5- Consulta por Mês do aniversariante");
			System.out.println("6- Consulta por domínio de e-mail");		
			System.out.println("7- Retorna lista completa");
			System.out.println("0- Sair");

			switch (validaImputInteger(imput)) {
			case 0:
				System.out.println("Programa finalizado");
				return;

			/**
			 * private int codigo; 
			 * private String nome; 
			 * private String email; 
			 * private Date dataNascimento; 
			 * private String telefone;
			 */
			case 1: // Incluir
				imput.nextLine();
				Pessoa pessoa = new Pessoa();
				System.out.println("** Incluir Pessoa **");
				System.out.print("Informe nome: ");
				pessoa.setNome(imput.nextLine());
				System.out.print("Informe Data de Nascimento (dd/mm/aaaa): ");
				while (!pessoa.setDataNascimento(imput.nextLine()));
				System.out.print("Informe E-mail: ");
				while (!pessoa.setEmail(imput.nextLine()));
				System.out.print("Informe Telefone: ");
				while (!pessoa.setTelefone(imput.nextLine()));

				if (listaPessoa.inserirPessoa(pessoa, fileName))
					System.out.println("Adicionado com sucesso!!");
				else
					System.out.println("Erro!!");
				break;
				
			case 2: // Alterar
//				imput.nextLine();
				System.out.println("** Alterar Pessoa **");
				System.out.println(listaPessoa.retornaListaCompleta()); // retorna toda a lista
				System.out.print("Informe código da Pessoa a ser alterada: ");
				int index = validaImputInteger(imput);
				if (listaPessoa.retornaCodigoExisteNaListaPessoa(index)) {
					System.out.println("** Alterar **");
					System.out.println("1- Nome");
					System.out.println("2- E-mail");
					System.out.println("3- Data de Nascimento");
					System.out.println("4- Telefone");		
					System.out.println("0- Sair");
					int opc = validaImputInteger(imput);
						
					if (opc > 0 && opc <= 4) {
						while (!listaPessoa.alterarPessoa(opc, index, imput.nextLine()));
							listaPessoa.gravar(fileName);
					}else {
						System.out.println("Exit Altera módulo.");
						break;
					}
				}
						
				System.out.println("Código não encontrado.");
				break;
				
			case 3: // Excluir // finalizar
				imput.nextLine();
				System.out.println("** Excluir por Nome **");
				System.out.print("Informe nome: ");
				if (listaPessoa.removerPessoaLista(imput.nextLine(), fileName))
					System.out.println("Exclusão com sucesso");
				else 
					System.out.println("Erro!!");
				
				break;
				
			case 4: // Consulta por Nome
				imput.nextLine();
				System.out.println("** Consulta por Nome **");
				System.out.print("Informe nome: ");
				System.out.println(listaPessoa.buscaPorNome(imput.nextLine(), "Não há pessoa com este nome cadastrado!"));
				break;

			case 5: // Consulta por Mês do aniversariante
				System.out.println("** Consulta por Mês do aniversariante **");
				System.out.print("Informe mês: ");
				int mes = validaImputInteger(imput);
				while (mes < 0 || mes > 12)
					mes = validaImputInteger(imput);
				System.out.println(listaPessoa.retornaListaCompletaAniversarioDoMes(mes));
				
				break;

			case 6: // Consulta por domínio de e-mail
//			imput.nextLine();
				System.out.println("** Consulta por domínio de e-mail **");
				System.out.println(listaPessoa.retornaListaCompletaDeDominio());
				System.out.print("Informe domínio: ");
				String texto = imput.nextLine();
				while (!new Utils().isValidEmailAddressDomainRegex(texto))
					texto = imput.nextLine();
				System.out.println(listaPessoa.retornaStringListDominios(texto, "Não há domínios iguais a este na lista."));
				break;
				
			case 7: // Retorna lista completa
				System.out.println(listaPessoa.retornaListaCompleta());
				break;

			default:
				break;
			}
		}
	}
	
	private static Integer validaImputInteger(Scanner imput) {
		String texto = imput.nextLine();
		while (!new Utils().isValidNumber(texto))
			texto = imput.nextLine();
		return Integer.parseInt(texto);
	}
	
}