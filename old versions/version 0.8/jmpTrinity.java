package packageNaMalupet;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class jmpTrinity extends JFrame {
	// Programmers:
	// Charles S. Carreon
	// Kurt Joshua P. Cayaga
	
	private static final long serialVersionUID = 1L;
	JPanel contentPane, songPlayingPanel, volumePanel;
	JTable table;
	
	JButton upButton, downButton, addButton, playButton, loopOneButton;
	
	int rowOfSelectedSong;
	String fileName, currentSongName;
	
	JFileChooser fileChooser;
	File musicFile0, musicFile1, musicFile2, musicFile3, musicFile4;
	File musicFile5, musicFile6, musicFile7, musicFile8, musicFile9;
	AudioInputStream inputAS;
	Clip clip;
		
	JLabel currentSongLabel, currentSongTitleLabel, volumeLabel;
	private JButton loopAllButton;
	private JButton shuffleButton;
	private JButton muteButton;

	float previousVolume = 0, currentVolume = 0;
	FloatControl floatControl;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jmpTrinity frame = new jmpTrinity();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public jmpTrinity() {
		setTitle("JMusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 380, 150);
		scrollPane.getVerticalScrollBar().setBackground(new Color(25, 20, 20));
		contentPane.add(scrollPane);
		
		// Table Contents
		String[] columnNames = {"#", "Song Title"};
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
		
		DefaultTableModel model = (new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		// Table
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(25, 20, 20));
		table.setBorder(null);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(30, 215, 96));
		table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
		
		// Table Header
		JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(30, 215, 96));
	    header.setForeground(new Color(25, 20, 20));
	    header.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
	    		
		// Current Song Playing
		songPlayingPanel = new JPanel();
		songPlayingPanel.setBounds(10, 159, 380, 55);
		songPlayingPanel.setBackground(new Color(25, 20, 20));
		contentPane.add(songPlayingPanel);
		songPlayingPanel.setLayout(null);
		
		currentSongLabel = new JLabel("Current Song Playing:");
		currentSongLabel.setForeground(new Color(30, 215, 96));
		currentSongLabel.setBounds(10, 11, 360, 14);
		songPlayingPanel.add(currentSongLabel);
		
		currentSongTitleLabel = new JLabel("");
		currentSongTitleLabel.setForeground(new Color(30, 215, 96));
		currentSongTitleLabel.setBounds(10, 30, 360, 14);
		songPlayingPanel.add(currentSongTitleLabel);
		
		// Up and Down Buttons
		rowOfSelectedSong = 0;
			
		// Up Button
		upButton = new JButton("Up");
		upButton.setBackground(new Color(25, 20, 20));
		upButton.setForeground(new Color(25, 20, 20));
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getValueAt(table.getSelectedRow(), 1) != null) {
					clip.stop();
					currentSongTitleLabel.setText(null);
				}
				
				if (rowOfSelectedSong > 1) {
					playButton.setText("Play");
					rowOfSelectedSong--;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					downButton.setEnabled(true);
					downButton.setBackground(new Color(30, 215, 96));
					
					if (model.getValueAt(table.getSelectedRow(), 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						loopOneButton.setEnabled(true);
						loopOneButton.setBackground(new Color(30, 215, 96));

						try {
							if (table.getSelectedRow() == 1) {
								inputAS = AudioSystem.getAudioInputStream(musicFile1);
							} else if (table.getSelectedRow() == 2) {
								inputAS = AudioSystem.getAudioInputStream(musicFile2);
							} else if (table.getSelectedRow() == 3) {
								inputAS = AudioSystem.getAudioInputStream(musicFile3);
							} else if (table.getSelectedRow() == 4) {
								inputAS = AudioSystem.getAudioInputStream(musicFile4);
							} else if (table.getSelectedRow() == 5) {
								inputAS = AudioSystem.getAudioInputStream(musicFile5);
							} else if (table.getSelectedRow() == 6) {
								inputAS = AudioSystem.getAudioInputStream(musicFile6);
							} else if (table.getSelectedRow() == 7) {
								inputAS = AudioSystem.getAudioInputStream(musicFile7);
							} else if (table.getSelectedRow() == 8) {
								inputAS = AudioSystem.getAudioInputStream(musicFile8);
							} else if (table.getSelectedRow() == 9) {
								inputAS = AudioSystem.getAudioInputStream(musicFile9);
							}
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						addButton.setText("Add");
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
						loopOneButton.setEnabled(false);
						loopOneButton.setBackground(new Color(25, 20, 20));
					}
				} else if (rowOfSelectedSong > 0) {
					playButton.setText("Play");
					rowOfSelectedSong--;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					upButton.setEnabled(false);
					upButton.setBackground(new Color(25, 20, 20));
					
					if (model.getValueAt(0, 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						clip.stop();
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile0);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						addButton.setText("Add");
						playButton.setEnabled(false);
						playButton.setBackground(new Color(25, 20, 20));
						loopOneButton.setEnabled(false);
						loopOneButton.setBackground(new Color(25, 20, 20));
					}
				}
			}
		});
		upButton.setBounds(150, 225, 90, 25);
		upButton.setEnabled(false);
		upButton.setFocusable(false);
		contentPane.add(upButton);
		
		// Down Button
		downButton = new JButton("Down");
		downButton.setBackground(new Color(30, 215, 96));
		downButton.setForeground(new Color(25, 20, 20));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getValueAt(table.getSelectedRow(), 1) != null) {
					clip.stop();
					currentSongTitleLabel.setText(null);
				}
				
				if (rowOfSelectedSong < 8) {
					playButton.setText("Play");
					rowOfSelectedSong++;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					upButton.setEnabled(true);
					upButton.setBackground(new Color(30, 215, 96));
								
					if (model.getValueAt(table.getSelectedRow(), 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						loopOneButton.setEnabled(true);
						loopOneButton.setBackground(new Color(30, 215, 96));
						
						try {
							if (table.getSelectedRow() == 0) {
								inputAS = AudioSystem.getAudioInputStream(musicFile0);
							} else if (table.getSelectedRow() == 1) {
								inputAS = AudioSystem.getAudioInputStream(musicFile1);
							} else if (table.getSelectedRow() == 2) {
								inputAS = AudioSystem.getAudioInputStream(musicFile2);
							} else if (table.getSelectedRow() == 3) {
								inputAS = AudioSystem.getAudioInputStream(musicFile3);
							} else if (table.getSelectedRow() == 4) {
								inputAS = AudioSystem.getAudioInputStream(musicFile4);
							} else if (table.getSelectedRow() == 5) {
								inputAS = AudioSystem.getAudioInputStream(musicFile5);
							} else if (table.getSelectedRow() == 6) {
								inputAS = AudioSystem.getAudioInputStream(musicFile6);
							} else if (table.getSelectedRow() == 7) {
								inputAS = AudioSystem.getAudioInputStream(musicFile7);
							} else if (table.getSelectedRow() == 8) {
								inputAS = AudioSystem.getAudioInputStream(musicFile8);
							}
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						addButton.setText("Add");
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
						loopOneButton.setEnabled(false);
						loopOneButton.setBackground(new Color(25, 20, 20));
					}
				} else if (rowOfSelectedSong < 9) {
					playButton.setText("Play");
					rowOfSelectedSong++;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					downButton.setEnabled(false);
					downButton.setBackground(new Color(25, 20, 20));
					
					if (model.getValueAt(9, 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						loopOneButton.setEnabled(true);
						loopOneButton.setBackground(new Color(30, 215, 96));
						
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile9);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						addButton.setText("Add");
						playButton.setEnabled(false);
						playButton.setBackground(new Color(25, 20, 20));
						loopOneButton.setEnabled(false);
						loopOneButton.setBackground(new Color(25, 20, 20));
					}
				}
			}
		});
		downButton.setBounds(150, 330, 90, 25);
		downButton.setEnabled(true);
		downButton.setFocusable(false);
		contentPane.add(downButton);
		
		// Add Music Button
		addButton = new JButton("Add");
		addButton.setBackground(new Color(30, 215, 96));
		addButton.setForeground(new Color(25, 20, 20));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getValueAt(table.getSelectedRow(), 1) != null) {
					clip.stop();
					playButton.setText("Play");
				} else {
					playButton.setEnabled(true);
					playButton.setBackground(new Color(30, 215, 96));
					addButton.setText("Change");
					loopOneButton.setEnabled(true);
					loopOneButton.setBackground(new Color(30, 215, 96));
				}
				
				fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				
				try {
					if (table.getSelectedRow() == 0) {
						musicFile0 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile0);
						fileName = musicFile0.getName();
					} else if (table.getSelectedRow() == 1) {
						musicFile1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile1);
						fileName = musicFile1.getName();
					} else if (table.getSelectedRow() == 2) {
						musicFile2 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile2);
						fileName = musicFile2.getName();
					} else if (table.getSelectedRow() == 3) {
						musicFile3 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile3);
						fileName = musicFile3.getName();
					} else if (table.getSelectedRow() == 4) {
						musicFile4 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile4);
						fileName = musicFile4.getName();
					} else if (table.getSelectedRow() == 5) {
						musicFile5 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile5);
						fileName = musicFile5.getName();
					} else if (table.getSelectedRow() == 6) {
						musicFile6 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile6);
						fileName = musicFile6.getName();
					} else if (table.getSelectedRow() == 7) {
						musicFile7 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile7);
						fileName = musicFile7.getName();
					} else if (table.getSelectedRow() == 8) {
						musicFile8 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile8);
						fileName = musicFile8.getName();
					} else if (table.getSelectedRow() == 9) {
						musicFile9 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						inputAS = AudioSystem.getAudioInputStream(musicFile9);
						fileName = musicFile9.getName();
					}
					clip = AudioSystem.getClip();
					clip.open(inputAS);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				model.setValueAt(fileName, table.getSelectedRow(), 1);
			}
		});
		addButton.setBounds(300, 225, 90, 25);
		addButton.setFocusable(false);
		contentPane.add(addButton);
		
		// Play Music Button
		playButton = new JButton("Play");
		playButton.setBackground(new Color(25, 20, 20));
		playButton.setForeground(new Color(25, 20, 20));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playButton.getText() == "Play") {
					clip.start();
					currentSongName = (String) model.getValueAt(table.getSelectedRow(), 1);
					currentSongTitleLabel.setText(currentSongName);
					playButton.setText("Pause");
				} else {
					clip.stop();
					currentSongTitleLabel.setText(null);
					playButton.setText("Play");
					
					if (loopOneButton.getText() == "Stop") {
						loopOneButton.setText("Loop One");
					}
				}
			}
		});
		playButton.setBounds(150, 277, 90, 25);
		playButton.setEnabled(false);
		playButton.setFocusable(false);
		contentPane.add(playButton);
		
		// Loop One Button
		loopOneButton = new JButton("Loop One");
		loopOneButton.setBackground(new Color(25, 20, 20));
		loopOneButton.setForeground(new Color(25, 20, 20));
		loopOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loopOneButton.getText() == "Loop One") {
					currentSongName = (String) model.getValueAt(table.getSelectedRow(), 1);
					currentSongTitleLabel.setText("(Looped) " + currentSongName);
					
					if (model.getValueAt(table.getSelectedRow(), 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						
						clip.loop(Clip.LOOP_CONTINUOUSLY);
						playButton.setText("Pause");
						loopOneButton.setText("Stop");
					}
				} else {
					clip.stop();
					currentSongTitleLabel.setText(null);
					loopOneButton.setText("Loop One");
					
					if (playButton.getText() == "Pause") {
						playButton.setText("Play");
					}
				}
			}
		});
		loopOneButton.setBounds(300, 277, 90, 25);
		loopOneButton.setEnabled(false);
		loopOneButton.setFocusable(false);
		contentPane.add(loopOneButton);
		
		// Volume Panel
		volumePanel = new JPanel();
		volumePanel.setBackground(new Color(25, 20, 20));
		volumePanel.setBounds(10, 217, 80, 157);
		contentPane.add(volumePanel);
		volumePanel.setLayout(null);
		
		// Volume Label
		volumeLabel = new JLabel("Volume:");
		volumeLabel.setBounds(10, 11, 46, 14);
		volumeLabel.setForeground(new Color(30, 215, 96));
		volumePanel.add(volumeLabel);
		
		// Volume Slider
		JSlider volumeSlider = new JSlider(SwingConstants.VERTICAL, -40, 6, 6);
		volumeSlider.setForeground(new Color(25, 20, 20));
		volumeSlider.setBackground(new Color(30, 215, 96));
		volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				currentVolume = volumeSlider.getValue();
				
				if (currentVolume == -40) {
					currentVolume = -80;
				}
				floatControl.setValue(currentVolume);
			}
		});
		volumeSlider.setBounds(10, 31, 20, 90);
		volumePanel.add(volumeSlider);
		
		// Mute Button
		muteButton = new JButton("Mute");
		muteButton.setBackground(new Color(30, 215, 96));
		muteButton.setForeground(new Color(25, 20, 20));
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				if(currentVolume != -80) {
					currentVolume = -80;
					volumeSlider.setValue(volumeSlider.getMinimum());
				} else {
					 currentVolume = 6;
					 volumeSlider.setValue(volumeSlider.getMaximum());
				}
				floatControl.setValue(currentVolume);
			}
		});
		muteButton.setBounds(0, 132, 80, 25);
		muteButton.setFocusable(false);
		volumePanel.add(muteButton);
				
		// No function Buttons
		// Loop All Button
		loopAllButton = new JButton("Loop All");
		loopAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopAllButton.setBounds(300, 313, 90, 25);
		loopAllButton.setFocusable(false);
		contentPane.add(loopAllButton);
		
		// Shuffle Button
		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		shuffleButton.setBounds(300, 349, 90, 25);
		shuffleButton.setFocusable(false);
		contentPane.add(shuffleButton);
		
		// Disabled Buttons
		loopAllButton.setEnabled(false);
		shuffleButton.setEnabled(false);
	}
}
