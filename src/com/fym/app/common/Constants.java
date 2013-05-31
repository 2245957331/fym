/**   
* @Title: Constants.java 
* @Package com.fym.app.common 
* @Description: TODO 
* @author fengyuming
* @date 2013-5-30 下午11:18:31 
* @version V1.0   
*/
package com.fym.app.common;

/** 
 * @ClassName: Constants 
 * @Description: TODO
 * @author fengyuming
 * @date 2013-5-30 下午11:18:31 
 *  
 */
public final class Constants {
	
	private Constants() {
	}

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}
	
	public static String mDefPakName = "com.fym.app";
	public static String mCurPakName = mDefPakName;
	
	public static void setPakName(String pakName)
	{
		mCurPakName = pakName;
		Extra.IMAGES = mCurPakName + ".IMAGES";
		
	}
	
	public static String getPakName()
	{
		return mCurPakName;
	}
	
	public static class Extra {
		public static String IMAGES = mDefPakName + ".IMAGES";
		public static String IMAGE_POSITION = mDefPakName + ".IMAGE_POSITION";
	}
	
	public static class Configs {
		// 分钟
		public static int Content_ListCacheTime = 5;
		public static int Content_ContentCacheTime = 60 * 24 * 3;
		public static int ImageCacheTime = 60 * 24 * 15;
		public static int Content_DefaultCacheTime = 60 * 24 * 3;
		
		public static int DiscussCacheTime=60;
	}
	
	public static final class DBContentType {
		public static final String Content_list = "list";
		public static final String Content_content = "content";
		public static final String Discuss="discuss";
	}

	public static final class WebSourceType{
		public static final String Json="json";
		public static final String Xml="xml";
	}
}
