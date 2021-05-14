package com.monty.services.impl;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.monty.services.AudioPlayer;

@Component
public class AudioPlayerImpl implements AudioPlayer,LineListener {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(AudioPlayerImpl.class);
	
	private boolean playCompleted;

	@Override
	public void soundAlarm() {
		// TODO Auto-generated method stub
		
		
		
		File yourFile=new File("src/main/resources/sound/ALARM2.wav");
		AudioInputStream stream=null;
		AudioFormat format=null;
		DataLine.Info info=null;
		Clip clip=null;
		
		try {
		   
		    
		    playCompleted=false;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.addLineListener(this);
		    clip.open(stream);
		    playInLoop(clip,5);
		    
		}
		catch (Exception e) {
		    LOGGER.error("Error while playing media: ",e);
		}finally {
			if(clip!=null) {
				clip.close();
			}
		}
		
	}

	
	//As the clip is of 3 seconds only, this will play the clip numberOfTimesToPlay times, there by increasing the duration
	private void playInLoop(Clip clip,int numberOfTimesToPlay) throws InterruptedException {
		clip.loop(numberOfTimesToPlay);
		
		while(!playCompleted) {
			//LOGGER.info("Sleeping");
			Thread.sleep(1000);
		}
	}

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		LineEvent.Type type=event.getType();
		
		if(type==LineEvent.Type.START) {
			//LOGGER.info("Clip started playing");
			playCompleted=false;
		}else if(type==LineEvent.Type.STOP) {
			//LOGGER.info("Clip Completed");
			playCompleted=true;
		}
		
	}
	
	
	

}
