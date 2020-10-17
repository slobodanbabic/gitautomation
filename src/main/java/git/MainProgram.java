package git;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

public class MainProgram {
	public static final String FILE_NAME = "Sample.java";

	public static void main(String[] args)
			throws IOException, NoFilepatternException, GitAPIException, URISyntaxException {
		File file = FileHelper.openFile(FILE_NAME);
		int i = (int) (Math.random() * 1000);
		String code = "public void print" + i + "(int i){System.out.println(i);}";
		// add code to Simple.java class
		FileHelper.writeToFile(file, code);		
		GitHelper.gitCommit();
		System.out.println("remote adding");
		GitHelper.gitAdd();		
		System.out.println("pushing");
		GitHelper.gitPush();
		System.out.println("finish");
		
	}

}
