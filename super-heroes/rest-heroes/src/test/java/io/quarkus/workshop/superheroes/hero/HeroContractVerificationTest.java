package io.quarkus.workshop.superheroes.hero;

import static org.mockito.Mockito.when;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

/*
    * This test class is used to verify the contract between the consumer and the provider.
    * It tells Pact what port the Hero provider is listening on, and create a JUnit test template, which will be expanded into the full set of tests needed to verify the contract.
 */
@QuarkusTest
@Provider("rest-heroes")
@PactFolder("pacts")
public class HeroContractVerificationTest {

    private static final String NO_HERO_FOUND_STATE = "No random hero found";
    @ConfigProperty(name = "quarkus.http.test-port")
    int quarkusPort;


    @BeforeEach
    void beforeEach(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", this.quarkusPort));

        // Tell Pact how to handle the "no hero found" state
        // We have to do this here because the CDI context isn't available
        // in the @State method below
        var noHeroState = Optional.of(context.getInteraction()
                .getProviderStates())
            .orElseGet(List::of)
            .stream().anyMatch(state -> NO_HERO_FOUND_STATE.equals(state.getName()));

        if (noHeroState) {
            PanacheMock.mock(Hero.class);
            when(Hero.findRandom()).thenReturn(Uni.createFrom()
                .nullItem());

        }
    }


    @State(NO_HERO_FOUND_STATE)
    public void clearData() throws NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {
        // Already handled in beforeEach
        // See https://github.com/quarkusio/quarkus/issues/22611
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

}
