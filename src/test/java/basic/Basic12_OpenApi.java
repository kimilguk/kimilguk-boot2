package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * 영화진흥위원회 오픈API 서비스에서 제공하는 일일박스오피스 목록을 출력하는 기능구현
 * @author kimilguk
 * Basic12_OpenApi.java
 */
public class Basic12_OpenApi {
	//Rest(Representational State Transfer:데이터를 재가공해서 통신)
	//API(Applicataion Program Interface) 외부연계 역할 메서드(아래)
	public static void serviceApi() throws IOException {
		BufferedReader bufferedReader = null;//전송받은 데이터를 임시 저장하는 공간.자동차범퍼.
		String urlStr = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20220101";
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");//URL쿼리스트링으로 파라미터를 보낸다.
        System.out.println("응답 code: " + conn.getResponseCode());//200 = OK
		bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();//응답데이터를 누적저장하는 클래스 객체
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();//객체 제거
        conn.disconnect();//객체 제거
		//System.out.println(sb.toString()+"파싱 전\n");//전체 결과출력
		//전체 데이터에서 화면에 출력할 부분 파싱(주의 Json데이터는 Key:Value 로 구성되고, 계층형 구조를 갖는다)
		JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
        //Object body = boxOfficeResult.get("dailyBoxOfficeList");
        System.out.println("파싱 후\n" + boxOfficeResult);//웹 화면에 JSON 객체를 보낸다.
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		serviceApi();
	}

}
