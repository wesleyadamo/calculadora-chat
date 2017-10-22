
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Servidor implements Runnable {

	public Socket cliente;

	public Servidor(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String[] args) throws Exception {

		// Cria um socket na porta 12345
		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Servidor rodando na porta 12345!");

		System.out.println("Aguardando conexão do cliente...");

		while (true) {
			Socket cliente = servidor.accept();
			// Cria uma thread do servidor para tratar a conexão
			Servidor tratamento = new Servidor(cliente);
			Thread t = new Thread(tratamento);
			// Inicia a thread para o cliente conectado

			t.start();
		}
	}

	public void run() {

		try {
			Scanner s = null;
			s = new Scanner(this.cliente.getInputStream());

			PrintStream saida = new PrintStream(this.cliente.getOutputStream());

			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");

			while (s.hasNextLine()) {
				Object obj = engine.eval(s.nextLine());
				saida.println(obj.toString());

			}

			// Finaliza objetos
			s.close();
			saida.close();
			this.cliente.close();
		} catch (IOException | ScriptException e) {
			e.printStackTrace();
		}
	}

}
