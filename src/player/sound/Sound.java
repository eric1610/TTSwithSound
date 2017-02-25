package player.sound;

import java.io.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Sound 
{

	public Sound() 
	{
		// TODO Auto-generated constructor stub		
	}
	
	
	public void play(String song)
	{
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	      //  Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	        
	   /*     clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                    clip.close();
	            }
	        });  */
	        
	        clip.open(AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\" + song)));

	        clip.start();

	 /*       while (!clip.isRunning())
	            Thread.sleep(10);
	        while (clip.isRunning())
	            Thread.sleep(10); 
	        clip.close();  */

	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	
	
	public static void main (String [] args)
	{
		Sound s = new Sound ();
		VoiceManager vm = VoiceManager.getInstance(); 
		Voice voice = vm.getVoice("kevin16");
		voice.allocate(); 
		try
		{
			Scanner fileScan = new Scanner (new File(System.getProperty("user.dir") + "\\" + "Play.txt"));
			String temp;
			while (fileScan.hasNextLine ())
			{
				temp = fileScan.nextLine();
				if (temp.length () >= 6 && temp.substring(0, 6).equals("/~Mus:"))
				{
					s.play (temp.substring(6));
				}
				else
				{
					voice.speak(temp);
				}
			}
			fileScan.close ();
		}
		catch (Exception e)
		{

		}
	}
}
