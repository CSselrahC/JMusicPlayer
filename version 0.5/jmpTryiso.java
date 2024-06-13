package packageNaMalupet;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
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

public class jmpTryiso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	JButton upButton, downButton, addButton, playButton;
	
	int rowOfSelectedSong;
	String fileName, currentSongName;
	
	JFileChooser fileChooser;
	File musicFile;
	File musicFile0, musicFile1, musicFile2, musicFile3, musicFile4;
	File musicFile5, musicFile6, musicFile7, musicFile8, musicFile9;
	AudioInputStream inputAS;
	Clip clip;
	
	JLabel currentSongLabel, currentSongTitleLabel;
	private JButton volumeUpButton;
	private JButton volumeDownButton;
	private JButton loopAllButton;
	private JButton loopOneButton;
	private JButton shuffleButton;

	float previousVolume = 0, currentVolume = 0;
	FloatControl floatControl;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jmpTryiso frame = new jmpTryiso();
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
	public jmpTryiso() {
		setTitle("JMusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 380, 150);
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
		scrollPane.setViewportView(table);
		
		// Current Song Playing
		JPanel songPlayingPanel = new JPanel();
		songPlayingPanel.setBounds(10, 158, 380, 55);
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
		table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
		
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
					System.out.println("rowOfSelectedSong: " + rowOfSelectedSong);
					
					if (model.getValueAt(table.getSelectedRow(), 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						
						if (table.getSelectedRow() == 1) {
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
						addButton.setText("Add");
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
					}
				} else if (rowOfSelectedSong > 0) {
					playButton.setText("Play");
					rowOfSelectedSong--;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					upButton.setEnabled(false);
					upButton.setBackground(new Color(25, 20, 20));
					System.out.println("rowOfSelectedSong: " + rowOfSelectedSong);
					
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
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
					}
				}
			}
		});
		upButton.setBounds(154, 220, 90, 25);
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
					System.out.println("rowOfSelectedSong: " + rowOfSelectedSong);
								
					if (model.getValueAt(table.getSelectedRow(), 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						
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
						}
					} else {
						addButton.setText("Add");
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
					}
				} else if (rowOfSelectedSong < 9) {
					playButton.setText("Play");
					rowOfSelectedSong++;
					table.setRowSelectionInterval(rowOfSelectedSong, rowOfSelectedSong);
					downButton.setEnabled(false);
					downButton.setBackground(new Color(25, 20, 20));
					System.out.println("rowOfSelectedSong: " + rowOfSelectedSong);
					
					if (model.getValueAt(9, 1) != null) {
						addButton.setText("Change");
						playButton.setEnabled(true);
						playButton.setBackground(new Color(30, 215, 96));
						
						try {
							inputAS = AudioSystem.getAudioInputStream(musicFile9);
							clip = AudioSystem.getClip();
							clip.open(inputAS);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						addButton.setText("Add");
						playButton.setBackground(new Color(25, 20, 20));
						playButton.setEnabled(false);
					}
				}
			}
		});
		downButton.setBounds(154, 256, 90, 25);
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
					model.setValueAt(fileName, table.getSelectedRow(), 1);
				} else {
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
					model.setValueAt(fileName, table.getSelectedRow(), 1);
					playButton.setEnabled(true);
					playButton.setBackground(new Color(30, 215, 96));
					addButton.setText("Change");
					loopOneButton.setEnabled(true);
				}
			}
		});
		addButton.setBounds(22, 221, 90, 25);
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
				}
			}
		});
		playButton.setBounds(22, 257, 90, 25);
		playButton.setEnabled(false);
		playButton.setFocusable(false);
		contentPane.add(playButton);
		
		// No function Buttons
		
		// Volume Buttons
		// Volume Up Button
		volumeUpButton = new JButton("Volume Up");
		volumeUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		volumeUpButton.setBounds(23, 332, 90, 25);
		contentPane.add(volumeUpButton);
		
		// Volume Down Button
		volumeDownButton = new JButton("Volume Down");
		volumeDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		volumeDownButton.setBounds(22, 368, 90, 25);
		contentPane.add(volumeDownButton);
		
		// Loop All Button
		loopAllButton = new JButton("Loop All");
		loopAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loopAllButton.setBounds(154, 333, 90, 25);
		contentPane.add(loopAllButton);
		
		// Loop One Button
		loopOneButton = new JButton("Loop One");
		loopOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		});
		loopOneButton.setBounds(154, 369, 90, 25);
		contentPane.add(loopOneButton);
		
		// Shuffle Button
		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		shuffleButton.setBounds(284, 333, 90, 25);
		contentPane.add(shuffleButton);
		
		// disabled buttons
		loopAllButton.setEnabled(false);
		loopOneButton.setEnabled(false);
		shuffleButton.setEnabled(false);
		volumeUpButton.setEnabled(false);
		volumeDownButton.setEnabled(false);

		JSlider volumeSlider = new JSlider(-40, 6);
		volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				currentVolume = volumeSlider.getValue();
				
				if(currentVolume == -40) currentVolume = -80;
				floatControl.setValue(currentVolume);
			}
		});
		volumeSlider.setBounds(22, 292, 200, 26);
		contentPane.add(volumeSlider);
	}
}
