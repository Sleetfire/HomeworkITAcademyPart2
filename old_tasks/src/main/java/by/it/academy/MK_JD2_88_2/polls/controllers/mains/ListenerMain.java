package by.it.academy.MK_JD2_88_2.polls.controllers.mains;

import by.it.academy.MK_JD2_88_2.polls.service.FileHandlerService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IFileHandlerService;
import by.it.academy.MK_JD2_88_2.polls.dto.Poll;
import by.it.academy.MK_JD2_88_2.polls.dto.SavedPoll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListenerMain {
    public static void main(String[] args) {

        IFileHandlerService<List<SavedPoll>> service = FileHandlerService.getInstance();
        List<SavedPoll> savedPolls = new ArrayList<>();
        savedPolls.add(new SavedPoll(LocalDateTime.now(), new Poll(1, new int[]{1, 2, 3}, "hello")));
        service.writeInFile("polls.txt", savedPolls);

        List<SavedPoll> getPools = service.readFromFile("polls.txt");
        for (SavedPoll pool : getPools) {
            System.out.println(pool.getTime());
            System.out.println(pool.getPool().getArtist());
            System.out.println(Arrays.toString(pool.getPool().getGenres()));
            System.out.println(pool.getPool().getAbout());
        }
    }
}
