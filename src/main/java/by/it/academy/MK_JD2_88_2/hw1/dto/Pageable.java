package by.it.academy.MK_JD2_88_2.hw1.dto;

public class Pageable {

    private int page;
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static Pageable of(int page, int size) {
        Pageable pageable = new Pageable();
        pageable.setPage(page);
        pageable.setSize(size);
        return pageable;
    }
}
