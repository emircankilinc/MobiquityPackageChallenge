package com.mobiquity.packer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author emircankilinc
 *
 */
public class PackerTestUtil {

	public final float MaxWeightForPackage = 100;
	public final int MaxItemsCount = 15;
	public final String MissingFilePath = "MissingFilePath";
	private final String fileName = "TestFile.txt";
	private final String CHARSET_UTF8 = "UTF8";

	public final List<String> HeavierPacketWeightThanMaxAllowableWeight = Arrays.asList("1000 : (1,15.3,€34)");

	public final List<String> HeavierItemWeightThanMaxAllowableWeight = Arrays
			.asList("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,110.00,€9) (6,46.34,€48)");

	public final List<String> MoreItemsThanMaxAllowed = Arrays.asList(
			"81 : (1,53.38,€45) (2,53.38,€45) (3,53.38,€45) (4,53.38,€45) (5,53.38,€45) (6,53.38,€45) (7,53.38,€45) (8,53.38,€45) (9,53.38,€45) "
					+ "(10,53.38,€45) (11,53.38,€45) (12,53.38,€45) (13,53.38,€45) (14,53.38,€45) (15,53.38,€45) (16,53.38,€45)");

	public final List<String> MuchItemCostThanMaxAllowed = Arrays
			.asList("81 : (1,53.38,€45) (2,88.62,€110) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");

	public final List<String> ItemsHasSameCostButDifferentWeight = Arrays.asList(
			"75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€74) (4,26.24,€74) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)");

	public final List<String> InvalidRequest = Arrays.asList(" 8 : (1,15.3,€3aB4)");

	public final List<String> validSingleLineRequest = Arrays
			.asList("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");

	public final List<String> validSingleLineResponse = Arrays.asList("4");

	public final List<String> validMultiLineRequest = Arrays.asList(
			"81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
			" 8 : (1,15.3,€34)",
			"75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
			"56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");

	public final List<String> validMultiLineResponse = Arrays.asList("4", "-", "2,7", "8,9");

	public void deleteExistingFile() {
		Path file = Paths.get(fileName);
		File packingFile = new File(file.toAbsolutePath().toString());
		if (packingFile.exists()) {
			packingFile.delete();
		}
	}

	public String createFileWithTestData(List<String> data) {
		Path file = Paths.get(fileName);
		try {
			Files.write(file, data, Charset.forName(CHARSET_UTF8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.toAbsolutePath().toString();
	}

}
