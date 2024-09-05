package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FolderCabinet implements Cabinet  {
    private List<Folder> folders;

    private class FolderFromCabinet implements MultiFolder {
        private String name;
        private String size;

        public FolderFromCabinet(String name, String size) throws Exception {
            switch (size) {
                case "SMALL":
                case "MEDIUM":
                case "LARGE":
                    this.name = name;
                    this.size = size;
                    break;
                default:
                    throw new Exception("[ERROR] Incorrect SIZE parameter: " + size);
            }
        }

        public String getName() {
            return name;
        }
        public String getSize() {
            return size;
        }

        @Override
        public List<Folder> getFolders() {
            return folders;
        }
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        for (Folder f: folders) {
            if (f.getName().equals(name))
                return Optional.of(f);
        }
        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> resultList = new ArrayList<Folder>();
        for (Folder f: folders) {
            if (f.getSize().equals(size))
                resultList.add(f);
        }
        return resultList;
    }

    @Override
    public int count() {
        return folders.size();
    }
}
