import junit.framework.Assert;

import org.junit.Test;


public class BoardTest {
    
    @Test
    public void testHammingAndMahhanTan(){
        int[][] blocks = new int[3][3];
        blocks[0][0] = 8;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 0;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 5;
        Board board = new Board(blocks);
        Assert.assertEquals(5, board.hamming());
        Assert.assertEquals(10, board.manhattan());
        Assert.assertFalse(board.isGoal());
    }
    
    @Test
    public void testIsGoal(){
        int[][] blocks = new int[3][3];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 0;
        Board board = new Board(blocks);
        Assert.assertTrue(board.isGoal());
    } 
    
    @Test
    public void testIterable(){
        int[][] blocks = new int[3][3];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 8;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 5;
        
        Board b = new Board(blocks);
        System.out.println(b);
        
        for(Board board: b.neighbors()){
            System.out.println(board);
        }
        
    }
}
