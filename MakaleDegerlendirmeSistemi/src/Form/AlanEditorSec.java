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
	DefaultTableModel modelimalaneditör = new DefaultTableModel();
	Object[] columnalaneditör = { "ID", "ALAN EDÝTÖRÜ ADI","ALAN EDÝTÖRÜ SOYADI","EMAÝL","ÞÝFRE","KATEGORÝ ADI","KATEGORÝ ID"};
	Object[] rowalaneditör = new Object[7];
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
	public void alaneditörtablodoldur() throws SQLException {
		ResultSet rs=ConnectionUtils.WHERE("select * from alaneditor inner join kategori on kategori.id = alaneditor.kid");
        modelimalaneditör.setRowCount(0);
        try {
            while(rs.next())
            {
            	rowalaneditör[0]=rs.getString("id");
            	rowalaneditör[1]=rs.getString("name");
            	rowalaneditör[2]=rs.getString("lastname");
            	rowalaneditör[3]=rs.getString("email");
            	rowalaneditör[4]=rs.getString("password");
            	rowalaneditör[5]=rs.getString("kategori.name");
            	rowalaneditör[6]=rs.getString("kategori.id");
            	modelimalaneditör.addRow(rowalaneditör);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        table.setModel(modelimalaneditör);

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
		
		JLabel aedýtoradý = new JLabel("");
		aedýtoradý.setForeground(new Color(0, 0, 128));
		aedýtoradý.setHorizontalAlignment(SwingConstants.CENTER);
		aedýtoradý.setBounds(64, 412, 526, 40);
		aedýtoradý.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aedýtoradý);
		
		JLabel aedýtorsoyadý = new JLabel("");
		aedýtorsoyadý.setForeground(new Color(0, 0, 128));
		aedýtorsoyadý.setHorizontalAlignment(SwingConstants.CENTER);
		aedýtorsoyadý.setBounds(64, 469, 526, 40);
		aedýtorsoyadý.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aedýtorsoyadý);
		
		JLabel aeditoremail = new JLabel("");
		aeditoremail.setForeground(new Color(0, 0, 128));
		aeditoremail.setHorizontalAlignment(SwingConstants.CENTER);
		aeditoremail.setBounds(64, 530, 526, 40);
		aeditoremail.setFont(new Font("Tahoma", Font.BOLD, 15));
		anasayfa.add(aeditoremail);
		
		JLabel aedýtorkategorý = new JLabel("");
		aedýtorkategorý.setForeground(new Color(0, 0, 128));
		aedýtorkategorý.setHorizontalAlignment(SwingConstants.CENTER);
		aedýtorkategorý.setFont(new Font("Tahoma", Font.BOLD, 15));
		aedýtorkategorý.setBounds(64, 582, 526, 40);
		anasayfa.add(aedýtorkategorý);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 908, 347);
		anasayfa.add(scrollPane);
		
	   
	    table = new JTable() {					//isCellEditable ekledim çünkü tablea 2kere týklayýnca üstünde düzenlemeyi engellemek için*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		id=Integer.parseInt(modelimalaneditör.getValueAt(table.getSelectedRow(),0).toString());
	    		aedýtoradý.setText("Alan Editörü Adý = "+modelimalaneditör.getValueAt(table.getSelectedRow(),1).toString());
				aedýtorsoyadý.setText("Alan Editörü Soyadý = "+(String) modelimalaneditör.getValueAt(table.getSelectedRow(),2)); 
				aeditoremail.setText("Alan Editörü Email = "+(String) modelimalaneditör.getValueAt(table.getSelectedRow(),3)); 
				aedýtorkategorý.setText("Alan Editörü Kategorisi = "+(String) modelimalaneditör.getValueAt(table.getSelectedRow(),5));
	    	}
	    });
	    modelimalaneditör.setColumnIdentifiers(columnalaneditör);
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
		
		JButton btnAlanEditoreYonlendir = new JButton("Makaleyi Editöre Yönlendýr");
		btnAlanEditoreYonlendir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id!=-1) {
					try {
					Connection connection = ConnectionUtils.getMyConnection();
					PreparedStatement pStatement=connection.prepareStatement("UPDATE makale SET AeditorID=? WHERE ID=?");
					pStatement.setInt(1, id);
					pStatement.setInt(2,home.seçilenmakaleid);
					pStatement.executeUpdate();
					JOptionPane.showMessageDialog(null , "Ýþlem Baþarýlý");
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
		
		
		
		alaneditörtablodoldur();
	}
}
