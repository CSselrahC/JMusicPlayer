package rndm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;

import javax.swing.DefaultListModel;




public class JMusicPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFileChooser fileChooser;
	File musicFile;
	AudioInputStream inputAS;
	Clip clip;
	JButton playButton;
	private JTable table;
	
	private DefaultListModel<String> modelFruits = new DefaultListModel<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMusicPlayer frame = new JMusicPlayer();
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
	public JMusicPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addButton = new JButton("Add Music");
		addButton.setForeground(new Color(128, 255, 255));
		addButton.setBackground(new Color(0, 128, 255));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				musicFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(musicFile);
				try {
					inputAS = AudioSystem.getAudioInputStream(musicFile);
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					clip.open(inputAS);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				playButton.setEnabled(true);
				playButton.setForeground(new Color(128, 255, 255));
				playButton.setBackground(new Color(0, 0, 255));
			}
		});
		addButton.setBounds(27, 357, 100, 50);
		addButton.setFocusable(false);
		contentPane.add(addButton);
		
		
		playButton = new JButton("Play");
		playButton.setFocusable(false);
		playButton.setEnabled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playButton.getText() == "Play") {
					clip.start();
					playButton.setText("Pause");
					playButton.setForeground(new Color(0, 0, 0));
					playButton.setBackground(new Color(0, 255, 0));
				} else {
					clip.stop();
					playButton.setText("Play");
					playButton.setForeground(new Color(128, 255, 255));
					playButton.setBackground(new Color(0, 0, 255));
				}
				
			}
		});
		playButton.setForeground(new Color(128, 255, 255));
		playButton.setBackground(new Color(124, 124, 124));
		playButton.setBounds(249, 357, 100, 50);
		contentPane.add(playButton);
		
		table = new JTable();
		table.setBounds(49, 34, 300, 200);
		contentPane.add(table);
	}
}
