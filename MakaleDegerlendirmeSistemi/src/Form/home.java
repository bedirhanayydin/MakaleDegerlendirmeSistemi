package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Database.AeditorDao;
import Database.HakemDao;
import Database.YoneticiDao;
import Model.Alaneditor;
import Model.ConnectionUtils;
import Model.Hakem;
import Model.Kategori;
import Model.Yonetici;

import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class home extends JFrame {

	private JPanel contentPane;
	public int mouseX,mouseY;
	public static JButton btnHakemIslem;
	public static JButton btnAEditor;
	public static JButton btnProfil;
	public static JButton btnAnasayfa;
	public static JTable table;
	public static DefaultTableModel modelim = new DefaultTableModel();
	public static Object[] column = { "ID", "YAZAR ADI","YAZAR SOYADI","A�IKLAMA","EMA�L"};
	public static Object[] row = new Object[5];
	private JTextField txtname;
	private JTextField txtlastname;
	private JTextField txtemail,txtalanad;
	private JPasswordField txtpassword;
	private JTextField txtalanemail;
	private JPasswordField passwordField;
	static  JComboBox<Kategori> comboBoxalanedit�r = new JComboBox<Kategori>();
	static  JComboBox<Kategori> comboboxalanedit�rg�ncellesil = new JComboBox<Kategori>();
	static JComboBox<Kategori> hakemcombobox = new JComboBox<Kategori>();
	static JComboBox<Kategori> comboboxguncellekategorihakem = new JComboBox<Kategori>();

	DefaultTableModel modelimalanedit�r = new DefaultTableModel();
	Object[] columnalanedit�r = { "ID", "ALAN ED�T�R� ADI","ALAN ED�T�R� SOYADI","EMA�L","��FRE","KATEGOR� ADI","KATEGOR� ID"};
	Object[] rowalanedit�r = new Object[7];
	private JTextField txtalanguncelledad;
	private JTextField txtalanguncellesoyad;
	private JTextField txtalanguncellemail;
	private JPasswordField passwordField_1;
	private JTable tablealanedit�r;
	public static int idkontrol=-1,se�ilenmakaleid=-1;
	private JTextField txthakemad;
	private JTextField txthakemsoyad;
	private JTextField txthakememail;
	private JPasswordField passwordhakem;
	private JTextField txthakemguncellead;
	private JTextField txthakemguncellesoyad;
	private JTextField txthakemguncelleemail;
	private JPasswordField passwordguncelles�fre;
	private JTable hakemtable;
	DefaultTableModel modelimhakem = new DefaultTableModel();
	Object[] columnhakem = { "ID", "HAKEM ADI","HAKEM SOYADI","EMA�L","��FRE","KATEGOR� ADI","KATEGOR� ID"};
	Object[] rowhakem = new Object[7];
	public static String mailString;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home home = new home();
					home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**  
	 * Create the frame.
	 * @throws SQLException 
	 */
	public home() throws SQLException {
		initGUI();
	}
	
	static int getSelectedItem(String find, JComboBox<Kategori> cb)
    {
        
        for(int i = 0; i<cb.getItemCount() ;i++)
        {cb.setSelectedIndex(i);
            
             if(((Kategori)cb.getSelectedItem()).getName().toString().equals(find))
                 return cb.getSelectedIndex();
        }
        return -1;
    }

	static void WhereKategori() throws SQLException
    {
        
        ResultSet rs=ConnectionUtils.WHERE("SELECT * FROM kategori");
        
        
        try {
            while(rs.next())
            {
                Kategori tut = new Kategori(rs.getInt("id"), rs.getString("name"));
                comboBoxalanedit�r.addItem(tut);
                comboboxalanedit�rg�ncellesil.addItem(tut);
                hakemcombobox.addItem(tut);
                comboboxguncellekategorihakem.addItem(tut);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
	
	public static void fill_table() throws SQLException {
		ResultSet myRs=ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.ID = makale.YazID");
		modelim.setRowCount(0);
		try {
			while (myRs.next()) {
				row[0] = myRs.getString("makale.ID");
				row[1] = myRs.getString("name");
				row[2] = myRs.getString("lastname");
				row[3] = myRs.getString("MakaleBilgi");
				row[4]=myRs.getString("email");
				modelim.addRow(row);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(modelim);
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
        tablealanedit�r.setModel(modelimalanedit�r);

	}
	
	public void hakemtablodoldur() throws SQLException {
		ResultSet rs=ConnectionUtils.WHERE("select * from hakem inner join kategori on kategori.id = hakem.kid");
        
		modelimhakem.setRowCount(0);
        try {
            while(rs.next())
            {
            	rowhakem[0]=rs.getString("id");
            	rowhakem[1]=rs.getString("name");
            	rowhakem[2]=rs.getString("lastname");
            	rowhakem[3]=rs.getString("email");
            	rowhakem[4]=rs.getString("password");
            	rowhakem[5]=rs.getString("kategori.name");
            	rowhakem[6]=rs.getString("kategori.id");
            	modelimhakem.addRow(rowhakem);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hakemtable.setModel(modelimhakem);

	}
	
	private void initGUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane); 

		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 0, 1170, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel men� = new JPanel();
		men�.setBounds(0, 0, 250, 700);
		panel.add(men�);
		men�.setBackground(new Color(51, 153, 204));
		
		JPanel header_panel = new JPanel();
		header_panel.setBackground(new Color(0, 139, 139));
		header_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				setLocation(x-mouseX,y-mouseY);
			}
		});
		header_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		header_panel.setBounds(0, 0, 1171, 29);
		contentPane.add(header_panel);
		
		
		
		
		
		JPanel anasayfa = new JPanel();
		anasayfa.setBackground(new Color(119, 136, 153));
		anasayfa.setForeground(new Color(255, 255, 255));
		anasayfa.setBounds(250, 30, 910, 670);
		panel.add(anasayfa);
		anasayfa.setLayout(null);
		
		
		
		
		
		JLabel lblYazarAd� = new JLabel("");
		lblYazarAd�.setForeground(Color.WHITE);
		lblYazarAd�.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYazarAd�.setBounds(64, 412, 346, 40);
		anasayfa.add(lblYazarAd�);
		
		JLabel lblYazarSoyad� = new JLabel("");
		lblYazarSoyad�.setForeground(Color.WHITE);
		lblYazarSoyad�.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYazarSoyad�.setBounds(64, 469, 346, 40);
		anasayfa.add(lblYazarSoyad�);
		
		JLabel lblMakaleBilgi = new JLabel("");
		lblMakaleBilgi.setForeground(Color.WHITE);
		lblMakaleBilgi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMakaleBilgi.setBounds(64, 530, 696, 40);
		anasayfa.add(lblMakaleBilgi);
		
	
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(1146, 0, 24, 31);
		panel.add(lbl_exit);
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
		men�.setLayout(null);
		
		JLabel lblhosgeldin = new JLabel("Hosgeldin "+login.yonetici.getName());
		lblhosgeldin.setBounds(10, 5, 230, 19);
		men�.add(lblhosgeldin);
		lblhosgeldin.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel profil = new JPanel();
		profil.setLayout(null);
		profil.setForeground(Color.WHITE);
		profil.setBackground(new Color(119, 136, 153));
		profil.setBounds(250, 730, 910, 670);
		panel.add(profil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 164, 96));
		panel_1.setBounds(148, 115, 612, 63);
		profil.add(panel_1);
		panel_1.setLayout(null);
		
		txtname = new JTextField(login.yonetici.getName());
		txtname.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtname.setBounds(76, 5, 480, 53);
		panel_1.add(txtname);
		txtname.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(244, 164, 96));
		panel_1_1.setBounds(148, 200, 612, 63);
		profil.add(panel_1_1);
		
		txtlastname = new JTextField(login.yonetici.getLastname());
		txtlastname.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtlastname.setColumns(10);
		txtlastname.setBounds(76, 5, 480, 53);
		panel_1_1.add(txtlastname);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(244, 164, 96));
		panel_1_2.setBounds(148, 371, 612, 63);
		profil.add(panel_1_2);
		
		txtpassword = new JPasswordField(login.yonetici.getPassword());
		txtpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpassword.setBounds(76, 5, 480, 53);
		panel_1_2.add(txtpassword);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setLayout(null);
		panel_1_3.setBackground(new Color(244, 164, 96));
		panel_1_3.setBounds(148, 287, 612, 63);
		profil.add(panel_1_3);
		
		txtemail = new JTextField(login.yonetici.getEmail());
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtemail.setColumns(10);
		txtemail.setBounds(76, 5, 480, 53);
		panel_1_3.add(txtemail);
		JCheckBox show_password = new JCheckBox("Show Password");
		show_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (show_password.isSelected()) 
					txtpassword.setEchoChar((char)0);
				else 
					txtpassword.setEchoChar('*');
			}
		});
		show_password.setForeground(new Color(240, 248, 255));
		show_password.setBackground(new Color(0, 139, 139));
		show_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		show_password.setBounds(577, 437, 127, 23);
		profil.add(show_password);
		show_password.setFocusPainted(false); //butonun �st�ne bas�nca �izmesini engelleme
		
		
		JButton kaydet = new JButton("KAYDET");
		kaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Yonetici yonetici=new Yonetici(login.yonetici.getId(), txtname.getText(), txtlastname.getText(), txtemail.getText(), txtpassword.getText());
				String cevapString=YoneticiDao.YoneticiUpdate(yonetici);
				JOptionPane.showMessageDialog(null,cevapString);
				login.yonetici=yonetici;
				txtname.setText(yonetici.getName());
				txtlastname.setText(yonetici.getLastname());
				txtemail.setText(yonetici.getEmail());
				txtpassword.setText(yonetici.getPassword());

			}
		});
		kaydet.setBounds(304, 500, 263, 50);
		profil.add(kaydet);
		kaydet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				kaydet.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				kaydet.setBackground(Color.white);
			}
		});
		
		kaydet.setForeground(new Color(0, 51, 204));
		kaydet.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		kaydet.setFocusPainted(false);
		kaydet.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		kaydet.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(home.class.getResource("/images/users_operations.png")));
		lblNewLabel_3.setBounds(10, 11, 115, 63);
		profil.add(lblNewLabel_3);
		
		
		
		
		JPanel alanedit�ri�lemleri = new JPanel();
		alanedit�ri�lemleri.setLayout(null);
		alanedit�ri�lemleri.setForeground(Color.WHITE);
		alanedit�ri�lemleri.setBackground(new Color(119, 136, 153));
		alanedit�ri�lemleri.setBounds(250, 1430, 910, 670);
		panel.add(alanedit�ri�lemleri);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(51, 153, 153));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.setBounds(0, 0, 910, 670);
		alanedit�ri�lemleri.add(tabbedPane);
		
		JPanel yenikay�t = new JPanel();
		yenikay�t.setBackground(new Color(119, 136, 153));
		yenikay�t.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.addTab("ALAN ED\u0130T\u00D6R\u00DC YEN\u0130 KAYIT", null, yenikay�t, null);
		yenikay�t.setLayout(null);
		
		txtalanad = new JTextField();
		txtalanad.setBounds(140, 145, 616, 51);
		txtalanad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtalanad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalanad.setColumns(10);
		yenikay�t.add(txtalanad);
		
		JTextField txtalansoyad = new JTextField();
		txtalansoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalansoyad.setColumns(10);
		txtalansoyad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtalansoyad.setBounds(140, 224, 616, 51);
		yenikay�t.add(txtalansoyad);
		
		txtalanemail = new JTextField();
		txtalanemail.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalanemail.setColumns(10);
		txtalanemail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtalanemail.setBounds(140, 305, 616, 51);
		yenikay�t.add(txtalanemail);
		
		passwordField = new JPasswordField((String) null);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBounds(140, 382, 616, 53);
		yenikay�t.add(passwordField);
		
		JCheckBox show_password_1 = new JCheckBox("Show Password");
		show_password_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (show_password_1.isSelected()) 
					passwordField.setEchoChar((char)0);
				else 
					passwordField.setEchoChar('*');
			}
		});
		show_password_1.setForeground(new Color(240, 248, 255));
		show_password_1.setBackground(new Color(0, 139, 139));
		show_password_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		show_password_1.setBounds(629, 442, 127, 23);
		yenikay�t.add(show_password_1);
		show_password_1.setFocusPainted(false); //butonun �st�ne bas�nca �izmesini engelleme
		
		comboBoxalanedit�r.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBoxalanedit�r.setBounds(140, 464, 306, 51);
		yenikay�t.add(comboBoxalanedit�r);
		
		JLabel lblNewLabel = new JLabel("AD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 166, 106, 30);
		yenikay�t.add(lblNewLabel);
		
		JLabel lblSoyad = new JLabel("SOYAD");
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoyad.setBounds(10, 245, 106, 30);
		yenikay�t.add(lblSoyad);
		
		JLabel lblEmail = new JLabel("EMA\u0130L");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(10, 326, 106, 30);
		yenikay�t.add(lblEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u015E\u0130FRE");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 403, 106, 30);
		yenikay�t.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("KATEGOR\u0130");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(10, 484, 120, 30);
		yenikay�t.add(lblNewLabel_1_2);
		
		JButton btnalanedit�r�kaydet = new JButton("KAYDET");
		btnalanedit�r�kaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				Kategori ka = new Kategori(((Kategori)comboBoxalanedit�r.getSelectedItem()).getId(), " ");
                
                Alaneditor ed = new Alaneditor(txtalanad.getText(), txtalansoyad.getText(), txtalanemail.getText(), passwordField.getText().toString(), ka);
                
                txtalanad.setText(""); txtalansoyad.setText(""); txtalanemail.setText(""); passwordField.setText("");
                
                String cv = null;
                
                try {
                    cv = AeditorDao.AeditorInsert(ed);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(null, cv);

				
				
			}
		});
		btnalanedit�r�kaydet.setBounds(493, 544, 263, 50);
		btnalanedit�r�kaydet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnalanedit�r�kaydet.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnalanedit�r�kaydet.setBackground(Color.white);
			}
		});
		
		btnalanedit�r�kaydet.setForeground(new Color(0, 51, 204));
		btnalanedit�r�kaydet.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnalanedit�r�kaydet.setFocusPainted(false);
		btnalanedit�r�kaydet.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnalanedit�r�kaydet.setBackground(Color.WHITE);
		yenikay�t.add(btnalanedit�r�kaydet);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("ALAN ED\u0130T\u00D6R\u00DC \u0130\u015ELEMLER\u0130");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2_1_1.setBounds(325, 52, 316, 51);
		yenikay�t.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(home.class.getResource("/images/operations.png")));
		lblNewLabel_4.setBounds(10, 11, 120, 65);
		yenikay�t.add(lblNewLabel_4);
		
		JPanel guncelles�l = new JPanel();
		guncelles�l.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.addTab("ALAN ED\u0130T\u00D6R\u00DC G\u00DCNCELLE S\u0130L", null, guncelles�l, null);
		guncelles�l.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 885, 244);
		guncelles�l.add(scrollPane_1);
		
		tablealanedit�r = new JTable() {					//isCellEditable ekledim ��nk� tablea 2kere t�klay�nca �st�nde d�zenlemeyi engellemek i�in*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tablealanedit�r.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtalanguncelledad.setText(modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),1).toString());
				txtalanguncellesoyad.setText((String) modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),2)); 
				txtalanguncellemail.setText((String) modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),3)); 
				passwordField_1.setText((String) modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),4));
				comboboxalanedit�rg�ncellesil.setSelectedIndex(getSelectedItem(modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),5).toString(), comboboxalanedit�rg�ncellesil));
				idkontrol=Integer.parseInt(modelimalanedit�r.getValueAt(tablealanedit�r.getSelectedRow(),0).toString());
			}
		});
		scrollPane_1.setViewportView(tablealanedit�r);
		 
	    modelimalanedit�r.setColumnIdentifiers(columnalanedit�r);
	    tablealanedit�r.setFont(new Font("Tahoma", Font.BOLD, 12));
		tablealanedit�r.setSelectionBackground(new Color(249,105,14));
		tablealanedit�r.setSelectionForeground(Color.white);
		tablealanedit�r.setRowHeight(30);
		tablealanedit�r.setShowGrid(true);
		tablealanedit�r.setBackground(new Color(248,248,248));
		tablealanedit�r.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 13));
		tablealanedit�r.getTableHeader().setBackground(Color.red);
		tablealanedit�r.getTableHeader().setForeground(Color.white);
		tablealanedit�r.getTableHeader().setOpaque(false);
	    
	    
		JLabel lblNewLabel_1 = new JLabel("AD");
		lblNewLabel_1.setBounds(87, 287, 106, 30);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		guncelles�l.add(lblNewLabel_1);
		
		txtalanguncelledad = new JTextField();
		txtalanguncelledad.setBounds(217, 287, 306, 30);
		txtalanguncelledad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalanguncelledad.setColumns(10);
		txtalanguncelledad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		guncelles�l.add(txtalanguncelledad);
		
		JLabel lblSoyad_1 = new JLabel("SOYAD");
		lblSoyad_1.setBounds(87, 341, 106, 30);
		lblSoyad_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		guncelles�l.add(lblSoyad_1);
		
		txtalanguncellesoyad = new JTextField();
		txtalanguncellesoyad.setBounds(217, 341, 306, 30);
		txtalanguncellesoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalanguncellesoyad.setColumns(10);
		txtalanguncellesoyad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		guncelles�l.add(txtalanguncellesoyad);
		
		JLabel lblEmail_1 = new JLabel("EMA\u0130L");
		lblEmail_1.setBounds(87, 392, 106, 30);
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		guncelles�l.add(lblEmail_1);
		
		txtalanguncellemail = new JTextField();
		txtalanguncellemail.setBounds(217, 392, 306, 30);
		txtalanguncellemail.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtalanguncellemail.setColumns(10);
		txtalanguncellemail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		guncelles�l.add(txtalanguncellemail);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015E\u0130FRE");
		lblNewLabel_1_1_1.setBounds(87, 443, 106, 30);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		guncelles�l.add(lblNewLabel_1_1_1);
		
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("KATEGOR\u0130");
		lblNewLabel_1_2_1.setBounds(87, 518, 120, 30);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		guncelles�l.add(lblNewLabel_1_2_1);
		comboboxalanedit�rg�ncellesil.setBounds(217, 498, 306, 51);
		
		comboboxalanedit�rg�ncellesil.setFont(new Font("Tahoma", Font.BOLD, 15));
		guncelles�l.add(comboboxalanedit�rg�ncellesil);
		
		JButton btnalanedit�rg�ncelle = new JButton("GUNCELLE");
		btnalanedit�rg�ncelle.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Alaneditor alaneditor=new Alaneditor();
				
				if(idkontrol != -1)
                {	
					Kategori kategori=new Kategori(((Kategori)comboboxalanedit�rg�ncellesil.getSelectedItem()).getId(), null);
					alaneditor.setName(txtalanguncelledad.getText());
					alaneditor.setLastname(txtalanguncellesoyad.getText());
					alaneditor.setEmail(txtalanguncellemail.getText());
					alaneditor.setPassword(passwordField_1.getText());
					alaneditor.setId(idkontrol);
					alaneditor.setKategori(kategori);
					idkontrol=-1;
                String cv = null;
                
                 try {
                    cv = AeditorDao.AeditorUpdate(alaneditor);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                 
                 JOptionPane.showMessageDialog(null, cv);
                 txtalanguncelledad.setText("");
                 txtalanguncellesoyad.setText("");
                 txtalanguncellemail.setText("");
                 passwordField_1.setText("");
                    alaneditor = null;
                    try {
                        alanedit�rtablodoldur();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null , "Once alan Editoru seciniz!","</>",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnalanedit�rg�ncelle.setBounds(479, 586, 177, 40);
		btnalanedit�rg�ncelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnalanedit�rg�ncelle.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnalanedit�rg�ncelle.setBackground(Color.white);
			}
		});
		btnalanedit�rg�ncelle.setForeground(new Color(0, 51, 204));
		btnalanedit�rg�ncelle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnalanedit�rg�ncelle.setFocusPainted(false);
		btnalanedit�rg�ncelle.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnalanedit�rg�ncelle.setBackground(Color.WHITE);
		guncelles�l.add(btnalanedit�rg�ncelle);
		
		passwordField_1 = new JPasswordField((String) null);
		passwordField_1.setBounds(217, 445, 306, 30);
		passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		guncelles�l.add(passwordField_1);
		
		JCheckBox show_password_1_1 = new JCheckBox("Show Password");
		show_password_1_1.setBounds(529, 450, 127, 23);
		show_password_1_1.setForeground(new Color(240, 248, 255));
		show_password_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (show_password_1_1.isSelected()) 
					passwordField_1.setEchoChar((char)0);
				else 
					passwordField_1.setEchoChar('*');
			}
		});
		show_password_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		show_password_1_1.setFocusPainted(false);
		show_password_1_1.setBackground(new Color(0, 139, 139));
		guncelles�l.add(show_password_1_1);
		
		JButton btnalanedit�rsil = new JButton("SIL");
		btnalanedit�rsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(idkontrol != -1)
                {	
					String cevapString=AeditorDao.AeditorDelete(idkontrol);
                    JOptionPane.showMessageDialog(null , cevapString);
                    idkontrol=-1;
                    try {
						alanedit�rtablodoldur();
						txtalanguncelledad.setText("");
		                 txtalanguncellesoyad.setText("");
		                 txtalanguncellemail.setText("");
		                 passwordField_1.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                else
                    JOptionPane.showMessageDialog(null , "Once alan Editoru seciniz!","</>",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnalanedit�rsil.setBounds(681, 586, 177, 40);
		btnalanedit�rsil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnalanedit�rsil.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnalanedit�rsil.setBackground(Color.white);
			}
		});
		btnalanedit�rsil.setForeground(new Color(0, 51, 204));
		btnalanedit�rsil.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnalanedit�rsil.setFocusPainted(false);
		btnalanedit�rsil.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnalanedit�rsil.setBackground(Color.WHITE);
		guncelles�l.add(btnalanedit�rsil);
		
		JButton btnalantabloyenile = new JButton("TABLO YENILE");
		btnalantabloyenile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alanedit�rtablodoldur();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnalantabloyenile.setForeground(new Color(0, 51, 204));
		btnalantabloyenile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnalantabloyenile.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnalantabloyenile.setBackground(Color.white);
			}
		});
		btnalantabloyenile.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnalantabloyenile.setFocusPainted(false);
		btnalantabloyenile.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnalantabloyenile.setBackground(Color.WHITE);
		btnalantabloyenile.setBounds(718, 261, 177, 40);
		guncelles�l.add(btnalantabloyenile);
		
		
		
		JPanel hakemi�lemleri = new JPanel();
		hakemi�lemleri.setLayout(null);
		hakemi�lemleri.setForeground(Color.WHITE);
		hakemi�lemleri.setBackground(new Color(119, 136, 153));
		hakemi�lemleri.setBounds(250, 2130, 910, 670);
		panel.add(hakemi�lemleri);
		
		JTabbedPane hakemtabbedpane = new JTabbedPane(JTabbedPane.TOP);
		hakemtabbedpane.setFont(new Font("Tahoma", Font.BOLD, 15));
		hakemtabbedpane.setBackground(new Color(51, 153, 153));
		hakemtabbedpane.setBounds(0, 0, 910, 670);
		hakemi�lemleri.add(hakemtabbedpane);
		
		JPanel hakemyenikay�t = new JPanel();
		hakemyenikay�t.setLayout(null);
		hakemyenikay�t.setFont(new Font("Tahoma", Font.BOLD, 15));
		hakemyenikay�t.setBackground(new Color(119, 136, 153));
		hakemtabbedpane.addTab("HAKEM YEN\u0130 KAYIT", null, hakemyenikay�t, null);
		
		txthakemad = new JTextField();
		txthakemad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakemad.setColumns(10);
		txthakemad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakemad.setBounds(140, 145, 616, 51);
		hakemyenikay�t.add(txthakemad);
		
		txthakemsoyad = new JTextField();
		txthakemsoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakemsoyad.setColumns(10);
		txthakemsoyad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakemsoyad.setBounds(140, 224, 616, 51);
		hakemyenikay�t.add(txthakemsoyad);
		
		txthakememail = new JTextField();
		txthakememail.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakememail.setColumns(10);
		txthakememail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakememail.setBounds(140, 305, 616, 51);
		hakemyenikay�t.add(txthakememail);
		
		passwordhakem = new JPasswordField((String) null);
		passwordhakem.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordhakem.setBounds(140, 382, 616, 53);
		hakemyenikay�t.add(passwordhakem);
		
		JCheckBox hakemsifreg�ster = new JCheckBox("Show Password");
		hakemsifreg�ster.setForeground(new Color(240, 248, 255));
		hakemsifreg�ster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hakemsifreg�ster.isSelected()) 
					passwordhakem.setEchoChar((char)0);
				else 
					passwordhakem.setEchoChar('*');
			}
		});
		hakemsifreg�ster.setFont(new Font("Tahoma", Font.BOLD, 13));
		hakemsifreg�ster.setFocusPainted(false);
		hakemsifreg�ster.setBackground(new Color(0, 139, 139));
		hakemsifreg�ster.setBounds(629, 442, 127, 23);
		hakemyenikay�t.add(hakemsifreg�ster);
		
		hakemcombobox.setFont(new Font("Tahoma", Font.BOLD, 15));
		hakemcombobox.setBounds(140, 464, 306, 51);
		hakemyenikay�t.add(hakemcombobox);
		
		JLabel lblNewLabel_2 = new JLabel("AD");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 166, 106, 30);
		hakemyenikay�t.add(lblNewLabel_2);
		
		JLabel lblSoyad_2 = new JLabel("SOYAD");
		lblSoyad_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoyad_2.setBounds(10, 245, 106, 30);
		hakemyenikay�t.add(lblSoyad_2);
		
		JLabel lblEmail_2 = new JLabel("EMA\u0130L");
		lblEmail_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail_2.setBounds(10, 326, 106, 30);
		hakemyenikay�t.add(lblEmail_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\u015E\u0130FRE");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(10, 403, 106, 30);
		hakemyenikay�t.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("KATEGOR\u0130");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_2.setBounds(10, 484, 120, 30);
		hakemyenikay�t.add(lblNewLabel_1_2_2);
		
		JButton btnhakemkaydet = new JButton("KAYDET");
		btnhakemkaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Kategori kategoriHakem = new Kategori(((Kategori)hakemcombobox.getSelectedItem()).getId(), " ");
                
                Hakem hakem = new Hakem(txthakemad.getText(), txthakemsoyad.getText(), txthakememail.getText(), passwordhakem.getText().toString(), kategoriHakem);
                
                txthakemad.setText(""); txthakemsoyad.setText(""); txthakememail.setText(""); passwordhakem.setText("");
                
                String cvv = null;
                
                try {
                    cvv = HakemDao.HakemInsert(hakem);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(null, cvv);

			}
		});
		btnhakemkaydet.setForeground(new Color(0, 51, 204));
		btnhakemkaydet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnhakemkaydet.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnhakemkaydet.setBackground(Color.white);
			}
		});
		btnhakemkaydet.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnhakemkaydet.setFocusPainted(false);
		btnhakemkaydet.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnhakemkaydet.setBackground(Color.WHITE);
		btnhakemkaydet.setBounds(493, 544, 263, 50);
		hakemyenikay�t.add(btnhakemkaydet);
		
		JLabel lblNewLabel_2_1 = new JLabel("HAKEM \u0130\u015ELEMLER\u0130");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2_1.setBounds(349, 56, 230, 51);
		hakemyenikay�t.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon(home.class.getResource("/images/operations.png")));
		lblNewLabel_4_1.setBounds(10, 11, 120, 65);
		hakemyenikay�t.add(lblNewLabel_4_1);
		
		JPanel hakemguncelles�l = new JPanel();
		hakemguncelles�l.setLayout(null);
		hakemguncelles�l.setFont(new Font("Tahoma", Font.BOLD, 15));
		hakemtabbedpane.addTab("HAKEM G\u00DCNCELLE S\u0130L", null, hakemguncelles�l, null);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 11, 885, 244);
		hakemguncelles�l.add(scrollPane_1_1);
		
		hakemtable = new JTable() {					//isCellEditable ekledim ��nk� tablea 2kere t�klay�nca �st�nde d�zenlemeyi engellemek i�in*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		hakemtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txthakemguncellead.setText(modelimhakem.getValueAt(hakemtable.getSelectedRow(),1).toString());
				txthakemguncellesoyad.setText((String) modelimhakem.getValueAt(hakemtable.getSelectedRow(),2)); 
				txthakemguncelleemail.setText((String) modelimhakem.getValueAt(hakemtable.getSelectedRow(),3)); 
				passwordguncelles�fre.setText((String) modelimhakem.getValueAt(hakemtable.getSelectedRow(),4));
				comboboxguncellekategorihakem.setSelectedIndex(getSelectedItem(modelimhakem.getValueAt(hakemtable.getSelectedRow(),5).toString(), comboboxguncellekategorihakem));
				idkontrol=Integer.parseInt(modelimhakem.getValueAt(hakemtable.getSelectedRow(),0).toString());
			
			}
		});
		scrollPane_1_1.setViewportView(hakemtable);
	    modelimhakem.setColumnIdentifiers(columnhakem);
	    hakemtable.setFont(new Font("Tahoma", Font.BOLD, 12));
	    hakemtable.setSelectionBackground(new Color(249,105,14));
	    hakemtable.setSelectionForeground(Color.white);
	    hakemtable.setRowHeight(30);
	    hakemtable.setShowGrid(true);
	    hakemtable.setBackground(new Color(248,248,248));
	    hakemtable.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 13));
	    hakemtable.getTableHeader().setBackground(Color.red);
	    hakemtable.getTableHeader().setForeground(Color.white);
	    hakemtable.getTableHeader().setOpaque(false);

		
		
		JLabel lblNewLabel_1_3 = new JLabel("AD");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(87, 287, 106, 30);
		hakemguncelles�l.add(lblNewLabel_1_3);
		
		txthakemguncellead = new JTextField();
		txthakemguncellead.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakemguncellead.setColumns(10);
		txthakemguncellead.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakemguncellead.setBounds(217, 287, 306, 30);
		hakemguncelles�l.add(txthakemguncellead);
		
		JLabel lblSoyad_1_1 = new JLabel("SOYAD");
		lblSoyad_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoyad_1_1.setBounds(87, 341, 106, 30);
		hakemguncelles�l.add(lblSoyad_1_1);
		
		txthakemguncellesoyad = new JTextField();
		txthakemguncellesoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakemguncellesoyad.setColumns(10);
		txthakemguncellesoyad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakemguncellesoyad.setBounds(217, 341, 306, 30);
		hakemguncelles�l.add(txthakemguncellesoyad);
		
		JLabel lblEmail_1_1 = new JLabel("EMA\u0130L");
		lblEmail_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail_1_1.setBounds(87, 392, 106, 30);
		hakemguncelles�l.add(lblEmail_1_1);
		
		txthakemguncelleemail = new JTextField();
		txthakemguncelleemail.setFont(new Font("Tahoma", Font.BOLD, 18));
		txthakemguncelleemail.setColumns(10);
		txthakemguncelleemail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txthakemguncelleemail.setBounds(217, 392, 306, 30);
		hakemguncelles�l.add(txthakemguncelleemail);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u015E\u0130FRE");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(87, 443, 106, 30);
		hakemguncelles�l.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("KATEGOR\u0130");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(87, 518, 120, 30);
		hakemguncelles�l.add(lblNewLabel_1_2_1_1);
		
		comboboxguncellekategorihakem.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboboxguncellekategorihakem.setBounds(217, 498, 306, 51);
		hakemguncelles�l.add(comboboxguncellekategorihakem);
		
		JButton btnhakemguncelle = new JButton("GUNCELLE");
		btnhakemguncelle.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				Hakem hakem=new Hakem();
				
				if(idkontrol != -1)
                {	
					Kategori kategori=new Kategori(((Kategori)comboboxguncellekategorihakem.getSelectedItem()).getId(), null);
					hakem.setName(txthakemguncellead.getText());
					hakem.setLastname(txthakemguncellesoyad.getText());
					hakem.setEmail(txthakemguncelleemail.getText());
					hakem.setPassword(passwordguncelles�fre.getText());
					hakem.setId(idkontrol);
					hakem.setKategori(kategori);
					idkontrol=-1;
                String cevap = null;
                
                 try {
                	 cevap = HakemDao.HakemUpdate(hakem);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                 
                 JOptionPane.showMessageDialog(null, cevap);
                 txthakemguncellead.setText("");
                 txthakemguncellesoyad.setText("");
                 txthakemguncelleemail.setText("");
                 passwordguncelles�fre.setText("");
                    hakem = null;
                    try {
					hakemtablodoldur();
					} catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null , "Once Hakem seciniz!","</>",JOptionPane.WARNING_MESSAGE);

				
			}
		});
		btnhakemguncelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnhakemguncelle.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnhakemguncelle.setBackground(Color.white);
			}
		});
		btnhakemguncelle.setForeground(new Color(0, 51, 204));
		btnhakemguncelle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnhakemguncelle.setFocusPainted(false);
		btnhakemguncelle.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnhakemguncelle.setBackground(Color.WHITE);
		btnhakemguncelle.setBounds(479, 586, 177, 40);
		hakemguncelles�l.add(btnhakemguncelle);
		
		passwordguncelles�fre = new JPasswordField((String) null);
		passwordguncelles�fre.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordguncelles�fre.setBounds(217, 445, 306, 30);
		hakemguncelles�l.add(passwordguncelles�fre);
		
		JCheckBox hakemguncelles�fregoster = new JCheckBox("Show Password");
		hakemguncelles�fregoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hakemguncelles�fregoster.isSelected()) 
					passwordguncelles�fre.setEchoChar((char)0);
				else 
					passwordguncelles�fre.setEchoChar('*');
			}
		});
		hakemguncelles�fregoster.setForeground(new Color(240, 248, 255));
		hakemguncelles�fregoster.setFont(new Font("Tahoma", Font.BOLD, 13));
		hakemguncelles�fregoster.setFocusPainted(false);
		hakemguncelles�fregoster.setBackground(new Color(0, 139, 139));
		hakemguncelles�fregoster.setBounds(529, 450, 127, 23);
		hakemguncelles�l.add(hakemguncelles�fregoster);
		
		JButton btnhakemsil = new JButton("SIL");
		btnhakemsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(idkontrol != -1)
                {	
					String hakemcevapString=HakemDao.hakemDelete(idkontrol);
                    JOptionPane.showMessageDialog(null , hakemcevapString);
                    idkontrol=-1;
                    try {
						hakemtablodoldur();
						txthakemguncellead.setText("");
		                 txthakemguncellesoyad.setText("");
		                 txthakemguncelleemail.setText("");
		                 passwordguncelles�fre.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                else
                    JOptionPane.showMessageDialog(null , "Once Hakem seciniz!","</>",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnhakemsil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnhakemsil.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnhakemsil.setBackground(Color.white);
			}
		});
		btnhakemsil.setForeground(new Color(0, 51, 204));
		btnhakemsil.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnhakemsil.setFocusPainted(false);
		btnhakemsil.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnhakemsil.setBackground(Color.WHITE);
		btnhakemsil.setBounds(681, 586, 177, 40);
		hakemguncelles�l.add(btnhakemsil);
		
		JButton btnhakemtabloyenile = new JButton("TABLO YENILE");
		btnhakemtabloyenile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hakemtablodoldur();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnhakemtabloyenile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnhakemtabloyenile.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnhakemtabloyenile.setBackground(Color.white);
			}
		});
		btnhakemtabloyenile.setForeground(new Color(0, 51, 204));
		btnhakemtabloyenile.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btnhakemtabloyenile.setFocusPainted(false);
		btnhakemtabloyenile.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnhakemtabloyenile.setBackground(Color.WHITE);
		btnhakemtabloyenile.setBounds(718, 261, 177, 40);
		hakemguncelles�l.add(btnhakemtabloyenile);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setVisible(false);
		scrollBar.setMaximum(2200);
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				anasayfa.setBounds(250, 30-scrollBar.getValue(), 910, 670);
				profil.setBounds(250, 730-scrollBar.getValue(), 910, 670);
				alanedit�ri�lemleri.setBounds(250, 1430-scrollBar.getValue(), 910, 670);
				hakemi�lemleri.setBounds(250, 2130-scrollBar.getValue(), 910, 670);

			}
		});
		scrollBar.setBounds(1156, 33, 14, 667);
		panel.add(scrollBar);
		
		
		btnHakemIslem = new JButton("Hakem \u0130\u015Flemleri");
		btnHakemIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(2100);
			}
		});
		btnHakemIslem.setBounds(52, 338, 150, 40);
		btnHakemIslem.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnHakemIslem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHakemIslem.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnHakemIslem.setBorderPainted(false);

			}
		});
		btnHakemIslem.setFocusPainted(false);
		btnHakemIslem.setBorderPainted(false);
		btnHakemIslem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHakemIslem.setContentAreaFilled(false);
		men�.add(btnHakemIslem);
		
		btnProfil = new JButton("Profil");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(700);
			}
		});
		btnProfil.setBounds(52, 274, 150, 40);
		btnProfil.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProfil.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnProfil.setBorderPainted(false);

			}
		});
		btnProfil.setBorderPainted(false);
		btnProfil.setFocusPainted(false);
		btnProfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnProfil.setContentAreaFilled(false);
		men�.add(btnProfil);
		
		btnAEditor = new JButton("Alan Edit\u00F6r\u00FC \u0130\u015Flemleri");
		btnAEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(1400);
			}
		});
		btnAEditor.setBounds(35, 410, 178, 40);
		btnAEditor.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnAEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAEditor.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAEditor.setBorderPainted(false);
			}
		});
		btnAEditor.setFocusPainted(false);
		btnAEditor.setBorderPainted(false);
		btnAEditor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAEditor.setContentAreaFilled(false);
		men�.add(btnAEditor);
		
		btnAnasayfa = new JButton("Anasayfa");
		btnAnasayfa.setBounds(52, 205, 150, 40);
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(0);
			}
		});
		btnAnasayfa.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnAnasayfa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnasayfa.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAnasayfa.setBorderPainted(false);

			}
		});
		btnAnasayfa.setFocusPainted(false);
		btnAnasayfa.setBorderPainted(false);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnasayfa.setContentAreaFilled(false);
		men�.add(btnAnasayfa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 890, 347);
		anasayfa.add(scrollPane);
		
		table = new JTable() {					//isCellEditable ekledim ��nk� tablea 2kere t�klay�nca �st�nde d�zenlemeyi engellemek i�in*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				se�ilenmakaleid=Integer.parseInt(modelim.getValueAt(table.getSelectedRow(),0).toString());
				lblYazarAd�.setText("Yazar Ad� = "+modelim.getValueAt(table.getSelectedRow(),1).toString());
				lblYazarSoyad�.setText("Yazar Soyad� = "+(String) modelim.getValueAt(table.getSelectedRow(),2)); 
				lblMakaleBilgi.setText("Makale Bilgisi = "+(String) modelim.getValueAt(table.getSelectedRow(),3)); 
				mailString= modelim.getValueAt(table.getSelectedRow(),4).toString();
			}
		});
		table.setBounds(100, 450, 1, 1);
		scrollPane.setViewportView(table);
	    modelim.setColumnIdentifiers(column);
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
		
		JButton btnAlanEditoreYonlendir = new JButton("Alan Edit\u00F6r\u00FCne Y\u00F6nlendir");
		btnAlanEditoreYonlendir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (se�ilenmakaleid!=-1) {
					AlanEditorSec alanEditorSec;
					try {
						alanEditorSec = new AlanEditorSec();
						alanEditorSec.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null , "Once alan Editoru seciniz!","</>",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAlanEditoreYonlendir.setBackground(new Color(0, 0, 128));
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
		btnAlanEditoreYonlendir.setBounds(700, 369, 200, 40);
		anasayfa.add(btnAlanEditoreYonlendir);
		
		JButton btnMakaleUygunDeildir = new JButton("Makale Uygun Degildir");
		btnMakaleUygunDeildir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakaleUygunDegildir makaleUygunDegildir=new MakaleUygunDegildir();
				makaleUygunDegildir.setVisible(true);
			}
		});
		btnMakaleUygunDeildir.setForeground(new Color(255, 0, 0));
		btnMakaleUygunDeildir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMakaleUygunDeildir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMakaleUygunDeildir.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnMakaleUygunDeildir.setBorderPainted(false);
			}
		});
		btnMakaleUygunDeildir.setFocusPainted(false);
		btnMakaleUygunDeildir.setContentAreaFilled(false);
		btnMakaleUygunDeildir.setBorderPainted(false);
		btnMakaleUygunDeildir.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnMakaleUygunDeildir.setBackground(new Color(0, 0, 128));
		btnMakaleUygunDeildir.setBounds(700, 432, 200, 40);
		anasayfa.add(btnMakaleUygunDeildir);
		
		WhereKategori();
		fill_table();
		alanedit�rtablodoldur();
		hakemtablodoldur();
		
	}
	public JButton getBtnAddBook() {
		return btnHakemIslem;
	}
	public JButton getBtnDeleteBook() {
		return btnAEditor;
	}
	public JButton getBtnEditBook() {
		return btnProfil;
	}
	
	public JButton getBtnBookList() {
		return btnAnasayfa;
	}
}
