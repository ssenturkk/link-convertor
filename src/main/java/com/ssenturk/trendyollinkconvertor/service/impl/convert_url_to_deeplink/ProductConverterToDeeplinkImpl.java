package com.ssenturk.trendyollinkconvertor.service.impl.convert_url_to_deeplink;
import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;

public class ProductConverterToDeeplinkImpl implements IBaseConverter {

    /**
     * Converts from the Product Page part of the URL to Product deeplink
     * Conversions are performed in parts
     *
     * @param link URL to be converted
     * @return deeplink can be converted
     */
    @Override
    public String convert(String link) {
        StringBuilder builder = new StringBuilder();
        if (link.matches(Constants.WEB_JUST_PRODUCT_REGEX)) {
            builder.append(Constants.DL_PRODUCT);
            builder.append(link.split(Constants.WEB_PRODUCT)[1]);
        } else if (link.matches(Constants.WEB_JUST_PRODUCT_BOUTIQUE_REGEX)) {
            builder.append(Constants.DL_PRODUCT);
            builder.append((link.split("\\?")[0]).split(Constants.WEB_PRODUCT)[1]);
            builder.append("&");
            builder.append(Constants.DL_CAMPAIGN_PARAM);
            builder.append((link.split("\\?")[1]).split("=")[1]);
        } else if (link.matches(Constants.WEB_JUST_PRODUCT_MERCHANT_REGEX)){
            builder.append(Constants.DL_PRODUCT);
            builder.append((link.split("\\?")[0]).split(Constants.WEB_PRODUCT)[1]);
            builder.append("&");
            builder.append(Constants.DL_MERCHANT_PARAM);
            builder.append((link.split("\\?")[1]).split("=")[1]);
        } else if (link.matches(Constants.WEB_PRODUCT_BOUTIQUE_MERCHANT_REGEX)) {
            builder.append(Constants.DL_PRODUCT);
            builder.append((link.split("\\?")[0]).split(Constants.WEB_PRODUCT)[1]);
            builder.append("&");
            builder.append(Constants.DL_CAMPAIGN_PARAM);
            builder.append(((link.split("\\?")[1]).split("&")[0]).split("=")[1]);
            builder.append("&");
            builder.append(Constants.DL_MERCHANT_PARAM);
            builder.append(((link.split("\\?")[1]).split("&")[1]).split("=")[1]);
        } else builder.append(Constants.DL_HOME);
        return builder.toString();
    }

}
