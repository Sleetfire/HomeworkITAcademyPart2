package by.it.academy.MK_JD2_88_2.polls.service.api;

import by.it.academy.MK_JD2_88_2.polls.dto.ChoiceWithCounter;
import by.it.academy.MK_JD2_88_2.polls.dto.Poll;
import by.it.academy.MK_JD2_88_2.polls.dto.SavedPoll;

import java.util.Comparator;
import java.util.List;

public interface IPollService {

    /**
     * Возвращает список артистов
     * @return список артистов
     */
    List<String> getArtists();

    /**
     * Возвращает список жанров
     * @return список жанров
     */
    List<String> getGenres();

    /**
     * Создает голос
     * @param pool голос
     */
    void createPoll(Poll pool);

    /**
     * Возвращает топ артистов
     * @param comparator на его основе происходит сортировка
     * @return отсортированный топ артистов
     */
    List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator);

    /**
     * Возвращает топ жанров
     * @param comparator на его основе происходит сортировка
     * @return отсортированный топ жанров
     */
    List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator);

    /**
     * Возвращает список голосов
     * @return список голосов
     */
    List<SavedPoll> getPolls();

    /**
     * Сохраняет голоса в список
     * @param poll голос
     */
    void writeSavedPoll(SavedPoll poll);
}
