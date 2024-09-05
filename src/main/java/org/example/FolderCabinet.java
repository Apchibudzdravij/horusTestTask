package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FolderCabinet implements Cabinet  {
    private final List<Folder> folders;

    public FolderCabinet() {
        this.folders = new ArrayList<>();
    }
    //nie jest używany w testach, ale może być użyteczny podczas skalowania aplikacji
    public FolderCabinet(List<Folder> list) {
        this.folders = list;
    }

    //nie jest używana w testach, ale może być użyteczna podczas skalowania aplikacji
    public void AddFolder(Folder folder) {
        this.folders.add(folder);
    }
    public void AddFolder(String name, String size) {
        try {
            this.folders.add(new FolderFromCabinet(name, size));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private final class FolderFromCabinet implements MultiFolder {
        private final String name;
        private final String size;

        public FolderFromCabinet(String name, String size) throws Exception {
            switch (size) {
                case "SMALL":
                case "MEDIUM":
                case "LARGE":
                    this.name = name;
                    this.size = size;
                    break;
                default:
                    throw new Exception("[ERROR] Incorrect SIZE parameter value: " + size);
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
        switch (size) {
            case "SMALL":
            case "MEDIUM":
            case "LARGE":
                List<Folder> resultList = new ArrayList<>();
                for (Folder f : folders) {
                    if (f.getSize().equals(size))
                        resultList.add(f);
                }
                return resultList;
            default:
                System.err.println("[ERROR] Unacceptable SIZE value provided: " + size);
                return new ArrayList<>();
        }
    }

    @Override
    public int count() {
        return folders.size();
    }
}
