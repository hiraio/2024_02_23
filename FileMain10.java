package edu.java.file10;

import java.io.File;

public class FileMain10 {
	public static final String TEST1 = "test1";
	public static final String TEST2 = "test2";
	public static final String DUMMY = "dummy.txt";
	
	public static void main(String[] args) {
		
		// 파일, 디렉토리 이름 변경/ 삭제
		// test1 -> test2 디렉토리 이름 변경
		File org = new File(TEST1);
		File dest = new File(TEST2);
		
		if(org.renameTo(dest)) {
			System.out.println("디렉토리 이름 변경 성공");
			
		} else {
			System.out.println("디렉토리 이름 변경 실패");
		}
		
		// test2\dummy.txt 파일 삭제
		String filePath = TEST2 + File.separator + DUMMY;
		File file = new File(filePath);
		if(file.delete()) { // delete() -> 지우고, 성공하면? true로 리턴
			System.out.println("파일 삭제 성공");
		} else {
			System.out.println("파일 삭제 실패");
		}
		
	} // end main()
	

} // end FileMain10
