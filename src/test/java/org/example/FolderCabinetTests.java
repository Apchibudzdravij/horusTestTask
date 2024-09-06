package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public final class FolderCabinetTests {
    private final FolderCabinet cabinet = new FolderCabinet();

    @Test
    @DisplayName("count Folders in FolderCabinet with 3 elements")
    public void countTest() {
        cabinet.addFolder("folder1", "MEDIUM");
        cabinet.addFolder("folder2", "SMALL");
        cabinet.addFolder("folder3", "LARGE");
        cabinet.addFolder("folder4", "incorrect size");//wyświetli błąd w konsoli
        Assertions.assertEquals(cabinet.count(), 3);
    }

    @Test
    @DisplayName("get all Folders with LARGE size")
    public void findFoldersBySizeTest() {
        cabinet.addFolder("folder1", "MEDIUM");
        cabinet.addFolder("folder2", "SMALL");
        cabinet.addFolder("folder3", "LARGE");
        cabinet.addFolder("folder4", "LARGE");
        Assertions.assertEquals(cabinet.findFoldersBySize("LARGE").size(), 2);
    }

    @Test
    @DisplayName("retrieve a Folder with the name: folder1")
    public void findFolderByNameTest() {
        cabinet.addFolder("folder1", "MEDIUM");
        cabinet.addFolder("folder2", "SMALL");
        Assertions.assertTrue(cabinet.findFolderByName("folder1").get().getSize().equals("MEDIUM"));//isPresent() ma gwarantowaną wartość true
    }

    @Test
    @DisplayName("not retrieve a Folder with the name: folder3")
    public void findFolderByNameFailTest() {
        cabinet.addFolder("folder1", "MEDIUM");
        cabinet.addFolder("folder1", "LARGE");
        cabinet.addFolder("folder2", "SMALL");
        Assertions.assertFalse(cabinet.findFolderByName("folder3").isPresent());
    }
}
