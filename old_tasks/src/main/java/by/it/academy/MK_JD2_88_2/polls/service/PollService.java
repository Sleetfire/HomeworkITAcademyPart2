package by.it.academy.MK_JD2_88_2.polls.service;

import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.dto.ChoiceWithCounter;
import by.it.academy.MK_JD2_88_2.polls.dto.Poll;
import by.it.academy.MK_JD2_88_2.polls.dto.SavedPoll;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PollService implements IPollService {

    private static final PollService instance = new PollService();

    private List<SavedPoll> polls = new ArrayList<>();
    private List<String> artists = new ArrayList<>();
    private List<String> genres = new ArrayList<>();

    private PollService() {
        this.artists.add("Ария");
        this.artists.add("Квин");
        this.artists.add("Металлика");
        this.artists.add("АЦ/ДЦ");

        this.genres.add("Поп");
        this.genres.add("Рок");
        this.genres.add("Джаз");
        this.genres.add("Фонк");
        this.genres.add("Народная");
        this.genres.add("Классика");
        this.genres.add("Рэп");
        this.genres.add("Инди");
        this.genres.add("Блюз");
        this.genres.add("Хэви-метал");
        this.genres.add("Хип-хоп");
    }

    @Override
    public List<String> getArtists() {
        return Collections.unmodifiableList(this.artists);
    }

    @Override
    public List<String> getGenres() {
        return Collections.unmodifiableList(this.genres);
    }

    @Override
    public void createPoll(Poll poll) {
        SavedPoll savedPoll = new SavedPoll(LocalDateTime.now(), poll);
        this.polls.add(savedPoll);
        this.polls.sort(SavedPoll::compareTo);
    }

    @Override
    public List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator) {
        Map<String, Integer> topArtists = new HashMap<>();

        for (SavedPoll poll : this.polls) {
            int artistIndex = poll.getPool().getArtist();
            String artistName = this.artists.get(artistIndex);

            Integer value = topArtists.getOrDefault(artistName, 0);
            topArtists.put(artistName, value + 1);
        }

        Comparator<Map.Entry<String, Integer>> topArtistComparator = (Comparator.comparingInt(Map.Entry::getValue));

        List<Map.Entry<String, Integer>> entries = topArtists.entrySet().stream()
                .sorted(topArtistComparator.reversed())
                .collect(Collectors.toList());

        List<ChoiceWithCounter<String>> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : entries) {
            result.add(new ChoiceWithCounter<>(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    @Override
    public List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator) {
        Map<String, Integer> topGenres = new HashMap<>();

        for (SavedPoll poll : this.polls) {
            for (int genre : poll.getPool().getGenres()) {
                String genreName = this.genres.get(genre);

                Integer value = topGenres.getOrDefault(genreName, 0);

                topGenres.put(genreName, value + 1);
            }
        }

        Comparator<Map.Entry<String, Integer>> topGenreComparator = Comparator.comparingInt(Map.Entry::getValue);

        List<Map.Entry<String, Integer>> entries = topGenres.entrySet().stream()
                .sorted(topGenreComparator.reversed())
                .collect(Collectors.toList());

        List<ChoiceWithCounter<String>> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : entries) {
            result.add(new ChoiceWithCounter<>(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    @Override
    public List<SavedPoll> getPolls() {
        return Collections.unmodifiableList(this.polls);
    }

    @Override
    public void writeSavedPoll(SavedPoll poll) {
        this.polls.add(poll);
        this.polls.sort(SavedPoll::compareTo);
    }

    public static PollService getInstance() {
        return instance;
    }

}
