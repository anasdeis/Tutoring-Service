package ca.mcgill.ecse321.tutoringservice;
//import android.content.Context;
//import com.loopj.android.http.*;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * This is a helper class to integrate our backend business with the android application
 */
public class HttpUtils {
  public static final String DEFAULT_BASE_URL = "https://tutoringservice-backend-123.herokuapp.com/";

  private static String baseUrl;
  private static AsyncHttpClient client = new AsyncHttpClient();

  static {
    baseUrl = DEFAULT_BASE_URL;
  }

  /**
   * getBaseUrl
   *
   * @return baseUrl
   */
  public static String getBaseUrl() {
    return baseUrl;
  }

  /**
   * setBaseUrl
   *
   * @param baseUrl
   */
  public static void setBaseUrl(String baseUrl) {
    HttpUtils.baseUrl = baseUrl;
  }

  /**
   * The following methods take the same arguments as parameter and are designed for CRUD operation
   *
   * @param url
   * @param params
   * @param responseHandler
   */
  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.get(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.post(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.delete(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void patch(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.patch(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.get(url, params, responseHandler);
  }

  public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.post(url, params, responseHandler);
  }

  /**
   * this method will get the absolute url by combining the base url with the relative url
   *
   * @param relativeUrl
   * @return
   */
  private static String getAbsoluteUrl(String relativeUrl) {
    return baseUrl + relativeUrl;
  }
}
