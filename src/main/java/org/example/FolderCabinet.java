package org.example;

import java.util.List;
import java.util.Optional;

public class FolderCabinet implements Cabinet, MultiFolder  {
    private List<Folder> folders;

    private class FolderFromCabinet implements Folder {
        private String name;
        private String size;

        public String getName() {
            return name;
        }
        public String getSize() {
            return size;
        }
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return List.of();
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}
