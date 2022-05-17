package by.it.academy.MK_JD2_88_2.polls.controllers.mains;

import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.dto.Poll;

import java.util.Arrays;
import java.util.List;

public class PollMain {
    public static void main(String[] args) {
        IPollService service = PollService.getInstance();
        List<String> artists = service.getArtists();
        List<String> genres = service.getGenres();

        int choiceArtist = 2;
        int[] choiceGenres = new int[3];

        choiceGenres[0] = 0;
        choiceGenres[1] = 5;
        choiceGenres[2] = 8;

        String about = "Привет, я Андрей";

        Poll pool = new Poll(choiceArtist, choiceGenres, about);
        service.createPoll(pool);

        System.out.println(service.getPolls().get(0).getPool().getArtist());
        System.out.println(Arrays.toString(service.getPolls().get(0).getPool().getGenres()));
        System.out.println(service.getPolls().get(0).getPool().getAbout());
        System.out.println(service.getPolls().get(0).getTime());

    }


}
