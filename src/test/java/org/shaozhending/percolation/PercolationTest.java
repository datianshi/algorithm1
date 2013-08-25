package org.shaozhending.percolation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {
	private Percolation percolation;
	@Before
	public void setUp(){
		percolation = new Percolation(5);
	}
	
	@Test
	public void shouldReturnCorrectNeighbors(){
		int[] result = percolation.getNeighbors(1, 1);
		Assert.assertEquals(2, result.length);
		Assert.assertEquals(1, result[0]);
		Assert.assertEquals(5, result[1]);
		
		result = percolation.getNeighbors(1, 5);
		Assert.assertEquals(2, result.length);
		Assert.assertEquals(9, result[0]);
		Assert.assertEquals(3, result[1]);
		
		result = percolation.getNeighbors(5, 1);
		Assert.assertEquals(2, result.length);
		Assert.assertEquals(15, result[0]);
		Assert.assertEquals(21, result[1]);
		
		result = percolation.getNeighbors(5, 5);
		Assert.assertEquals(2, result.length);
		Assert.assertEquals(19, result[0]);
		Assert.assertEquals(23, result[1]);
		
		result = percolation.getNeighbors(2, 1);
		Assert.assertEquals(3, result.length);
		Assert.assertEquals(0, result[0]);
		Assert.assertEquals(6, result[1]);
		Assert.assertEquals(10, result[2]);
		
		result = percolation.getNeighbors(1, 2);
		Assert.assertEquals(3, result.length);
		Assert.assertEquals(2, result[0]);
		Assert.assertEquals(6, result[1]);
		Assert.assertEquals(0, result[2]);
		
		result = percolation.getNeighbors(5, 2);
		Assert.assertEquals(3, result.length);
		Assert.assertEquals(16, result[0]);
		Assert.assertEquals(22, result[1]);
		Assert.assertEquals(20, result[2]);
		
		result = percolation.getNeighbors(2, 5);
		Assert.assertEquals(3, result.length);
		Assert.assertEquals(4, result[0]);
		Assert.assertEquals(14, result[1]);
		Assert.assertEquals(8, result[2]);
		
		result = percolation.getNeighbors(2, 3);
		Assert.assertEquals(4, result.length);
		Assert.assertEquals(2, result[0]);
		Assert.assertEquals(8, result[1]);
		Assert.assertEquals(12, result[2]);
		Assert.assertEquals(6, result[3]);
	}
	
	@Test
	public void shouldReturnCorrectFullSite(){
		Assert.assertFalse(percolation.isFull(1, 1));
		percolation.open(1, 2);
		Assert.assertFalse(percolation.isFull(2, 3));
		percolation.open(2, 2);
		percolation.open(2, 3);
		Assert.assertTrue(percolation.isFull(2, 3));
		percolation.open(3, 3);
		percolation.open(4, 2);
		Assert.assertFalse(percolation.isFull(4, 2));
		Assert.assertTrue(percolation.isFull(3, 3));
		percolation.open(3, 2);
		Assert.assertTrue(percolation.isFull(4, 2));
	}
	
	@Test
	public void shouldReturnCorrectPercolation(){
		Assert.assertFalse(percolation.percolates());
		percolation.open(1, 2);
		percolation.open(2, 2);
		percolation.open(3, 3);
		percolation.open(4, 3);
		percolation.open(5, 3);
		
		Assert.assertFalse(percolation.percolates());
		percolation.open(2, 3);
		Assert.assertTrue(percolation.percolates());
	}
		
}
