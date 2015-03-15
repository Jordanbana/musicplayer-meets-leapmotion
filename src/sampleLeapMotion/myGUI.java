package sampleLeapMotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class myGUI extends JFrame {
	public static final long serialVersionUID = 1;
	
	public Clip clip;
	URL[] urlArray;
	
	JLabel albumIconLabel;
	
	public static final String BASE_URL_PATH = "http://jbwebsitebuilder.com"  +
	        "/Java_Music_File/";
	public static final String[] URL_PATHS = {
		      "20120928010410!Headlines.png",
		      "Childish-gambino-because-the-internet.gif",
		   };
	
	
	public void changeSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream ais = AudioSystem.
                getAudioInputStream( urlArray[1] );
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                }
            });
	}
	
	public myGUI() throws Exception{
		super("LEAP meets JAVA GUI");
		setSize(500,600);
		setLocation(500,0);
		
		//ARRAY of TYPE URL TO HAVE MORE THAN 1 song
		urlArray = new URL[2];
        //code for music
        URL url = new URL(
                "http://jbwebsitebuilder.com/Java_Music_File/Headlines.wav");
        
        URL url2 = new URL(
                "http://jbwebsitebuilder.com/Java_Music_File/3005.wav");

        urlArray[0] = url;
        urlArray[1] = url2;
        
        clip = AudioSystem.getClip();
        
        
        JPanel mainJpanel = new JPanel();
        //Button and action listener
		JButton testButton = new JButton("STOP MUSIC");
		mainJpanel.add(testButton);
		testButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				clip.stop();
			}
		});
		
		albumIconLabel = new JLabel("");
		//trying to display album cover
		String fullUrlPath = BASE_URL_PATH + URL_PATHS[0];
		try {
            URL imageurl = new URL(fullUrlPath);
            BufferedImage img = ImageIO.read(imageurl);
            ImageIcon icon = new ImageIcon(img);
            albumIconLabel.setIcon(icon);
    		mainJpanel.add(albumIconLabel);
            //JOptionPane.showMessageDialog(null, icon);
         } catch (MalformedURLException e) {
            e.printStackTrace();
         }
		
		//adding components to the JFRAME
		add(mainJpanel);
		
        
            // getAudioInputStream() also accepts a File or InputStream
            AudioInputStream ais = AudioSystem.
                getAudioInputStream( urlArray[0] );
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // A GUI element to prevent the Clip's daemon Thread
                    // from terminating at the end of the main()
                    //JOptionPane.showMessageDialog(null, "Close to exit!");
                }
            });
		
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
