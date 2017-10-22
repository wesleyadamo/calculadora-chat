
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame implements Runnable {

	private JPanel contentPane;
	private ArrayList<String> vet = new ArrayList<>();
	private Socket cl;
	private JButton buttonigual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculadora(JButton b, Socket cliente, JTextArea jta, ArrayList v) {
		cl = cliente;
		buttonigual = b;
		vet = v;

	}

	public Calculadora() throws UnknownHostException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(21, 26, 441, 55);
		contentPane.add(textArea);

		JButton button1 = new JButton("1");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setText(textArea.getText() + " " + 1);
				vet.add("1");
			}
		});
		button1.setBounds(21, 121, 89, 23);
		contentPane.add(button1);

		JButton button2 = new JButton("2");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setText(textArea.getText() + " " + 2);
				vet.add("2");
			}
		});
		button2.setBounds(126, 121, 89, 23);
		contentPane.add(button2);

		JButton button3 = new JButton("3");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 3);
				vet.add("3");
			}
		});
		button3.setBounds(237, 121, 89, 23);
		contentPane.add(button3);

		JButton button4 = new JButton("4");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 4);
				vet.add("4");
			}
		});
		button4.setBounds(21, 167, 89, 23);
		contentPane.add(button4);

		JButton button5 = new JButton("5");
		button5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 5);
				vet.add("5");
			}
		});
		button5.setBounds(126, 167, 89, 23);
		contentPane.add(button5);

		JButton button6 = new JButton("6");
		button6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 6);
				vet.add("6");
			}
		});
		button6.setBounds(237, 167, 89, 23);
		contentPane.add(button6);

		JButton button7 = new JButton("7");
		button7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 7);
				vet.add("7");
			}
		});
		button7.setBounds(21, 225, 89, 23);
		contentPane.add(button7);

		JButton button8 = new JButton("8");
		button8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 8);
				vet.add("8");
			}
		});
		button8.setBounds(126, 225, 89, 23);
		contentPane.add(button8);

		JButton button9 = new JButton("9");
		button9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 9);
				vet.add("9");
			}
		});
		button9.setBounds(237, 225, 89, 23);
		contentPane.add(button9);

		JButton button0 = new JButton("0");
		button0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " " + 0);
				vet.add("0");
			}
		});
		button0.setBounds(126, 271, 89, 23);
		contentPane.add(button0);

		JButton buttonmult = new JButton("*");
		buttonmult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + "  * ");
				String c = vet.get(vet.size() - 1);

				char d = c.charAt(0);

				if (Character.isDigit(d)) {
					vet.add("*");
				} else
					vet.set(vet.size() - 1, "*");
			}
		});
		buttonmult.setBounds(384, 225, 89, 23);
		contentPane.add(buttonmult);

		JButton buttonsub = new JButton("-");
		buttonsub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + "  - ");
				String c = vet.get(vet.size() - 1);

				char d = c.charAt(0);

				if (Character.isDigit(d)) {
					vet.add("-");
				} else
					vet.set(vet.size() - 1, "-");
			}
		});
		buttonsub.setBounds(384, 167, 89, 23);
		contentPane.add(buttonsub);

		JButton buttondiv = new JButton("/");

		JButton buttonadd = new JButton("+");
		buttonadd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + " + ");

				String c = vet.get(vet.size() - 1);

				char d = c.charAt(0);

				if (Character.isDigit(d)) {
					vet.add("+");
				} else
					vet.set(vet.size() - 1, "+");

			}
		});
		buttonadd.setBounds(384, 121, 89, 23);
		contentPane.add(buttonadd);

		buttondiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(textArea.getText() + "  / ");
				String c = vet.get(vet.size() - 1);

				char d = c.charAt(0);

				if (Character.isDigit(d)) {
					vet.add("/");
				} else
					vet.set(vet.size() - 1, "/");
			}
		});
		buttondiv.setBounds(384, 271, 89, 23);
		contentPane.add(buttondiv);

		buttonigual = new JButton("=");

		buttonigual.setBounds(237, 271, 89, 23);
		contentPane.add(buttonigual);

		Socket cliente = new Socket("10.130.31.209", 12345);

		Calculadora tratamento = new Calculadora(buttonigual, cliente, textArea, vet);
		Thread t = new Thread(tratamento);
		t.start();

		RecebedorCalc rcl = new RecebedorCalc(cliente, textArea);
		Thread rc = new Thread(rcl);
		rc.start();

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				vet.clear();

			}
		});
		btnLimpar.setBounds(21, 271, 89, 23);
		contentPane.add(btnLimpar);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		this.buttonigual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PrintStream saida;
				String s;

				try {

					saida = new PrintStream(cl.getOutputStream());

					s = "";

					for (String c : vet)
						s += "" + c;

					saida.flush();
					saida.println(s);

				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}
}

class RecebedorCalc implements Runnable {

	private Socket cliente;
	private JTextArea jtarea;

	public RecebedorCalc(Socket cliente, JTextArea jta) {
		this.cliente = cliente;
		jtarea = jta;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Scanner s;
		while (true) {
			try {
				s = new Scanner(cliente.getInputStream());
				while (s.hasNextLine()) {

					jtarea.setText(jtarea.getText() + " = " + s.nextLine());

				}

				// Finaliza objetos
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
