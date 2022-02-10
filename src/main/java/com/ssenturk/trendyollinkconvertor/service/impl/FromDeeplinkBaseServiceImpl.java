package com.ssenturk.trendyollinkconvertor.service.impl;

import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.exception.InputNotValidException;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;
import com.ssenturk.trendyollinkconvertor.service.impl.convert_deeplink_to_url.ProductConverterToUrlImpl;
import com.ssenturk.trendyollinkconvertor.service.impl.convert_deeplink_to_url.SearchConverterToUrlImpl;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FromDeeplinkBaseServiceImpl implements IBaseConverter {

    /**
     * Converts deeplink to URL
     * Conversion is performed by page type
     *
     * @param link link to be converted
     * @return URL
     *  @throws InputNotValidException  If the deeplink contains Turkish characters
     *  @throws InputNotValidException If the deeplink  doesn't start with "ty://?Page="
     */
    @Override
    public String convert(String link) {
        if (link.matches(Constants.TURKISH_CHARACTERS_REGEX)){
            throw new InputNotValidException(Constants.inputNotValidExceptionTurkishCharectersText);
        } else if (!link.startsWith(Constants.DL_ROOT)){
            throw new InputNotValidException(Constants.inputNotValidExceptionDeeplinkText);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.WEB_ROOT);
        link = link.replace(Constants.DL_ROOT, "");
        if (link.contains(Constants.DL_PRODUCT))
            builder.append(new ProductConverterToUrlImpl().convert(link));
        else if(link.contains(Constants.DL_SEARCH))
            builder.append(new SearchConverterToUrlImpl().convert(link));

        return builder.toString();
    }

}
