package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class FolderCabinetTests {
    private final FolderCabinet cabinet = new FolderCabinet();

    @Test
    @DisplayName("Test 1")
    public void countTest() {
        cabinet.AddFolder("folder1", "MEDIUM");
        cabinet.AddFolder("folder2", "SMALL");
        cabinet.AddFolder("folder3", "LARGE");
        cabinet.AddFolder("folder4", "incorrect size");//wyświetli błąd w konsoli
        Assertions.assertEquals(cabinet.count(), 3);
    }

    @Test
    @DisplayName("Test 2")
    public void findFoldersBySizeTest() {
        cabinet.AddFolder("folder1", "MEDIUM");
        cabinet.AddFolder("folder2", "SMALL");
        cabinet.AddFolder("folder3", "LARGE");
        cabinet.AddFolder("folder4", "LARGE");
        Assertions.assertEquals(cabinet.findFoldersBySize("LARGE").size(), 2);
    }

    @Test
    @DisplayName("Test 3")
    public void findFolderByNameTest() {
        cabinet.AddFolder("folder1", "MEDIUM");
        cabinet.AddFolder("folder2", "SMALL");
        Assertions.assertTrue(cabinet.findFolderByName("folder1").get().getSize().equals("MEDIUM"));//isPresent() is guaranteed to be true
    }
}
