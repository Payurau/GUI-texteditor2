import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class texteditor extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnTiedosto;
	private JMenuItem mntmAvaa;
	private JMenuItem mntmTallenna;
	private JMenuItem mntmSulje;
	private JMenu mnMuokkaa;
	private JMenu mnInfo;
	private JMenuItem mntmEtsi;
	private JMenuItem mntmKorvaa;
	private JMenuItem mntmTietoja;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	

	private JEditorPane editorpane;
	private JTextField txtHaeTst;
	private JButton button;


	public void avaaTiedosto() {
		Scanner lukija=null;
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.setApproveButtonText("Avaa tiedosto");
		valintaikkuna.setDialogTitle("Tiedoston valinta");
		valintaikkuna.showOpenDialog(null);
		String rivi = "";
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();


	try {
		File tiedosto = new File (uusiTiedosto);
		lukija = new Scanner(tiedosto);
		
		while (lukija.hasNextLine()) {
			rivi += lukija.nextLine()+"\n";
			System.out.println(rivi);
		}
	}
		catch (FileNotFoundException p) {
			System.out.println("Tiedostoa ei löydy.");
		}

		editorpane.setText(rivi);
	}
	
	
	
	
	public void tallennaTiedosto() {
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showSaveDialog(null);
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		System.out.println("Kirjoitettava tiedosto: "+uusiTiedosto);
		
		try {
			PrintWriter writer = new PrintWriter(uusiTiedosto);
			String sisalto = editorpane.getText();
			
			writer.println(sisalto);
			
			writer.flush();
			writer.close();
		} catch (Exception e1) {
			System.out.println("Tallennuksessa tapahtui virhe!");
			e1.printStackTrace();
		}
	}
	
	
	JPanel myPanel = new JPanel();
	public void avaaTietoja() {
		
		
		JLabel kuvaus = new JLabel("<html>Nimi: Pauliina Rauramo<br/>Tiedoston luontipäivä: 21.10.2019</html>");
		kuvaus.setFont(new Font("Arial", Font.PLAIN,12));
		myPanel.add(kuvaus);
		
		JLabel kuva = new JLabel("");
		kuva = new javax.swing.JLabel();
		kuva.setIcon(new javax.swing.ImageIcon("C:\\Users\\pauli\\eclipse-workspace\\GUI\\src\\kuvia\\kukka.jpeg"));
		myPanel.add(kuva);
		
		JOptionPane.showConfirmDialog
		(
			null, 
			myPanel,
			"Tietoa tekijästä", JOptionPane.DEFAULT_OPTION,
			JOptionPane.PLAIN_MESSAGE
			
		);
	}
	
	public void etsi() {
		String uusi = txtHaeTst.getText();
		String sisalto = editorpane.getText();
		sisalto = sisalto.toLowerCase();
		int indeksi = sisalto.indexOf(uusi);
		
		if(indeksi<0) {
			System.out.println("Hakusanaa ei löytynyt");
		} else {
		
		System.out.println("Indeksi: "+indeksi);
		
		editorpane.setSelectionColor(Color.yellow);
		
		editorpane.setSelectionStart(indeksi);
		editorpane.setSelectionEnd(indeksi + uusi.length());
		}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					texteditor frame = new texteditor();
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
	public texteditor() {
		setFont(new Font("Arial", Font.PLAIN, 17));
		setForeground(SystemColor.activeCaption);
		setTitle("Tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 515);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		setJMenuBar(menuBar);
		
		mnTiedosto = new JMenu("Tiedosto");
		mnTiedosto.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mnTiedosto);
		
		mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTiedosto();
			}
		});
		
		
		mntmAvaa.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/open-folder-outline.png")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmAvaa.setFont(new Font("Arial", Font.PLAIN, 12));
		mnTiedosto.add(mntmAvaa);
		
		mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
		});
		mntmTallenna.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmTallenna.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/download-button.png")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		mntmSulje = new JMenuItem("Sulje");
		mntmSulje.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/close-button.png")));
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmSulje.setFont(new Font("Arial", Font.PLAIN, 12));
		mnTiedosto.add(mntmSulje);
		
		mnMuokkaa = new JMenu("Muokkaa");
		mnMuokkaa.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mnMuokkaa);
		String haettava = "auto";
		mntmEtsi = new JMenuItem("Etsi sanaa \"auto\"");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sisalto = editorpane.getText();
				sisalto = sisalto.toLowerCase();
				int indeksi = sisalto.indexOf(haettava);
				System.out.println("Indeksi: "+indeksi);
				
				editorpane.setSelectionColor(Color.RED);
				
				editorpane.setSelectionStart(indeksi);
				editorpane.setSelectionEnd(indeksi + haettava.length());
				
			}
		});
		mntmEtsi.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmEtsi);
		
		txtHaeTst = new JTextField();
		txtHaeTst.setToolTipText("Kirjoita hakusana ja paina Hae");
		mnMuokkaa.add(txtHaeTst);
		txtHaeTst.setColumns(10);
		
		mntmKorvaa = new JMenuItem("Hae");
		mntmKorvaa.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/search.png")));
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsi();

			}
		});
		mntmKorvaa.setFont(new Font("Arial", Font.PLAIN, 12));
		mnMuokkaa.add(mntmKorvaa);
		
		mnInfo = new JMenu("Info");
		mnInfo.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mnInfo);
		
		
		
		mntmTietoja = new JMenuItem("Tietoja");
		mntmTietoja.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/information.png")));
		mntmTietoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTietoja();

			}
		});
		mntmTietoja.setFont(new Font("Arial", Font.PLAIN, 12));
		mnInfo.add(mntmTietoja);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTiedosto();
			}
		});
		btnNewButton.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/folder.png")));
		toolBar.add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
			
		});
		btnNewButton_1.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/save.png")));
		toolBar.add(btnNewButton_1);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTietoja();
			}
		});
		button.setIcon(new ImageIcon(texteditor.class.getResource("/kuvia/information.png")));
		toolBar.add(button);
		
		editorpane = new JEditorPane();
		contentPane.add(editorpane, BorderLayout.CENTER);
	}

}
