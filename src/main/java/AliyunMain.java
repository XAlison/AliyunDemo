import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName: main
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2018/12/29 16:36
 * @Version 1.0
 */
public class AliyunMain {
    public static void main(String[] args) {

        String imgFile = "d:\\微信图片_20181229163732.jpg";//待处理的图片
        String imgbese = Img2Base64Util.getImgStr(imgFile);
        System.out.println(imgbese.length());

//        String host = "https://ocrapi-invoice.taobao.com";
//        String path = "/ocrservice/invoice";

        String host = "https://ocrapi-ugc.taobao.com";
        String path = "/ocrservice/ugc";
        String method = "POST";
        String appcode = "5c420b8efc6240c39378da04c25ccc15";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"img\":\"" + imgbese + "\"}";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
