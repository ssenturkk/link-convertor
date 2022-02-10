package com.ssenturk.trendyollinkconvertor.service.impl.convert_url_to_deeplink;

import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;


public class SearchConverterToDeeplinkImpl implements IBaseConverter {

    /**
     * Converts from the Search Page part of the URL to Search deeplink
     * Conversions are performed in parts
     *
     * @param link URL to be converted
     * @return deeplink can be converted
     */
    @Override
    public String convert(String link) {
        StringBuilder builder = new StringBuilder();
        if (link.matches(Constants.WEB_SEARCH_QUERY_REGEX) || link.matches(Constants.WEB_SEARCH_QUERY_ENCODED_REGEX)) {
            builder.append(Constants.DL_SEARCH);
            builder.append(link.split("=")[1]);
        }
        return builder.toString();
    }

}
