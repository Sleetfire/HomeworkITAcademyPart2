package by.it.academy.MK_JD2_88_2.polls.service;

import by.it.academy.MK_JD2_88_2.polls.service.api.IFileHandlerService;
import by.it.academy.MK_JD2_88_2.polls.dto.SavedPoll;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerService implements IFileHandlerService<List<SavedPoll>> {

    private static IFileHandlerService instance = new FileHandlerService();

    private FileHandlerService() {
    }

    @Override
    public List<SavedPoll> readFromFile(String fileName) {
        List<SavedPoll> savedPolls = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            savedPolls = (List<SavedPoll>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return savedPolls;
    }

    @Override
    public void writeInFile(String fileName, List<SavedPoll> info) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IFileHandlerService getInstance() {
        return instance;
    }
}
