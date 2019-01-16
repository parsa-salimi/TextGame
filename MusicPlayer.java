import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/* https://alvinalexander.com/java/java-audio-example-java-au-play-sound */

public class MusicPlayer {
	String musicName;
	InputStream in;
	AudioStream audioStream;
	public void play(String song) throws Exception {
			  
		    // open the sound file as a Java input stream
		    musicName = song;
		    in = new FileInputStream(musicName);

		    // create an audiostream from the inputstream
		    audioStream = new AudioStream(in);

		    // play the audio clip with the audioplayer class
		    AudioPlayer.player.start(audioStream);
		 
	}
	
	public void stop() throws Exception {
		AudioPlayer.player.stop(audioStream);
	}

	public void playMood(int mentalHealth) {
		// TODO Auto-generated method stub
		
	}
}
