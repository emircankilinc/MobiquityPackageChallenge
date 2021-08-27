package com.mobiquity.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.service.intf.IPackerService;

/**
 * @author emircankilinc
 *
 */
public class PackerService implements IPackerService {

	@Override
	public List<Item> solvePackProblem(Pack packageProblem) {

		List<Item> items = packageProblem.getItems();

		Integer packageWeight = packageProblem.getWeight();

		return getSolution(packageWeight, items);
	}

	/**
	 * Package Challenge is same as 0-1 Knapsack Problem. So Memoization Technique
	 * (an extension of recursive approach) is used while solving this problem. But
	 * normally this solution return only result total weight value but in this case
	 * solution contains item index. For this reason some new features and logics
	 * added this algorithm.
	 * 
	 * Actually in this algorithm we need 2D array but in this situation we don't
	 * need for this array because of we have to hold items which has to be in the
	 * package and total cost is max.
	 * 
	 * 
	 * 
	 * @param List of items and package weight
	 * @return List of Items which will be in the package.
	 */
	public List<Item> getSolution(Integer packageWeight, List<Item> items) {

		// Size of item
		Integer sizeOfItem = items.size();

		Integer weightOfPackage = packageWeight;

		// Sorting item list because of below constraint.
		// You would prefer to send a package which weighs less in case there is more
		// than one package with the same price.
		items.sort((item1, item2) -> item1.getWeight().compareTo(item2.getWeight()));

		// Initialize Boolean array to keep track of which item is in the package
		Boolean[] isItemInPackage = new Boolean[items.size()];

		// Default Boolean.False assign all array elements.
		for (int i = 0; i < isItemInPackage.length; i++) {
			isItemInPackage[i] = Boolean.FALSE;
		}

		// start recursion and return maximum value with item/items which is/are in the
		// package
		solution(weightOfPackage, items, sizeOfItem, isItemInPackage);

		// Adding item result list if item is in the package
		List<Item> result = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (Boolean.TRUE.equals(isItemInPackage[i])) {
				result.add(items.get(i));
			}
		}

		// Sorting item result list with its index value.
		result.sort((item1, item2) -> item1.getIndex().compareTo(item2.getIndex()));

		return result;

	}

	/**
	 * Memoization Technique (an extension of recursive approach) is applied to
	 * solve this problem with adding some new features.
	 * 
	 * @param weightOfPackage, list of items, size of item and isItemInPackage
	 *                         boolean array is used with recursive solution
	 * 
	 * @return Integer value that total cost is as large as possible
	 */
	private static Integer solution(Integer weightOfPackage, List<Item> items, Integer sizeOfItem,
			Boolean[] isItemInPackage) {

		// Base condition for recursive algorithm in this problem
		if (sizeOfItem == 0 || weightOfPackage == 0)
			return 0;

		// If item's weight is heavier than total weight of package then this item
		// cannot be in the package so skip this item and continue recursion with
		// remain items
		if (items.get(sizeOfItem - 1).getWeight() > weightOfPackage) {

			return solution(weightOfPackage, items, sizeOfItem - 1, isItemInPackage);

		} else {
			// Creating two boolean array for which items should be in the array if first
			// item has maximum total cost so we take this otherwise we take the second one.
			Boolean firstItemInPackageArray[] = new Boolean[isItemInPackage.length];
			Boolean secondItemInPackageArray[] = new Boolean[isItemInPackage.length];

			// System.arraycopy method that is also in the Java Core library. Using this
			// copying array from source array to destination array. Position 0 to length of
			// above initialized boolean array length
			System.arraycopy(isItemInPackage, 0, firstItemInPackageArray, 0, firstItemInPackageArray.length);
			System.arraycopy(isItemInPackage, 0, secondItemInPackageArray, 0, secondItemInPackageArray.length);

			// Changing first array element that at end of the array Boolean.False default
			// value to Boolean.True
			firstItemInPackageArray[sizeOfItem - 1] = true;

			// Adding first element at end of the items list and calculate the solution with
			// recursive
			Integer firstSolution = items.get(sizeOfItem - 1).getCost()
					+ solution(weightOfPackage - items.get(sizeOfItem - 1).getWeight().intValue(), items,
							sizeOfItem - 1, firstItemInPackageArray);

			// Passing end of the element without adding to package and trigger the
			// recursive solution
			Integer secondSolution = solution(weightOfPackage, items, sizeOfItem - 1, secondItemInPackageArray);

			// Checks which solution makes total cost is maximum
			// Return Maximum cost solution and items which should be in the array
			if (firstSolution > secondSolution) {
				System.arraycopy(firstItemInPackageArray, 0, isItemInPackage, 0, firstItemInPackageArray.length);
				return firstSolution;
			} else {
				System.arraycopy(secondItemInPackageArray, 0, isItemInPackage, 0, secondItemInPackageArray.length);
				return secondSolution;
			}

		}
	}

}
