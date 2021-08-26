package com.mobiquity.service.intf;

import java.util.List;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Pack;

/**
 * @author emircankilinc
 *
 */
public interface IFileOperationService {

	/**
	 * Checks filePath is valid and file exists.
	 * 
	 * @param filePath the string where file exist in this path
	 * @return Boolean.True if filePath is valid and file exist; otherwise return
	 *         Boolean.False
	 * @throws APIException if filePath is empty string or null
	 */
	public Boolean isFileExist(String filePath) throws APIException;

	/**
	 * Opens and reads lines from file where given path
	 * 
	 * @param filePath the string where file exist in this path
	 * @return List of String which contains each line in the file.
	 * @throws APIException if IOException is thrown from Files.lines
	 */
	public List<String> getAllLinesFromFile(String filePath) throws APIException;

	/**
	 * Parsing line and converting to Pack model.
	 * 
	 * @param line the string that
	 * @return Pack which contains Item List and Pack
	 *         capacity information
	 * @throws APIException if NumberFormatException is thrown while parsing String
	 *                      to Double or Integer
	 */
	public Pack getPackInformationFromLine(String line) throws APIException;

}
