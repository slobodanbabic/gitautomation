package git;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class FileHelper {

	public static File openFile(String fileName) {
		File file = null;
		String fileSeparator = System.getProperty("file.separator");
		File workspace = new File(System.getProperty("user.dir"));
		// absolute file name with path
		String absoluteFilePath = workspace + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java"
				+ fileSeparator + "git" + fileSeparator + fileName;
		file = new File(absoluteFilePath);
		try {
			file.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static void writeToFile(File file, String code) {

		try {
			StringBuilder sb = readFileLineByLineUsingFiles(file);
			int index = sb.lastIndexOf("}");
			sb.deleteCharAt(index);
			sb.append(code);
			sb.append("}");

			Formatter formatter = new Formatter();
			String newCode = formatter.formatSource(sb.toString());

			FileOutputStream fileOut = new FileOutputStream(file);
			fileOut.write(newCode.getBytes());
			fileOut.close();

		} catch (IOException ex) {
			System.out.println(ex);
		} catch (FormatterException ex) {
			System.out.println(ex);
		}
	}

	public static StringBuilder readFileLineByLineUsingFiles(File file) {
		StringBuilder sb = new StringBuilder();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (String line : allLines) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}
}
