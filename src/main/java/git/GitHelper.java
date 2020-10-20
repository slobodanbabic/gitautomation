package git;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitHelper {

	public static void gitCommit() {
		Git git;
		try {
			git = Git.open(new File(System.getProperty("user.dir")));
			git.add().addFilepattern(".").call();
			git.commit().setAll(true).setMessage("Update Sample.java").call();
		} catch (IOException | GitAPIException e) {
			e.printStackTrace();
		}
	}

	public static void gitPush() {
		try {
			Git git = Git.open(new File(System.getProperty("user.dir")));
			git.push().setRemote("origin").setCredentialsProvider(
					new UsernamePasswordCredentialsProvider("slobodannbabic@gmail.com", "Tomicevo007")).call();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public static void gitAdd() {
		try {
			Git git = Git.open(new File(System.getProperty("user.dir")));
			git.remoteAdd().setName("origin").setUri(new URIish("https://github.com/slobodanbabic/gitautomation.git"))
					.call();
		} catch (GitAPIException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static Repository openRepository() throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository = builder.setGitDir(new File(System.getProperty("user.dir"))).readEnvironment()
				.findGitDir() // scan up the file system tree
				.build();
		return repository;
	}

	public static Repository createNewRepository() throws IOException {
		// prepare a new folder
		File localPath = File.createTempFile(System.getProperty("user.dir"), "");
		localPath.delete();
		// create the directory
		Repository repository = FileRepositoryBuilder.create(new File(System.getProperty("user.dir"), ".git"));
		repository.create();
		return repository;
	}
}