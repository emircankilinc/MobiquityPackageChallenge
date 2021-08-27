package com.mobiquity.packer;

import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.constant.ServiceConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.service.impl.FileOperationService;
import com.mobiquity.service.impl.PackerService;
import com.mobiquity.service.intf.IFileOperationService;
import com.mobiquity.service.intf.IPackerService;

public class Packer {

	private static IFileOperationService fileOperationService = new FileOperationService();
	private static IPackerService packerService = new PackerService();

	private Packer() {
	}

	/**
	 * Api signature method. Taking filePath and reads file's lines and solve
	 * packaging problem after that solve problems with returning item's indexes in
	 * each line.
	 * 
	 * @param filePath string for path of file
	 * @return String which contains item indexes with new line
	 */
	public static String pack(String filePath) throws APIException {

		// Checking file exists
		if (!fileOperationService.isFileExist(filePath)) {

			throw new APIException("File Path : '" + filePath + "' doesn't exist!");
		}

		StringBuilder result = new StringBuilder();

		// Reading all lines from file
		List<String> allLinesFromFile = fileOperationService.getAllLinesFromFile(filePath);

		for (String line : allLinesFromFile) {

			// calling solve method for each line
			result.append(solve(line)).append("\n");
		}

		System.out.println(result.toString());
		return result.toString().trim();

	}

	/**
	 * Solve packaging problem with taking each line and parsing to package problem.
	 * After this operation, solving operation begins.
	 * 
	 * @param line string that each line of file
	 * @return String which contains item indexes with new line
	 */
	private static String solve(String line) throws APIException {

		// Parsing each line of file to package problem structure
		Pack packageInformationFromLine = fileOperationService.getPackInformationFromLine(line);

		StringBuilder result = new StringBuilder();

		// Call solvePackProblem function from packerservice
		List<Item> solveProblem = packerService.solvePackProblem(packageInformationFromLine);

		if (!solveProblem.isEmpty()) {

			// If problem has solution and we have to return item's index/indexes
			result.append(solveProblem.stream().map(item -> item.getIndex().toString())
					.collect(Collectors.joining(ServiceConstants.COMMA)));

		} else {

			// If problem has no solution so we have to return dash char
			result.append("-");

		}

		return result.toString();
	}

}
