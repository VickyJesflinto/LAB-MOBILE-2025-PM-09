import java.util.ArrayList;
import java.util.List;

public class BookData {
    public static List<Book> getDummyBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "A story of the Jazz Age in 1920s America.", "Fiction", "A timeless classic.", "image_uri_1", 1925, true, 5));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice in the Deep South.", "Fiction", "A powerful and moving story.", "image_uri_2", 1960, true, 5));
        books.add(new Book("1984", "George Orwell", "A dystopian novel about totalitarianism.", "Dystopian", "Chilling and thought-provoking.", "image_uri_3", 1949, false, 4));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "A romantic novel about manners and marriage.", "Romance", "Witty and delightful.", "image_uri_4", 1813, true, 5));
        books.add(new Book("Moby-Dick", "Herman Melville", "A tale of obsession and revenge on the high seas.", "Adventure", "Epic and symbolic.", "image_uri_5", 1851, false, 3));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "A story about teenage rebellion and alienation.", "Fiction", "Iconic and relatable.", "image_uri_6", 1951, true, 4));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "A fantasy adventure in Middle-earth.", "Fantasy", "Magical and adventurous.", "image_uri_7", 1937, true, 5));
        books.add(new Book("War and Peace", "Leo Tolstoy", "A historical novel set during the Napoleonic Wars.", "Historical", "Profound and expansive.", "image_uri_8", 1869, false, 4));
        books.add(new Book("The Alchemist", "Paulo Coelho", "A journey of self-discovery and following dreams.", "Philosophical", "Inspiring and uplifting.", "image_uri_9", 1988, true, 5));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", "A psychological exploration of guilt and redemption.", "Crime", "Dark and compelling.", "image_uri_10", 1866, false, 4));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "An epic fantasy trilogy.", "Fantasy", "Masterful and immersive.", "image_uri_11", 1954, true, 5));
        books.add(new Book("Brave New World", "Aldous Huxley", "A dystopian novel about a controlled society.", "Dystopian", "Provocative and visionary.", "image_uri_12", 1932, false, 4));
        books.add(new Book("Jane Eyre", "Charlotte Brontë", "A novel about love, independence, and resilience.", "Romance", "Passionate and enduring.", "image_uri_13", 1847, true, 5));
        books.add(new Book("The Odyssey", "Homer", "An epic poem about Odysseus's journey home.", "Epic", "Timeless and heroic.", "image_uri_14", -800, false, 4));
        books.add(new Book("The Divine Comedy", "Dante Alighieri", "A journey through Hell, Purgatory, and Paradise.", "Epic", "Profound and allegorical.", "image_uri_15", 1320, false, 5));
        return books;
    }
}
