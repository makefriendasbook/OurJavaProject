package client_sever6;

public class Main {

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = "20190724000320745";
	private static final String SECURITY_KEY = "3YZER_NKNmOfbI2wxd6K";

	public static void main(String[] args) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		String query = "高度600米";
		System.out.println(api.getTransResult(query, "auto", "en"));
	}

}
