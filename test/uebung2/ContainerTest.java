package uebung2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container container;

    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @AfterEach
    void tearDown() {
        container = null;
    }

    @Test
    void addMemberTest() {
        Member m1 = new StringMember(0, "A");
        Member m2 = new DoubleMember(0, 1.5);

        assertDoesNotThrow(() -> container.addMember(m1));
        assertThrows(ContainerException.class, () -> container.addMember(m1));
        assertThrows(ContainerException.class, () -> container.addMember(m2));
    }

    @Test
    void deleteMemberTest() {
        Member m1 = new StringMember(0, "A");
        Member m2 = new DoubleMember(1, 1.5);

        assertEquals(1, container.deleteMember(0));
        try {
            container.addMember(m1);
        } catch (ContainerException e) {
            throw new IllegalStateException();
        }
        assertEquals(1, container.deleteMember(1));
        assertEquals(0, container.deleteMember(0));
        assertEquals(1, container.deleteMember(0));
    }

    @Test
    void dumpTest() {
        Member m1 = new StringMember(0, "A");
        Member m2 = new DoubleMember(1, 1.5);

        System.out.println("Leer:");
        container.dump();

        try {
            container.addMember(m1);
        } catch (ContainerException e) {
            throw new IllegalStateException();
        }
        System.out.println("\n1 Objekt:");
        container.dump();

        try {
            container.addMember(m2);
        } catch (ContainerException e) {
            throw new IllegalStateException();
        }
        System.out.println("\n2 Objekte:");
        container.dump();
    }

    @Test
    void sizeTest() {
        Member m1 = new StringMember(0, "A");
        Member m2 = new DoubleMember(1, 1.5);

        assertEquals(0, container.size());

        try {
            container.addMember(m1);
            container.addMember(m2);
        } catch (ContainerException e) {
            throw new IllegalStateException();
        }
        assertEquals(2, container.size());
    }
}
