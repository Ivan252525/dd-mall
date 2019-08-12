package com.dd.common.utils.http;

import com.dd.common.utils.string.StringUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

public class HttpsUtils {
    private static class TrustAnyTrustManager implements X509TrustManager {


        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }


        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                throws CertificateException {
        }


        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }


    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    /**
     * post方式请求服务器(https协议)
     *
     * @param url 请求地址
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static HttpResponse getter(String url) throws IOException {
        return getter(url, null);
    }


    public static HttpResponse getter(String url, String cookie)
            throws IOException {
        HttpResponse response = new HttpResponse();
        StringBuilder body = new StringBuilder();
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(
                    null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL realUrl = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("GET");
            if (!StringUtils.isEmpty(cookie)) {
                conn.setRequestProperty("Cookie", cookie);
            }

            response.setCode(200);
            // 获取所有响应头字段
            response.setHeaders(conn.getHeaderFields());

            boolean isImg = false;
            for (Map.Entry<String, List<String>> entry : response.getHeaders().entrySet()) {
                if ("Content-Type".equals(entry.getKey()) && "image/jpeg".equals(entry.getValue().get(0))) {
                    isImg = true;
                }
            }

            if (isImg) {
                response.setInputStream(conn.getInputStream());
            } else {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    body.append(line);
                }
                response.setBody(body.toString());
            }
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return response;
    }


    public static HttpsURLConnection getConnectionStream(String url, String content, String charset)
            throws IOException {
        return getConnectionStream(url, content, charset, "");
    }


    public static HttpsURLConnection getConnectionStream(
            String url, String content, String charset, String cookie) throws IOException {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(
                    null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL realUrl = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("GET");
            setProperty(charset, conn, cookie);
            return conn;


        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }


    public static String post(String url, String content, String charset, String contentType) throws IOException {
        return post(url, content, charset, "", contentType);
    }


    /**
     * post方式请求服务器(https协议)
     *
     * @param url     请求地址
     * @param content 参数
     * @param charset 编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static String post(String url, String content, String charset, String cookie, String contentType)
            throws IOException {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(
                    null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            setProperty(charset, conn, cookie);
            conn.setRequestMethod("POST");
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            if (!StringUtils.isEmpty(contentType)) {
                conn.setRequestProperty("Content-Type", content);
            }
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(content.getBytes(charset));
            out.flush();
            out.close();

            StringBuilder result = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }


    public static String postFile(String url, Map<String, String> param, String fileKey, String fileName, byte[] file, String cookie)
            throws IOException {
        final String NEWLINE = "\r\n";
        final String PREFIX = "--";
        final String BOUNDARY = "-----------------------------1575017231431605357584454111";
        final String CHARSET = "UTF-8";
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(
                    null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            setProperty(CHARSET, conn, cookie);
            conn.setRequestMethod("POST");
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.connect();

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            if (param != null && !param.isEmpty()) {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    out.writeBytes(PREFIX + BOUNDARY + NEWLINE);
                    out.writeBytes("Content-Disposition: form-data; "
                            + "name=\"" + key + "\"" + NEWLINE);
                    out.writeBytes(NEWLINE);
                    out.write(value.getBytes(CHARSET));
                    out.writeBytes(NEWLINE);
                }
            }
            if (file != null && file.length > 0) {
                out.writeBytes("Content-Disposition: form-data; " + "name=\""
                        + fileKey + "\"" + "; filename=\"" + fileName
                        + "\"" + NEWLINE);
                // 换行，重要！！不要忘了
                out.writeBytes(NEWLINE);
                out.write(file); // 上传文件的内容
                out.writeBytes(NEWLINE); // 最后换行
            }
            out.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE); // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
            out.flush();

            StringBuilder result = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }


    private static void setProperty(String encoding, URLConnection conn, String cookie) {
        conn.setRequestProperty("Accept-Charset", encoding);
        conn.setRequestProperty(
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.1;SV1)");
        if (!StringUtils.isEmpty(cookie)) {
            conn.setRequestProperty("Cookie", cookie);
        }
    }
}
