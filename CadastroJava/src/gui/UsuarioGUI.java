package gui;

import gui.ViewTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.html.ImageView;

import dao.UsuarioDAo;
import model.Usuario;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import java.awt.Color;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class UsuarioGUI<TextPrompt, Image, BufferedImage> extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioGUI frame = new UsuarioGUI();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public UsuarioGUI() throws ParseException {
		setForeground(new Color(0, 0, 102));
		setIconImage(Toolkit.getDefaultToolkit().getImage(UsuarioGUI.class.getResource("/fotos/Icone-Caracteristicas-cadastro (1).png")));
		setBackground(new Color(0, 102, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		lblNome.setBounds(34, 121, 69, 20);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
		lblCpf.setBounds(34, 164, 46, 29);
		contentPane.add(lblCpf);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		lblEmail.setBounds(34, 226, 69, 14);
		contentPane.add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 16));
		lblTelefone.setBounds(24, 267, 72, 20);
		contentPane.add(lblTelefone);

		tfNome = new JTextField();
		tfNome.setBounds(143, 121, 180, 30);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(143, 220, 180, 30);
		contentPane.add(tfEmail);

		JFormattedTextField tfTelefone = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		tfTelefone.setBounds(143, 264, 180, 30);
		contentPane.add(tfTelefone);

		JFormattedTextField tfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		tfCpf.setBounds(143, 165, 180, 30);
		contentPane.add(tfCpf);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setForeground(new Color(0, 0, 51));
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Usuario usuarios = new Usuario();
				usuarios.setNome(tfNome.getText());
				usuarios.setCpf(tfCpf.getText());
				usuarios.setEmail(tfEmail.getText());
				usuarios.setTelefone(tfTelefone.getText());

				// fazendo a validação dos dados

				if ((tfNome.getText().isEmpty()) || (tfCpf.getText().isEmpty()) || (tfEmail.getText().isEmpty())
						|| (tfTelefone.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios", "AVISO",
							JOptionPane.ERROR_MESSAGE);
				} else {
					UsuarioDAo dao = new UsuarioDAo();
					dao.adcionarUsuario(usuarios);
					JOptionPane.showMessageDialog(null, "Usuário: " + tfNome.getText() + "\n INSERIDO ");
					dispose();
					Tabela v;
					try {
						v = new Tabela();
						v.setVisible(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				tfNome.setName("");
				tfCpf.setText("");
				tfEmail.setText("");
				tfTelefone.setText("");

			}
		});

		btnCadastrar.setBounds(10, 346, 135, 50);
		contentPane.add(btnCadastrar);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.setForeground(new Color(0, 0, 51));
		btnLimpar.setBackground(new Color(255, 255, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText("");
				tfCpf.setText("");
				tfEmail.setText("");
				tfTelefone.setText("");

			}

		});
		btnLimpar.setBounds(299, 346, 125, 50);
		contentPane.add(btnLimpar);

		JButton btnNewButton = new JButton("ATUALIZAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Tabela v;
				try {
					v = new Tabela();
					v.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setBounds(155, 346, 125, 50);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(345, 22, 2, 2);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 434, 62);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		JLabel lblCadastro = new JLabel("CADASTRO DE USUARIO");
		lblCadastro.setForeground(new Color(255, 255, 255));
		panel.add(lblCadastro);
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel label_foto = new JLabel("");
		label_foto.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/fotos/Icone-Caracteristicas-cadastro (1).png")));
		label_foto.setVerticalAlignment(SwingConstants.CENTER);
		label_foto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_foto);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBounds(0, 407, 434, 69);
		contentPane.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 61, 434, 346);
		contentPane.add(panel_1);

	}
}
