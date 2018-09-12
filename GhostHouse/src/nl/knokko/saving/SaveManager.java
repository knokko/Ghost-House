package nl.knokko.saving;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
	
	private static final File SAVE_FOLDER = new File("Ghost House Saves");
	
	static {
		SAVE_FOLDER.mkdir();
	}
	
	public static String[] getSaveFiles(){
		File[] files = SAVE_FOLDER.listFiles();
		List<String> folders = new ArrayList<String>(files.length);
		for(File file : files){
			if(file.isDirectory())
				folders.add(file.getName());
		}
		return folders.toArray(new String[folders.size()]);
	}
	
	public static Long[] getSaveTimes(String saveFile){
		File[] files = new File(SAVE_FOLDER + File.separator + saveFile).listFiles();
		List<Long> saveTimes = new ArrayList<Long>(files.length);
		for(File file : files){
			if(checkSaveTime(file))
				saveTimes.add(Long.parseLong(file.getName()));
		}
		return saveTimes.toArray(new Long[saveTimes.size()]);
	}
	
	private static boolean checkSaveTime(File file){
		if(!file.isDirectory())
			return false;
		if(file.listFiles().length == 0)
			return false;
		try {
			Long.parseLong(file.getName());
			return true;
		} catch(NumberFormatException ex){
			return false;
		}
	}
}
