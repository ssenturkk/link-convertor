package com.ssenturk.trendyollinkconvertor.constant;

public class Constants {

    public static final String WEB_JUST_PRODUCT_REGEX= "^\\/[a-zA-Z-]{1,256}\\/[a-zA-Z-]{1,256}-p-[0-9]{1,25}$";
    public static final String WEB_JUST_PRODUCT_BOUTIQUE_REGEX= "^\\/[a-zA-Z-]{1,256}\\/[a-zA-Z-]{1,256}-p-[0-9]{1,25}[?]boutiqueId=[0-9]{1,25}$";
    public static final String WEB_JUST_PRODUCT_MERCHANT_REGEX= "^\\/[a-zA-Z-]{1,256}\\/[a-zA-Z-]{1,256}-p-[0-9]{1,25}[?]merchantId=[0-9]{1,25}$";
    public static final String WEB_PRODUCT_BOUTIQUE_MERCHANT_REGEX= "^\\/[a-zA-Z-]{1,256}\\/[a-zA-Z-]{1,256}-p-[0-9]{1,25}[?]boutiqueId=[0-9]{1,25}&merchantId=[0-9]{1,25}$";
    public static final String WEB_SEARCH_QUERY_REGEX= "^\\/sr[?]q[=][a-zA-Z-]{1,256}$";
    public static final String WEB_SEARCH_QUERY_ENCODED_REGEX= ".*(%)([2-9A-F]){2}.*";

    public static final String DL_JUST_PRODUCT_REGEX= "^Product&ContentId=[0-9]{1,25}$";
    public static final String DL_JUST_PRODUCT_COMPAING_REGEX= "^Product&ContentId=[0-9]{1,25}&CampaingId=[0-9]{1,25}$";
    public static final String DL_JUST_PRODUCT_MERCHANT_REGEX= "^Product&ContentId=[0-9]{1,25}&MerchantId=[0-9]{1,25}$";
    public static final String DL_JUST_PRODUCT_COMPAING_MERCHANT_REGEX= "^Product&ContentId=[0-9]{1,25}&CampaingId=[0-9]{1,25}&MerchantId=[0-9]{1,25}$";
    public static final String DL_SEARCH_QUERY_REGEX= "^Search&Query=[a-zA-Z]{1,256}$";
    public static final String DL_SEARCH_QUERY_ENCODED_REGEX= "^Search&Query=.*(%)([2-9A-F]){2}.*$";

    public static final String TURKISH_CHARACTERS_REGEX = "^.*[ığüşöçİĞÜŞÖÇ].*";

    public static final String WEB_ROOT = "https://www.trendyol.com";
    public static final String WEB_PRODUCT_NAME = "/brand/name-p-";
    public static final String WEB_PRODUCT = "-p-";
    public static final String WEB_SEARCH = "sr?q=";
    public static final String WEB_BOUTIQUE_PARAM = "boutiqueId=";
    public static final String WEB_MERCHANT_PARAM = "merchantId=";

    public static final String DL_ROOT = "ty://?Page=";
    public static final String DL_PRODUCT = "Product&ContentId=";
    public static final String DL_HOME = "Home";

    public static final String DL_SEARCH = "Search&Query=";
    public static final String DL_CAMPAIGN_PARAM = "CampaignId=";
    public static final String DL_MERCHANT_PARAM = "MerchantId=";

    public static final String inputNotValidExceptionTurkishCharectersText = "Turkish character problem!";
    public static final String inputNotValidExceptionUrlText = "Url must starts with https://www.trendyol.com";
    public static final String inputNotValidExceptionDeeplinkText = "Deeplink must starts with ty://?Page=";
}
