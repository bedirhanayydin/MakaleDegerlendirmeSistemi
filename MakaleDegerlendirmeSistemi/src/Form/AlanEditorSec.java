package Form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Model.ConnectionUtils;

import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AlanEditorSec extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelimalanedit�r = new DefaultTableModel();
	Object[] columnalanedit�r = { "ID", "ALAN ED�T�R� ADI","ALAN ED�T�R� SOYADI","EMA�L","��FRE","KATEGOR� ADI","KATEGOR� ID"};
	Object[] rowalanedit�r = new Object[7];
	int id=-1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlanEditorSec frame = new AlanEditorSec();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void alanedit�rtablodoldur() throws SQLException {
		ResultSet rs=ConnectionUtils.WHERE("select * from alaneditor inner join kategori on kategori.id = alaneditor.kid");
        modelimalanedit�r.setRowCount(0);
        try {
            while(rs.next())
            {
            	rowalanedit�r[0]=rs.getString("id");
            	rowalanedit�r[1]=rs.getString("name");
            	rowalanedit�r[2]=rs.getString("lastname");
            	rowalanedit�r[3]=rs.getString("email");
            	rowalanedit�r[4]=rs.getString("password");
            	rowalanedit�r[5]=rs.getString("kategori.name");
            	rowalanedit�r[6]=rs.getString("kategori.id");
            	modelimalanedit�r.addRow(rowalanedit�r);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        table.setModel(modelimalanedit�r);

	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AlanEditorSec() throws SQLException {
		setUndecorated(true);
		setBounds(100, 100, 928, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel anasayfa = new JPanel();
		anasayfa.setForeground(Color.WHITE);
		anasayfa.setBackground(new Color(102, 204, 153));
		anasayfa.setBounds(0, 0, 928, 701);
		contentPane.add(anasayfa);
		anasayfa.setLayout(null);
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(904, 0, 24, 31);
		anasayfa.add(lbl_exit);
		lbl_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"The program is closing, are you sure","WARNING",JOptionPane.YES_NO_OPTION)==0) {
						System.exit(0);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_exit.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_exit.setForeground(new Color(51, 153, 204));
			}
		});
		lbl_exit.setForeground(new Color(51, 153, 204));
		lbl_exit.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_exit.setBackground(Color.BLACK);
		
		JLabel aed�torad� = new JLabel("");
		aed�torad�.setForeground(new Color(0, 0, 128));
		aed�torad�.setHorizontalAlignment(SwingConstants.CENTER);
		aed�torad�.setBounds(64, 412, 526, 40);
		aed�torad�.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aed�torad�);
		
		JLabel aed�torsoyad� = new JLabel("");
		aed�torsoyad�.setForeground(new Color(0, 0, 128));
		aed�torsoyad�.setHorizontalAlignment(SwingConstants.CENTER);
		aed�torsoyad�.setBounds(64, 469, 526, 40);
		aed�torsoyad�.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aed�torsoyad�);
		
		JLabel aeditoremail = new JLabel("");
		aeditoremail.setForeground(new Color(0, 0, 128));
		aeditoremail.setHorizontalAlignment(SwingConstants.CENTER);
		aeditoremail.setBounds(64, 530, 526, 40);
		aeditoremail.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aeditoremail);
		
		JLabel aed�torkategor� = new JLabel("");
		aed�torkategor�.setForeground(new Color(0, 0, 128));
		aed�torkategor�.setHorizontalAlignment(SwingConstants.CENTER);
		aed�torkategor�.setFont(new Font("Tahoma", Font.BOLD, 15));
		aed�torkategor�.setBounds(64, 582, 526, 40);
		anasayfa.add(aed�torkategor�);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 908, 347);
		anasayfa.add(scrollPane);
		
	   
	    table = new JTable() {					//isCellEditable ekledim ��nk� tablea 2kere t�klay�nca �st�nde d�zenlemeyi engellemek i�in*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		id=Integer.parseInt(modelimalanedit�r.getValueAt(table.getSelectedRow(),0).toString());
	    		aed�torad�.setText("Alan Edit�r� Ad� = "+modelimalanedit�r.getValueAt(table.getSelectedRow(),1).toString());
				aed�torsoyad�.setText("Alan Edit�r� Soyad� = "+(String) modelimalanedit�r.getValueAt(table.getSelectedRow(),2)); 
				aeditoremail.setText("Alan Edit�r� Email = "+(String) modelimalanedit�r.getValueAt(table.getSelectedRow(),3)); 
				aed�torkategor�.setText("Alan Edit�r� Kategorisi = "+(String) modelimalanedit�r.getValueAt(table.getSelectedRow(),5));
	    	}
	    });
	    modelimalanedit�r.setColumnIdentifiers(columnalanedit�r);
	    table.setFont(new Font("Tahoma", Font.BOLD, 12));
	    table.setSelectionBackground(new Color(249,105,14));
	    table.setSelectionForeground(Color.white);
	    table.setRowHeight(30);
	    table.setShowGrid(true);
	    table.setBackground(new Color(248,248,248));
	    table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 13));
	    table.getTableHeader().setBackground(Color.red);
	    table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		scrollPane.setViewportView(table);
		
		JButton btnAlanEditoreYonlendir = new JButton("Makaleyi Edit�re Y�nlend�r");
		btnAlanEditoreYonlendir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id!=-1) {
					try {
					Connection connection = ConnectionUtils.getMyConnection();
					PreparedStatement pStatement=connection.prepareStatement("UPDATE makale SET AeditorID=? WHERE ID=?");
					pStatement.setInt(1, id);
					pStatement.setInt(2,home.se�ilenmakaleid);
					pStatement.executeUpdate();
					JOptionPane.showMessageDialog(null , "��lem Ba�ar�l�");
					dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					} else {
						JOptionPane.showMessageDialog(null , "Once alan Editoru seciniz!","</>",JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		btnAlanEditoreYonlendir.setBounds(684, 427, 234, 40);
		btnAlanEditoreYonlendir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAlanEditoreYonlendir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAlanEditoreYonlendir.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAlanEditoreYonlendir.setBorderPainted(false);
			}
		});
		btnAlanEditoreYonlendir.setFocusPainted(false);
		btnAlanEditoreYonlendir.setContentAreaFilled(false);
		btnAlanEditoreYonlendir.setBorderPainted(false);
		btnAlanEditoreYonlendir.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnAlanEditoreYonlendir.setBackground(new Color(0, 0, 128));
		anasayfa.add(btnAlanEditoreYonlendir);
		
		
		
		alanedit�rtablodoldur();
	}
}
