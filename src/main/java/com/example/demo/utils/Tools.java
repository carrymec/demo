package com.example.demo.utils;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：常用工具
 */
public class Tools {

    /**
     * 随机生成六位数验证码
     *
     * @return
     */
    public static int getRandomNum() {
        Random r = new Random();
        return r.nextInt( 900000 ) + 100000;//(Math.random()*(999999-100000)+100000)
    }
    
    /**
     * 处理表情包
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }
    
    /**
     * 随机生成四位数验证码
     *
     * @return
     */
    public static int getRandomNum4() {
        Random r = new Random();
        return r.nextInt( 9000 ) + 1000;
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     *
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        return s != null && !"".equals( s ) && !"null".equals( s );
    }

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals( s ) || "null".equals( s );
    }

    /**
     * 字符串转换为字符串数组
     *
     * @param str        字符串
     * @param splitRegex 分隔符
     * @return
     */
    public static String[] str2StrArray(String str, String splitRegex) {
        if (isEmpty( str )) {
            return null;
        }
        return str.split( splitRegex );
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     *
     * @param str 字符串
     * @return
     */
    public static String[] str2StrArray(String str) {
        return str2StrArray( str, ",\\s*" );
    }

    /**
     * 往文件里的内容
     *
     * @param
     * @param content 写入的内容
     */
    public static void writeFile(String fileP, String content) {
        String filePath = String.valueOf( Thread.currentThread().getContextClassLoader().getResource( "" ) ) + "../../";    //项目路径
        filePath = filePath.replaceAll( "file:/", "" );
        filePath = filePath.replaceAll( "%20", " " );
        filePath = filePath.trim() + fileP.trim();
        if (filePath.indexOf( ":" ) != 1) {
            filePath = File.separator + filePath;
        }
        try {
            OutputStreamWriter write = new OutputStreamWriter( new FileOutputStream( filePath ), "utf-8" );
            BufferedWriter writer = new BufferedWriter( write );
            writer.write( content );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 往文件里的内容（Projectpath下）
     *
     * @param
     * @param content 写入的内容
     */
    public static void writeFileCR(String fileP, String content) {
        String filePath = PathUtil.getProjectpath() + fileP;
        try {
            OutputStreamWriter write = new OutputStreamWriter( new FileOutputStream( filePath ), "utf-8" );
            BufferedWriter writer = new BufferedWriter( write );
            writer.write( content );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile( check );
            Matcher matcher = regex.matcher( email );
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile( "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$" );
            Matcher matcher = regex.matcher( mobileNumber );
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 检测KEY是否正确
     *
     * @param paraname 传入参数
     * @param FKEY     接收的 KEY
     * @return 为空则返回true，不否则返回false
     */
    public static boolean checkKey(String paraname, String FKEY) {
        paraname = (null == paraname) ? "" : paraname;
        return MD5.md5( paraname + DateUtil.getDays() + ",fh," ).equals( FKEY );
    }

    /**
     * 读取txt里的全部内容
     *
     * @param fileP    文件路径
     * @param encoding 编码
     * @return
     */
    public static String readTxtFileAll(String fileP, String encoding) {
        StringBuffer fileContent = new StringBuffer();
        try {
            String filePath = String.valueOf( Thread.currentThread().getContextClassLoader().getResource( "" ) ) + "../../";    //项目路径
            filePath = filePath.replaceAll( "file:/", "" );
            filePath = filePath.replaceAll( "%20", " " );
            filePath = filePath.trim() + fileP.trim();
            if (filePath.indexOf( ":" ) != 1) {
                filePath = File.separator + filePath;
            }
            File file = new File( filePath );
            if (file.isFile() && file.exists()) {        // 判断文件是否存在
                InputStreamReader read = new InputStreamReader( new FileInputStream( file ), encoding );    // 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader( read );
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    fileContent.append( lineTxt );
                    fileContent.append( "\n" );
                }
                read.close();
            } else {
                System.out.println( "找不到指定的文件,查看此路径是否正确:" + filePath );
            }
        } catch (Exception e) {
            System.out.println( "读取文件内容出错" );
        }
        return fileContent.toString();
    }

    /**
     * 读取Projectpath某文件里的全部内容
     *
     * @param
     */
    public static String readFileAllContent(String fileP) {
        StringBuffer fileContent = new StringBuffer();
        try {
            String encoding = "utf-8";
            File file = new File( PathUtil.getProjectpath() + fileP );//文件路径
            if (file.isFile() && file.exists()) {        // 判断文件是否存在
                InputStreamReader read = new InputStreamReader( new FileInputStream( file ), encoding );    // 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader( read );
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    fileContent.append( lineTxt );
                    fileContent.append( "\n" );
                }
                read.close();
            } else {
                System.out.println( "找不到指定的文件,查看此路径是否正确:" + fileP );
            }
        } catch (Exception e) {
            System.out.println( "读取文件内容出错" );
        }
        return fileContent.toString();
    }


    /*
     * 两个double相加
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal( Double.toString( d1 ) );
        BigDecimal b2 = new BigDecimal( Double.toString( d2 ) );
        return b1.add( b2 ).doubleValue();
    }

    public static double sum(List <Double> values) {
        return values.parallelStream().mapToDouble( d1 -> d1 ).sum();
    }

    /*
     * 两个double相减
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal( Double.toString( d1 ) );
        BigDecimal b2 = new BigDecimal( Double.toString( d2 ) );
        return b1.subtract( b2 ).doubleValue();

    }

    /**
     * 两个double相乘
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal( Double.toString( d1 ) );
        BigDecimal b2 = new BigDecimal( Double.toString( d2 ) );
        return b1.multiply( b2 ).doubleValue();

    }

    /**
     * 两个double相除
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2) {
        int scale = 2;//小数点后的位数
        BigDecimal b1 = new BigDecimal( Double.toString( d1 ) );
        BigDecimal b2 = new BigDecimal( Double.toString( d2 ) );
        return b1.divide( b2, scale, BigDecimal.ROUND_HALF_UP ).doubleValue();
    }

    //double保留两位小数
    public static double getTwoRest(double d) {
        return (double) Math.round( d * 100 ) / 100;
    }

    public static double getTwoRest2(double d) {
        BigDecimal b = new BigDecimal( d );
        double df = b.setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
        return df;
    }

    public static void main(String[] args) {
    	
        System.out.println( filterEmoji("zxw") );
    }

}
