package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.UsuarioDAo;
import factory.ConnectionFactory;
import model.Usuario;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Tabela<DafaultTableMode> extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	protected MouseEvent evt;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JFormattedTextField tfCpf_1;
	private JFormattedTextField tfTelefone;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblCadastro;
	private JLabel label_foto;

	/**
	 * Launch the application.
	 */
	// Método principal
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabela frame = new Tabela();
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
	 * @throws ParseException 
	 */
	// Construtor da tabela
	public Tabela() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 345);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 0, 51));
		
		JFormattedTextField tfTelefone = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		tfTelefone.setBounds(398, 186, 115, 35);
		contentPane.add(tfTelefone);
		

		
		JFormattedTextField tfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		tfCpf.setBounds(150, 186, 115, 35);
		contentPane.add(tfCpf);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			// Passar para os campos de textos os dados inseridos na tabela
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
				
					tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfCpf.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfEmail.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					tfTelefone.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				}
			}

			@Override
			// Passar para os campos de textos os dados inseridos na tabela quando o botão
			// do mouse é liberado

			public void mouseReleased(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
				
					tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfCpf.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfEmail.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					tfTelefone.setText(table.getValueAt(table.getSelectedRow(), 4).toString());

				}
			}
		});
		// criando o modelo de tabela com os campos
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "id", "Nome", "Cpf", "Email", "Telefone" }) {
					boolean[] columnEditables = new boolean[] { false, true, true, true, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setBounds(0, 0, 366, 250);

		scrollPane = new JScrollPane();

		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (table.getSelectedRow() != -1) {
				
					tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfCpf.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfEmail.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					tfTelefone.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				}

			}
		});
		scrollPane.setBounds(0, 61, 553, 83);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton btExcluir = new JButton("EXCLUIR");
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btExcluir.setForeground(new Color(0, 0, 51));
		btExcluir.setEnabled(true);
		
		
		// EXCLUSÃO DE VALORES
		// DefaultTableModel é a implementação concreta padrão de TableModel usada por
		// JTable quando nenhum modelo é especificado no construto
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DafaultTableMode modelo = ((DafaultTableMode) table.getModel());
				int linha = table.getSelectedRow();
				// Se for selecionado alguma linha da tabela
				if (table.getSelectedRow() != -1) {
					Usuario usuarios = new Usuario();

					String valor = String.valueOf(table.getValueAt(linha, 0));
					// pega o valor da coluna 0 , onde está o Id
					long valor_convertido = Long.valueOf(valor).longValue();
					// converte o valor para long
					UsuarioDAo dao = new UsuarioDAo();
					JOptionPane.showMessageDialog(null,
							"Usuário: " + String.valueOf(table.getValueAt(linha, 1)) + "EXCLUIDO ");
					dao.deletar(valor_convertido);
					((DefaultTableModel) modelo).removeRow(table.getSelectedRow());
					tfNome.setText("");
					tfCpf.setText("");
					tfEmail.setText("");
					tfTelefone.setText("");

				}else {
					JOptionPane.showMessageDialog(null,
							"SELECIONE A LINHA ", "Aviso", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btExcluir.setBounds(7, 261, 131, 34);
		contentPane.add(btExcluir);
		//ATUALIZANDO DADOS
		JButton btnNewButton = new JButton("ATUALIZAR");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*DafaultTableMode é uma implementação do JTable para quando nenhum modelo de tabela ´
				 * é especificado no construtor*/
				DafaultTableMode modelo = ((DafaultTableMode) table.getModel());
				Usuario usuario = new Usuario();
				int linha = table.getSelectedRow();
				if (table.getSelectedRowCount() > 0) {

					String valor = String.valueOf(table.getValueAt(linha, 0));
					long valor_convertido = Long.valueOf(valor).longValue();
					Usuario usuarios = new Usuario();
					usuarios.setNome(tfNome.getText());
					usuarios.setCpf(tfCpf.getText());
					usuarios.setEmail(tfEmail.getText());
					usuarios.setTelefone(tfTelefone.getText());
					usuarios.setId(valor_convertido);

					// fazendo a validação dos dados

					if ((tfNome.getText().isEmpty()) || (tfCpf.getText().isEmpty()) || (tfEmail.getText().isEmpty())
							|| (tfTelefone.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios", "AVISO",
								JOptionPane.ERROR_MESSAGE);
					} else {
						UsuarioDAo dao = new UsuarioDAo();

						JOptionPane.showMessageDialog(null, "Usuário: " + tfNome.getText() + "\n ATUALIZADO ");
						dao.update(usuarios);

						dao.read();
						
						//excluindo os dados e depois inserindo os valores atualizados 
						while (table.getModel().getRowCount() > 0) {
							((DefaultTableModel) table.getModel()).removeRow(0);
						}
						readTable();

					}
					tfNome.setName("");
					tfCpf.setText("");
					tfEmail.setText("");
					tfTelefone.setText("");

				}

			}

		});

		btnNewButton.setBounds(314, 261, 138, 34);
		contentPane.add(btnNewButton);

		tfNome = new JTextField();
		tfNome.setBounds(20, 186, 115, 35);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(273, 186, 115, 35);
		contentPane.add(tfEmail);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 553, 62);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		lblCadastro = new JLabel("CADASTRO DE USUARIO");
		lblCadastro.setForeground(Color.WHITE);
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblCadastro);
		
		label_foto = new JLabel("");
		label_foto.setIcon(new ImageIcon(Tabela.class.getResource("/fotos/Icone-Caracteristicas-cadastro (1).png")));
		label_foto.setVerticalAlignment(SwingConstants.CENTER);
		label_foto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_foto);
		

		
	
		
		
		
		readTable();
	}
	//lendo os valores da tabela
	public void readTable() {
		DafaultTableMode modelo = ((DafaultTableMode) table.getModel());

		UsuarioDAo dao = new UsuarioDAo();

		for (Usuario u : dao.read()) {
			((DefaultTableModel) modelo)
					.addRow(new Object[] { u.getId(), u.getNome(), u.getCpf(), u.getEmail(), u.getTelefone() });

		}
	}
}
