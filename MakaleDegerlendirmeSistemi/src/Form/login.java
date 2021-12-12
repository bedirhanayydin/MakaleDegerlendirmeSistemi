package Form;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import Database.YoneticiDao;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import Model.Yonetici;

public class login {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	public static Yonetici yonetici;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);		
					window.frame.setFocusable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 *  the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 804, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 785, 444);
		frame.setLocationRelativeTo(null);		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		panel.setBounds(0, 0, 284, 444);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/images/LOG\u0130N.png")));
		lblNewLabel_1.setBounds(20, 46, 254, 359);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(282, 0, 503, 444);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals("email")) {
					txtEmail.setText("");
			}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtEmail.getText().equals("")) {
					txtEmail.setText("email");
			}
		}});

			
		txtEmail.setToolTipText("EMA\u0130L");
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		txtEmail.setText("ba@gmail.com");
		txtEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEmail.setBounds(130, 145, 252, 38);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);
		
		
		JButton btn_login = new JButton("Giris Yap");
		btn_login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				yonetici=new Yonetici(-1, null, null, txtEmail.getText(), pwdPassword.getText());
				try {
					yonetici=YoneticiDao.YoneticiLogin(yonetici);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (yonetici.getId()==-1) {
					JOptionPane.showMessageDialog(null,"Yanlýþ Giriþ!!! ");

				} else {
					JOptionPane.showMessageDialog(null,"Hoþgeldiniz "+ yonetici.getName() +" "+ yonetici.getLastname() );
					
					try {
					home home = new home();
					home.setVisible(true);
					frame.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_login.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_login.setBackground(Color.white);
			}
		});
		
		btn_login.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_login.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_login.setForeground(new Color(0, 51, 204));
		btn_login.setBackground(Color.WHITE);
		btn_login.setBounds(130, 322, 252, 50);
		panel_1.add(btn_login);
		btn_login.setFocusPainted(false); //butonun üstüne basýnca çizmesini engelleme

		JLabel lbl_Exit = new JLabel("X");
		lbl_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"The program is closing, are you sure","WARNING",JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_Exit.setForeground(Color.red);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_Exit.setForeground(new Color(51, 153, 204));
			}
		});
		lbl_Exit.setForeground(new Color(51, 153, 204));
		lbl_Exit.setBackground(Color.BLACK);
		lbl_Exit.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_Exit.setBounds(478, 0, 18, 32);
		panel_1.add(lbl_Exit);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (pwdPassword.getText().equals("password")) {
					pwdPassword.setText("");
			}
			}
			@SuppressWarnings("deprecation")
			// belli derleyici uyarýlarýný devre dýþý býrakýr
			public void focusLost(FocusEvent e) {
				if (pwdPassword.getText().equals("")) {
					pwdPassword.setText("password");
			}
		}});

		pwdPassword.setForeground(Color.GRAY);
		pwdPassword.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		pwdPassword.setText("123456");
		pwdPassword.setToolTipText("PASSWORD");
		pwdPassword.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pwdPassword.setBounds(130, 215, 252, 32);
		panel_1.add(pwdPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Edit\u00F6r Giris");
		lblNewLabel_2.setForeground(new Color(51, 153, 204));
		lblNewLabel_2.setBackground(new Color(51, 153, 204));
		lblNewLabel_2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
		lblNewLabel_2.setBounds(153, 41, 229, 38);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(login.class.getResource("/images/padlock.png")));
		lblNewLabel_3.setBounds(392, 209, 23, 38);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setIcon(new ImageIcon(login.class.getResource("/images/name.png")));
		lblNewLabel_3_1.setBounds(392, 145, 23, 38);
		panel_1.add(lblNewLabel_3_1);
		
		JCheckBox show_password = new JCheckBox("Sifreyi G\u00F6ster");
		show_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (show_password.isSelected()) 
					pwdPassword.setEchoChar((char)0);
				else 
					pwdPassword.setEchoChar('*');
			}
		});
		show_password.setForeground(new Color(0, 51, 204));
		show_password.setBackground(Color.WHITE);
		show_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		show_password.setBounds(130, 257, 127, 23);
		panel_1.add(show_password);
		show_password.setFocusPainted(false); //butonun üstüne basýnca çizmesini engelleme


	}
}
