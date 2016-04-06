package com.cienet.util;

/**
 * 
 * @ClassName: Constants
 * @Description: 常量类
 * @author liyongqiang
 * @date 2014年7月7日 下午11:33:22
 * 
 */
public class Constants {

    public static String DOWNLOAD_PATH = "\\download\\";

    public static String MUBILE_FILE_NAME = "mubile.xlsx";

    public static String MEMBER_FILE_WORD = "wordTemplet.doc";

    public static String TEMPLET_PATH = "\\templet\\";

    public static String QUESTIONTEST_FILE_NAME = "";

    public static String TEMP_PATH = "\\temp\\";

    /**
     * @Fields LEVEL_EASY :易
     */
    public static Integer LEVEL_EASY = 1;

    /**
     * @Fields LEVEL_COMMON : 中
     */
    public static Integer LEVEL_COMMON = 2;

    /**
     * @Fields LEVEL_HARD : 难
     */
    public static Integer LEVEL_HARD = 3;

    /**
     * @Fields Single_Choice : 单选
     */
    public static Integer Single_Choice = 1;

    /**
     * @Fields MULTIPLE_CHOICE : 多选
     */
    public static Integer MULTIPLE_CHOICE = 2;

    /**
     * @Fields Judge : 判断
     */
    public static Integer JUDGE = 3;

    public static String Single_Choice_NAME = "单选题";

    public static String MULTIPLE_CHOICE_NAME = "多选题";

    public static String JUDGE_NAME = "判断题";

    public static String FLASH_UPLOAD_PATH = "/upload/flash/";
    public static String MEDIA_UPLOAD_PATH = "/upload/media/";
    public static String IMAGE_UPLOAD_PATH = "/upload/image/";
    public static String FILE_UPLOAD_PATH = "/upload/file/";

    public static String[] FLASH_UPLOAD_EXTENSIONS = { "swf", "flv" };
    public static String[] MEDIA_UPLOAD_EXTENSIONS = { "swf", "flv", "mp3",
            "wav", "avi", "rm", "rmvb" };
    public static String[] IMAGE_UPLOAD_EXTENSIONS = { "jpg", "jpeg", "bmp",
            "gif", "png" };
    public static String[] FILE_UPLOAD_EXTENSIONS = { "zip", "rar", "7z",
            "doc", "docx", "xls", "xlsx", "ppt", "pptx" };
}
