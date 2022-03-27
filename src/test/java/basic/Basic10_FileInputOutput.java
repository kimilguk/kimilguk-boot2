package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 입력,출력 스트림 클래스를 사용하여 파일 입출력을 구현한다. 
 * @author kimilguk
 * Basic10_FileInputOutput.java
 */
public class Basic10_FileInputOutput {
	public static void main(String[] args) throws IOException {
		//내PC에 저장된 텍스트메모장파일 읽어들이고, 출력하기 구현
		//내부변수 선언
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		//오브젝트,실행가능한 클래스형 변수 생성,인스턴스(실행)
		fileInputStream = new FileInputStream("C:\\egov\\eGovFrameDev-4.0.0-64bit\\eclipse\\eclipse.ini");
		fileOutputStream = new FileOutputStream("C:\\egov\\eGovFrameDev-4.0.0-64bit\\eclipse\\eclipse_ini.txt");//신규파일에 저장하는 클래스명령어 집합
		//특수문자를 " " 내부에서 사용할때 특수문자(예, " \ ...)를 문자로 인식하게 하는 역할 역슬래시 " \" \\ "
		//아래 반복문으로 for(시작;끝;증가)만 사용, for 대신에 while(~조건동안){구현을 반복}
		int byte_content;
		while( (byte_content = fileInputStream.read()) != -1 ) {//-1은 문서끝을 읽어들일때까지
			System.out.print("바이트형 문자 읽어 들이기 " + (char)byte_content);
			//read()로 읽어들인 바이트문자를 manifest_bak.txt파일이 생성되면서 내용이 저장(아래)
			fileOutputStream.write(byte_content);
		}
	}
}
