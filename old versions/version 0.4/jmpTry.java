package packageNaMalupet;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class jmpTry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JTable table;
	
	int trackNumber, rowOfSelectedSong;
	String songTitle, trackNumberString, fileName;
	JScrollPane scrollPane;
	
	JFileChooser fileChooser;
	File musicFile0, musicFile1, musicFile2, musicFile3, musicFile4;
	File musicFile5, musicFile6, musicFile7, musicFile8, musicFile9;
	AudioInputStream inputAS;
	Clip clip;
	
	JButton playButton, addButton, upButton, downButton, stopButton;
	JButton loopAllButton, loopOneButton, shuffleButton, volumeUpButton, volumeDownButton;
	
	JLabel songLabel, currentSongLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jmpTry frame = new jmpTry();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
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
		setTitle("JMusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Table
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
		
		table = new JTable(data, columnNames);
		
		
		// Scroll Pane
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 22, 366, 137);
		contentPane.add(scrollPane);
		
		// Up and Down Buttons
		rowOfSelectedSong = 0;
		table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
			
		// Up Button
		upButton = new JButton("Up");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (data[table.getSelectedRow()][1] != null) {
					clip.stop();
					currentSongLabel.setText(null);
				}
				
				if (rowOfSelectedSong > 0) {
					rowOfSelectedSong--;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					System.out.println(rowOfSelectedSong);
					
					if (data[table.getSelectedRow()][1] != null) {
						playButton.setEnabled(true);
						playButton.setText("Play");
						playButton.setForeground(new Color(128, 255, 255));
						playButton.setBackground(new Color(0, 0, 255));
						if (table.getSelectedRow() == 0) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile0);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 1) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile1);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 2) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile2);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 3) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile3);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 4) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile4);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 5) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile5);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 6) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile6);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 7) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile7);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 8) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile8);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 9) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile9);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					} else {
						playButton.setText("Play");
						playButton.setEnabled(false);
						playButton.setForeground(new Color(128, 255, 255));
						playButton.setBackground(new Color(127, 127, 127));
					}
					
				}
			}
		});
		upButton.setBounds(153, 221, 89, 23);
		contentPane.add(upButton);
		
		// Down Button
		downButton = new JButton("Down");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (data[table.getSelectedRow()][1] != null) {
					clip.stop();
					currentSongLabel.setText(null);
				}
				
				if (rowOfSelectedSong < 9) {
					rowOfSelectedSong++;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					System.out.println(rowOfSelectedSong);
					
					if (data[table.getSelectedRow()][1] != null) {
						playButton.setEnabled(true);
						playButton.setText("Play");
						playButton.setForeground(new Color(128, 255, 255));
						playButton.setBackground(new Color(0, 0, 255));
						if (table.getSelectedRow() == 0) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile0);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 1) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile1);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 2) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile2);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 3) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile3);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 4) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile4);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 5) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile5);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 6) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile6);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 7) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile7);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 8) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile8);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (table.getSelectedRow() == 9) {
							try {
								inputAS = AudioSystem.getAudioInputStream(musicFile9);
								clip = AudioSystem.getClip();
								clip.open(inputAS);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					} else {
						playButton.setText("Play");
						playButton.setEnabled(false);
						playButton.setForeground(new Color(128, 255, 255));
						playButton.setBackground(new Color(127, 127, 127));
					}
				}
			}
		});
		downButton.setBounds(153, 311, 89, 23);
		contentPane.add(downButton);
		
		// Play Music Button
		playButton = new JButton("Play");
		playButton.setEnabled(false);
		playButton.setForeground(new Color(128, 255, 255));
		playButton.setBackground(new Color(127, 127, 127));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playButton.getText() == "Play") {
					
					if (table.getSelectedRow() == 0) {
						currentSongLabel.setText((String) table.getModel().getValueAt(0, 1));
					} else if (table.getSelectedRow() == 1) {
						currentSongLabel.setText((String) table.getModel().getValueAt(1, 1));
					} else if (table.getSelectedRow() == 2) {
						currentSongLabel.setText((String) table.getModel().getValueAt(2, 1));
					} else if (table.getSelectedRow() == 3) {
						currentSongLabel.setText((String) table.getModel().getValueAt(3, 1));
					} else if (table.getSelectedRow() == 4) {
						currentSongLabel.setText((String) table.getModel().getValueAt(4, 1));
					} else if (table.getSelectedRow() == 5) {
						currentSongLabel.setText((String) table.getModel().getValueAt(5, 1));
					} else if (table.getSelectedRow() == 6) {
						currentSongLabel.setText((String) table.getModel().getValueAt(6, 1));
					} else if (table.getSelectedRow() == 7) {
						currentSongLabel.setText((String) table.getModel().getValueAt(7, 1));
					} else if (table.getSelectedRow() == 8) {
						currentSongLabel.setText((String) table.getModel().getValueAt(8, 1));
					} else if (table.getSelectedRow() == 9) {
						currentSongLabel.setText((String) table.getModel().getValueAt(9, 1));
					}
					
					clip.start();
					playButton.setText("Pause");
					playButton.setForeground(new Color(0, 0, 0));
					playButton.setBackground(new Color(0, 255, 0));
				} else {
					currentSongLabel.setText(null);
					clip.stop();
					playButton.setText("Play");
					playButton.setForeground(new Color(128, 255, 255));
					playButton.setBackground(new Color(0, 0, 255));
				}
			}
		});
		playButton.setBounds(153, 265, 89, 23);
		contentPane.add(playButton);
		
		// Stop Music Button
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		stopButton.setBounds(270, 265, 89, 23);
		contentPane.add(stopButton);
		
		// Add Music Button
		addButton = new JButton("Add");
		//addButton.setBorder(new RoundedBorder(10));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (data[table.getSelectedRow()][1] == null) {
					fileChooser = new JFileChooser();
					fileChooser.showOpenDialog(null);
					
					if (table.getSelectedRow() == 0) {
						musicFile0 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile0);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile0.getName();
						
					} else if (table.getSelectedRow() == 1) {
						musicFile1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile1);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile1);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile1.getName();
						
					} else if (table.getSelectedRow() == 2) {
						musicFile2 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile2);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile2.getName();
						
					} else if (table.getSelectedRow() == 3) {
						musicFile3 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile3);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile3.getName();
						
					} else if (table.getSelectedRow() == 4) {
						musicFile4 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile4);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile4.getName();
						
					} else if (table.getSelectedRow() == 5) {
						musicFile5 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile5);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile5.getName();
						
					} else if (table.getSelectedRow() == 6) {
						musicFile6 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile6);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile6);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile6.getName();
						
					} else if (table.getSelectedRow() == 7) {
						musicFile7 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile7);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile7.getName();
						
					} else if (table.getSelectedRow() == 8) {
						musicFile8 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile8);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile8.getName();
						
					} else if (table.getSelectedRow() == 9) {
						musicFile9 = new File(fileChooser.getSelectedFile().getAbsolutePath());
						System.out.println(musicFile0);
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile9);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fileName = musicFile9.getName();
					}
					
					data[table.getSelectedRow()][1] = fileName;
					table = new JTable(data, columnNames);
				}
				
				
				if (data[0][1] != null || data[1][1] != null || data[2][1] != null || data[3][1] != null || data[4][1] != null || 
						data[5][1] != null || data[6][1] != null || data[7][1] != null || data[8][1] != null || data[9][1] != null) {
						playButton.setEnabled(true);
						playButton.setForeground(new Color(128, 255, 255));
						playButton.setBackground(new Color(0, 0, 255));
				}
				table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
			}
		});
		addButton.setBounds(10, 221, 89, 23);
		contentPane.add(addButton);
		
		// Volume Up Button
		volumeUpButton = new JButton("Volume Up");
		volumeUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		volumeUpButton.setBounds(10, 299, 89, 23);
		contentPane.add(volumeUpButton);
		
		// Volume Down Button
		volumeDownButton = new JButton("Volume Down");
		volumeDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		volumeDownButton.setBounds(10, 326, 89, 23);
		contentPane.add(volumeDownButton);
		
		// Loop All Music Button
		loopAllButton = new JButton("Loop All");
		loopAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopAllButton.setBounds(270, 345, 89, 23);
		contentPane.add(loopAllButton);
		
		// Loop One Music Button
		loopOneButton = new JButton("Loop");
		loopOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopOneButton.setBounds(270, 311, 89, 23);
		contentPane.add(loopOneButton);
		
		// Shuffle Button
		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		shuffleButton.setBounds(270, 372, 89, 23);
		contentPane.add(shuffleButton);
		
		
		stopButton.setEnabled(false);
		loopAllButton.setEnabled(false);
		loopOneButton.setEnabled(false);
		shuffleButton.setEnabled(false);
		volumeUpButton.setEnabled(false);
		volumeDownButton.setEnabled(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(10, 158, 366, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		songLabel = new JLabel("Current Song Playing:");
		songLabel.setBounds(0, 0, 366, 14);
		panel.add(songLabel);
		songLabel.setForeground(new Color(30, 200, 34));
		
		// Current Song Label
		currentSongLabel = new JLabel();
		currentSongLabel.setBounds(0, 15, 366, 14);
		panel.add(currentSongLabel);
		currentSongLabel.setForeground(new Color(30, 200, 34));
	}
}
