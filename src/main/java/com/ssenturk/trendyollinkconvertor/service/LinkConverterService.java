package com.ssenturk.trendyollinkconvertor.service;

import com.ssenturk.trendyollinkconvertor.entity.ConvertedLink;
import com.ssenturk.trendyollinkconvertor.enumaration.ConverterOperation;
import com.ssenturk.trendyollinkconvertor.object.LinkConverter;
import com.ssenturk.trendyollinkconvertor.repository.LinkConverterRepository;
import com.ssenturk.trendyollinkconvertor.service.api.IBaseConverter;
import com.ssenturk.trendyollinkconvertor.service.impl.FromDeeplinkBaseServiceImpl;
import com.ssenturk.trendyollinkconvertor.service.impl.FromWebUrlBaseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LinkConverterService {

    private final LinkConverterRepository converterRepository;
    private final RedisService cacheService;

    public LinkConverterService(LinkConverterRepository converterRepository, RedisService cacheService) {
        this.converterRepository = converterRepository;
        this.cacheService = cacheService;
    }

    /**
     * Returns a deeplink converted from posted link
     *
     * @param linkConverter requested url
     * @return converted deeplink
     */
    public String convertFromWeburl(LinkConverter linkConverter) {
        // first check if there is a convertedlink in redis
        if(cacheService.exists(linkConverter.getLink())){
            return cacheService.get(linkConverter.getLink());
        } else {
            IBaseConverter baseConverter = new FromWebUrlBaseServiceImpl();
            FromWebUrlBaseServiceImpl fromWebUrlBaseService = (FromWebUrlBaseServiceImpl) baseConverter;
            save(linkConverter, ConverterOperation.URL_TO_DEEPLINK.name(), fromWebUrlBaseService.convert(linkConverter.getLink()));
            return fromWebUrlBaseService.convert(linkConverter.getLink());
        }
    }

    /**
     * Returns a url converted from posted deeplink
     *
     * @param linkConverter requested deeplink
     * @return converted URL
     */
    public String convertFromDeepLink(LinkConverter linkConverter) {
        // first check if there is a convertedlink in redis
        if(cacheService.exists(linkConverter.getLink())){
            return cacheService.get(linkConverter.getLink());
        } else {
            IBaseConverter baseConverter = new FromDeeplinkBaseServiceImpl();
            FromDeeplinkBaseServiceImpl fromDeeplinkBaseService = (FromDeeplinkBaseServiceImpl) baseConverter;

            save(linkConverter, ConverterOperation.DEEPLINK_TO_URL.name(), fromDeeplinkBaseService.convert(linkConverter.getLink()));
            return fromDeeplinkBaseService.convert(linkConverter.getLink());
        }
    }

    /**
     * The converted link is saved in the database and Redis is updated
     *
     * @param linkConverter requested link
     * @param operation operation type URL to deeplink or deeplink to URL
     * @param convertedLink convertedLink
     */
    private void save(LinkConverter linkConverter, String operation, String convertedLink){
        ConvertedLink convertedLinkEntity = new ConvertedLink();
        convertedLinkEntity.setOperation(operation);

        if (operation == ConverterOperation.URL_TO_DEEPLINK.name()) {
            convertedLinkEntity.setUrl(linkConverter.getLink());
            convertedLinkEntity.setDeeplink(convertedLink);
        } else {
            convertedLinkEntity.setDeeplink(linkConverter.getLink());
            convertedLinkEntity.setUrl(convertedLink);
        }

        converterRepository.save(convertedLinkEntity);
        // update redis after every conversion
        cacheService.set(linkConverter.getLink(), convertedLink);
    }


}