package com.cwc.ddd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class OnceKey
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//如果要改教室的座位数 这里改,
		String[][] home = new String[13][8];
		String s = "讲师机箱空白";
		//如果要改 讲师和机箱的位置 这里改
		home[12][4] = "讲师";
		home[12][7] = "机箱";
		//如果要改柱子那个地方的空位置 在这改
		home[5][0] = "空白";
		home[5][7] = "空白";
		PrintWriter pw=null;
		BufferedReader br=null;
		try
		{
			pw = new PrintWriter("dest.xls");
			br = new BufferedReader(new FileReader("source.txt"));
			String line = null;
			int row = 12;
			int cols = 7;
			Random rd = new Random();
			while((line = br.readLine())!=null){
				line = line.trim();
				
				
				if(home[row][cols]==null){
					home[row][cols] = line;
				}else{
					cols--;
					if(cols<0){
						cols = 7;
						row--;
					}
					home[row][cols] = line;
				}
				
				cols--;
				if(cols<0){
					cols = 7;
					row--;
				}
			}
			String temp1 =null;
			String temp2 = null;
			for(int i=0;i<10000;i++){
				int row1 = rd.nextInt(13);
				int cols1 = rd.nextInt(8);
				int row2 = rd.nextInt(13);
				int cols2 = rd.nextInt(8);
				temp1 =home[row1][cols1];
				temp2 = home[row2][cols2];
				while(temp1==null||s.contains(temp1)){
					row1 = rd.nextInt(13);
					cols1 = rd.nextInt(8);
					temp1 =home[row1][cols1];
				}
				
				while(temp2==null||s.contains(temp2)){
					row2 = rd.nextInt(13);
					cols2 = rd.nextInt(8);
					temp2 =home[row2][cols2];
				}
				
				String temp = home[row1][cols1];
				home[row1][cols1] = home[row2][cols2];
				home[row2][cols2] = temp;
			}
			
			for(int i=0;i<13;i++){
				for(int j=0;j<8;j++){
					pw.print(home[i][j]+"\t");
					if(j==3){
						pw.print("\t");
					}
				}
				pw.println();
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
				pw = null;
			}
			
			if(br!=null){
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				br=null;
			}
		}

	}

}
