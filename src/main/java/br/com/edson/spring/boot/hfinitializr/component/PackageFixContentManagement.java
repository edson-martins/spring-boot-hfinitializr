package br.com.edson.spring.boot.hfinitializr.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.edson.spring.boot.hfinitializr.domain.Bug;
import br.com.edson.spring.boot.hfinitializr.domain.Note;
import br.com.edson.spring.boot.hfinitializr.util.ListUtils;
import lombok.extern.log4j.Log4j2;
/**
* Class      : PackageFixContentManagement
* Subclass   : IOException
* 
* Description: This is the hotfix/bundle management class used to generate
*              the archive file.
*              If a bundle will be generate, the archive content is readme file,
*              patch contents file, bat file with numbers of child bugs related
*              with the bundle, and child bug folders used to put deliverables
*              of each bug.
*
* @author Edson Martins
*/
@Component
@Log4j2
public class PackageFixContentManagement extends IOException {

	private static final long serialVersionUID = 6056662130978888681L;

	/**
	 * Description: Method used to generate the readme file content
	 * @param id
	 * @param title
	 * @param notes
	 * @return String representing the readme file based in the template 
	 *         'templates/_readme_template_txt'
	 * @throws IOException
	 */
	public String generateReadmeContent(String id, String title, List<Note> notes) throws IOException {

		String readme = null;

		Resource resource = new ClassPathResource("templates/_readme_template_txt");
		InputStream inputStream = resource.getInputStream();

		readme = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

		readme = readme.replace("{ROOT_BUG_ID}", id);
		readme = readme.replace("{ROOT_DESCRIPTION}", title);

		for (Note note : ListUtils.safeNoteList(notes)) {
			readme = readme.concat(note.getNote() + "\n");
		}

		inputStream.close();
		return readme;
	}
	
	/**
	 * Description: Method used to generate a list of child bugs from bundle as
	 *              string.
	 * @param data is a list of bug (child bug when processing a bundle)
	 * @return child bug string separated by \n
	 */
	public String generateChildBugListContent(List<Bug> data) {

		String childBug = "";

		for (Bug bug : ListUtils.safeBugList(data)) {
			childBug = childBug.concat(bug.getBugId() + "\n");
		}
		return childBug;
	}
	
	/**
	 * Description: Method used to generate the zip archive and wrapping byte[]
	 * @param id
	 * @param title
	 * @param notes
	 * @param childBugs
	 * @return ByteArrayOutputStream representing the zip archive 
	 * @throws IOException
	 */
	public ByteArrayOutputStream assembler(String id, String title, List<Note> notes, List<Bug> childBugs) throws IOException {

		String rootFolder     = id + "/";
		String readmeFilename = id + "_readme.txt";

		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteOutputStream);

		ZipEntry zipEntry = new ZipEntry(rootFolder);
		zipEntry.setSize(16384L);

		zipOutputStream.putNextEntry(zipEntry);
		zipOutputStream.closeEntry();

		// - ------------------------------------------------------------------------
		// - Adding Updated bug list, patch content and readme file into zip archive
		// - ------------------------------------------------------------------------
		log.info("Creating readme file to the hotfix number " + id + ".");

		// - ------------------------------------------------------------------------
		// - README
		// - ------------------------------------------------------------------------
		zipOutputStream.putNextEntry(new ZipEntry(id + "/" + readmeFilename));

		byte[] readme = generateReadmeContent(id, title, notes).getBytes();
		zipOutputStream.write(readme, 0, readme.length);
		zipOutputStream.closeEntry();

		log.info("Creating patch contents file to the hotfix number " + id + ".");
		// - ------------------------------------------------------------------------
		// - Patch content
		// - ------------------------------------------------------------------------
		zipOutputStream.putNextEntry(new ZipEntry(id + "/patch_contents.csv"));
		zipOutputStream.closeEntry();
		
		log.info("Creating bundle bug folders (if available) to the hotfix number " + id + ".");
		// - ------------------------------------------------------------------------
		// - Bug folders
		// - ------------------------------------------------------------------------
		for (Bug bugs : ListUtils.safeBugList(childBugs)) {
			String parentFolder = bugs.getBugId() + "/";
			zipOutputStream.putNextEntry(new ZipEntry(rootFolder + parentFolder));
			zipOutputStream.closeEntry();
		}

		log.info("Creating bug list (if available) to the hotfix number " + id + ".");
		// - ------------------------------------------------------------------------
		// - Update bug list file
		// - ------------------------------------------------------------------------
		if (null != childBugs && !childBugs.isEmpty()) {
			zipOutputStream.putNextEntry(new ZipEntry(id + "/buglist.dat"));
			byte[] updateBugs = generateChildBugListContent(childBugs).getBytes();
			zipOutputStream.write(updateBugs, 0, updateBugs.length);
			zipOutputStream.closeEntry();
		}
		
		zipOutputStream.close();
		byteOutputStream.close();
		
		log.info("Finished hotfix #" + id + ".");
		return byteOutputStream;
	}
}
