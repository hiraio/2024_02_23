package edu.java.file09;

import java.io.File;
import java.io.IOException;

public class FileMain09 {
	public static final String DIR_NAME = "test1";
	public static final String FILE_NAME = "dummy.txt";
	public static final String FILE_PATH = 
			DIR_NAME + File.separator + FILE_NAME;

	public static void main(String[] args) {
		System.out.println(FILE_PATH);

		File f = new File(FILE_PATH);

		if (!f.exists()) {
			System.out.println("파일이 없습니다.");

			// createNewfile() : 새로운 빈 파일 생성
			try {
				if (f.createNewFile()) {
					System.out.println("파일 생성 성공");
				} else {
					System.out.println("파일 생성 실패");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("파일이 이미 존재합니다.");

		}

	} // end main()

} // end FileMain09
