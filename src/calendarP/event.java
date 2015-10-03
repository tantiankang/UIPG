package calendarP;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class event extends TimerTask {
	

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream in;
		AudioStream as;

		try {
	
			in = new FileInputStream(new File("heart123.wav"));
			as = new AudioStream(in);

				System.out.println("thread started");
				AudioPlayer.player.start(as);
				

			//AudioPlayer.player.sleep(1000);
			//AudioPlayer.player.stop(as);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	}

}
