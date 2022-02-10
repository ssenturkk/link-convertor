package com.ssenturk.trendyollinkconvertor.service.impl.convert_deeplink_to_url;
import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;

public class ProductConverterToUrlImpl implements IBaseConverter {

    /**
     * Converts from the Product Page part of the deeplink to Product URL
     * Conversions are performed in parts
     *
     * @param link link to be converted
     * @return URL can be converted
     */
    @Override
    public String convert(String link) {
        StringBuilder builder = new StringBuilder();
        if (link.matches(Constants.DL_JUST_PRODUCT_COMPAING_MERCHANT_REGEX)) {
            builder.append(Constants.WEB_PRODUCT_NAME);
            builder.append((link.split("&")[1]).split("=")[1]);
            builder.append("?");
            builder.append(Constants.WEB_BOUTIQUE_PARAM);
            builder.append((link.split("&")[2]).split("=")[1]);
            builder.append("&");
            builder.append(Constants.WEB_MERCHANT_PARAM);
            builder.append((link.split("&")[3]).split("=")[1]);
        } else if (link.matches(Constants.DL_JUST_PRODUCT_COMPAING_REGEX)) {
            builder.append(Constants.WEB_PRODUCT_NAME);
            builder.append((link.split("&")[1]).split("=")[1]);
            builder.append("?");
            builder.append(Constants.WEB_BOUTIQUE_PARAM);
            builder.append((link.split("&")[2]).split("=")[1]);
        } else if (link.matches(Constants.DL_JUST_PRODUCT_MERCHANT_REGEX)){
            builder.append(Constants.WEB_PRODUCT_NAME);
            builder.append((link.split("&")[1]).split("=")[1]);
            builder.append("?");
            builder.append(Constants.WEB_MERCHANT_PARAM);
            builder.append((link.split("&")[2]).split("=")[1]);
        } else if (link.matches(Constants.DL_JUST_PRODUCT_REGEX)) {
            builder.append(Constants.WEB_PRODUCT_NAME);
            builder.append(link.split("=")[1]);
        }
        return builder.toString();
    }

}
