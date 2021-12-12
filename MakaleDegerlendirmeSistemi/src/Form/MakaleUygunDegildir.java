package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Model.ConnectionUtils;
import Model.Mail;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class MakaleUygunDegildir extends JFrame {

	private JPanel contentPane;
	private JTextField txtMailBaslik;
	private JTextField txtmailAdres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakaleUygunDegildir frame = new MakaleUygunDegildir();
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
	public MakaleUygunDegildir() {
		setUndecorated(true);
		setBounds(100, 100, 639, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel makaleuygundegil = new JPanel();
		makaleuygundegil.setForeground(Color.WHITE);
		makaleuygundegil.setBackground(new Color(51, 51, 204));
		makaleuygundegil.setBounds(0, 0, 639, 441);
		contentPane.add(makaleuygundegil);
		makaleuygundegil.setLayout(null);
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(615, 0, 24, 31);
		makaleuygundegil.add(lbl_exit);
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
		
		JLabel lblNewLabel = new JLabel("Mail \u0130\u00E7eri\u011Fi:");
		lblNewLabel.setBounds(44, 206, 136, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		makaleuygundegil.add(lblNewLabel);
		
		JLabel lblMailBal = new JLabel("Mail Ba\u015Fl\u0131\u011F\u0131:");
		lblMailBal.setBounds(44, 148, 98, 19);
		lblMailBal.setFont(new Font("Tahoma", Font.BOLD, 15));
		makaleuygundegil.add(lblMailBal);
		
		txtMailBaslik = new JTextField();
		txtMailBaslik.setBounds(190, 143, 311, 31);
		txtMailBaslik.setFont(new Font("Tahoma", Font.BOLD, 13));
		makaleuygundegil.add(txtMailBaslik);
		txtMailBaslik.setColumns(10);
		
		JLabel lblMail = new JLabel("Mail Adresi:");
		lblMail.setBounds(44, 71, 98, 19);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 15));
		makaleuygundegil.add(lblMail);
		
		txtmailAdres = new JTextField(home.mailString);
		txtmailAdres.setBounds(190, 66, 311, 31);
		txtmailAdres.setEditable(false);
		txtmailAdres.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtmailAdres.setColumns(10);
		makaleuygundegil.add(txtmailAdres);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 206, 311, 123);
		makaleuygundegil.add(scrollPane);
		
		JTextArea mailIcerigi = new JTextArea();
		mailIcerigi.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane.setViewportView(mailIcerigi);
		
		JButton btnMailGonder = new JButton("Mail G\u00F6nder");
		btnMailGonder.setForeground(SystemColor.text);
		btnMailGonder.setBounds(208, 355, 234, 40);
		btnMailGonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mail mail = new Mail(txtMailBaslik.getText(), mailIcerigi.getText());
                try {
                    //mail.sendMail(txtmailAdres.getText());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


                try {
                   mail.sendMail("bedirhhanaydin@gmail.com");
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    makDelete(home.seçilenmakaleid);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Mail gonderildi ve makale silindi");
                home.seçilenmakaleid = -1;
                try {
                	
					home.fill_table();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
			}
		});
		btnMailGonder.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMailGonder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMailGonder.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnMailGonder.setBorderPainted(false);
			}
		});
		btnMailGonder.setFocusPainted(false);
		btnMailGonder.setContentAreaFilled(false);
		btnMailGonder.setBorderPainted(false);
		btnMailGonder.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnMailGonder.setBackground(new Color(0, 0, 128));
		makaleuygundegil.add(btnMailGonder);
		
		
	}
	public static void makDelete(int id) throws SQLException {

	        Connection cn = ConnectionUtils.getMyConnection();
	
	        PreparedStatement PS;

	        PS = cn.prepareStatement("DELETE FROM makale WHERE ID=?");
	        PS.setInt(1, id);
	        PS.executeUpdate();

    }
}
