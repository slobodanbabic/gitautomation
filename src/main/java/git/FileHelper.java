package git;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class FileHelper {

	public static File openOrCreateNewFile(String fileName) {
		File file = null;
		String fileSeparator = System.getProperty("file.separator");
		String packageName = FileHelper.class.getPackage().getName();
		Path resourceDirectory = Paths.get("src", "main", "java", packageName);
		String pathName = resourceDirectory.toString();
		file = new File(pathName + fileSeparator + fileName);
		try {
			if (file.createNewFile()) {
				Formatter formatter = new Formatter();
				StringBuilder classPattern = new StringBuilder();
				classPattern.append("package " + packageName + ";");
				String name = fileName.split("\\.")[0];
				classPattern.append("public class " + name + "{}");
				FileOutputStream fileOut = new FileOutputStream(file);
				String code = formatter.formatSource(classPattern.toString());
				fileOut.write(code.getBytes());
				fileOut.close();
				System.out.println(fileName + " file is created.");
			}
		} catch (FormatterException | IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void appendToFile(File file, String code) {
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

		} catch (IOException | FormatterException ex) {
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
