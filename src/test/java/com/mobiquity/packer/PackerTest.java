package com.mobiquity.packer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mobiquity.exception.APIException;

/**
 * @author emircankilinc
 *
 */
public class PackerTest extends PackerTestUtil {

	@Before
	public void prepareTestProcess() {
		deleteExistingFile();
	}

	@Test
	public void testForMissingFile() {
		try {
			Packer.pack(MissingFilePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForHeavierPacketWeightThanMaxAllowableWeight() {
		try {
			String filePath = createFileWithTestData(HeavierPacketWeightThanMaxAllowableWeight);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForHeavierItemWeightThanMaxAllowableWeight() {
		try {
			String filePath = createFileWithTestData(HeavierItemWeightThanMaxAllowableWeight);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForMoreItemsThanMaxAllowed() {
		try {
			String filePath = createFileWithTestData(MoreItemsThanMaxAllowed);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForMuchItemCostThanMaxAllowed() {
		try {
			String filePath = createFileWithTestData(MuchItemCostThanMaxAllowed);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForItemsHasSameCostButDifferentWeight() {
		try {
			String filePath = createFileWithTestData(ItemsHasSameCostButDifferentWeight);
			String pack = Packer.pack(filePath);
			assertEquals("2,3,4", pack);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForInvalidRequest() {
		try {
			String filePath = createFileWithTestData(InvalidRequest);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForValidSingleLineRequest() throws APIException {
		String filePath = createFileWithTestData(validSingleLineRequest);
		String pack = Packer.pack(filePath);
		String[] split = pack.split("\\r?\\n");
		List<String> results = Arrays.asList(split);
		assertEquals(validSingleLineResponse, results);
	}

	@Test
	public void testForValidMultiLineRequest() throws APIException {
		String filePath = createFileWithTestData(validMultiLineRequest);
		String pack = Packer.pack(filePath);
		String[] split = pack.split("\\r?\\n");
		List<String> results = Arrays.asList(split);
		assertEquals(validMultiLineResponse, results);
	}

}
