package by.it.academy.MK_JD2_88_2.polls.service.api;

public interface IFileHandlerService<T> {

    /**
     * Считывает информацию из файла
     * @param fileName название файла
     * @return информацию из файла
     */
    T readFromFile(String fileName);

    /**
     * Записывает информацию в файл
     * @param fileName название файла
     * @param info информация
     */
    void writeInFile(String fileName, T info);

}
