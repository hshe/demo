package com.its.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;


public class UserUtils {

	@Value("${COMMON_CACHE_KEY_TIME}")
	private int COMMON_CACHE_KEY_TIME;
	@Value("${SSO_DOMAIN_BASE_URL}")
	private static String SSO_DOMAIN_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private static String SSO_USER_TOKEN;
	
	//http/token user'message.
	public static Object getCurrentUser(HttpServletRequest request){
		/*String token = CookieUtils.getCookieValue(request, "ITS_TOKEN");
		if(null == token){
			//重新登录
//			请求过期
		}
		
		
		
		
		String json = HttpClientUtil.doGet(SSO_DOMAIN_BASE_URL+SSO_USER_TOKEN+"/"+token);
		String status = JsonUtils.getJsonByKey(json, "status");//?
		*/
		
//		if("200" == status){
//			//outof date
//			Sysuser user = JsonUtils.jsonToPojo(JsonUtils.getJsonByKey(json, "data"), Sysuser.class);
//			return user;
//		}
		return null;
	}
	
	public static Object userLogout(String token){
		String json = HttpClientUtil.doGet(SSO_DOMAIN_BASE_URL+"/logout/"+token);
		return null;
	}
}
