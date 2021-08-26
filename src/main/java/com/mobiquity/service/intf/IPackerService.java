package com.mobiquity.service.intf;

import java.util.List;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;

/**
 * @author emircankilinc
 *
 */
public interface IPackerService {

	/**
	 * Determine which items to put into the pack so that the total weight is less
	 * than or equal to the package limit and total cost is maximum.
	 * 
	 * @param Pack information that contains item list and pack capacity
	 * @return List of Items which will be in the package.
	 */
	public List<Item> solvePackProblem(Pack pack);

}
