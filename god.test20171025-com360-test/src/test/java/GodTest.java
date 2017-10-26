import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class GodTest {

	@Test
	public void test() {
		System.out.println("시작");

		// String command = "timeout 3";
		String command = "date";

		Process process = null;

		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println(e);
		}

		if (process != null) {
			try {
				int waitFor = process.waitFor();

				System.out.println("waitFor: " + waitFor);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			// OutputStream out = process.getOutputStream();
			// PrintStream printStream = new PrintStream(out);
			// printStream.println();

			// BufferedReader input = new BufferedReader(new
			// InputStreamReader(process.getInputStream()));
			// String line;
			// try {
			// while ((line = input.readLine()) != null) {
			// System.out.println(line);
			// }
			// } catch (IOException e) {
			// System.out.println(e);
			// }
			// try {
			// input.close();
			// } catch (IOException e) {
			// System.out.println(e);
			// }

			try {
				System.err.println(IOUtils.toString(process.getErrorStream()));
				System.out.println(IOUtils.toString(process.getInputStream()));
			} catch (IOException e) {
				System.out.println(e);
			}

		}

		System.out.println("끝");
	}

}
