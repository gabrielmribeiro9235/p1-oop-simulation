import java.time.LocalDate;
import java.time.LocalDate;

public class Post {
    private String quote;
    private final LocalDate date;
    private int claps;
    private int boos;
    private final UserAccount user;

    public Post(UserAccount user, String quote) {
        this.quote = quote;
        this.date = LocalDate.now();
        this.user = user;
        this.boos = 0;
        this.claps = 0;
    }

    public UserAccount getUser() {
        return user;
    }

    public String getQuote() {
        return quote;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getClaps() {
        return claps;
    }

    public int getBoos() {
        return boos;
    }

    public void clap() {
        claps++;
    }

    public void boo() {
        boos++;
    }

    public String show() {
        return String.format(
                "[%s] %s says \"%s\" | Claps: %d | Boos: %d",
                date.toString(), user.getUserName(), quote, claps, boos
        );
    }
}
