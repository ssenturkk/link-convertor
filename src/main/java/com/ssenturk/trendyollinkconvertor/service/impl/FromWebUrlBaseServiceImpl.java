package com.ssenturk.trendyollinkconvertor.service.impl;

import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.exception.InputNotValidException;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;
import com.ssenturk.trendyollinkconvertor.service.impl.convert_url_to_deeplink.ProductConverterToDeeplinkImpl;
import com.ssenturk.trendyollinkconvertor.service.impl.convert_url_to_deeplink.SearchConverterToDeeplinkImpl;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FromWebUrlBaseServiceImpl  implements IBaseConverter {

    /**
     * Converts URL to deeplink
     * Conversion is performed by page type
     *
     * @param link link to be converted
     * @return deeplink
     *  @throws InputNotValidException  If the URL contains Turkish characters
     *  @throws InputNotValidException If the URL  doesn't start with "https://www.trendyol.com"
     */
    @Override
    public String convert(String link) {
        if (link.matches(Constants.TURKISH_CHARACTERS_REGEX)){
            throw new InputNotValidException(Constants.inputNotValidExceptionTurkishCharectersText);
        } else if (!link.startsWith(Constants.WEB_ROOT)){
            throw new InputNotValidException(Constants.inputNotValidExceptionUrlText);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(Constants.DL_ROOT);
            link = link.replace(Constants.WEB_ROOT, "");
            if (link.contains(Constants.WEB_PRODUCT))
        builder.append(new ProductConverterToDeeplinkImpl().convert(link));
            else if(link.contains(Constants.WEB_SEARCH))
                    builder.append(new SearchConverterToDeeplinkImpl().convert(link));
            else builder.append(Constants.DL_HOME);

        return builder.toString();
    }

}
