package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.UsuarioDAo;
import model.Usuario;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class ViewTable<DafaultTableMode> extends JFrame {

	private JPanel contentPane;
	private JTable JUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTable frame = new ViewTable<Object>();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public ViewTable() {
		getContentPane().setLayout(null);
		
		
		getContentPane().add(tabela);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 372, 261);
		contentPane.add(scrollPane);
		
		tabela = new JTable();
		tabela.setFillsViewportHeight(true);
		tabela.setColumnSelectionAllowed(true);
		tabela.setCellSelectionEnabled(true);
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"id", "nome", "cpf", "email", "telefone"
			}
		) {
		
		});
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.setBounds(0, 35, 372, 215);
		contentPane.add(tabela);
		readTable();
	
	}
	public void readTable() {
	
		DafaultTableMode modelo =  ((DafaultTableMode) tabela.getModel());

		UsuarioDAo dao = new UsuarioDAo();
		for(Usuario u: dao.read()) {
		((DefaultTableModel) modelo).addRow(new Object[] {
				u.getId(),u.getNome(),u.getCpf(),u.getEmail(),u.getTelefone()
		});
			
		}
	}
	JScrollPane scroll = new JScrollPane();
	private JTable tabela;
}


