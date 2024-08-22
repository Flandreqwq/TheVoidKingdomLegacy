package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.HashSet;
import java.util.Set;

public record BookShelf(String namespaceID, double ideaAmount, double inspirationAmount, double rankAmount,
                        int expDiscount, double stopPossible) {
    public static final Set<BookShelf> BOOK_SHELVES = new HashSet<>();
    public static final BookShelf HONEY_BOOKSHELF = create("vkblocks:honey_bookshelf", 1.0, 0.0, 0.0, 0, 0.1);
    public static final BookShelf MELON_BOOKSHELF = create("vkblocks:melon_bookshelf", 0.0, 1.0, 0.0, 0, 0.0);
    public static final BookShelf SEA_BOOKSHELF = create("vkblocks:sea_bookshelf", 0.0, 0.0, 0.03, 0, 0.0);
    public static final BookShelf SEA_HEART_BOOKSHELF = create("vkblocks:sea_heart_bookshelf", 1.0, 0.0, 0.08, 0, 0.0);
    public static final BookShelf SEA_CRYSTAL_BOOKSHELF = create("vkblocks:sea_crystal_bookshelf", 0.0, 0.0, 0.0, 10, 0.0);
    public static final BookShelf SEA_HONEY_BOOKSHELF = create("vkblocks:sea_honey_bookshelf", 0.0, 0.5, 0.03, 0, 0.05);
    public static final BookShelf DEEP_BOOKSHELF = create("vkblocks:deep_bookshelf", 1.0, 0.0, 0.0, 0, 0.0);
    public static final BookShelf ECHO_DEEP_BOOKSHELF = create("vkblocks:echo_deep_bookshelf", 1.0, 0.0, 0.0, 15, -0.1);
    public static final BookShelf SOUL_DEEP_BOOKSHELF = create("vkblocks:soul_deep_bookshelf", 1.0, 1.0, 0.0, 0, -0.1);
    public static final BookShelf ECHO_SCULK_BOOKSHELF = create("vkblocks:echo_sculk_bookshelf", 1.0, 0.0, 0.0, 30, -0.2);
    public static final BookShelf SOUL_SCULK_BOOKSHELF = create("vkblocks:soul_sculk_bookshelf", 1.0, 2.0, 0.0, 0, -0.2);

    public static BookShelf create(String namespaceID, double ideaAmount, double inspirationAmount, double rankAmount, int expDiscount, double stopPossible) {
        BookShelf bookShelf = new BookShelf(namespaceID, ideaAmount, inspirationAmount, rankAmount, expDiscount, stopPossible);
        BOOK_SHELVES.add(bookShelf);
        return bookShelf;
    }
}
