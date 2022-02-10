package com.ssenturk.trendyollinkconvertor.service;

import com.ssenturk.trendyollinkconvertor.constant.Constants;
import com.ssenturk.trendyollinkconvertor.object.LinkConverter;
import com.ssenturk.trendyollinkconvertor.repository.LinkConverterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkConverterServiceTest {

    private LinkConverterService linkConverterService;
    private LinkConverterRepository converterRepository;
    private RedisService cacheService;

    @BeforeEach
    public void setUp()  throws  Exception{
        converterRepository = Mockito.mock(LinkConverterRepository.class);
        cacheService = Mockito.mock(RedisService.class);
        linkConverterService = new LinkConverterService(converterRepository, cacheService);
    }

    private LinkConverter createLinkConverter(String link) {
        return LinkConverter.builder()
                .link(link)
                .build();
    }

    /* ------------------------Url convert to Deeplink ------------------------*/

    @Test
    void whenUrlConvertedCall_withBoutiqueIdAndMerchantId_fromProductDetailPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));

        assertEquals(Constants.DL_ROOT
                + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", result);

    }

    @Test
    void whenUrlConvertedCall_withHasNoParameter_fromProductDetailPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/casio/erkek-kol-saat-p-1925865"));

        assertEquals(Constants.DL_ROOT
                + "Product&ContentId=1925865", result);
    }

    @Test
    void whenUrlConvertedCall_withBoutiqueId_fromProductDetailPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/casio/erkek-kol-saat-p-1925865?boutiqueId=439892"));

        assertEquals(Constants.DL_ROOT
                + "Product&ContentId=1925865&CampaignId=439892", result);
    }

    @Test
    void whenUrlConvertedCall_withMerchantId_fromProductDetailPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/casio/erkek-kol-saat-p-1925865?merchantId=105064"));

        assertEquals(Constants.DL_ROOT
                + "Product&ContentId=1925865&MerchantId=105064", result);
    }

    @Test
    void whenUrlConvertedCall_fromSearchPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/sr?q=elbise"));

        assertEquals(Constants.DL_ROOT
                + "Search&Query=elbise", result);
    }

    @Test
    void whenUrlConvertedCall_withHasUnicode_fromSearchPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/sr?q=%C3%BCt%C3%BC"));

        assertEquals(Constants.DL_ROOT
                + "Search&Query=%C3%BCt%C3%BC", result);
    }

    @Test
    void whenUrlConvertedCall_fromOtherPage_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/Hesabim/Favoriler"));

        assertEquals(Constants.DL_ROOT
                + "Home", result);
    }

    @Test
    void whenUrlConvertedCall_fromOtherPage_returnDeeplink_2() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/Hesabim/#/Siparislerim"));

        assertEquals(Constants.DL_ROOT
                + "Home", result);
    }

    @Test
    void whenUrlConvertedCall_justStartsWithWebRoot_returnDeeplink() {
        String result = linkConverterService.
                convertFromWeburl(createLinkConverter(Constants.WEB_ROOT
                        + "/casio/erkek-kol-saat-p-rolex-saat"));

        assertEquals(Constants.DL_ROOT
                + "Home", result);
    }


    /* ------------------------ Deeplink convert to Url ------------------------*/

    @Test
    void whenDeeplinkConvertedCall_withCampaingIdAndMerchantId_fromProductDetailPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Product&ContentId=1925865&CampaingId=439892&MerchantId=105064"));

        assertEquals(Constants.WEB_ROOT
                + "/brand/name-p-1925865?boutiqueId=439892&merchantId=105064", result);
    }

    @Test
    void whenDeeplinkConvertedCall_fromProductDetailPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Product&ContentId=1925865"));

        assertEquals(Constants.WEB_ROOT
                + "/brand/name-p-1925865", result);
    }

    @Test
    void whenDeeplinkConvertedCall_withCampaingId_fromProductDetailPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Product&ContentId=1925865&CampaingId=439892"));

        assertEquals(Constants.WEB_ROOT
                + "/brand/name-p-1925865?boutiqueId=439892", result);
    }

    @Test
    void whenDeeplinkConvertedCall_withMerchantId_fromProductDetailPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Product&ContentId=1925865&MerchantId=105064"));

        assertEquals(Constants.WEB_ROOT
                + "/brand/name-p-1925865?merchantId=105064", result);
    }

    @Test
    void whenDeeplinkConvertedCall_fromSearchPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Search&Query=elbise"));

        assertEquals(Constants.WEB_ROOT + "/sr?q=elbise", result);
    }

    @Test
    void whenDeeplinkConvertedCall_withUnicode_fromSearchPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Search&Query=%C3%BCt%C3%BC"));

        assertEquals(Constants.WEB_ROOT + "/sr?q=%C3%BCt%C3%BC" , result);
    }

    @Test
    void whenDeeplinkConvertedCall_fromOtherPage_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Favorites"));

        assertEquals(Constants.WEB_ROOT, result);
    }

    @Test
    void whenDeeplinkConvertedCall_fromOtherPage_returnUrl_2() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Orders"));

        assertEquals(Constants.WEB_ROOT, result);
    }

    @Test
    void whenDeeplinkConvertedCall_justStartsWithDeeplinkRoot_returnUrl() {
        String result = linkConverterService.
                convertFromDeepLink(createLinkConverter(Constants.DL_ROOT
                        + "Product&ContentId="));

        assertEquals(Constants.WEB_ROOT, result);
    }



    /*
     ExceptionHandler lar icin test methodlar yazilacak
     Bir de valid esnasında fırlatılan general exception icin test methodları yazılacak
     */

//    @Test
//    @DisplayName("Test assert exception")
//    void testCustomException() {
//        final ExpectCustomException expectEx = new ExpectCustomException();
//
//        InvalidParameterCountException exception = assertThrows(InvalidParameterCountException.class, () -> {
//            expectEx.constructErrorMessage("sample ","error");
//        });
//        assertEquals("Invalid parametercount: expected=3, passed=2", exception.getMessage());
//    assertEquals("Deeplink must starts with ty://?Page=", result);
//    }
}
