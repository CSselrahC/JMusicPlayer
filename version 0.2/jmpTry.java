package packageNaMalupet;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class jmpTry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	int trackNumber;
	String songTitle;
	String trackNumberString;
	String fileName;
	JScrollPane scrollPane;
	
	JFileChooser fileChooser;
	File musicFile;
	AudioInputStream inputAS;
	Clip clip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jmpTry frame = new jmpTry();
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
	public jmpTry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton upButton = new JButton("Up");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		upButton.setBounds(153, 221, 89, 23);
		contentPane.add(upButton);
		
		JButton downButton = new JButton("Down");
		downButton.setBounds(153, 311, 89, 23);
		contentPane.add(downButton);
		
		JButton volumeUpButton = new JButton("Volume Up");
		volumeUpButton.setBounds(10, 299, 89, 23);
		contentPane.add(volumeUpButton);
		
		JButton volumeDownButton = new JButton("Volume Down");
		volumeDownButton.setBounds(10, 326, 89, 23);
		contentPane.add(volumeDownButton);
		
		JButton playButton = new JButton("Play");
		playButton.setForeground(new Color(128, 255, 255));
		playButton.setBackground(new Color(0, 0, 255));
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
		playButton.setBounds(153, 265, 89, 23);
		contentPane.add(playButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.setBounds(270, 265, 89, 23);
		contentPane.add(stopButton);
		
		
		
		String[] columnNames = {"Track Number", "Song Title"};
		
		String[][] data = {{"01", null},
							{"02", null},
							{"03", null},
							{"04", null},
							{"05", null},
							{"06", null},
							{"07", null},
							{"08", null},
							{"09", null},
							{"10", null}};
		
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		
		
		trackNumber = 0;
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trackNumber++;
				if (trackNumber <= 5) {
					fileChooser = new JFileChooser();
					fileChooser.showOpenDialog(null);
					musicFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
					System.out.println(musicFile);
					try {
						inputAS = AudioSystem.getAudioInputStream(musicFile);
						clip = AudioSystem.getClip();
						clip.open(inputAS);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					fileName = musicFile.getName();
					
					data[trackNumber - 1][1] = fileName;
					System.out.println(trackNumber);
					table = new JTable(data, columnNames);
				}
			}
		});
		addButton.setBounds(10, 221, 89, 23);
		contentPane.add(addButton);
		
		
		
		JButton loopAllButton = new JButton("Loop All");
		loopAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopAllButton.setBounds(270, 345, 89, 23);
		contentPane.add(loopAllButton);
		
		JButton loopOneButton = new JButton("Loop");
		loopOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopOneButton.setBounds(270, 311, 89, 23);
		contentPane.add(loopOneButton);
		
		JButton shuffleButton = new JButton("Shuffle");
		shuffleButton.setBounds(270, 372, 89, 23);
		contentPane.add(shuffleButton);
		
		
		scrollPane.setBounds(10, 22, 366, 137);
		contentPane.add(scrollPane);
		
		
	}
}
