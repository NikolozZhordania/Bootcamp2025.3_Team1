package ge.tbc.testautomation.tbcbankapp.api.steps;

import ge.tbc.testautomation.tbcbankapp.api.client.OffersAPI;
import ge.tbc.testautomation.tbcbankapp.api.data.models.request.offers.OffersRequest;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.offers.Offer;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.offers.OffersResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.OffersConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OffersSteps {

    private final OffersAPI api = new OffersAPI();
    private Response rawResponse;
    private OffersResponse offersResponse;
    private List<Offer> offers;

    private static final DateTimeFormatter OFFER_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final ZoneId TBILISI = ZoneId.of(Timezone.TBILISI);

    @Step("Fetch active TBC Card offers")
    public OffersSteps fetchOffers() {
        OffersRequest request = OffersRequest.builder()
                .filter(List.of(Payload.FILTER_TBC))
                .locale(Payload.LOCALE)
                .segment(Payload.SEGMENT_ALL)
                .pageIndex(Payload.PAGE_INDEX)
                .pageSize(Payload.PAGE_SIZE)
                .build();

        this.rawResponse = api.getOffers(request);
        this.offersResponse = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(OffersResponse.class);
        this.offers = offersResponse.getList();
//        System.out.println(rawResponse.asPrettyString());
        return this;
    }

    @Step("Validate offers list is not empty")
    public OffersSteps validateOffersNotEmpty() {
        assertThat("Offers list must not be empty", offers, is(not(empty())));
        return this;
    }

    @Step("Validate all offers have required fields")
    public OffersSteps validateOfferFields() {
        offers.forEach(offer -> {
            assertThat("Offer title must not be blank", offer.getTitle(), not(blankOrNullString()));
            assertThat("Offer slug must not be blank",  offer.getSlug(),  not(blankOrNullString()));
        });
        return this;
    }

    @Step("Validate endDate is always after startDate for every offer")
    public OffersSteps validateEndDateAfterStartDate() {
        offers.stream()
                .filter(o -> o.getStartDate() != null && o.getEndDate() != null)
                .forEach(offer -> {
                    LocalDateTime start = LocalDateTime.parse(offer.getStartDate(), OFFER_DATE_FORMAT);
                    LocalDateTime end   = LocalDateTime.parse(offer.getEndDate(),   OFFER_DATE_FORMAT);
                    assertThat(
                            "endDate must be after startDate for offer: " + offer.getTitle(),
                            end.isAfter(start), is(true)
                    );
                });
        return this;
    }

    @Step("Validate no active offer has already expired")
    public OffersSteps validateNoOffersExpired() {
        ZonedDateTime now = ZonedDateTime.now(TBILISI);
        offers.stream()
                .filter(o -> o.getEndDate() != null)
                .forEach(offer -> {
                    ZonedDateTime end = LocalDateTime
                            .parse(offer.getEndDate(), OFFER_DATE_FORMAT)
                            .atZone(TBILISI);
                    assertThat(
                            "Active offer must not have an expired endDate: " + offer.getTitle()
                                    + " (ended: " + offer.getEndDate() + ")",
                            end.isAfter(now), is(true)
                    );
                });
        return this;
    }

    @Step("Validate remaining days are positive for all active offers")
    public OffersSteps validateRemainingDaysArePositive() {
        ZonedDateTime now = ZonedDateTime.now(TBILISI);
        offers.stream()
                .filter(o -> o.getEndDate() != null)
                .forEach(offer -> {
                    ZonedDateTime end = LocalDateTime
                            .parse(offer.getEndDate(), OFFER_DATE_FORMAT)
                            .atZone(TBILISI);
                    long remainingDays = ChronoUnit.DAYS.between(now, end);
                    assertThat(
                            "Remaining days must be >= 0 for offer: " + offer.getTitle()
                                    + " (endDate: " + offer.getEndDate() + ")",
                            remainingDays, greaterThanOrEqualTo(0L)
                    );
                });
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public OffersSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }
}
