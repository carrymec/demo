package com.example.demo.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SignatureException;
import java.util.*;


public class IpUtil {
	
	  public static String ipAddress(HttpServletRequest request){
			 String ipAddress = null;   
		     ipAddress = request.getHeader("x-forwarded-for");   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		      ipAddress = request.getHeader("Proxy-Client-IP");   
		     }   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		         ipAddress = request.getHeader("WL-Proxy-Client-IP");   
		     }   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		      ipAddress = request.getRemoteAddr();   
		      if(ipAddress.equals("127.0.0.1")){   
		       //根据网卡取本机配置的IP   
		        InetAddress inet=null;   
		    try {   
		       inet = InetAddress.getLocalHost();   
		    } catch (UnknownHostException e) {   
		     e.printStackTrace();   
		    }   
		    	ipAddress= inet.getHostAddress();   
		      }   
		     }   
		     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
		     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
		         if(ipAddress.indexOf(",")>0){   
		             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
		         }   
		     } 	
		    return ipAddress;
	  }
	  
	    /**  
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串  
	     * @param params 需要排序并参与字符拼接的参数组  
	     * @return 拼接后字符串  
	     */   
	    public static String createLinkString(Map<String, String> params) {   
	        List<String> keys = new ArrayList<String>(params.keySet());   
	        Collections.sort(keys);   
	        String prestr = "";   
	        for (int i = 0; i < keys.size(); i++) {   
	            String key = keys.get(i);   
	            String value = params.get(key);   
	            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符   
	                prestr = prestr + key + "=" + value;   
	            } else {   
	                prestr = prestr + key + "=" + value + "&";   
	            }   
	        }   
	        return prestr;   
	    } 
	    
		 /**  
	     * 签名字符串  
	     * @param text需要签名的字符串  
	     * @param key 密钥  
	     * @param input_charset编码格式  
	     * @return 签名结果  
	     */   
	    public static String sign(String text, String key, String input_charset) {   
	        text = text + "&key=" + key;   
	        return DigestUtils.md5Hex(getContentBytes(text, input_charset));   
	    }  
	    /**  
	     * @param content  
	     * @param charset  
	     * @return  
	     * @throws SignatureException  
	     * @throws UnsupportedEncodingException  
	     */   
	    public static byte[] getContentBytes(String content, String charset) {   
	        if (charset == null || "".equals(charset)) {   
	            return content.getBytes();   
	        }   
	        try {   
	            return content.getBytes(charset);   
	        } catch (UnsupportedEncodingException e) {   
	            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);   
	        }   
	    } 
	    /**  
	     *  
	     * @param requestUrl请求地址  
	     * @param requestMethod请求方法  
	     * @param outputStr参数  
	     */   
	    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){   
	        // 创建SSLContext   
	        StringBuffer buffer = null;   
	        try{   
		        URL url = new URL(requestUrl);   
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();   
		        conn.setRequestMethod(requestMethod);   
		        conn.setDoOutput(true);   
		        conn.setDoInput(true);   
		        conn.connect();   
		        //往服务器端写内容   
		        if(null !=outputStr){   
		            OutputStream os=conn.getOutputStream();   
		            os.write(outputStr.getBytes("utf-8"));   
		            os.close();   
		        }   
		        // 读取服务器端返回的内容   
		        InputStream is = conn.getInputStream();   
		        InputStreamReader isr = new InputStreamReader(is, "utf-8");   
		        BufferedReader br = new BufferedReader(isr);   
		        buffer = new StringBuffer();   
		        String line = null;   
		        while ((line = br.readLine()) != null) {   
		        	buffer.append(line);   
		        }   
		        br.close();
	        }catch(Exception e){   
	            e.printStackTrace();   
	        }
	        return buffer.toString();
	    }     
	    public static String urlEncodeUTF8(String source){   
	        String result=source;   
	        try {   
	            result=java.net.URLEncoder.encode(source, "UTF-8");   
	        } catch (UnsupportedEncodingException e) {   

	            e.printStackTrace();   
	        }   
	        return result;   
	    } 
	    public static void main(String[] args) {
//
	    }
		/**
		 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
		 * @param strxml
		 * @return
		 * @throws
		 * @throws IOException
		 */
		public static Map doXMLParse(String strxml) throws Exception {
			if(null == strxml || "".equals(strxml)) {
				return null;
			}
			/*=============  !!!!注意，修复了微信官方反馈的漏洞，更新于2018-10-16  ===========*/
			try {
	            Map<String, String> data = new HashMap<String, String>();
	            // 在这里更换
	            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
	            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
	            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
	            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	            documentBuilderFactory.setXIncludeAware(false);
	            documentBuilderFactory.setExpandEntityReferences(false);
	 
	            InputStream stream = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
	            org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(stream);
	            doc.getDocumentElement().normalize();
	            NodeList nodeList = doc.getDocumentElement().getChildNodes();
	            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
	                Node node = nodeList.item(idx);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
	                    data.put(element.getNodeName(), element.getTextContent());
	                }
	            }
	            try {
	                stream.close();
	            } catch (Exception ex) {
	                // do nothing
	            }
	            return data;
	        } catch (Exception ex) {
	            throw ex;
	        }
		}
		
	    /**  
	     * 除去数组中的空值和签名参数  
	     * @param sArray 签名参数组  
	     * @return 去掉空值与签名参数后的新签名参数组  
	     */   
	    public static Map<String, String> paraFilter(Map<String, String> sArray) {   
	        Map<String, String> result = new HashMap<String, String>();   
	        if (sArray == null || sArray.size() <= 0) {   
	            return result;   
	        }   
	        for (String key : sArray.keySet()) {   
	            String value = sArray.get(key);   
	            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")   
	                    || key.equalsIgnoreCase("sign_type")) {   
	                continue;   
	            }   
	            result.put(key, value);   
	        }   
	        return result;   
	    } 
			
}
