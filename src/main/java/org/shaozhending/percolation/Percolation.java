package org.shaozhending.percolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Percolation {
	private WeightedQuickUnionUF wQuickUnion;
	private int width;
	//false means block, while true means open
	private boolean[] site;
	private int numOfObjects;

	public Percolation(int N){
		   numOfObjects = N*N;
		   site = new boolean[numOfObjects];
		   width = N;
		   wQuickUnion = new WeightedQuickUnionUF(N*N);
		   for(int i=0; i < numOfObjects; i++){
			   site[i] = false;
		   }
	   }

	public void open(int i, int j) {
		if(!valid(i,j)){
			throw new IndexOutOfBoundsException("Site: [" + i +"][" + j + "]");
		}
		int position = index(i, j);
		site[position] = true;
		int[] neighbors = getNeighbors(i, j);
		for(int n = 0; n< neighbors.length; n++){
			if(site[neighbors[n]]){
				wQuickUnion.union(position, neighbors[n]);
			}
		}
	}

	public boolean isOpen(int i, int j) {
		return site[index(i,j)];
	}

	public boolean isFull(int i, int j) {
		int position = index(i, j);
		if(!site[position]){
			return false;
		}
		for(int col=0; col< width; col++){
			if(wQuickUnion.connected(position, col)){
				return true;
			}
		}
		return false;
	}

	public boolean percolates() {
		for(int i=0; i < width; i++){
			if(isFull(width, i)){
				return true;
			}
		}
		return false;
	}
	
	protected int index(int i, int j){
		return (i - 1) * width + j - 1;
	}
	
	protected int[] getNeighbors(int i, int j){
		int[] positions = new int[4];
		//up
		int count = 0;
		if(valid(i-1, j)){
			positions[count] = index(i-1,j);
			count++;
		}
		//right
		if(valid(i, j+1)){
			positions[count] = index(i,j+1);
			count++;
		}
		//down
		if(valid(i+1, j)){
			positions[count] = index(i+1, j);
			count++;
		}
		//left
		if(valid(i, j-1)){
			positions[count] = index(i, j-1);
			count++;
		}
		int[] result = new int[count];
		for(int ix=0; ix<count ;ix++){
			result[ix] = positions[ix];
		}
		return result;
	}
	
	protected boolean valid(int i, int j){
		if(i > width || j > width || i< 1 || j <1){
			return false;
		}
		else{
			return true;
		}
	}
}
