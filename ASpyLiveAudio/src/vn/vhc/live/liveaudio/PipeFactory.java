package vn.vhc.live.liveaudio;

import vn.vhc.live.UtilGame;

import android.telephony.TelephonyManager;



public class PipeFactory {

	private FFMPEGWrapper ffWrapper;
	private String ffmpegCommand;

	public PipeFactory() {
		ffWrapper = FFMPEGWrapper.getInstance();
		ffmpegCommand = ffWrapper.data_location + ffWrapper.ffmpeg;
	}

	
	public AudioInputPipe getADPCMAudioInputPipe(String publisherString) {
		String command = ffmpegCommand
				+ " -analyzeduration 0 -i pipe:0 -re -vn -acodec "
				+ AudioCodec.ADPCM_SWF.name + " -ar "
				+ AudioCodec.ADPCM_SWF.RATE_11025 + " -ac 1 -f flv "
				+ publisherString;
		return newAudioInputPipe(command);
	}

	//using this option	
	public AudioInputPipe getNellymoserAudioInputPipe(String publisherString) {
//		String command = ffmpegCommand
//				+ " -analyzeduration 0 -muxdelay 0 -muxpreload 0 -i pipe:0 -re -vn -acodec "
//				+ AudioCodec.Nellymoser.name
//				+ " -ar 8000 -ac 1 -ab 16k -f flv " + publisherString;
//		return newAudioInputPipe(command);
		
		String command = ffmpegCommand
		+ " -analyzeduration 0 -muxdelay 0 -muxpreload 0 -i pipe:0 -re -vn -acodec "
		+ AudioCodec.Nellymoser.name
		+ " -ar 8000 -ac 1 64k -f flv " + publisherString;
		return newAudioInputPipe(command);
	}
	public String getBitRate()
	{
		if(UtilGame.speedConnect.toLowerCase().equals("wifi")) return " -ab 128k";
		return getBitRateBasedSpeed(UtilGame.typeSubNet);
	}
	 public String getBitRateBasedSpeed(int subType){
        
            switch(subType)
            {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            	return " -ab 64k";
            case TelephonyManager.NETWORK_TYPE_CDMA:
            	return " -ab 32k";
            case TelephonyManager.NETWORK_TYPE_EDGE:
            	return " -ab 64k";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            	return " -ab 128k";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            	return " -ab 64k";
            case TelephonyManager.NETWORK_TYPE_GPRS:
            	return " -ab 64k";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            	return " -ab 128k";
            case TelephonyManager.NETWORK_TYPE_HSPA:
            	return " -ab 128k";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            	return " -ab 128k";
            case TelephonyManager.NETWORK_TYPE_UMTS:            	
                return " -ab 128k"; // ~ 400-7000 kbps
            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return " -ab 32k";
            }
           
    }
	public AudioInputPipe getAACAudioInputPipe(String publisherString)
			throws Exception {
//		String command = ffmpegCommand
//				+ " -strict experimental -analyzeduration 0 -muxdelay 0 -muxpreload 0 -i pipe:0 -re -vn -acodec "
//				+ AudioCodec.AAC.name + " -ac 1 -ar 8000 -ab 16k -f flv "
//				+ publisherString;
//		return newAudioInputPipe(command);
		
		String command = ffmpegCommand
		+ " -strict experimental -analyzeduration 0 -muxdelay 0 -muxpreload 0 -i pipe:0 -re -vn -acodec "
		+ AudioCodec.AAC.name + " -ac 1 -ar 44100 -ab 32k -f flv "
		+ publisherString;
		return newAudioInputPipe(command);
	}

	
	public AudioOutputPipe getAudioOutputPipe(String publisherString,
			int audioFileFormat, String codecName, String sourceCodec) {
		String command;
		// add -strict experimental and acodec for AAC
		if (sourceCodec.equals(AudioCodec.AAC.name)) {
			command = ffmpegCommand
					+ " -strict experimental -acodec aac -analyzeduration 0 -muxdelay 0 -muxpreload 0 -vn -itsoffset -2 -i "
					+ publisherString + " -re -vn -acodec ";
		} else if (sourceCodec.equals(AudioCodec.Nellymoser.name)) {
			command = ffmpegCommand
					+ " -analyzeduration 0 -vn -itsoffset -5 -acodec nellymoser -ar 8000 -ac 1 -i "
					+ publisherString + " -re -vn -acodec ";
		} else if (sourceCodec.equals(AudioCodec.ADPCM_SWF.name)) {
			command = ffmpegCommand
					+ "  -acodec adpcm_swf -analyzeduration 0 -muxdelay 0 -muxpreload 0 -vn -itsoffset -2 -i "
					+ publisherString + " -re -vn -acodec ";
		} else {
			throw new UnsupportedOperationException(
					"no support dor source codec [" + sourceCodec + "]");
		}
		if (audioFileFormat == AudioCodec.AUDIO_FILE_FORMAT_WAV) {
			if (codecName.equals(AudioCodec.PCM_S16LE.name)) {
				command += AudioCodec.PCM_S16LE.name + " -ar "
						+ AudioCodec.PCM_S16LE.RATE_11025 + " -ac 1";
			}
			command += " -f wav pipe:1";
		}
		FFMPEGAudioOutputPipe pipe = new FFMPEGAudioOutputPipe(command);
		return pipe;
	}

	
	private FFMPEGAudioInputPipe newAudioInputPipe(String command) {
		FFMPEGAudioInputPipe pipe = new FFMPEGAudioInputPipe(command);
		pipe.setBootstrap(FFMPEGBootstrap.AMR_BOOTSTRAP);
		return pipe;
	}

	
	public AudioInputPipe getVideoInputPipe(String publisherString) {
		String command = ffmpegCommand
				+ " -analyzeduration 0 -i pipe:0 -re -an -r 25 -f flv -b 100k -s 320x240 "
				+ publisherString;
		FFMPEGAudioInputPipe pipe = new FFMPEGAudioInputPipe(command);
		return pipe;
	}

}
