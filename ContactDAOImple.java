package edu.java.contact04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

//DAO(Data Access Object) :
//- 데이터의 전송을 담당하는 역할
public class ContactDAOImple implements ContactDAO {

	// 싱글톤 디자인 패턴 적용

	// 1. private static 자기 자신 타입의 변수 선언
	private static ContactDAOImple instance = null;

	// 2. private 생성자
	private ContactDAOImple() {
	}

	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static ContactDAOImple getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}

	// 연락처 저장 배열
	ArrayList<ContactVO> list = new ArrayList<>();

	// 데이터를 저장할 폴더와 파일 이름 정의
	private static final String DATA_DIR = "data";
	private static final String DATA_FILE = "contact.data";
	private static final String DATA_PATH = DATA_DIR + File.separator + DATA_FILE;
	// data 폴더의 contact.data 파일을 관리할 File 객체 선언
	private File dataDirFile;
	private File dataFile;

	// TODO : data 폴더가 있는지 검사하고, 없으면 생성하는 함수
	private void initDataDir() {
		File dataDirFile = new File(DATA_DIR);

		if (!dataDirFile.exists()) { // 파일,폴더가 없는 경우
			System.out.println("폴더가 없습니다.");
			// mkdir() : 디렉토리를 생성하는 메소드. 성공하면 true 실패하면 false
			// mkdirs() : 상위디렉토리를 포함하여 생성하는 메소드.
			if (dataDirFile.mkdirs()) {
				System.out.println("폴더 생성 성공");
			} else {
				System.out.println("폴더 생성 실패");
			}
		} else { // 파일 폴더가 있는 경우
			System.out.println("폴더가 이미 존재합니다.");
		}
	} // initDataDir()

	// TODO : 데이터 파일이 존재하는지 검사하고,
	// 없는 경우 - 새로운 데이터 파일 생성
	// 있는 경우 - 기존 파일에서 데이터를 읽어서 ArrayList에 추가
	private void initDataFile() {

		File dataFile = new File(DATA_PATH);

		if (!dataFile.exists()) {
			System.out.println("파일이 없습니다.");

			// createNewfile() : 새로운 빈 파일 생성
			try {
				if (dataFile.createNewFile()) {
					System.out.println("파일 생성 성공");
				} else {
					System.out.println("파일 생성 실패");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			OutputStream out = null;
			BufferedOutputStream bout = null;
			ObjectOutputStream oout = null;

			try {
				out = new FileOutputStream(DATA_PATH);
				bout = new BufferedOutputStream(out);
				oout = new ObjectOutputStream(bout);

				oout.writeObject(list);

			} catch (FileNotFoundException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					oout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	} // end initDataFile()

	// TODO : 멤버 변수 list 객체를 data\contact.data. 파일에 저장(쓰기)
	private void writeDataToFile() {

		OutputStream out = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;

		try {

			out = new FileOutputStream(DATA_PATH);
			bout = new BufferedOutputStream(out);
			oout = new ObjectOutputStream(bout);

			oout.writeObject(list);

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				oout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // writeDataTofile()

	// TODO : data\contact.data 파일에서 ArrayList 객체를 읽어와서
	// 멤버 변수 list에 저장(읽기)
	private void readDataFromFile() {

		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;

		ContactVO vo = new ContactVO();

		try {
			in = new FileInputStream(DATA_PATH);
			bin = new BufferedInputStream(in);
			oin = new ObjectInputStream(bin);

			list = (ArrayList<ContactVO>) oin.readObject();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {

			try {
				oin.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // end readDataFromFile()

	public int getSize() {
		return list.size();
	}

	@Override
	public int insert(ContactVO vo) {
		initDataDir();
		list.add(vo);
		initDataFile();
		return 1;

	}

	@Override
	public ArrayList<ContactVO> select() {

		readDataFromFile();
		return list;
	}

	@Override
	public ContactVO select(int index) {
		return list.get(index);

	}

	@Override
	public int update(int index, ContactVO vo) {
		list.get(index).setPhone(vo.getPhone());
		list.get(index).setEmail(vo.getEmail());
		return 1;
	}

	public int remove(int index) {
		list.remove(index);
		return 1;
	}

}
