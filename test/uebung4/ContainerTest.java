package uebung4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uebung4.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container<UserStory> container;

    @BeforeEach
    void setUp() {
        Container<?> tmp = Container.getInstance();
        container = (Container<UserStory>) tmp;
    }

    @AfterEach
    void tearDown() {
        container.selfDelete();
        container = null;
    }

    @Test
    void testNoStrategySet() {
        container.setPersistenceStrategie(null);
        PersistenceException e = assertThrows(PersistenceException.class, () -> container.store());
        System.out.println("Message: " + e.getMessage());
        assertEquals(e.getMessage(), "Strategy not initialized");
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
    }

    @Test
    void testMongoDBNotImplementedOldFashioned() {
        container.setPersistenceStrategie( new PersistenceStrategyMongoDB<>() );
        PersistenceException e = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(e.getMessage(), "Not implemented!");
        assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ImplementationNotAvailable);
    }

    @Test
    void testMongoDBNotImplementedHipSolution() {
        container.setPersistenceStrategie(new PersistenceStrategyMongoDB<>());
        PersistenceException e = assertThrows(PersistenceException.class, () -> container.store() );
        assertEquals(e.getMessage(), "Not implemented!");
        assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ImplementationNotAvailable );
    }

    @Test
    void testWrongLocationOfFile() {
        PersistenceStrategyStream<UserStory> strat = new PersistenceStrategyStream<>();

        // FileStreams do not like directories, so try this out ;-)
        strat.setLOCATION("/Users/saschaalda/tmp");
        container.setPersistenceStrategie(strat);
        PersistenceException e = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(e.getMessage(), "Error in opening the connection, File could not be found");
        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType()) ;
    }

    @Test
    void testStoreDeleteAndLoad() {
        try {
            container.setPersistenceStrategie(new PersistenceStrategyStream<>());
            UserStory testStory = new UserStory(1);
            container.addObject(testStory);

            assertEquals(1, container.size());
            container.store();

            container.deleteObject(testStory);
            assertEquals(0 , container.size());

            container.load();
            assertEquals(1 , container.size());

        } catch (PersistenceException | ContainerException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    /*
     * Auto-Generated test-methods
    @Test
    void getInstance() {
    }

    @Test
    void setModusWithString() {
    }

    @Test
    void getCurrentList() {
    }

    @Test
    void addObject() {
    }

    @Test
    void deleteObject() {
    }

    @Test
    void size() {
    }

    @Test
    void load() {
    }

    @Test
    void setPersistenceStrategie() {
    }

    @Test
    void store() {
    }
    */
}
