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
public class PackerTest {

	private PackerTestUtil packerTestUtil = new PackerTestUtil();

	@Before
	public void prepareTestProcess() {
		packerTestUtil.deleteExistingFile();
	}

	@Test
	public void testForMissingFile() {
		try {
			Packer.pack(packerTestUtil.MissingFilePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForHeavierPacketWeightThanMaxAllowableWeight() {
		try {
			String filePath = packerTestUtil
					.createFileWithTestData(packerTestUtil.HeavierPacketWeightThanMaxAllowableWeight);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForHeavierItemWeightThanMaxAllowableWeight() {
		try {
			String filePath = packerTestUtil
					.createFileWithTestData(packerTestUtil.HeavierItemWeightThanMaxAllowableWeight);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForMoreItemsThanMaxAllowed() {
		try {
			String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.MoreItemsThanMaxAllowed);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForMuchItemCostThanMaxAllowed() {
		try {
			String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.MuchItemCostThanMaxAllowed);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForItemsHasSameCostButDifferentWeight() {
		try {
			String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.ItemsHasSameCostButDifferentWeight);
			String pack = Packer.pack(filePath);
			assertEquals("2,3,4", pack);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForInvalidRequest() {
		try {
			String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.InvalidRequest);
			Packer.pack(filePath);
		} catch (Exception e) {
			assertEquals(APIException.class, e.getClass());
		}
	}

	@Test
	public void testForValidSingleLineRequest() throws APIException {
		String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.validSingleLineRequest);
		String pack = Packer.pack(filePath);
		String[] split = pack.split("\\r?\\n");
		List<String> results = Arrays.asList(split);
		assertEquals(packerTestUtil.validSingleLineResponse, results);
	}

	@Test
	public void testForValidMultiLineRequest() throws APIException {
		String filePath = packerTestUtil.createFileWithTestData(packerTestUtil.validMultiLineRequest);
		String pack = Packer.pack(filePath);
		String[] split = pack.split("\\r?\\n");
		List<String> results = Arrays.asList(split);
		assertEquals(packerTestUtil.validMultiLineResponse, results);
	}

}
