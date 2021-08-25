package com.mobiquity.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.intf.IFileOperationService;

/**
 * @author emircankilinc
 *
 */
public class FileOperationService implements IFileOperationService {

	@Override
	public Boolean isFileExist(String filePath) throws APIException {
		if (StringUtils.isEmpty(filePath) || StringUtils.isWhitespace(filePath)) {
			throw new APIException("File Path can not be empty");
		}
		Path path = Paths.get(filePath);
		File packingFile = new File(path.toAbsolutePath().toString());
		return packingFile.exists();
	}

	@Override
	public List<String> getAllLinesFromFile(String filePath) {
		Path path = Paths.get(filePath);
		try {
			List<String> lines = Files.lines(path, Charset.forName("Cp1252")).collect(Collectors.toList());
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
