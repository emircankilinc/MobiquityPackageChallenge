package com.mobiquity.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.mobiquity.constant.PackerConstants;
import com.mobiquity.constant.ServiceConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.service.intf.IFileOperationService;

/**
 * @author emircankilinc
 *
 */
public class FileOperationService implements IFileOperationService {

	@Override
	public Boolean isFileExist(String filePath) throws APIException {

		// Checks file path is valid
		// if file path is empty or contains white space char then throws ApiException
		if (StringUtils.isEmpty(filePath) || StringUtils.isWhitespace(filePath)) {

			throw new APIException("File Path can not be empty");

		}

		Path path = Paths.get(filePath);

		File packingFile = new File(path.toAbsolutePath().toString());

		// If file path is valid and file exists then return true otherwise return false
		return packingFile.exists();
	}

	@Override
	public List<String> getAllLinesFromFile(String filePath) throws APIException {

		Path path = Paths.get(filePath);

		try {

			// Get all lines from file with specific charset otherwise may be
			// MalformedInputException
			List<String> lines = Files.lines(path, Charset.forName(ServiceConstants.CHARSET_CP1252))
					.collect(Collectors.toList());

			return lines;

		} catch (IOException e) {
			// If IOException is thrown because of Files.lines then catch this exception and
			// suppress with APIException
			throw new APIException(e.getMessage(), e);

		}
	}

	@Override
	public com.mobiquity.model.Pack getPackInformationFromLine(String line) throws APIException {
		try {

			// First splitting line with ":"
			String[] lineSplittingWithColon = line.split(ServiceConstants.COLON);

			// Taking first element after splitting with colon which is used for package
			// weight
			Integer packageWeight = Integer.parseInt(lineSplittingWithColon[0].trim());

			// Validating maximum allowable package's weight
			if (PackerConstants.MAX_ALLOWABLE_PACKAGE_WEIGHT
					.compareTo(Double.parseDouble(lineSplittingWithColon[0].trim())) == -1) {
				throw new APIException("Max weight that a package can take is exceeded!");
			}

			// Remain part is splitting with space char (i.e: "(3,3.98,�16)",
			// "(4,26.24,�55)")
			String[] itemsArray = lineSplittingWithColon[1].trim().split(ServiceConstants.SPACE);

			// Validating maximum allowable item's numbers
			if (PackerConstants.MAX_ALLOWABLE_NUMBER_OF_ITEMS.compareTo(itemsArray.length) == -1) {
				throw new APIException("Max items count is exceeded!");
			}

			List<Item> itemList = new ArrayList<>();

			for (String itemLine : itemsArray) {

				// Left and Right parenthesis replacing with empty char (i.e: "3,3.98,�16")
				String itemPropertiesWithoutBrackets = itemLine
						.replace(ServiceConstants.LEFT_PARENTHESIS, ServiceConstants.EMPTY)
						.replace(ServiceConstants.RIGHT_PARENTHESIS, ServiceConstants.EMPTY);

				// Remain part is splitting with comma (i.e: "3","3.98","�16")
				String[] itemProperties = itemPropertiesWithoutBrackets.split(ServiceConstants.COMMA);

				Integer itemIndex = Integer.parseInt(itemProperties[0].trim());
				Double itemWeight = Double.parseDouble(itemProperties[1].trim());
				Integer itemCost = Integer.parseInt(itemProperties[2].trim().substring(1));

				// Validating maximum allowable item's cost and weight
				if (PackerConstants.MAX_ALLOWABLE_ITEM_COST.compareTo(itemCost) == -1) {
					throw new APIException("Max cost of an item is exceeded!");
				}
				if (PackerConstants.MAX_ALLOWABLE_ITEM_WEIGHT.compareTo(itemWeight) == -1) {
					throw new APIException("Max weight of an item is exceeded!");
				}

				//Adding Item to item list
				itemList.add(new Item(itemIndex, itemWeight, itemCost));

			}

			// return Pack Model with list of item and package weight
			return new Pack(itemList, packageWeight);

		} catch (NumberFormatException e) {
			// If NumberFormatException is thrown because of parsing String to Integer and
			// Double then catch this exception and
			// suppress with APIException
			throw new APIException(e.getMessage(), e);
		}
	}

}