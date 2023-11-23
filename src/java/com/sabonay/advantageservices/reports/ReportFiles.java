package com.sabonay.advantageservices.reports;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 01 Apr, 2023
 * @time 13.39.49 pm
 */
public class ReportFiles {

    private static final String REPORT_BASE_DIR = "/com/sabonay/advantageservices/reports/";

    private static final String GENERAL_REPORTS = REPORT_BASE_DIR + "general/";
    public static final String SHC_LOGO = REPORT_BASE_DIR + "images/shc_logo.jpg";
    public static final String COAT_OF_ARMS = REPORT_BASE_DIR + "images/coat_of_arms.png";

    public static final String DEMAND_NOTICE_REPORT = REPORT_BASE_DIR + "demand-notice.jasper";
    private static final String TEMPLATES = REPORT_BASE_DIR + "templates/";
    public static String header_portrait_template = TEMPLATES + "header_portrait.jasper";
    public static String club_header_portrait_template = TEMPLATES + "club_header_portrait.jasper";
    public static String club_footer_portrait_template = TEMPLATES + "club_footer_portrait.jasper";
    public static String header_landscape_template = TEMPLATES + "header_portrait.jasper";
    public static String footer_portrait_template = TEMPLATES + "footer_portrait.jasper";
    public static String footer_landscape_template = TEMPLATES + "footer_landscape.jasper";

}
