public class UserAccount {
    private String email;
    private String userName;
    private final Post[] posts;
    private int numberOfPosts;
    private final UserAccount[] followers;
    private int numberOfFollowers;
    private final int TIMELINE_SIZE = 10;
    private final Post[] timeline;
    private int numberOfPostsInTimeline;
    private int timelineNext;

    public UserAccount(String email, String userName) {
        this.email = email;
        this.userName = userName;
        posts = new Post[1000000];
        followers = new UserAccount[1000000];
        timeline = new Post[TIMELINE_SIZE];
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void publish(String quote) {
        posts[numberOfPosts++]= new Post(this, quote);

        for (int i = 0; i < numberOfFollowers; i++) {
            followers[i].updateTimeline(posts[numberOfPosts-1]);
        }
    }

    public void updateTimeline(Post newPost) {
        timeline[timelineNext] = newPost;

        timelineNext = (timelineNext + 1) % TIMELINE_SIZE;

        if (numberOfPostsInTimeline < TIMELINE_SIZE) numberOfPostsInTimeline++;
    }

    public boolean delete(int postIdx) {
        if (postIdx > numberOfPosts || postIdx < 0) return false;

        for (int i = postIdx; i < numberOfPosts - 1; i++) {
            posts[i] = posts[i+1];
        }

        posts[--numberOfPosts] = null;

        return true;
    }

    public void clapPost(int postIdx) {
        if (postIdx > numberOfPostsInTimeline || postIdx < 0) return;

        timeline[postIdx].clap();
    }

    public void booPost(int postIdx) {
        if (postIdx > numberOfPostsInTimeline || postIdx < 0) return;

        timeline[postIdx].boo();
    }

    public String showTimeline() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numberOfPostsInTimeline; i++) {
            builder.append(timeline[i].show()).append("\n");
        }

        return builder.toString();
    }

    public void acceptFollower(UserAccount newFollower) {
        for (int i = 0; i < numberOfFollowers; i++) {
            if (followers[i].getUserName().equals(newFollower.getUserName())) return;
        }
        followers[numberOfFollowers++] = newFollower;
    }

    public void blockFollower(UserAccount follower) {
        int deleteIdx = -1;
        for (int i = 0; i < numberOfFollowers; i++) {
            if (followers[i].getUserName().equals(follower.getUserName())) {
                deleteIdx = i;
                break;
            }
        }
        if (deleteIdx == -1) return;
        for (int i = deleteIdx; i < numberOfFollowers - 1; i++) {
            followers[i] = followers[i + 1];
        }
        followers[--numberOfFollowers] = null;
    }

    public String showMyFriends() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numberOfFollowers; i++) {
            builder.append(followers[i].showUser()).append("\n");
        }

        return builder.toString();
    }

    public String showUser() {
        return "username: " + userName;
    }

    public String showMyPosts() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numberOfPosts; i++) {
            builder.append(posts[i].show()).append("\n");
        }

        return builder.toString();
    }
}
