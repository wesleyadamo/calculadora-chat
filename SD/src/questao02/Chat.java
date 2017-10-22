package questao02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;

public class Chat extends JFrame {
	JScrollPane jsp;
	JRadioButton rdbtnMulticast;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Chat("wiara").setVisible(true);
	}

	public Chat(String nome) throws UnknownHostException, IOException {
		getContentPane().setLayout(null);

		setSize(10, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setLocationRelativeTo(null);
		setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 617, 389);
		getContentPane().add(panel);
		panel.setLayout(null);
		jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(31, 69, 377, 309);
		panel.add(jsp);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		jsp.setViewportView(textArea);

		JLabel labelnome = new JLabel("New label");
		labelnome.setBounds(31, 32, 83, 14);
		labelnome.setText(nome);
		panel.add(labelnome);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(28, 426, 379, 60);
		getContentPane().add(textArea_1);

		JButton btnEnviar = new JButton("Enviar");

		btnEnviar.setBounds(439, 463, 89, 23);
		getContentPane().add(btnEnviar);
		setSize(633, 547);

		Socket socket = new Socket("localhost", 12345);
		Cliente c = new Cliente(socket, textArea, textArea_1, nome, btnEnviar);

		Thread t = new Thread(c);
		t.start();

	}
}

class Cliente implements Runnable {

	private Socket cliente;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private String labelnome;
	private JButton btnEnviar;

	public Cliente(Socket cliente, JTextArea textArea, JTextArea textArea_1, String labelnome, JButton btnEnviar) {
		this.cliente = cliente;
		this.textArea = textArea;
		this.textArea_1 = textArea_1;
		this.labelnome = labelnome;
		this.btnEnviar = btnEnviar;

	}

	public void run() {
		try {
			PrintStream saida;

			textArea.setText("Você está conectado");

			saida = new PrintStream(this.cliente.getOutputStream());

			saida.println(labelnome);

			RecebedorChat r = new RecebedorChat(cliente, textArea);
			new Thread(r).start();

			btnEnviar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					saida.println(textArea_1.getText());
					textArea.setText(textArea.getText() + "\n\nVoce: " + textArea_1.getText());

				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class RecebedorChat implements Runnable {

	private Socket cliente;
	private JTextArea textArea;

	public RecebedorChat(Socket servidor, JTextArea textArea) {
		cliente = servidor;
		this.textArea = textArea;

	}

	@SuppressWarnings("resource")
	public void run() {
		try {
			Scanner s = null;
			s = new Scanner(cliente.getInputStream());
			String str;

			while (s.hasNextLine()) {
				str = s.nextLine();
				textArea.setText(textArea.getText() + "\n" + str);

			}

		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}
}
