package com.mobiquity.service.intf;

import java.util.List;

import com.mobiquity.exception.APIException;

/**
 * @author emircankilinc
 *
 */
public interface IFileOperationService {

	public Boolean isFileExist(String filePath) throws APIException;

	public List<String> getAllLinesFromFile(String filePath);

}
