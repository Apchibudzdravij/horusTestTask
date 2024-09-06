package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FolderCabinet implements Cabinet  {
    private final List<Folder> folders;

    private static final Logger logger = LoggerFactory.getLogger(FolderCabinet.class);

    public FolderCabinet() {
        this.folders = new ArrayList<>();
    }
    //nie jest używany w testach, ale może być użyteczny podczas skalowania aplikacji
    public FolderCabinet(List<Folder> list) {
        this.folders = list;
    }

    //nie jest używana w testach, ale może być użyteczna podczas skalowania aplikacji
    public void addFolder(Folder folder) {
        this.folders.add(folder);
    }
    public void addFolder(String name, String size) {
        try {
            this.folders.add(new FolderFromCabinet(name, size));
        } catch (Exception e) {
            logger.error(e.getMessage());
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
                    throw new Exception("Incorrect SIZE parameter value: " + size);
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
        return folders.stream()
                .filter(f -> f.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        switch (size) {
            case "SMALL", "MEDIUM", "LARGE":
                return folders.stream()
                        .filter(f -> f.getSize().equals(size))
                        .collect(Collectors.toList());
            default:
                logger.error("Unacceptable SIZE value provided: {}", size);
                return new ArrayList<>();
        }
    }

    @Override
    public int count() {
        return folders.size();
    }
}
