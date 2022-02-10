package com.ssenturk.trendyollinkconvertor.service.api;

public interface IBaseConverter {

    /**
     * Converts the link according to implemented class behaviour
     *
     * @param link link to be converted
     * @return converted link
     */
    String convert(String link);
}
