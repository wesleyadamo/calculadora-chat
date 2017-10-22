package questao02;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ServidorChat implements Runnable {

	public Socket cliente;
	public String nomecliente;

	public static ArrayList<Socket> clientessConectados = new ArrayList<Socket>();

	public ServidorChat(Socket cliente, String nomecliente) {
		this.cliente = cliente;
		this.nomecliente = nomecliente;

	}

	public static void main(String[] args) throws Exception {

		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Servidor rodando na porta 12345!");

		System.out.println("Aguardando conexão do cliente...");

		while (true) {
			Socket cliente = servidor.accept();

			Scanner s = null;
			s = new Scanner(cliente.getInputStream());

			if (s.hasNextLine()) {

				String nomeCliente = s.nextLine();

				clientessConectados.add(cliente);

				for (Socket sc : clientessConectados) {

					if (sc != cliente && !sc.isClosed()) {
						PrintStream saida = new PrintStream(sc.getOutputStream());
						saida.println("\n" + nomeCliente + " está conectado(a)");

					}
				}

				// Cria uma thread do servidor para tratar a conexão
				ServidorChat tratamento = new ServidorChat(cliente, nomeCliente);
				Thread t = new Thread(tratamento);
				// Inicia a thread para o cliente conectado

				t.start();

			}
		}
	}

	public void run() {
		System.out.println("Conexao  com o cliente " + this.cliente.getInetAddress().getHostAddress() + "/"
				+ this.cliente.getInetAddress().getHostName());

		try {
			Scanner s = null;
			PrintStream saida;
			String str;
			s = new Scanner(this.cliente.getInputStream());

			while (s.hasNextLine()) {

				str = s.nextLine();

				for (int i = 0; i < clientessConectados.size(); i++) {
					if (clientessConectados.get(i).isClosed()) {
						clientessConectados.remove(i);

					} else if (clientessConectados.get(i) != cliente) {
						saida = new PrintStream(clientessConectados.get(i).getOutputStream());
						saida.println("\n" + nomecliente + ": " + str);

					}
				}

			}

			// Finaliza objetos
			s.close();

			this.cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
