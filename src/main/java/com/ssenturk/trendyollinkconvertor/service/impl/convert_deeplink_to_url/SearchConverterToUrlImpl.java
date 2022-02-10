package com.ssenturk.trendyollinkconvertor.service.impl.convert_deeplink_to_url;

import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;

public class SearchConverterToUrlImpl implements IBaseConverter {

    /**
     * Converts from the Search Page part of the deeplink to Search URL
     * Conversions are performed in parts
     *
     * @param link link to be converted
     * @return URL can be converted
     */
    @Override
    public String convert(String link) {
        StringBuilder builder = new StringBuilder();
        if (link.matches(Constants.DL_SEARCH_QUERY_REGEX) || link.matches(Constants.DL_SEARCH_QUERY_ENCODED_REGEX)) {
            builder.append("/");
            builder.append(Constants.WEB_SEARCH);
            builder.append(link.split("=")[1]);
        }
        return builder.toString();
    }

}
