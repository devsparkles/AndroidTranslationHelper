import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Start {

	/***
	 * This application take a CSV file containing the keys and the translations and
	 * will look inside an xml strings.xml file and replace every value inside a
	 * string tag with the same key with the value inside the CSV
	 * 
	 * @param args
	 *            this array take three values, the first is the number of the
	 *            column of the CSV that will be used this number represent the
	 *            language the second represent the path of the source CSV the last
	 *            represent the path of the strings.xml that will be updated with
	 *            the CSV value
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// if the validation succeed
		if (isArgumentValid(args)) {
			readCSV(args[0], args[1], args[2]);
		}
	}

	static boolean isArgumentValid(String[] args) {
		boolean isValid = true;

		// test if the first argument is valid.
		// this first argument should be an integer
		try {
			Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			isValid = false;
			// not and int :(
			System.out.println(
					"Sorry you must enter as a fisrt argument the column of the language you want to work with");
		}

		// test if the second and last arguments are valid file that actually exist
		// further test will be done to make sure the first one is a CSV and the last
		// one a XML
		File csvFile = new File(args[1]);
		if (!csvFile.exists() || !csvFile.isFile()) {
			isValid = false;
			System.out.println("Sorry the second argument must be a path to the source CSV");
		}

		File xmlFile = new File(args[2]);
		if (!xmlFile.exists() || !xmlFile.isFile()) {
			isValid = false;
			System.out.println("Sorry the third argument must be a path to the target XML");
		}
		return isValid;
	}

	/***
	 * This method will read the CSV and for each line
	 * 
	 * @param csvPath
	 * @param xmlPath
	 */
	static void readCSV(String language, String csvPath, String xmlPath) {

		int lang = Integer.parseInt(language);

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {
				String[] translationLine = line.split(cvsSplitBy);
				// the 0 in the array represent the key inside de CSV 
				// the lang represent the column that will be used to replace the value inside de xml file.
				updateStringFile(xmlPath, translationLine[0], translationLine[lang]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/***
	 * This method will update a key a strings.xml file.
	 * 
	 * @param file
	 *            the path to the strings.xml
	 * @param key
	 *            the key in the strings.xml you want to update
	 * @param value
	 *            the value you want to replace
	 * @throws IOException
	 *             the error that might be raised if your file doesn't not exist
	 */
	public static void updateStringFile(String file, String key, String value) throws IOException {

		String regex = "(?<=<string name=\"" + key + "\">).*(?=<\\/string>)";
		Path path = Paths.get(file);
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll("foo", "bar");

		content = content.replaceAll(regex, value);
		Files.write(path, content.getBytes(charset));

	}

}
